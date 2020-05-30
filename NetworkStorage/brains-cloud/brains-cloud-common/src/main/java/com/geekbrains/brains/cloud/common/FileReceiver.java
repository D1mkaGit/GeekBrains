package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class FileReceiver {
    private final String rootDir;
    private State currentState = State.IDLE;
    private int nextLength;
    private long fileLength;
    private long receivedFileLength;
    private BufferedOutputStream out;

    public FileReceiver( String rootDir ) {
        this.rootDir = rootDir;
    }

    public void startReceive() {
        currentState = State.NAME_LENGTH;
        receivedFileLength = 0L;
        System.out.println("STATE: Start file receiving");
    }

    public void receive( ByteBuf buf, Runnable finishOperation ) throws Exception {
        if (currentState == State.NAME_LENGTH) {
            if (buf.readableBytes() >= 4) {
                System.out.println("STATE: Get filename length");
                nextLength = buf.readInt();
                currentState = State.NAME;
            }
        }

        if (currentState == State.NAME) {
            if (buf.readableBytes() >= nextLength) {
                byte[] fileName = new byte[nextLength];
                buf.readBytes(fileName);
                System.out.println("STATE: Filename received - " + new String(fileName, StandardCharsets.UTF_8));
                out = new BufferedOutputStream(new FileOutputStream(rootDir + "/" + new String(fileName)));
                currentState = State.FILE_LENGTH;
            }
        }

        if (currentState == State.FILE_LENGTH) {
            if (buf.readableBytes() >= 8) {
                fileLength = buf.readLong();
                System.out.println("STATE: File length received - " + fileLength);
                currentState = State.FILE;
            }
        }

        if (currentState == State.FILE) {
            while (buf.readableBytes() > 0) {
                out.write(buf.readByte());
                receivedFileLength++;
                if (fileLength == receivedFileLength) {
                    currentState = State.IDLE;
                    System.out.println("File received");
                    out.close();
                    finishOperation.run();
                    return;
                }
            }
        }
    }

    public enum State {
        IDLE, NAME_LENGTH, NAME, FILE_LENGTH, FILE
    }
}
