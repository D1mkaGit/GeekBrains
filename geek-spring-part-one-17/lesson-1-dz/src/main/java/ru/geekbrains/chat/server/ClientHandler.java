package ru.geekbrains.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.chat.server.UserRepository.LogEventType.*;

public class ClientHandler {

    private static final Logger logger = LogManager.getLogger(ClientHandler.class);
    List<String> blackList;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private String login;

    public ClientHandler( Server server, Socket socket ) {
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) { // /auth login72 pass72
                            String[] tokens = str.split(" ");
                            login = tokens[1];
                            String newNick = UserRepository.getNickByLoginAndPass(login, tokens[2]);
                            if (newNick != null) {
                                if (!server.isNickBusy(newNick)) {
                                    sendMsg("/authok");
                                    UserRepository.log(LOGIN, login);
                                    nick = newNick;
                                    server.subscribe(this);
                                    blackList = UserRepository.getBlackList(nick);
                                    break;
                                } else {
                                    UserRepository.log(INCORRECT_LOGIN, login);
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                UserRepository.log(INCORRECT_LOGIN, login);
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                        if (str.startsWith("/register")) {
                            String[] tokens = str.split(" ");
                            String login = tokens[2];
                            String nick = tokens[1];
                            String pass = tokens[3];
                            if (UserRepository.checkIfLoginIsAvailableInDb(login) &&// проверка на
                                    // существующий акаунт
                                    UserRepository.checkIfNickIsAvailableInDb(nick)) {// проверка на
                                // существующий ник
                                // тогда регаем пользователя
                                sendMsg("/regOk");
                                UserRepository.addUser(login, pass, nick);
                            } else {
                                sendMsg("/regExists");
                                //System.out.println("Существующий Логин или Псевдоним, попробуйте указать другие данные");
                            }
                        }
                    }
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w ")) { // /w nick3 lsdfhldf sdkfjhsdf wkerhwr
                                String[] tokens = str.split(" ", 3);
                                String m = str.substring(tokens[1].length() + 4);
                                server.sendPersonalMsg(this, tokens[1], tokens[2]);
                            }
                            if (str.startsWith("/block ")) { // /blacklist nick3
                                String[] tokens = str.split(" ");
                                String blackListNick = tokens[1];
                                if (blackListNick != null) {
                                    if (server.isNickBusy(blackListNick)) {
                                        boolean alreadyBlocked = false;
                                        for (String s : blackList) {
                                            if (s.equals(blackListNick)) {
                                                sendMsg("Пользовател " + blackListNick + " уже присутствует в черном " +
                                                        "списке");
                                                alreadyBlocked = true;
                                                break;
                                            }
                                        }
                                        if (!alreadyBlocked) {
                                            UserRepository.addUserToBlackListForSpecificUser(nick,
                                                    blackListNick);
                                            blackList.add(blackListNick);
                                            sendMsg("Вы добавили пользователя " + blackListNick + " в черный список");
                                        }
                                    } else {
                                        sendMsg("Вы не можете добавить " + blackListNick + ", т.к. такого ника нет в " +
                                                "чате.");
                                    }
                                }
                            }
                            if (str.equals("/getblacklist")) { // /blacklist nick3
                                if (blackList.isEmpty()) sendMsg("У вас нет черного списка");
                                else
                                    sendMsg("У вас в черном списке следующие пользователи: " + UserRepository.getBlacklist(nick));
                            }
                            if (str.startsWith("/unblock ")) { // /blacklist nick3
                                String[] tokens = str.split(" ");
                                String blackListNick = tokens[1];
                                if (blackListNick != null && !blackList.isEmpty()) {
                                    boolean isBlocked = false;
                                    for (int i = 0; i < blackList.size(); i++) {
                                        if (blackList.get(i).equals(blackListNick)) {
                                            UserRepository.removeUserToBlackListForSpecificUser(nick,
                                                    blackListNick);
                                            blackList.remove(blackListNick);
                                            sendMsg("Вы удалили из чернорго списка пользователя под ником: " + blackListNick);
                                            isBlocked = true;
                                            break;
                                        }
                                    }
                                    if (!isBlocked) {
                                        sendMsg("Пользователя с ником " + blackListNick + " нет в черном списке");
                                    }
                                }
                            }
                        } else {
                            server.broadcastMsg(this, nick + ": " + str);
                        }
                        logger.debug("Клиент с ником " + nick + " отправил следующее сообщение: " + str);
                        System.out.println("Client: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                    UserRepository.log(LOGOUT, this.login);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    public boolean checkBlackList( String nick ) {
        return blackList.contains(nick);
    }

    public void sendMsg( String msg ) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
