package lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    String nick;
    DataInputStream in;
    DataOutputStream out;
    MainServ serv;
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public ClientHandler( MainServ serv, Socket socket ) {
        try {
            this.socket = socket;
            this.serv = serv;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/auth")) {
                                String[] tokens = msg.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                String errMsg = null;
                                for (ClientHandler client : serv.getClients()) {
                                    if (client.nick.equals(newNick)) {
                                        errMsg = "Вы уже вошли в чат в другом окне";
                                        newNick = null;
                                        break;
                                    }
                                }
                                if (newNick != null) {
                                    sendMsg("/authok:" + newNick);
                                    nick = newNick;
                                    serv.subscribe(ClientHandler.this);
                                    break;
                                } else {
                                    if (errMsg == null) errMsg = "Неверный логин/пароль";
                                    sendMsg(errMsg);
                                }
                            }
                        }

                        while (true) {
                            String msg = in.readUTF();
                            if (msg.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if (msg.startsWith("/w ")) {
                                String oppositeNickName = msg.split(" ", 3)[1];
                                for (ClientHandler client : serv.getClients()) {
                                    if (client.nick.equals(oppositeNickName))
                                        client.out.writeUTF(nick + ": " + msg.split(" " + oppositeNickName + " ")[1]);
                                }
                            } else {
                                serv.broadcastMsg(nick + " " + msg);
                            }
                        }
                    } catch (
                            IOException e) {
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
                        serv.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg( String msg ) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
