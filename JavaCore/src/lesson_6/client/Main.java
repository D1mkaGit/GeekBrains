package lesson_6.client;

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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 350, 375));
        primaryStage.show();
    }
}
