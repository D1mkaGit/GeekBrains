package ru.geekbrains.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import static ru.geekbrains.chat.server.UserRepository.getBlackList;

//1. Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервер:
// запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду.

@Component
public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private final Vector<ClientHandler> clients;

    public Server() {

        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            logger.debug("Сервер запускается...");
            server = new ServerSocket(8189);
            logger.info("Сервер запущен. Ожидаем клиентов...");
            while (true) {
                logger.debug("Ждем клиентов...");
                socket = server.accept();
                logger.info("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
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
            UserRepository.disconnect();
        }
    }

    public void sendPersonalMsg( ClientHandler from, String nickTo, String msg ) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                logger.debug("Клиент с ником " + from.getNick() + " отправил клиенту c ником " + nickTo + " следующее" +
                        " сообщение: ");
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
        logger.debug("Клиент с ником " + nickTo + " не найден в чате");
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
                logger.debug(nick + " уже занят");
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
                    if (bl.equals(cl.getNick())) {
                        sb.append("(b)");
                    }
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
