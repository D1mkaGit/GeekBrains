package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.channel.Channel;
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
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import static com.geekbrains.brains.cloud.common.ProtoFileSender.SRV_TEMP_PATH;

public class MainController implements Initializable {
    public VBox rootNode;

    @FXML
    TextField requestFileName;

    @FXML
    TextField sendFileName;

    @FXML
    ListView<String> filesList;

    @FXML
    ListView<String> serverFilesList;
    private Channel currentChannel;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> ProtoNetwork.getInstance().start(networkStarter)).start();
        try {
            networkStarter.await();
            currentChannel = ProtoNetwork.getInstance().getCurrentChannel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshLocalFilesList();
        refreshServerFilesList();
    }

    public void pressOnDownloadBtn() {
        if (requestFileName.getLength() > 0) {
            ProtoFileSender.sendRequest(requestFileName.getText(), currentChannel);
            showAlert("Вы запросили: " + requestFileName.getText());
            requestFileName.clear();
            refreshLocalFilesList();
        }
    }

    public void pressOnUploadBtn() throws IOException {
        if (sendFileName.getLength() > 0) {
            String pathAndFile = "client_storage/" + sendFileName.getText();
            if (Files.exists(Paths.get(pathAndFile))) {
                ProtoFileSender.sendFile((byte) 26, Paths.get(pathAndFile), currentChannel,
                        future -> {
                            if (!future.isSuccess()) {
                                future.cause().printStackTrace();
                            }
                            if (future.isSuccess()) {
                                System.out.println("Файл успешно передан");
                            }
                        });
                showAlert("Вы отправили на сервер: " + sendFileName.getText());
            } else {
                showAlert("Файла " + sendFileName.getText() + " не существует");
            }
            sendFileName.clear();
            refreshServerFilesList();
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

    public void refreshServerFilesList() {
        String serverFilesListContainer = SRV_TEMP_PATH + "_serverFilesList.txt";
        Platform.runLater(() -> {
            try {
                serverFilesList.getItems().clear();
                Files.deleteIfExists(Paths.get(serverFilesListContainer));
                ProtoFileSender.sendReqForFilesList(currentChannel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                for (int i = 0; i < 20; i++) {
                    if (!Files.exists(Paths.get(serverFilesListContainer)))
                        Thread.sleep(500);
                    else
                        break;
                }
                String file = Files.lines(Paths.get(serverFilesListContainer)).collect(Collectors.joining("\n"));
                Arrays.stream(file.split("\\|"))
                        .forEach(o -> serverFilesList.getItems().add(o));

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
