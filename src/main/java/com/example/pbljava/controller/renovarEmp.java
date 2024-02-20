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

public class renovarEmp {


    @FXML
    private Button Renovar;

    @FXML
    private TableView<Emprestimo> tabelageral;

    //@FXML
    private ObservableList<Emprestimo> Emprestimo;






    @FXML
    void initialize() {


        this.Emprestimo = FXCollections.observableArrayList(ControladorDados.getInstancia().getUsuario().getHistorico_livro()); //DAO FICARIA AQUI

        TableColumn<Emprestimo, Integer> tituloCol = new TableColumn<>("id");
        TableColumn<Emprestimo, Livro> autorCol = new TableColumn<>("Livro");
        TableColumn<Emprestimo, LocalDate> devolverCol = new TableColumn<>("Devolver esperado");

        tituloCol.setCellValueFactory(new PropertyValueFactory<Emprestimo,Integer>("id_emprestimo"));
        autorCol.setCellValueFactory(new PropertyValueFactory<Emprestimo,Livro>("livro"));
        devolverCol.setCellValueFactory(new PropertyValueFactory<>("data_devolucao_esperada"));

        autorCol.setCellFactory(column -> {
            return new TableCell<Emprestimo, Livro>() {
                @Override
                protected void updateItem(Livro item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getTitulo());
                    }
                }
            };
        });
        this.tabelageral.getColumns().addAll(tituloCol,autorCol,devolverCol);
        this.tabelageral.setItems(Emprestimo);


    }

    @FXML
    void RenovarAction(ActionEvent event) {

        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            Emprestimo emprestimo = this.tabelageral.getSelectionModel().getSelectedItem();
            try {
                emprestimo.Renovar_emprestimo(LocalDate.of(2024,2,22),LocalDate.of(2024,2,22));
                this.Emprestimo.set(i,emprestimo);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

}
