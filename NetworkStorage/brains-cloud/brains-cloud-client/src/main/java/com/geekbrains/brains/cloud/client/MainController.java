package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.CloudBoxCommandsList;
import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.channel.Channel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import static com.geekbrains.brains.cloud.common.CommonCommandSender.sendCommand;
import static com.geekbrains.brains.cloud.common.CommonFileRename.renameFileInLocation;

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
            sendCommand(currentChannel, "/auth " + loginField.getText() + "|" + passwordField.getText().hashCode(),
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
            ProtoNetwork.getInstance().setOnReceivedCallback(() -> refreshLocalFilesList());
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
                } else showAlert("Не верный логин или пароль");
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public void sendRefreshServerFilesListRequest() { // по каждому чиху с файлами неа сервере, отправляем список
        // файлов, тут оставил на всякий случай запрос, так как используется кнопкой с UI
        sendCommand(currentChannel, "/list " + loggedInUserName, CloudBoxCommandsList.CMD_SIGNAL_BYTE);

    }

    public void sendToServer() {
//        TODO придумать как это хозяйство запустить параллельно, если запрошен большой файл, то он лочит загрузку
//         следующего, просто запустить содержимое метода в треде не выходит
        String selectedFile = filesList.getSelectionModel().getSelectedItem();
        Path filePath = Paths.get(client_storage_location, selectedFile);
        if (selectedFile != null) {
            trySendFile(filePath);
        }
    }

    public void sendToClient() {
        String selectedFile = serverFilesList.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            sendCommand(currentChannel, "/request " + selectedFile, CloudBoxCommandsList.CMD_SIGNAL_BYTE);
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
            sendCommand(currentChannel, "/delete " + selectedFile, CloudBoxCommandsList.CMD_SIGNAL_BYTE);
        }
    }

    public void renameNameSelectedLocally() {
        String selectedFile = filesList.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            renameSelectedFile(currentChannel, selectedFile, Location.CLIENT);
            refreshLocalFilesList();
        }
    }

    public void renameNameSelectedOnServer() {
        String selectedFile = serverFilesList.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            renameSelectedFile(currentChannel, selectedFile, Location.SERVER);
        }
    }

    public void addFileLocally() {
        FileChooser fileChooser = new FileChooser();
        Window primaryStage = null;
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                Files.copy(file.toPath(), Paths.get(client_storage_location, file.getName()));
                refreshLocalFilesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addFileOnServer() {
        FileChooser fileChooser = new FileChooser();
        Window primaryStage = null;
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            trySendFile(file.toPath());
        }
    }

    private enum Location {
        SERVER, CLIENT
    }

    private void renameSelectedFile( Channel currentChannel, String _selectedFile, Location location ) {
        if (_selectedFile != null) {
            TextInputDialog dialog = new TextInputDialog(_selectedFile);
            dialog.setTitle("Переименование файла");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите новое имя файла для " + _selectedFile + ": ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newFileName -> {
                if (location == Location.CLIENT)
                    renameFileInLocation(client_storage_location, _selectedFile, newFileName);
                if (location == Location.SERVER)
                    sendCommand(currentChannel, "/rename " + _selectedFile + "|" + newFileName,
                            CloudBoxCommandsList.CMD_SIGNAL_BYTE);
            });
        }
    }

    private void trySendFile( Path filePath ) {
        try {
            ProtoFileSender.sendFile(filePath, currentChannel, future -> {
                if (!future.isSuccess()) {
                    System.out.println("Не удалось отправить файл на сервер");
                    future.cause().printStackTrace();
                }
                if (future.isSuccess()) {
                    System.out.println("Файл успешно передан");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
