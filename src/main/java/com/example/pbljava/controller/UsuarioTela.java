package com.example.pbljava.controller;

import com.example.pbljava.components.ControladorDados;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class UsuarioTela {

    @FXML
    private Label nomeUsuario;

    @FXML
    private Label statususuario;

    @FXML
    private Button HomeButton;
    @FXML
    private MenuButton Menubutton;

    @FXML
    private GridPane Gridpane;

    @FXML
    void initialize() {


        this.nomeUsuario.setText(ControladorDados.getInstancia().getUsuario().getNome());

        if(ControladorDados.getInstancia().getUsuario().getStatus()){
            this.statususuario.setText("Disponível");
        }else{
            this.statususuario.setText("Bloqueado");

        }


        System.out.println(ControladorDados.getInstancia().getUsuario().getStatus());
        Menubutton.getItems().clear();

        MenuItem menuItem1 = new MenuItem("Pesquisar");
        MenuItem menuItem2 = new MenuItem("Renovar");

        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openPage("/com/example/pbljava/visitante_pesquisa.fxml");
            }
        });


        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                openPage("/com/example/pbljava/renovarEmp.fxml");



            }
        });


        Menubutton.getItems().addAll(menuItem1,menuItem2);
    }


    @FXML
    void MenubuttonAction(ActionEvent event) {



    }
    private int cont=0;

    private void openPage(String url){

        Parent root = null;
        try{
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(url)));
        }catch (Exception e){
            //logica
        }

        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : Gridpane.getChildren()) {
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);
            if (colIndex != null && rowIndex != null && colIndex == 1 && rowIndex == 0) {
                nodesToRemove.add(node);
            }
        }
        Gridpane.getChildren().removeAll(nodesToRemove);

        this.Gridpane.add(root,1,0);

    }


    @FXML
    void HomeButtonAction(ActionEvent event) {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/hello-view.fxml"));

            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);

            Stage stage = (Stage) HomeButton.getScene().getWindow();

            //novo

            stage.setWidth(1200);
            stage.setHeight(800);

            stage.centerOnScreen();

            stage.setTitle("Cena 1");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        }catch(Exception e){
            //System.out.println("algo deu errado(?)");
        }

    }
}
