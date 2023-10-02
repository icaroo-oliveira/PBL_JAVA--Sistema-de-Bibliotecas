package org.biblioteca.dao;

import org.biblioteca.dao.Emprestimo.EmprestimoDAO;
import org.biblioteca.dao.Emprestimo.EmprestimoDAOLista;
import org.biblioteca.dao.Livro.LivroDAO;
import org.biblioteca.dao.Livro.LivroDAOLista;
import org.biblioteca.dao.Operadores.OperadoresDAO;
import org.biblioteca.dao.Operadores.OperadoresDAOLista;

import org.biblioteca.dao.Usuario.UsuarioDAO;
import org.biblioteca.dao.Usuario.UsuarioDAOLista;


/**
 * Classe DAO, aplicando padrão singleton para todos os DAO
 */
public class DAO {

    private static EmprestimoDAO EmprestimoDAO;
    private static LivroDAO LivroDAO;
    private static OperadoresDAO OperadoresDAO;
    private static UsuarioDAO UsuarioDAO;

    public static EmprestimoDAO getEmprestimoDAO() {
        if (EmprestimoDAO == null) {
            EmprestimoDAO = new EmprestimoDAOLista();
        }
        return EmprestimoDAO;
    }
    public static OperadoresDAO getOperadoresDAO() {
        if (OperadoresDAO == null) {
            OperadoresDAO = new OperadoresDAOLista();
        }
        return OperadoresDAO;
    }

    public static LivroDAO getLivroDAO() {
        if (LivroDAO == null) {
            LivroDAO = new LivroDAOLista();
        }
        return LivroDAO;
    }

    public static UsuarioDAO getUsuarioDAO() {
        if (UsuarioDAO == null) {
            UsuarioDAO = new UsuarioDAOLista();
        }
        return UsuarioDAO;
    }

}
