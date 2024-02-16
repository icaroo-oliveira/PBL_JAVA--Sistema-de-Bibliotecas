package com.example.pbljava.controller;

import com.example.pbljava.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Button operadorButton;

    @FXML
    private Button usuarioButton;

    @FXML
    private Button visitanteButton;

    @FXML
    void operadorButtonAction(ActionEvent event) {
        try{
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/login-view.fxml"));


            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);

            Stage stage = (Stage) operadorButton.getScene().getWindow();
            stage.setTitle("Cena 1");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("algo deu errado(?)");
        }

    }

    @FXML
    void usuarioButtonAction(ActionEvent event) {

    }

    @FXML
    void visitanteButtonAction(ActionEvent event) {

        try{
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pagina-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/pagina-view.fxml"));


            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);

            Stage stage = (Stage) visitanteButton.getScene().getWindow();
            stage.setTitle("Cena 1");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("algo deu errado(?)");
        }


    }

}
