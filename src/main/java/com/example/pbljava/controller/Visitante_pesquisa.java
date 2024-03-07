package com.example.pbljava.controller;

import com.example.pbljava.Model.Emprestimo;
import com.example.pbljava.Model.Livro;
import com.example.pbljava.components.ControladorDados;
import com.example.pbljava.dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

import com.example.pbljava.HelloApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Visitante_pesquisa {



    @FXML
    private Label Labelerror;
    @FXML
    private Button Homebutton;

    @FXML
    private Button Reservarr;

    private Operadormain operadorr;

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
    private TableView<Livro> tabelageral;

    @FXML
    private Button tituloButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    private ObservableList<Livro> Livros;


    @FXML
    void initialize() {

        if(ControladorDados.getInstancia().getT().equals("1")) {
            Reservarr.setVisible(true);

        } else {
            Reservarr.setVisible(false);

        }
        this.Livros= FXCollections.observableArrayList();

        TableColumn<Livro, String> nomeCol = new TableColumn<>("Titulo");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("titulo"));


        TableColumn<Livro, String> autorcol = new TableColumn<>("Autor");
        autorcol.setCellValueFactory(new PropertyValueFactory<Livro,String>("autor"));


        TableColumn<Livro, Boolean> dispocol = new TableColumn<>("Disponibilidade");
        dispocol.setCellValueFactory(new PropertyValueFactory<Livro,Boolean>("disponibilidade"));

        TableColumn<Livro, Integer> idcol = new TableColumn<>("ISBN");
        idcol.setCellValueFactory(new PropertyValueFactory<Livro,Integer>("ISBN"));




        this.tabelageral.getColumns().addAll(nomeCol,autorcol,dispocol,idcol);
        this.tabelageral.setItems(Livros);

        choiceBox.getItems().addAll("Autor", "Título", "ISBN");

    }

    @FXML
    void AutorbuttonAction(ActionEvent event) {
        this.Livros.clear();
        try {

            if(this.choiceBox.getValue().equals("Autor")){
                List<Livro> lista_autor = DAO.getLivroDAO().pesquisarPorAutor(autortb.getText());
                //System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }
            }else if(this.choiceBox.getValue().equals("Título")){
                List<Livro> lista_autor = DAO.getLivroDAO().pesquisarPorTitulo(autortb.getText());
                //System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }
            }else if (this.choiceBox.getValue().equals("ISBN")){
                List<Livro> lista_autor = DAO.getLivroDAO().pesquisarPorISBN(Integer.parseInt(autortb.getText()));
                //System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }

            }
            this.autortb.setText("");

        } catch (Exception e) {
            this.labelerror1.setText("bora ver");
        }

    }
    @FXML
    void ReservarrAction(ActionEvent event) {

        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            Livro livro = this.tabelageral.getSelectionModel().getSelectedItem();
            try {
                livro.Reservar_livro(ControladorDados.getInstancia().getUsuario(),LocalDate.now());
                //System.out.println(livro.getFila().size());
                this.Livros.set(i,livro);

            } catch (Exception e) {
                this.Labelerror.setText("ERRO na renovação. \n" +
                        "Verifique:\n" +
                        "1- Se você está Multado\n" +
                        "2- Se você está Bloqueado\n" +
                        "3- Se o está Livro disponível\n");
            }

        }

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
