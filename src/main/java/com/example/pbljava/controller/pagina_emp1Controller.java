package com.example.pbljava.controller;

import com.example.pbljava.Model.Livro;
import com.example.pbljava.components.ControladorDados;
import com.example.pbljava.dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class pagina_emp1Controller {




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
        this.Livros= FXCollections.observableArrayList();

        TableColumn<Livro, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Livro,String>("titulo"));

        this.tabelageral.getColumns().add(nomeCol);
        this.tabelageral.setItems(Livros);

        choiceBox.getItems().addAll("Autor", "Título", "ISBN");

    }

    @FXML
    void AutorbuttonAction(ActionEvent event) {
        this.Livros.clear();
        try {

            if(this.choiceBox.getValue().equals("Autor")){
                List<Livro> lista_autor = DAO.getLivroDAO().pesquisarPorAutor(autortb.getText());
                System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }
            }else if(this.choiceBox.getValue().equals("Título")){
                List<Livro> lista_autor = DAO.getLivroDAO().pesquisarPorTitulo(autortb.getText());
                System.out.println(lista_autor.size());
                if (!lista_autor.isEmpty()) {
                    for(Livro livro: lista_autor){
                        this.Livros.add(livro);
                    }
                }
            }else if (this.choiceBox.getValue().equals("ISBN")){
                List<Livro> lista_autor = DAO.getLivroDAO().pesquisarPorISBN(Integer.parseInt(autortb.getText()));
                System.out.println(lista_autor.size());
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
    void OkbutoonAction(ActionEvent event) {
        System.out.println("ola a todos");

        int i = this.tabelageral.getSelectionModel().getSelectedIndex();

        if(i>=0){
            System.out.println("okkk");
            Livro livro = this.tabelageral.getSelectionModel().getSelectedItem();
            Parent root = null;
            try{

                //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pagina_emp2.fxml"));

                System.out.println("melaek");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pbljava/pagina_emp2.fxml"));
                root= fxmlLoader.load();



                this.operadorr= ControladorDados.getInstancia().getOperadorr();
                ControladorDados controla = ControladorDados.getInstancia();

                System.out.println("melaek11");
                controla.setLivro(livro);
                System.out.println("melaek111");
                controla.setOperadorr(this.operadorr);

                this.operadorr.getP().setCenter(root);
                System.out.println("melaek11111111");
            }catch (Exception e){
                System.out.println("algo errado");

            }



        }

    }

}
