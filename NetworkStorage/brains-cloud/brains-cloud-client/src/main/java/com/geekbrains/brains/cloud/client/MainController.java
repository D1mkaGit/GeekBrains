package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.AbstractMessage;
import com.geekbrains.brains.cloud.common.FileMessage;
import com.geekbrains.brains.cloud.common.FileRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public VBox rootNode;
    @FXML
    TextField requestFileName;

    @FXML
    TextField sendFileName;

    @FXML
    ListView<String> filesList;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        Network.start();
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    AbstractMessage am = Network.readObject();
                    if (am instanceof FileMessage) {
                        FileMessage fm = (FileMessage) am;
                        Files.write(Paths.get("client_storage/" + fm.getFilename()), fm.getData(), StandardOpenOption.CREATE);
                        refreshLocalFilesList();
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            } finally {
                Network.stop();
            }
        });
        t.setDaemon(true);
        t.start();
        refreshLocalFilesList();
    }

    public void pressOnDownloadBtn() {
        if (requestFileName.getLength() > 0) {
            Network.sendMsg(new FileRequest(requestFileName.getText()));
            showAlert("Вы запросили: " + requestFileName.getText());
            requestFileName.clear();
        }
    }

    public void pressOnUploadBtn() throws IOException {
        if (sendFileName.getLength() > 0) {
            String pathAndFile = "client_storage/" + sendFileName.getText();
            if (Files.exists(Paths.get(pathAndFile))) {
                Network.sendMsg(new FileMessage(Paths.get(pathAndFile)));
                showAlert("Вы отправили на сервер: " + sendFileName.getText());
            } else {
                showAlert("Файла " + sendFileName.getText() + " не существует");
            }
            sendFileName.clear();
        }
    }

    private void showAlert( String msg ) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void refreshLocalFilesList() {
        Platform.runLater(() -> {
            try {
                filesList.getItems().clear();
                Files.list(Paths.get("client_storage"))
                        .filter(p -> !Files.isDirectory(p))
                        .map(p -> p.getFileName().toString())
                        .forEach(o -> filesList.getItems().add(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
