package lesson_6.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Controller c;

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("sample.fxml"));
        primaryStage.setTitle("Chat");
        c = loader.getController();
        c.setStage(primaryStage);

        Scene scene = new Scene(root, 310, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            c.Dispose();
            Platform.exit();
            System.exit(0);
        });


//
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Chat");
//        primaryStage.setScene(new Scene(root, 350, 375));
//        primaryStage.show();
    }
}


//    класс Controller
//
//    public void Dispose() {
//        System.out.println("Отправляем сообщение на сервер о завершении работы");
//        try {
//            if (out != null) {
//                out.writeUTF("/end");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
///////////////////
//
//    Класс  Main
//
//    Controller c;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader();
//
//        Parent root = loader.load(getClass().getResourceAsStream("sample.fxml"));
//        primaryStage.setTitle("Chat 2k19");
//        c = loader.getController();
//
//        Scene scene = new Scene(root, 310, 350);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        primaryStage.setOnCloseRequest(event -> {
//            c.Dispose();
//            Platform.exit();
//            System.exit(0);
//        });