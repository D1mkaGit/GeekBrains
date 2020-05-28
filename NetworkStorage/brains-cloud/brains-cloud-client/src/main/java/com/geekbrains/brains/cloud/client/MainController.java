package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.CloudBoxCommandsList;
import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
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
    TextField requestFileName;

    @FXML
    TextField sendFileName;

    @FXML
    ListView<String> filesList;

    @FXML
    ListView<String> serverFilesList;
    static String serverFilesListString;
    private Channel currentChannel;
    private Alert alert;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> ProtoNetwork.getInstance().start(networkStarter)).start();
        try {
            networkStarter.await();
            currentChannel = ProtoNetwork.getInstance().getCurrentChannel();
            ProtoNetwork.getInstance().setOnReceivedCallback(() -> {
                showAlert("Файл: " + requestFileName.getText() + " скачан");
                requestFileName.clear();
                refreshLocalFilesList();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshLocalFilesList();
        sendRefreshServerFilesListRequest();
    }

    public void pressOnDownloadBtn() {
        if (requestFileName.getLength() > 0) {
            byte[] filenameBytes = ("/request " + requestFileName.getText()).getBytes(StandardCharsets.UTF_8);
            ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + filenameBytes.length);
            buf.writeByte(CloudBoxCommandsList.CMD_SIGNAL_BYTE);
            buf.writeInt(filenameBytes.length);
            buf.writeBytes(filenameBytes);
            currentChannel.writeAndFlush(buf);

            showAlert("Вы запросили: " + requestFileName.getText());
        }
    }

    public void pressOnUploadBtn() throws IOException {
        if (sendFileName.getLength() > 0) {
            String fileName = sendFileName.getText();
            Path filePath = Paths.get("client_storage", fileName);
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
                Files.list(Paths.get("client_storage"))
                        .filter(p -> !Files.isDirectory(p))
                        .map(p -> p.getFileName().toString())
                        .forEach(o -> filesList.getItems().add(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendRefreshServerFilesListRequest() {
        byte[] cmdNameBytes = ("/list ").getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + cmdNameBytes.length);
        buf.writeByte(CloudBoxCommandsList.CMD_SIGNAL_BYTE);
        buf.writeInt(cmdNameBytes.length);
        buf.writeBytes(cmdNameBytes);
        currentChannel.writeAndFlush(buf);

        ProtoNetwork.getInstance().setOnReceivedFLCallback(() -> {
            Platform.runLater(() -> {
                serverFilesList.getItems().clear();
                Arrays.stream(serverFilesListString.split("\\|"))
                        .forEach(o -> serverFilesList.getItems().add(o));
            });
        });
    }
}
