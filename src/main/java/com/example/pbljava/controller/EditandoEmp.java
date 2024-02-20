package com.example.pbljava.controller;

import com.example.pbljava.Model.Livro;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.components.ControladorDados;
import com.example.pbljava.dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditandoEmp {




    private Operadormain operadorr;
    private Livro livro;

    @FXML
    private Button Autorbutton;

    @FXML
    private Button CategoriaButton;

    @FXML
    private TextField autortb;

    @FXML
    private Button Okbutoon;
    @FXML
    private Button isbnButton;

    @FXML
    private Label labelerror1;

    @FXML
    private TableView<Usuario> tabelageral;

    @FXML
    private Button tituloButton;

    @FXML
    //private ChoiceBox<String> choiceBox;

    private ObservableList<Usuario> Usuario;


    @FXML
    void initialize() {
        this.Usuario= FXCollections.observableArrayList();

        TableColumn<Usuario, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nome"));

        TableColumn<Usuario, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("id"));

        this.tabelageral.getColumns().addAll(nomeCol,idCol);
        this.tabelageral.setItems(Usuario);

        //choiceBox.getItems().addAll("Nome", "ID");

    }

    @FXML
    void AutorbuttonAction(ActionEvent event) {
        this.Usuario.clear();
        try {
            Usuario usuario = DAO.getUsuarioDAO().findById(Integer.parseInt(autortb.getText()));
            this.Usuario.add(usuario);
            this.autortb.setText("");
        } catch (Exception e) {
            this.labelerror1.setText("bora ver");
        }

    }
    @FXML
    void OkbutoonAction(ActionEvent event){

        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Usuario usuario = this.tabelageral.getSelectionModel().getSelectedItem();


            try{
                ControladorDados.getInstancia().setUsuario(usuario);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/EmpContr.fxml"));


                Parent root= fxmlLoader.load();

                System.out.println("aiai");
                ControladorDados.getInstancia().getOperadorr().getP().setCenter(root);

                System.out.println("aiai");

            }catch (Exception e){
                System.out.println("algo errado");

            }



        }

    }

}
