package ru.geekbrains.chat.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;
    @FXML
    TextField msgField;
    @FXML
    TextArea chatArea;
    @FXML
    HBox bottomPanel;
    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    ListView<String> clientsList;

    @FXML
    HBox registerPanel;
    @FXML
    TextField nickNameField;
    @FXML
    TextField loginNameField;
    @FXML
    PasswordField password1Field;
    @FXML
    PasswordField password2Field;


    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    List<TextArea> textAreas;
    private boolean isAuthorized;
    private boolean isRegister;
    private boolean isAlreadyRegistered;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        setAuthorized(false);
        setRegister(false);
        textAreas = new ArrayList<>();
        textAreas.add(chatArea);
    }

    public void setAuthorized( boolean isAuthorized ) {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            if (isRegister) {
                upperPanel.setVisible(false);
                upperPanel.setManaged(false);
                registerPanel.setVisible(true);
                registerPanel.setVisible(true);
            } else {
                upperPanel.setVisible(true);
                upperPanel.setManaged(true);
                registerPanel.setVisible(false);
                registerPanel.setVisible(false);
            }
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientsList.setVisible(false);
            clientsList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientsList.setVisible(true);
            clientsList.setManaged(true);
            chatArea.clear();
        }
    }

    public void setRegister( boolean isRegister ) {
        this.isRegister = isRegister;
        if (isRegister) {
            registerPanel.setVisible(true);
            registerPanel.setVisible(true);
        } else {
            registerPanel.setVisible(false);
            registerPanel.setVisible(false);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/authok")) {
                            setAuthorized(true);
                            break;
                        } else if (str.startsWith("/regOk")) {
                            setRegister(false);
                            System.out.println("Регистрация прошла успешно");
                            // логинемся пользователем
                            try {
                                out.writeUTF("/auth " + loginNameField.getText() + " " + password1Field.getText());
                                loginNameField.clear();
                                password1Field.clear();
                                password2Field.clear();
                                nickNameField.clear();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (str.startsWith("/regExists")) {
                            isAlreadyRegistered = true;
                            System.out.println("Существующий Логин или Псевдоним, попробуйте указать другие данные");
                            for (TextArea o : textAreas) {
                                o.appendText("Существующий Логин или Псевдоним, попробуйте указать другие данные" + "\n");
                            }
                        } else {
                            for (TextArea o : textAreas) {
                                o.appendText(str + "\n");
                            }
                        }
                    }
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/serverclosed")) break;
                            if (str.startsWith("/clientslist ")) {
                                String[] tokens = str.split(" ");
                                Platform.runLater(() -> {
                                    clientsList.getItems().clear();
                                    for (int i = 1; i < tokens.length; i++) {
                                        clientsList.getItems().add(tokens[i]);
                                    }
                                });
                            }
                        } else {
                            chatArea.appendText(str + "\n");
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
                    setAuthorized(false);
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
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

    public void openRegistration() {
        isRegister = true;
        setAuthorized(false);
    }

    public void tryToRegister() {
        System.out.println(nickNameField.getText());
        if (fieldNotEmpty(nickNameField) &&
                fieldNotEmpty(loginNameField) &&
                checkPasswordFieldAreEquals()) {
            // делаем регистрацию
            connect();
            sendDataToRegisterOnServer(nickNameField.getText(), loginNameField.getText(), password2Field.getText());
        } else {
            for (TextArea o : textAreas) {
                o.appendText("Для регистрации заданы неверные данные" + "\n");
            }
            //System.out.println("Неверные данные");
        }
    }

    private void sendDataToRegisterOnServer( String nick, String login, String pass ) {
        System.out.println("Пробуем зарегистрироваться со следующими данными:");
        System.out.println("Псевдоним: " + nick);
        System.out.println("Логин: " + login);
        System.out.println("Пароль: " + pass);
        try {
            out.writeUTF("/register " + nick + " " + login + " " + pass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void selectClient( MouseEvent mouseEvent ) {
        if (mouseEvent.getClickCount() == 2) {
            MiniStage ms = new MiniStage(clientsList.getSelectionModel().getSelectedItem(), out, textAreas);
            ms.show();
        }
    }

    private boolean fieldNotEmpty( TextField field ) {
        return field.getText() != "" || field.getText() != null;
    }

    private boolean checkPasswordFieldAreEquals() {
        return password1Field.getText().equals(password2Field.getText())
                && password1Field.getText() != null && !password2Field.getText().equals("");
        // тут можно добавить любую проверку
    }
}
