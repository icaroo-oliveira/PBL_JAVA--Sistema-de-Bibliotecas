package com.example.pbljava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/pbljava/hello-view.fxml"));

        //novo
        stage.setMinWidth(1000);
        stage.setMaxWidth(1200);
        stage.setMinHeight(800);
        stage.setMaxHeight(1000);

        stage.setWidth(1200);
        stage.setHeight(800);
        //novo

        stage.centerOnScreen();
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);

        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}