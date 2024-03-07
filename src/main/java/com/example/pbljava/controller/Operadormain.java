package com.example.pbljava.controller;

import com.example.pbljava.Model.Usuario;
import com.example.pbljava.components.ControladorDados;
import com.example.pbljava.dao.DAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Operadormain {

    @FXML
    private Button Homebutton;

    @FXML
    private BorderPane Painelprincipaloperador;

    @FXML
    private Label nomeoperador;

    @FXML
    private Button pagina0;

    @FXML
    private Button pagina1;

    @FXML
    private Button pagina2;


    @FXML
    private Button pagina3;

    @FXML
    private Button pagina4;

    @FXML
    private Button pagina5;


    @FXML
    void initialize() {
        if(getControlador().getOperador().getCargo().equals("adm")) {
            pagina3.setVisible(true);
            pagina4.setVisible(true);
            pagina5.setVisible(true);
        } else {
            pagina3.setVisible(false);
            pagina4.setVisible(false);
            pagina5.setVisible(false);
        }
    }



    public ControladorDados getControlador(){
        ControladorDados controla = ControladorDados.getInstancia();
        return controla;
    }









    @FXML
    void pagina0Action(ActionEvent event) {
        //System.out.println("melancia");
        //ControladorDados controla = ControladorDados.getInstancia();
        //controla.setOperadorr(this);
        getControlador().setOperadorr(this);
        openPage("/com/example/pbljava/pagina_emp1.fxml");
    //emprestimo
    }

    @FXML
    void pagina1Action(ActionEvent event) {
        //System.out.println("melancia");

        //ControladorDados controla = ControladorDados.getInstancia();
        //controla.setOperadorr(this);

        getControlador().setOperadorr(this);

        openPage("/com/example/pbljava/EditandoEmp.fxml");//nothing

    }

    @FXML
    void pagina2Action(ActionEvent event) {
        //System.out.println("melancia");
        openPage("/com/example/pbljava/page2.fxml");//livros

    }

    @FXML
    void pagina3Action(ActionEvent event) {

        openPage("/com/example/pbljava/CriarUsuario.fxml");


    }

    @FXML
    void pagina4Action(ActionEvent event) {

        openPage("/com/example/pbljava/ADDOperador.fxml");


    }

    @FXML
    void pagina5Action(ActionEvent event) {

        openPage("/com/example/pbljava/relatorio.fxml");


    }

    private void openPage(String url){
        Parent root = null;
        try{
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(url)));
        }catch (Exception e){
            //logica
        }

        this.Painelprincipaloperador.setCenter(root);

    }

    public BorderPane getP(){
        return this.Painelprincipaloperador;
    }


    @FXML
    void HomebuttonAction(ActionEvent event) {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/hello-view.fxml"));

            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent);

            Stage stage = (Stage) Homebutton.getScene().getWindow();

            //novo
            stage.setMinWidth(1000);
            stage.setMaxWidth(1200);
            stage.setMinHeight(800);
            stage.setMaxHeight(1000);

            stage.setWidth(1200);
            stage.setHeight(800);

            stage.centerOnScreen();

            stage.setTitle("HOME");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        }catch(Exception e){
            //System.out.println("algo deu errado(?)");
        }
    }


}
