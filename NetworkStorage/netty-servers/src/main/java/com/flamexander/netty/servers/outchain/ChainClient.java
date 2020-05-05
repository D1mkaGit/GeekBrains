package com.flamexander.netty.servers.outchain;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChainClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            out.write(10);
            String x = in.nextLine();
            System.out.println("Answer: " + x);
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
