package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage s;
    static Doctor doc;
    static Admin admin;
    static Patient patient;

    @Override
    public void start(Stage stage) throws IOException {
        s = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("screen1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 900);
        stage.setTitle("Hospital Management System");
        stage.setScene(scene);
        stage.show();
    }

    public Controller changescene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        s.setScene(new Scene(fxmlLoader.load(), 1440, 900));
        s.show();
        return fxmlLoader.getController();
    }
    public static void main(String[] args) {
        launch();
    }

}