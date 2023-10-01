import excepctions.EmprestimoException;
import org.junit.jupiter.api.Test;
import Model.*;
import dao.DAO;

import java.time.LocalDate;

class LivroTest {

    @Test

    public void testando() throws Exception {

        DAO.getUsuarioDAO().create(new Usuario("Junior bandidão"));
        DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino"));
        DAO.getUsuarioDAO().create(new Usuario("Fabio"));
        DAO.getUsuarioDAO().create(new Usuario("Gabriel"));

        DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1));
        DAO.getLivroDAO().create(new Livro("Iliada","Homero",6));




        //DAO.getEmprestimoDAO().create(new Emprestimo(DAO.getUsuarioDAO().findById(1),DAO.getLivroDAO().findById(1)));



        //DAO.getEmprestimoDAO().update(DAO.getEmprestimoDAO().findById(1), new Emprestimo(DAO.getUsuarioDAO().findById(1),DAO.getLivroDAO().findById(1)));


        /*List<Emprestimo> lista = DAO.getEmprestimoDAO().findMany();


        for(Emprestimo emprestimo:lista){
            System.out.println(emprestimo.getLivro().getTitulo());
        }*/











        LocalDate dataFicticia = LocalDate.of(2023, 8, 15);
        LocalDate dataFict_dev = LocalDate.of(2023, 8, 22);



        DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(0),dataFicticia,dataFict_dev);

        System.out.println(DAO.getEmprestimoDAO().findById(0).getId_emprestimo());

        try{
            DAO.getEmprestimoDAO().findById(1).Renovar_emprestimo(LocalDate.of(2023,8,21),LocalDate.of(2023,8,28));
        }catch (EmprestimoException e){
            System.out.println(e.getMessage());
            System.out.println(e.getId());
        }


        //System.out.println(DAO.getEmprestimoDAO().findById(0).getId_emprestimo());
        //tentando acessar um elemento que não existe
        /*DAO.getEmprestimoDAO().findById(0).Devolucao(LocalDate.of(2023,8,17));


        DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(0),LocalDate.of(2023,8,18),LocalDate.of(2023,8,25));

        //COLOCAR EXCEÇÃO PARA O CASO DE NAO ENCONTRAR O ID
        DAO.getEmprestimoDAO().findById(1).Devolucao(LocalDate.of(2023,8,20));

        //DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(0),dataFicticia,dataFict_dev);


        DAO.getLivroDAO().findById(1).Fazer_emprestimo(DAO.getUsuarioDAO().findById(1),LocalDate.of(2023,8,23),LocalDate.of(2023,8,30));

        DAO.getLivroDAO().findById(0).Fazer_emprestimo(DAO.getUsuarioDAO().findById(2),LocalDate.of(2023,5,12),LocalDate.of(2023,5,30));


        List<Emprestimo> lista = DAO.getEmprestimoDAO().findMany();

        for(Emprestimo emp:lista){
            System.out.println(emp.getLivro().getTitulo());
        }

        List<Livro> topers = Sistema.getPesquisa().TopLivros();

        for(Livro emp:topers){
            System.out.println(emp.getPopularity());
        }*/




        /*for(Livro livro:Sistema.getPesquisa().pesquisarPorAutor("Homero")){
            System.out.println(livro.getTitulo());

        }*/
       /* List<Emprestimo> lista = DAO.getEmprestimoDAO().findMany();

        System.out.println(DAO.getEmprestimoDAO().findById(0).getQnt_emprestimo());

        DAO.getEmprestimoDAO().findById(0).Renovar_emprestimo();

        System.out.println(DAO.getEmprestimoDAO().findById(0).getQnt_emprestimo());

        DAO.getLivroDAO().findById(2).Fazer_emprestimo(DAO.getUsuarioDAO().findById(1));

        DAO.getEmprestimoDAO().findById(0).Renovar_emprestimo();

        System.out.println(DAO.getEmprestimoDAO().findById(0).getQnt_emprestimo());




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