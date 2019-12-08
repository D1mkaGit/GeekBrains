package lesson_6.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    @FXML
    TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    HBox bottomPanel;
    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;

    Stage stage;
    private String nick;

    private boolean isAuthorized;

    public void setStage( Stage stage ) {
        this.stage = stage;
    }

    public void setAuthorized( boolean isAuthorized ) {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            textArea.clear();
            Platform.runLater((() -> stage.setTitle(nick))); // часа 3 голову ломал, потом нашел в гугле про
            // многопоточность и javaFX
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String srt = in.readUTF();
                            if (srt.startsWith("/authok")) {
                                setAuthorized(true);
                                nick = String.valueOf(srt.split(":", 2)[1]);
                                break;
                            } else {
                                textArea.appendText(srt + "\n");
                            }
                        }

                        while (true) {
                            String srt = in.readUTF();
                            if (srt.equals("/serverClosed")) {
                                break;
                            }
                            textArea.appendText(srt + "\n");
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
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            if (!socket.isClosed()) {
                out.writeUTF(textField.getText());
                textField.clear();
                textField.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Dispose() {
        System.out.println("Отправляем сообщение на сервер о завершении работы");
        try {
            if (out != null) {
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
