package com.example.pbljava.controller;

import com.example.pbljava.HelloApplication;
import com.example.pbljava.components.ControladorDados;
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

            //novo
            stage.setMinWidth(1000);
            stage.setMaxWidth(1200);
            stage.setMinHeight(800);
            stage.setMaxHeight(1000);

            stage.setWidth(1200);
            stage.setHeight(800);


            stage.centerOnScreen();
            //novo




            stage.setTitle("Cena 1");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("algo deu errado(?)");
        }

    }

    @FXML
    void usuarioButtonAction(ActionEvent event) {

        try{

            ControladorDados.getInstancia().setT("1");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/Usuariologin.fxml"));


            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);

            Stage stage = (Stage) visitanteButton.getScene().getWindow();

            //novo
            stage.setMinWidth(1000);
            stage.setMaxWidth(1200);
            stage.setMinHeight(800);
            stage.setMaxHeight(1000);

            stage.setWidth(1200);
            stage.setHeight(800);

            stage.centerOnScreen();
            //novo




            stage.setTitle("Cena 1");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("algo deu errado(?)");
        }

    }

    @FXML
    void visitanteButtonAction(ActionEvent event) {

        try{
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pagina-view.fxml"));
            ControladorDados.getInstancia().setT("0");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/visitante_pesquisa.fxml"));


            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);

            Stage stage = (Stage) visitanteButton.getScene().getWindow();

            //novo
            stage.setMinWidth(1000);
            stage.setMaxWidth(1200);
            stage.setMinHeight(800);
            stage.setMaxHeight(1000);

            stage.setWidth(1200);
            stage.setHeight(800);

            stage.centerOnScreen();
            //novo

            stage.setTitle("Cena 1");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("algo deu errado(?)");
        }


    }

}
