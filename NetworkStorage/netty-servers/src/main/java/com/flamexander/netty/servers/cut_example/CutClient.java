package com.flamexander.netty.servers.cut_example;

import java.io.DataOutputStream;
import java.net.Socket;

public class CutClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8189);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write((byte) 100);
        Thread.sleep(50);
        out.write((byte) 110);
        out.close();
        socket.close();
    }
}
