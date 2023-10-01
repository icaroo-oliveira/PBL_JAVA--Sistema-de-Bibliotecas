package dao;

import dao.Emprestimo.EmprestimoDAO;
import dao.Emprestimo.EmprestimoDAOLista;
import dao.Livro.LivroDAO;
import dao.Livro.LivroDAOLista;
import dao.Operadores.OperadoresDAO;
import dao.Operadores.OperadoresDAOLista;
import dao.Usuario.UsuarioDAO;
import dao.Usuario.UsuarioDAOLista;

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
