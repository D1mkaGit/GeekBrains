package com.flamexander.netty.servers.file_transfer;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileCreationApp {
    public static void main(String[] args) {
        byte[] buf = new byte[1024 * 1024];
        try (FileOutputStream out = new FileOutputStream("2.txt")) {
            for (int i = 0; i < 2500; i++) {
                out.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
