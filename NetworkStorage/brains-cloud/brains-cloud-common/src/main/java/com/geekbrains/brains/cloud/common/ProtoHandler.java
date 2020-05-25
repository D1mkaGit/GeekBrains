package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.geekbrains.brains.cloud.common.ProtoFileSender.SRV_TEMP_PATH;

public class ProtoHandler extends ChannelInboundHandlerAdapter {
    private final byte SIGNAL_BYTE_SERVER_FILE = 25;
    private final byte SIGNAL_BYTE_CLIENT_FILE = 26;
    private final byte SIGNAL_BYTE_REQUEST = 24;
    private final byte SIGNAL_BYTE_REQUEST_FILE_LIST = 22;
    private final byte SIGNAL_BYTE_FILE_LIST = 23;
    private State currentState = State.IDLE;
    private int nextLength;
    private long fileLength;
    private long receivedFileLength;
    private BufferedOutputStream out;
    private boolean isRequest;
    private boolean isServerFile;
    private String storagePath;

    private Callback onReceivedCallback;

    public void setOnReceivedCallback( Callback onReceivedCallback ) {
        this.onReceivedCallback = onReceivedCallback;
    }

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
        ByteBuf buf = ((ByteBuf) msg);
        while (buf.readableBytes() > 0) {
            if (currentState == State.IDLE) {
                isServerFile = false;
                isRequest = false;
                storagePath = "server_storage/";
                byte read = buf.readByte();
                if (read == SIGNAL_BYTE_SERVER_FILE || read == SIGNAL_BYTE_CLIENT_FILE) {
                    currentState = State.NAME_LENGTH;
                    receivedFileLength = 0L;
                    if (read == SIGNAL_BYTE_SERVER_FILE) isServerFile = true;
                    System.out.println("STATE: Start file receiving");
                } else if (read == SIGNAL_BYTE_REQUEST) {
                    currentState = State.NAME_LENGTH;
                    receivedFileLength = 0L;
                    System.out.println("STATE: Start file requesting");
                    isRequest = true;
                } else if (read == SIGNAL_BYTE_REQUEST_FILE_LIST) {
                    System.out.println("STATE: Start fileList requesting");
                    ProtoFileSender.sendFilesList("server_storage", ctx.channel());
                    currentState = State.IDLE;
                } else if (read == SIGNAL_BYTE_FILE_LIST) {
                    System.out.println("STATE: Start fileList sending");
                    receivedFileLength = 0L;
                    currentState = State.SERVER_FILES_LIST_FILE_NAME_LENGTH;
                } else {
                    System.out.println("ERROR: Invalid first byte - " + read);
                }
            }

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
                    String fileNameAsString = new String(fileName, StandardCharsets.UTF_8);
                    if (isRequest) {
                        String pathAndFile = storagePath + fileNameAsString;
                        System.out.println("File " + fileNameAsString + " have been requested");
                        if (Files.exists(Paths.get(pathAndFile))) {
                            ProtoFileSender.sendFile((byte) 25, Paths.get(pathAndFile), ctx.channel(), future -> {
                                if (!future.isSuccess()) {
                                    future.cause().printStackTrace();
                                }
                                if (future.isSuccess()) {
                                    System.out.println("Файл " + fileNameAsString + " успешно передан");
                                }
                            });
                        } else {
                            System.out.println("Файла " + fileNameAsString + " не существует");
                        }
                        currentState = State.IDLE;
                    } else {
                        System.out.println("STATE: Filename received - " + fileNameAsString);
                        if (isServerFile) {
                            storagePath = "client_storage/";
                        }
                        // если файл с сервера, путь клиентский подставляем
                        out = new BufferedOutputStream(new FileOutputStream(storagePath + fileNameAsString));
                        currentState = State.FILE_LENGTH;
                    }
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
                        if (isServerFile) onReceivedCallback.callback();
                        System.out.println("File received");
                        out.close();
                        break;
                    }
                }
            }

            if (currentState == State.SERVER_FILES_LIST_FILE_NAME_LENGTH) {
                if (buf.readableBytes() >= 4) {
                    System.out.println("STATE: Get length for filename with file list");
                    nextLength = buf.readInt();
                    buf.discardReadBytes();
                    currentState = State.SERVER_FILES_LIST_FILE_NAME;
                }
            }

            if (currentState == State.SERVER_FILES_LIST_FILE_NAME) {
                if (buf.readableBytes() >= nextLength) { //для получения листа не важно название файла в котором
                    // передаем, храним в другом файле
                    buf.readBytes(new byte[nextLength]);
                    currentState = State.SERVER_FILES_LIST_FILE_LENGTH;
                }
            }

            if (currentState == State.SERVER_FILES_LIST_FILE_LENGTH) {
                if (buf.readableBytes() >= 8) {
                    System.out.println("STATE: Get file list length");
                    fileLength = buf.readLong();
                    out = new BufferedOutputStream(new FileOutputStream(SRV_TEMP_PATH + "_serverFilesList.txt"));
                    currentState = State.SERVER_FILES_LIST_FILE;
                }
            }

            if (currentState == State.SERVER_FILES_LIST_FILE) {
                while (buf.readableBytes() > 0) {
                    out.write(buf.readByte());
                    receivedFileLength++;
                    if (fileLength == receivedFileLength) {
                        currentState = State.IDLE;
                        System.out.println("File List received");
                        out.close();
                        break;
                    }
                }
            }
        }
        if (buf.readableBytes() == 0) {
            buf.release();
        }
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
        cause.printStackTrace();
        ctx.close();
    }

    public enum State {
        IDLE, NAME_LENGTH, NAME, FILE_LENGTH, FILE,
        SERVER_FILES_LIST_FILE_NAME_LENGTH,
        SERVER_FILES_LIST_FILE_NAME,
        SERVER_FILES_LIST_FILE_LENGTH,
        SERVER_FILES_LIST_FILE
    }
}
