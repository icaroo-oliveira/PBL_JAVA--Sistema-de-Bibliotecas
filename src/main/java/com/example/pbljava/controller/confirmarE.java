package com.example.pbljava.controller;

import com.example.pbljava.Model.Emprestimo;
import com.example.pbljava.components.ControladorDados;
import com.example.pbljava.dao.DAO;
import com.example.pbljava.excepctions.EmprestimoException;
import com.example.pbljava.excepctions.LivroException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class confirmarE {

    @FXML
    private Button confirmaemprestimo;

    @FXML
    void confirmaemprestimoAction(ActionEvent event) throws Exception {

        System.out.println(DAO.getEmprestimoDAO().findMany().size());

        ControladorDados controla = ControladorDados.getInstancia();
        System.out.println(controla.getUsuario());
        Emprestimo emprestimo= new Emprestimo(controla.getUsuario(),controla.getLivro());

    }

}
