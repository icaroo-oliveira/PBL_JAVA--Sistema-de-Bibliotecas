package com.example.pbljava.controller;

import com.example.pbljava.HelloApplication;
import com.example.pbljava.Model.Operadores;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.components.ControladorDados;
import com.example.pbljava.dao.DAO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class logincontroler {

    @FXML
    private TextField Identrar;

    @FXML
    private Button botaoentrar;
    @FXML
    private Label labelerror1;
    @FXML
    private TextField senharentrar;

    @FXML
    void botaoentrarAction(ActionEvent event) throws Exception {

        try {
            Operadores operador = DAO.getOperadoresDAO().findById(Integer.parseInt(Identrar.getText()));
            String senha =senharentrar.getText();
            Parent parent = null;
            if (operador.getSenha_de_acesso().equals(senha)) {

                //novo
                ControladorDados controla = ControladorDados.getInstancia();
                controla.setOperador(operador);
                //novo



                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/operatormain.fxml"));
                System.out.println("hi");

                parent = fxmlLoader.load();

                System.out.println("hi");
                Scene scene = new Scene(parent);

                System.out.println("sou");
                Stage stage = (Stage) botaoentrar.getScene().getWindow();

                //novo
                stage.setMinWidth(1000);
                stage.setMaxWidth(1200);
                stage.setMinHeight(800);
                stage.setMaxHeight(1000);

                stage.setWidth(900);
                stage.setHeight(700);


                stage.centerOnScreen();

                //novo







                stage.setTitle("Cena 1");

                stage.setScene(scene);
                stage.show();



            }
            this.labelerror1.setText("");

        } catch (Exception e) {
            this.labelerror1.setText("bora ver");
        }

    }

    private void clearAll() {
        this.senharentrar.clear();
        this.Identrar.clear();
        this.labelerror1.setText("");

    }

    private void openPage(String url){
        Parent root = null;
        try{
            root= FXMLLoader.load(getClass().getResource(url));
        }catch (Exception e){

        }
    }
}

