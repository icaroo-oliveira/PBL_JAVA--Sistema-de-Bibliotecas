package dao;

import dao.Emprestimo.EmprestimoDAO;
import dao.Emprestimo.EmprestimoDAOLista;
public class DAO {

    private static EmprestimoDAO EmprestimoDAO;

    public static EmprestimoDAO getEmprestimoDAO() {
        if (EmprestimoDAO == null) {
            EmprestimoDAO = new EmprestimoDAOLista();
        }
        return EmprestimoDAO;
    }
}
