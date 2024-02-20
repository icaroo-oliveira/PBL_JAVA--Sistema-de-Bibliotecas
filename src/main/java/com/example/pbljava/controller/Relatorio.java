package com.example.pbljava.controller;

import com.example.pbljava.Model.Livro;
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

public class Relatorio {





    @FXML
    private Label labelerror1;

    @FXML
    private TableView<Livro> tabelageral;


    @FXML
    private ChoiceBox<String> choiceBox;

    private ObservableList<Livro> Livros;


    @FXML
    void initialize() {
        this.Livros= FXCollections.observableArrayList();

        TableColumn<Livro, String> nomeCol = new TableColumn<>("Titulo");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("titulo"));

        this.tabelageral.getColumns().add(nomeCol);
        this.tabelageral.setItems(Livros);

        choiceBox.getItems().addAll("Emprestados", "Reservados", "Atrasados","Populares");

    }

    @FXML
    void AutorbuttonAction(ActionEvent event) {
        this.Livros.clear();
        try {

            if(this.choiceBox.getValue().equals("Emprestados")){
                List<Livro> lista_autor = DAO.getLivroDAO().getLista_emprestados();
                System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }
            }else if(this.choiceBox.getValue().equals("Reservados")){
                List<Livro> lista_autor = DAO.getLivroDAO().getLista_reservados();
                System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }
            }else if (this.choiceBox.getValue().equals("Atrasados")){
                List<Livro> lista_autor = DAO.getLivroDAO().getLista_atrasados(LocalDate.now());
                System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }

            }else if (this.choiceBox.getValue().equals("Populares")){




                TableColumn<Livro, Integer> populaCol = new TableColumn<>("Populares");
                populaCol.setCellValueFactory(new PropertyValueFactory<Livro,Integer>("popularity"));

                this.tabelageral.getColumns().add(populaCol);
                //this.tabelageral.setItems(Livros);


                List<Livro> lista_autor = DAO.getLivroDAO().TopLivros();
                System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }

            }

        } catch (Exception e) {
            this.labelerror1.setText("bora ver");
        }

    }

}
