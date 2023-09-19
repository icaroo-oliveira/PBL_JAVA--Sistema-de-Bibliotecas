import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Model.*;
import dao.DAO;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test

    public void testando(){

        DAO.getUsuarioDAO().create(new Usuario("Junior bandidão"));
        DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino"));
        DAO.getUsuarioDAO().create(new Usuario("Fabio"));
        DAO.getUsuarioDAO().create(new Usuario("Gabriel"));

        DAO.getLivroDAO().create(new Livro("Fome","Knut Hansum",123));
        DAO.getLivroDAO().create(new Livro("DEMIAN","Hermam Hesse",124));
        DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1));
        DAO.getLivroDAO().create(new Livro("Iliada","Homero",6));





        DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(0));
        //DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(0));



        List<Emprestimo> lista = DAO.getEmprestimoDAO().findMany();

        System.out.println(DAO.getEmprestimoDAO().findById(0).getQnt_emprestimo());

        DAO.getEmprestimoDAO().findById(0).Renovar_emprestimo();

        System.out.println(DAO.getEmprestimoDAO().findById(0).getQnt_emprestimo());

        /*

        Queue<Usuario> fila = DAO.getLivroDAO().findById(2).getFila();

        for(Usuario carinha:fila){
            System.out.println(carinha.getNome());
            }*/





        //DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(2));







        /*

        Queue<Usuario> lista = DAO.getLivroDAO().findById(2).getFila();

        for (Usuario elemento : lista) {
            System.out.println(elemento.getNome());
        }
        //System.out.println(DAO.getEmprestimoDAO().findById(0).getData_emprestimo());





        DAO.getEmprestimoDAO().findById(0).Devolucao();


        DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(1));


        for (Usuario elemento : lista) {
            System.out.println(elemento.getNome());
        }

        DAO.getEmprestimoDAO().findById(1).Devolucao();

        DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(2));

        */
//DAO TEM Q TER no momento que vai acrescentar um cara na lista de espera pelo livro, n posso ir na maozuda e fazer o que quero nao, provavelmente.
    }



}