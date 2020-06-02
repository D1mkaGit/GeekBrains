package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.CloudBoxCommandsList;
import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class MainController implements Initializable {
    public VBox rootNode;

    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;

    @FXML
    HBox mainPanel;

    @FXML
    TextField requestFileName;

    @FXML
    TextField sendFileName;

    @FXML
    ListView<String> filesList;

    @FXML
    ListView<String> serverFilesList;

    static String serverFilesListString;
    static String loggedInUserName;
    private Channel currentChannel;
    private Alert alert;
    private final String client_storage_location = "client_storage";

    static boolean isAuthorized;

    public void setAuthorized( boolean isAuthorized ) {
        MainController.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            mainPanel.setVisible(false);
            mainPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            mainPanel.setVisible(true);
            mainPanel.setManaged(true);
//            client_storage_location += "/" + loggedInUserName;
//            File directory = new File(client_storage_location);
//            if (! directory.exists()){
//                directory.mkdir();
//            }
        }
    }

    public void tryToAuth() {
        if (loginField.getLength() > 0 && passwordField.getLength() > 0) {
            sendCommand("/auth " + loginField.getText() + "|" + passwordField.getText().hashCode(),
                    CloudBoxCommandsList.LOGIN_SIGNAL_BYTE);
        }
    }


    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        setAuthorized(false);
        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> ProtoNetwork.getInstance().start(networkStarter)).start();
        try {
            networkStarter.await();
            currentChannel = ProtoNetwork.getInstance().getCurrentChannel();
            ProtoNetwork.getInstance().setOnReceivedCallback(() -> {
                //showAlert("Файл: " + requestFileName.getText() + " скачан");
                refreshLocalFilesList();
            });
            ProtoNetwork.getInstance().setOnReceivedFLCallback(() -> Platform.runLater(() -> {
                if (serverFilesListString != null) {
                    serverFilesList.getItems().clear();
                    Arrays.stream(serverFilesListString.split("\\|"))
                            .forEach(o -> serverFilesList.getItems().add(o));
                }
            }));
            ProtoNetwork.getInstance().setOnReceivedLoginCallback(() -> {
                loginField.clear();
                passwordField.clear();
                if (isAuthorized) {
                    setAuthorized(true);
                    refreshLocalFilesList();
                    sendRefreshServerFilesListRequest();
                } else showAlert("Не верный логин или пароль");
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pressOnDownloadBtn() {
        if (requestFileName.getLength() > 0) {
            sendCommand("/request " + requestFileName.getText(), CloudBoxCommandsList.CMD_SIGNAL_BYTE);

            showAlert("Вы запросили: " + requestFileName.getText());
        }
    }

    public void pressOnUploadBtn() throws IOException {
        if (sendFileName.getLength() > 0) {
            String fileName = sendFileName.getText();
            Path filePath = Paths.get(client_storage_location, fileName);
            if (Files.exists(filePath)) {
                showAlert("Вы отправили на сервер: " + fileName);

                ProtoFileSender.sendFile(filePath, currentChannel, future -> {
                    if (!future.isSuccess()) {
                        System.out.println("Не удалось отправить файл на сервер");
                        future.cause().printStackTrace();
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл успешно передан");
                        showAlert("Сервер получил: " + fileName);
                        sendFileName.clear();
                        sendRefreshServerFilesListRequest();
                    }
                });
            } else {
                showAlert("Файла " + sendFileName.getText() + " не существует");
            }
        }
    }

    private void showAlert( String msg ) {
        Platform.runLater(() -> {
            if (alert != null) alert.close();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    public void refreshLocalFilesList() {
        Platform.runLater(() -> {
            try {
                filesList.getItems().clear();
                Files.list(Paths.get(client_storage_location))
                        .filter(p -> !Files.isDirectory(p))
                        .map(p -> p.getFileName().toString())
                        .forEach(o -> filesList.getItems().add(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendRefreshServerFilesListRequest() {
        sendCommand("/list " + loggedInUserName, CloudBoxCommandsList.CMD_SIGNAL_BYTE);

    }

    public void sendToServer() {
//        TODO придумать как это хозяйство запустить параллельно, если запрошен большой файл, то он лочит загрузку
//         следующего, просто запустить содержимое метода в треде не выходит
        String selectedFile = filesList.getSelectionModel().getSelectedItem();
        Path filePath = Paths.get(client_storage_location, selectedFile);
        if (selectedFile != null) {
            try {
                ProtoFileSender.sendFile(filePath, currentChannel, future -> {
                    if (!future.isSuccess()) {
                        System.out.println("Не удалось отправить файл на сервер");
                        future.cause().printStackTrace();
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл успешно передан");
                        sendRefreshServerFilesListRequest();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToClient() {
        String selectedFile = serverFilesList.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            sendCommand("/request " + selectedFile, CloudBoxCommandsList.CMD_SIGNAL_BYTE);
        }
    }

    public void deleteSelectedLocally() {
        String selectedFile = filesList.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            try {
                System.out.println("Удаляем " + selectedFile + " в локальном сторедже");
                Files.delete(Paths.get(client_storage_location, selectedFile));
                refreshLocalFilesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteSelectedOnServer() {
        String selectedFile = serverFilesList.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            sendCommand("/delete " + selectedFile, CloudBoxCommandsList.CMD_SIGNAL_BYTE);
            sendRefreshServerFilesListRequest();
        }
    }

    public void editNameSelectedLocally( ActionEvent actionEvent ) {
        String selectedFile = filesList.getSelectionModel().getSelectedItem();
//        TODO придумать, как редактировать локальные файлы, а так-же это сделать для файлов на сервере
        if (selectedFile != null) {


//            File oldfile = (client_storage_location, selectedFile);
//            if (oldfile.renameTo(newfile)) {
//                System.out.println("Rename succesful");
//            } else {
//                System.out.println("Rename failed");
//            }
        }
    }

    private void sendCommand( String s, byte cmdSignalByte ) {
        byte[] cmdNameBytes = (s).getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + cmdNameBytes.length);
        buf.writeByte(cmdSignalByte);
        buf.writeInt(cmdNameBytes.length);
        buf.writeBytes(cmdNameBytes);
        currentChannel.writeAndFlush(buf);
    }
}
