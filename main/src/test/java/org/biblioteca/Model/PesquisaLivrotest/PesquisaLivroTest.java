package org.biblioteca.Model.PesquisaLivrotest;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Sistema;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PesquisaLivroTest {

    Livro o_principe,o_cortico,equacoes_diferencais,tudo_sobre_poo;
    Livro fome;
    Livro demian;

    Livro Os_tres;
    Livro sidarta;

    Livro edo_2,edo_3;


    @BeforeEach
    void setUp() {


        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123,"Romance"));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124,"Romance"));
        Os_tres = DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1,"Infanto-Juvenil"));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6,"Romance"));

        o_principe = DAO.getLivroDAO().create(new Livro("O príncipe","Niccolò Machiavelli",2,"Não ficção"));
        o_cortico = DAO.getLivroDAO().create(new Livro("O cortiço","Aluísio Azevedo",3,"Romance"));
        equacoes_diferencais = DAO.getLivroDAO().create(new Livro("Equações Diferenciais","Dennis G.Zill",4,"Matemática"));
        tudo_sobre_poo =DAO.getLivroDAO().create(new Livro("Tudo sobre programação orientadas a objetos","Rafael Tosta",100,"Ciência da Computação"));
        edo_2 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));
        edo_3 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));

    }

    @AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();
    }

    @Test
    void pesquisarPorAutor() {

        assertEquals(2,Sistema.getPesquisa().pesquisarPorAutor("Herman Hesse").size());
        assertEquals(1,Sistema.getPesquisa().pesquisarPorAutor("Knut Hamsun").size());
    }

    @Test
    void pesquisarPorCategoria() {

        assertEquals(4,Sistema.getPesquisa().pesquisarPorCategoria("Romance").size());

        assertEquals(3,Sistema.getPesquisa().pesquisarPorCategoria("Matemática").size());
    }

    @Test
    void pesquisarPorTitulo() {
        assertEquals(3,Sistema.getPesquisa().pesquisarPorTitulo("equações diferenciais").size()); //exceção de ponteiro nulo<< qnd escreve :equacoes diferencias

    }

    @Test
    void pesquisarPorISBN() {
        assertEquals(3,Sistema.getPesquisa().pesquisarPorISBN(4).size()); //exceção de ponteiro nulo<< qnd escreve :equacoes diferencias

    }

    @Test
    void topLivros() throws Exception {

        demian.setPopularity();
        demian.setPopularity();
        demian.setPopularity();
        demian.setPopularity();

        DAO.getLivroDAO().update(demian);
        sidarta.setPopularity();
        sidarta.setPopularity();
        sidarta.setPopularity();

        DAO.getLivroDAO().update(sidarta);

        tudo_sobre_poo.setPopularity();
        tudo_sobre_poo.setPopularity();

        DAO.getLivroDAO().update(tudo_sobre_poo);


        List<Livro> livros = Sistema.getPesquisa().TopLivros();
        assertEquals(demian,livros.get(0));
        assertEquals(sidarta,livros.get(1));
        assertEquals(tudo_sobre_poo,livros.get(2));


    }
}