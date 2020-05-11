package com.flamexander.netty.servers.file_transfer.caching_error;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Client {
    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);

        Socket socket = new Socket("localhost", 8189);
        ObjectEncoderOutputStream out = new ObjectEncoderOutputStream(socket.getOutputStream());
        ObjectDecoderInputStream in = new ObjectDecoderInputStream(socket.getInputStream(), 100 * 1024 * 1024);
        out.writeObject(new FileRequest());
        new Thread(() -> {
            try {
                while (true) {
                    Object input = in.readObject();
                    FileMessage fm = (FileMessage) input;
                    boolean append = true;
                    if (fm.partNumber == 1) {
                        append = false;
                    }
                    System.out.println(fm.partNumber + " / " + fm.partsCount);
                    FileOutputStream fos = new FileOutputStream(fm.filename, append);
                    fos.write(fm.data);
                    fos.close();
                    if (fm.partNumber == fm.partsCount) {
                        cdl.countDown();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        cdl.await();
        in.close();
        out.close();
        socket.close();
        System.out.println("end");
    }
}
