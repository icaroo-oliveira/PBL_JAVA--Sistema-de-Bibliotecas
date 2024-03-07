package com.example.pbljava.controller;

import com.example.pbljava.Model.Livro;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CriarUsuario {


    @FXML
    private Button BotaoAdicionar;

    @FXML
    private Button BotaoEditar;

    @FXML
    private Button Botaodeletar;

    @FXML
    private TextField EnderecoText;

    @FXML
    private TextField NomeText;

    @FXML
    private TextField TelefoneText;


    @FXML
    private TextField TextUsuario;

    @FXML
    private Button buscar;

    @FXML
    private TableView<Usuario> tabelageral;

    private ObservableList<Usuario> usuariodados;
    @FXML
    void initialize() {

        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem1 = new MenuItem("Banir");
        MenuItem menuItem2 = new MenuItem("Desbanir");
        contextMenu.getItems().addAll(menuItem1,menuItem2);


        this.usuariodados = FXCollections.observableArrayList(DAO.getUsuarioDAO().findMany()); //DAO FICARIA AQUI

        TableColumn<Usuario, String> nomeCol = new TableColumn<>("Nome");
        TableColumn<Usuario, String> EnderecoCol = new TableColumn<>("Endereco");
        TableColumn<Usuario, String> TelefoneCol = new TableColumn<>("Telefone");
        TableColumn<Usuario, Integer> IDcol = new TableColumn<>("ID");

        nomeCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nome"));
        EnderecoCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("endereco"));
        TelefoneCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("telefone"));
        IDcol.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("id"));

        this.tabelageral.getColumns().addAll(nomeCol,EnderecoCol,TelefoneCol,IDcol);
        this.tabelageral.setItems(usuariodados);


        tabelageral.setOnMouseClicked((MouseEvent event) -> {
            if(this.tabelageral.getSelectionModel().getSelectedIndex()!=-1 && event.getButton().equals(MouseButton.SECONDARY)){
                //System.out.println(tabelageral.getSelectionModel().getSelectedItem().getStatus());
                this.tabelageral.setContextMenu(contextMenu);
                menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Usuario usuario = tabelageral.getSelectionModel().getSelectedItem();
                        usuario.setStatus(false);
                        try {
                            DAO.getUsuarioDAO().update(usuario);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

                menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Usuario usuario = tabelageral.getSelectionModel().getSelectedItem();
                        usuario.setStatus(true);
                        try {
                            DAO.getUsuarioDAO().update(usuario);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

            }

        });

    }

    private void clearAll() {
        this.EnderecoText.clear();
        this.TelefoneText.clear();
        this.NomeText.clear();
    }

    @FXML
    void BotaoAdicionarAction(ActionEvent event) {

        Usuario usuario = DAO.getUsuarioDAO().create(new Usuario(NomeText.getText(),EnderecoText.getText(),TelefoneText.getText(),0));
        this.usuariodados.add(usuario);
    }

    @FXML
    void BotaoEditarAction(ActionEvent event) {
        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Usuario usuario = this.tabelageral.getSelectionModel().getSelectedItem();
            if(!(NomeText.getText()).isEmpty()){
                usuario.setNome(NomeText.getText());
            }
            if(!(EnderecoText.getText()).isEmpty()){
                usuario.setEndereco(EnderecoText.getText());
            }
            if(!(TelefoneText.getText()).isEmpty()){
                usuario.setTelefone(TelefoneText.getText());
            }



            this.usuariodados.set(i,usuario);
        }

    }

    @FXML
    void BotaodeletarAction(ActionEvent event) {
        int i = this.tabelageral.getSelectionModel().getSelectedIndex();
        if(i>=0){
            Usuario usuario = this.tabelageral.getSelectionModel().getSelectedItem();
            try {
                DAO.getUsuarioDAO().delete(usuario);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.usuariodados.remove(usuario);
        }


    }

    @FXML
    void buscarAction(ActionEvent event) {
        try {


            this.usuariodados.clear();
            Usuario usuario = DAO.getUsuarioDAO().findById(Integer.parseInt((TextUsuario.getText())));
            this.usuariodados.add(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
