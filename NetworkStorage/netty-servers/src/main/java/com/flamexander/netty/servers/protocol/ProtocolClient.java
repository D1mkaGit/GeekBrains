package com.flamexander.netty.servers.protocol;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ProtocolClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write(15);
            byte[] filenameBytes = "1234.txt".getBytes();
            out.writeInt(filenameBytes.length);
            out.write(filenameBytes);
            // 15 0 0 0 8 _ _ _ _ _ _ _ _
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
