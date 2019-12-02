package lesson_6.a_console_chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static lesson_6.a_console_chat.constants.IP_ADDRESS;
import static lesson_6.a_console_chat.constants.PORT;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            System.out.println("Клиент запущен!");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Клиент подключился к серверу. Теперь можно общаться!\nВведите текст: ");
            Scanner scanner = new Scanner(System.in);

            Thread incomingMessages = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String incomingText;
                        try {
                            incomingText = in.readUTF();
                            if (incomingText.equals("/serverClosed")) {
                                throw new SocketException("Connection reset");
                            }
                            System.out.println("Admin: " + incomingText);
                        } catch (IOException e) {
                            if (e.getMessage().contains("Connection reset")
                                    || e.getMessage().contains("Connection refused")) {
                                System.out.println("Потеря соединения с сервером, попробуйте запустить приложение " +
                                        "еще раз");
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
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
