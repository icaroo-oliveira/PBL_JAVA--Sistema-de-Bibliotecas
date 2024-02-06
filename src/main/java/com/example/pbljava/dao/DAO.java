package com.example.pbljava.dao;

import org.biblioteca.dao.Emprestimo.EmprestimoDAO;
import org.biblioteca.dao.Emprestimo.EmprestimoDAOLista;
import org.biblioteca.dao.Livro.LivroDAO;
import org.biblioteca.dao.Livro.LivroDAOLista;
import org.biblioteca.dao.Operadores.OperadoresDAO;
import org.biblioteca.dao.Operadores.OperadoresDAOLista;

import org.biblioteca.dao.Usuario.UsuarioDAO;
import org.biblioteca.dao.Usuario.UsuarioDAOLista;

import java.io.IOException;


/**
 * Classe DAO, aplicando padrão singleton para todos os DAO
 * é usada acessando os atributos estaticos da classe
 */
public class DAO {

    /**
     * atributo da CLASSE DAO
     * atributo estatico do tipo EmprestimoDAO
     * usado para manter uma unica instancia do DAO dos emprestimos
     */
    private static EmprestimoDAO EmprestimoDAO;
    /**
     * atributo da CLASSE DAO
     * atributo estatico do tipo LivroDAO
     * usado para manter uma unica instancia do DAO dos livros
     */
    private static LivroDAO LivroDAO;
    /**
     * atributo da CLASSE DAO
     * atributo estatito do tipo OperadoresDAO
     * usado para manter uma unica instancia do DAO dos Operadores
     */
    private static OperadoresDAO OperadoresDAO;
    /**
     * atributo da CLASSE DAO
     * atributo estatico do tipo UsuarioDAO
     * usado para manter uma unica instancia do DAO dos usuarios
     */
    private static UsuarioDAO UsuarioDAO;

    /**
     * método estatico para aplicar o padrão singleton na classe EmprestimoDAOLista
     * @return retorna o DAO relacionado aos empréstimos
     */
    public static EmprestimoDAO getEmprestimoDAO() {
        if (EmprestimoDAO == null) {
            try {
                EmprestimoDAO = new EmprestimoDAOLista();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return EmprestimoDAO;
    }

    /**
     * método estatico para aplicar o padrão singleton na classe OperadoresDAOLista
     * @return retorna o DAO relacionado aos operadores
     */
    public static OperadoresDAO getOperadoresDAO() {
        if (OperadoresDAO == null) {
            try {
                OperadoresDAO = new OperadoresDAOLista();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return OperadoresDAO;
    }

    /**
     * método estatico para aplicar o padrão singleton na classe LivroDAOLista
     * @return retorna o DAO relacionado aos livros
     */
    public static LivroDAO getLivroDAO() {
        if (LivroDAO == null) {
            try {
                LivroDAO = new LivroDAOLista();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return LivroDAO;
    }

    /**
     * método estatico para aplicar o padrão singleton na classe UsuarioDAOLista
     * @return retorna o DAO relacionado aos usuario
     */
    public static UsuarioDAO getUsuarioDAO() {
        if (UsuarioDAO == null) {
            try {
                UsuarioDAO = new UsuarioDAOLista();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return UsuarioDAO;
    }

}
