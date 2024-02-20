package com.example.pbljava.controller;

import com.example.pbljava.Model.Livro;
import com.example.pbljava.Model.Operadores;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ADDOperador {


    @FXML
    private Button BotaoAdicionar;

    @FXML
    private Button BotaoEditar;

    @FXML
    private Button Botaodeletar;

    @FXML
    private TextField CargoText;

    @FXML
    private TextField NomeText;

    @FXML
    private TextField SenhaText;



    @FXML
    private TableView<Operadores> tabelageral;

    private ObservableList<Operadores> operadoresdados;
    @FXML
    void initialize() {

        this.operadoresdados = FXCollections.observableArrayList(DAO.getOperadoresDAO().findMany()); //DAO FICARIA AQUI

        TableColumn<Operadores, String> nomeCol = new TableColumn<>("Nome");
        TableColumn<Operadores, String> cargoCol = new TableColumn<>("Cargo");
        TableColumn<Operadores, Integer> IDcol = new TableColumn<>("ID");

        nomeCol.setCellValueFactory(new PropertyValueFactory<Operadores,String>("nome"));
        cargoCol.setCellValueFactory(new PropertyValueFactory<Operadores,String>("cargo"));
        IDcol.setCellValueFactory(new PropertyValueFactory<Operadores,Integer>("id"));

        this.tabelageral.getColumns().addAll(nomeCol,cargoCol,IDcol);
        this.tabelageral.setItems(operadoresdados);


    }

    private void clearAll() {
        this.CargoText.clear();
        this.SenhaText.clear();
        this.NomeText.clear();
    }

    @FXML
    void BotaoAdicionarAction(ActionEvent event) {
        Operadores operator = DAO.getOperadoresDAO().create(new Operadores(NomeText.getText(),null,null,null,CargoText.getText(),0));
        operator.setSenha_de_acesso(SenhaText.getText());
        try {
            DAO.getOperadoresDAO().update(operator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.operadoresdados.add(operator);
        clearAll();
    }

    @FXML
    void BotaoEditarAction(ActionEvent event) {
        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Operadores operator = this.tabelageral.getSelectionModel().getSelectedItem();
            if(!(NomeText.getText()).isEmpty()){
                operator.setNome(NomeText.getText());
            }
            if(!(CargoText.getText()).isEmpty()){
                operator.setCargo(CargoText.getText());
            }
            if(!(SenhaText.getText()).isEmpty()){
                System.out.println("ok");
                operator.setSenha_de_acesso(SenhaText.getText());
            }



            this.operadoresdados.set(i,operator);
            try {
                DAO.getOperadoresDAO().update(operator);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        clearAll();

    }

    @FXML
    void BotaodeletarAction(ActionEvent event) {
        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Operadores operator = this.tabelageral.getSelectionModel().getSelectedItem();
            try {
                DAO.getOperadoresDAO().delete(operator);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.operadoresdados.remove(operator);
        }
        clearAll();


    }

}
