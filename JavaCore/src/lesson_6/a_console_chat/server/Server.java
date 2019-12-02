package lesson_6.a_console_chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static lesson_6.a_console_chat.constants.PORT;

public class Server {
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;

    public Server() {
        socket = null;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            socket = server.accept();

            System.out.println("Клиент подключился! Теперь можно общаться!\nВведите текст: ");

            Scanner scanner = new Scanner(System.in);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread incomingMessages = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String incomingText;
                        try {
                            incomingText = in.readUTF();
                            if (incomingText.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                throw new SocketException("Connection closed");
                            }
                            System.out.println("Client: " + incomingText);
                        } catch (IOException e) {
                            if (e.getMessage().contains("Connection reset")
                                    || e.getMessage().contains("Connection closed")) {
                                System.out.println("Клиент отключился от сервера, попробуйте перезапустить сервер еще" +
                                        " раз");
                            } else {
                                e.printStackTrace();
                            }
                            try {
                                socket.close();
                                System.exit(0);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
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
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert socket != null;
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
