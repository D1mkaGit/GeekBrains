package lesson_6.a_console_chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static lesson_6.a_console_chat.constants.PORT;

public class Server {
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;

    public Server() {
        socket = null;
        ServerSocket server;
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");
            socket = server.accept();
            System.out.println("Клиент подключился! Теперь можно общаться!\nВведите текст: ");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        Thread incomingMessages = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String incomingText = in.readUTF();
                        if (incomingText.equals("/end")) {
                            out.writeUTF("/serverClosed");
                            break;
                        }
                        System.out.println("Client: " + incomingText);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    in.close();
                    out.close();
                    System.out.println("Клиент отключился от сервера, попробуйте перезапустить сервер еще раз");
                    socket.close();
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread outgoingMessages = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String outgoingText = scanner.nextLine();
                    //System.out.println("You: " + outgoingText);
                    try {
                        out.writeUTF(outgoingText);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        incomingMessages.start();
        outgoingMessages.start();

        try {
            incomingMessages.join();
            outgoingMessages.join();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }
}
