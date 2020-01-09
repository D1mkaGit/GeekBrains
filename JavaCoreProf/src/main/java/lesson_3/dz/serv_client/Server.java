package lesson_3.dz.serv_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    DataInputStream inData;
    DataOutputStream outData;

    ObjectInputStream inObject;
    ServerSocket server;
    Socket socket;

    public Server() {
        server = null;
        socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");

                try {
                    this.inData = new DataInputStream(socket.getInputStream());
                    this.outData = new DataOutputStream(socket.getOutputStream());
                    this.inObject = new ObjectInputStream(socket.getInputStream());

                    new Thread(() -> {
                        try {
                            while (true) {
                                try {
                                    byte[] byteData = (byte[]) inObject.readObject();
                                    System.out.println("Текст до десериализации: " + Arrays.toString(byteData));
                                    String str = new String(byteData);
                                    System.out.println("Текст который мы восстановили из набора байтов: " + str);
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                inData.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                outData.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

