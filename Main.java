package com.clinic.royan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage; // **Declare static Stage**

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Royan Clinic");
        primaryStage.setScene(new Scene(root, 450, 200));
        primaryStage.setResizable(false);
        setPrimaryStage(primaryStage);
        primaryStage.show();

//        primaryStage.hide();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
