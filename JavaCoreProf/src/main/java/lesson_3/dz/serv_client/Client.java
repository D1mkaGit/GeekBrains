package lesson_3.dz.serv_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream outObject;

    public Client() {
        connect();

    }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());

            try {
                while (true) {
                    // тут будем передовать
                    System.out.print("Введите текст для передачи в виде объекта на сервер: ");
                    Scanner sc = new Scanner(System.in);
                    convertAndSendDataToServer(sc.nextLine());
                }
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertAndSendDataToServer( String data ) {
        byte[] byteData = data.getBytes();
        try {
            System.out.println("Текст до сериализации: " + data);
            outObject.writeObject(byteData);
            System.out.println("Вот так он выглядит в байтовом представлении: " + Arrays.toString(byteData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
