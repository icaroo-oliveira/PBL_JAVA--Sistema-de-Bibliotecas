package com.example.pbljava.controller;

import com.example.pbljava.Model.Livro;
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

public class Page2 {

    @FXML
    private TextField AnopublicacaoText;

    @FXML
    private TextField AutorText;

    @FXML
    private Button BotaoAdicionar;

    @FXML
    private Button BotaoEditar;

    @FXML
    private Button Botaodeletar;

    @FXML
    private TextField CategoriaText;

    @FXML
    private TextField ISBNText;

    @FXML
    private TextField LocalizacaoText;

    @FXML
    private TextField TituloText;

    @FXML
    private TableView<Livro> tabelageral;

    private ObservableList<Livro> livrossdados;
    @FXML
    void initialize() {

        this.livrossdados = FXCollections.observableArrayList(DAO.getLivroDAO().findMany()); //DAO FICARIA AQUI

        TableColumn<Livro, String> tituloCol = new TableColumn<>("Titulo");
        TableColumn<Livro, String> autorCol = new TableColumn<>("Autor");
        TableColumn<Livro, String> isbnCol = new TableColumn<>("ISBN");
        TableColumn<Livro, String> categoriaCol = new TableColumn<>("Categoria");

        tituloCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("titulo"));
        autorCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("autor"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("ISBN"));
        categoriaCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("categoria"));

        this.tabelageral.getColumns().addAll(tituloCol,autorCol,isbnCol,categoriaCol);
        this.tabelageral.setItems(livrossdados);


    }

    private void clearAll() {
        this.TituloText.clear();
        this.ISBNText.clear();
        this.AnopublicacaoText.clear();
        this.AutorText.clear();
        this.LocalizacaoText.clear();
        this.CategoriaText.clear();
    }

    @FXML
    void BotaoAdicionarAction(ActionEvent event) {
        Livro livro = DAO.getLivroDAO().create(new Livro(AutorText.getText(),Integer.parseInt(ISBNText.getText()),LocalizacaoText.getText(),Integer.parseInt(AnopublicacaoText.getText()),TituloText.getText(),CategoriaText.getText()));
        this.livrossdados.add(livro);
    }

    @FXML
    void BotaoEditarAction(ActionEvent event) {
        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Livro livro = this.tabelageral.getSelectionModel().getSelectedItem();
            if(!(AutorText.getText()).isEmpty()){
                livro.setAutor(AutorText.getText());
            }
            if(!(ISBNText.getText()).isEmpty()){
                livro.setISBN(Integer.parseInt(ISBNText.getText()));
            }
            if(!(LocalizacaoText.getText()).isEmpty()){
                livro.setLocalizao(LocalizacaoText.getText());
            }
            if(!(AnopublicacaoText.getText()).isEmpty()){
                livro.setAno_publicacao(Integer.parseInt(AnopublicacaoText.getText()));
            }

            if(!(TituloText.getText()).isEmpty()){
                livro.setTitulo(TituloText.getText());
            }
            if(!(CategoriaText.getText()).isEmpty()){
                livro.setCategoria(CategoriaText.getText());
            }

            this.livrossdados.set(i,livro);
        }

    }

    @FXML
    void BotaodeletarAction(ActionEvent event) {
        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Livro livro = this.tabelageral.getSelectionModel().getSelectedItem();
            try {
                DAO.getLivroDAO().delete(livro);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.livrossdados.remove(livro);
        }


    }

}
