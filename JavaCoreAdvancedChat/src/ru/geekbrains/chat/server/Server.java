package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import static ru.geekbrains.chat.server.WorkWithDbService.getBlackList;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            WorkWithDbService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
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
            WorkWithDbService.disconnect();
        }
    }

    public void sendPersonalMsg( ClientHandler from, String nickTo, String msg ) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
    }

    public void broadcastMsg( ClientHandler from, String msg ) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
    }

    public boolean isNickBusy( String nick ) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        for (ClientHandler o : clients) {
            StringBuilder sb = new StringBuilder();
            String out = null;
            List<String> blackList = getBlackList(o.getNick());
            sb.append("/clientslist ");
            for (ClientHandler cl : clients) {
                for (String bl : blackList) {
                    if (bl.equals(cl.getNick())) sb.append("(b)");
                }
                sb.append(cl.getNick()).append(" ");
            }
            out = sb.toString();
            o.sendMsg(out);
        }
    }

    public void subscribe( ClientHandler client ) {
        clients.add(client);
        broadcastClientsList();
    }

    public void unsubscribe( ClientHandler client ) {
        clients.remove(client);
        broadcastClientsList();
    }
}
