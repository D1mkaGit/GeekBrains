package main.java.ru.geekbrains.chat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Geekbrains Chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        // primaryStage.setOnCloseRequest(event -> System.out.println("On Close"));
    }
}
