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
    private Alert alert;
    private final String serverFilesListContainer = "client_storage/temp/_serverFilesList.txt";

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> ProtoNetwork.getInstance().start(networkStarter)).start();
        try {
            networkStarter.await();
            currentChannel = ProtoNetwork.getInstance().getCurrentChannel();
            ProtoNetwork.getInstance().setOnReceivedCallback(() -> {
                Platform.runLater(() -> {
                    if (alert.isShowing()) alert.close();
                });
                showAlert("Файл: " + requestFileName.getText() + " скачан");
                requestFileName.clear();
                refreshLocalFilesList();
            });
            ProtoNetwork.getInstance().setOnReceivedFLCallback(this::refreshServerFilesList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshLocalFilesList();
        sendRefreshServerFilesListRequest();
    }

    public void pressOnDownloadBtn() {
        if (requestFileName.getLength() > 0) {
            ProtoFileSender.sendRequest(requestFileName.getText(), currentChannel);
            showAlert("Вы запросили: " + requestFileName.getText());
        }
    }

    public void pressOnUploadBtn() throws IOException {
        if (sendFileName.getLength() > 0) {
            String fileName = sendFileName.getText();
            String pathAndFile = "client_storage/" + fileName;
            if (Files.exists(Paths.get(pathAndFile))) {
                showAlert("Вы отправили на сервер: " + fileName);
                ProtoFileSender.sendFile((byte) 26, Paths.get(pathAndFile), currentChannel,
                        future -> {
                            if (!future.isSuccess()) {
                                future.cause().printStackTrace();
                            }
                            if (future.isSuccess()) {
                                System.out.println("Файл " + fileName + " успешно передан");
                                Platform.runLater(() -> {
                                    if (alert.isShowing()) alert.close();
                                });
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
                Files.list(Paths.get("client_storage"))
                        .filter(p -> !Files.isDirectory(p))
                        .map(p -> p.getFileName().toString())
                        .forEach(o -> filesList.getItems().add(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void refreshServerFilesList() {
        Platform.runLater(() -> {
            if (Files.exists(Paths.get(serverFilesListContainer))) {
                try {
                    Arrays.stream(Files.lines(Paths.get(serverFilesListContainer))
                            .collect(Collectors.joining("\n")).split("\\|"))
                            .forEach(o -> serverFilesList.getItems().add(o));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendRefreshServerFilesListRequest() {
        Platform.runLater(() -> serverFilesList.getItems().clear());
        try {
            Files.deleteIfExists(Paths.get(serverFilesListContainer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProtoFileSender.sendReqForFilesList(currentChannel);
    }
}
