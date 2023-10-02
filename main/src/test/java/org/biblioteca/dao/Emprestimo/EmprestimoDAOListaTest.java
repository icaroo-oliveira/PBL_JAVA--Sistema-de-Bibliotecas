package org.biblioteca.dao.Emprestimo;

import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoDAOListaTest {

    Usuario samanta;
    Usuario junior;
    Usuario joao;

    Livro demian,sidarta,fome;

    Emprestimo emp1,emp2,emp3;

    @BeforeEach
    void setUp() throws LivroException, EmprestimoException {

        samanta = DAO.getUsuarioDAO().create(new Usuario("Samantha","feira 6","1",0));
        junior = DAO.getUsuarioDAO().create(new Usuario("Junior","feira 10","2",0));
        joao = DAO.getUsuarioDAO().create(new Usuario("Joao","feira 4","1",0));

        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6));

        emp1 = DAO.getEmprestimoDAO().create(new Emprestimo(junior,fome, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8)));
        emp2 = DAO.getEmprestimoDAO().create(new Emprestimo(samanta,demian, LocalDate.of(2023,10,2),LocalDate.of(2023,10,9)));
        emp3 = DAO.getEmprestimoDAO().create(new Emprestimo(joao,sidarta, LocalDate.of(2023,10,3),LocalDate.of(2023,10,10)));


    }

    @AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();
    }

    @Test
    void create() throws LivroException, EmprestimoException {
        Emprestimo esperado = new Emprestimo(junior, fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        Emprestimo atual = DAO.getEmprestimoDAO().create(new Emprestimo(junior,fome, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8)));

        assertEquals(esperado,atual);
    }

    @Test
    void delete() throws Exception{
        DAO.getEmprestimoDAO().delete(emp1);

        int tamanho_esperado = 2;
        assertEquals(tamanho_esperado, DAO.getEmprestimoDAO().findMany().size());
    }

    @Test
    void deleteMany() {
        DAO.getEmprestimoDAO().deleteMany();
        assertEquals(0, DAO.getEmprestimoDAO().findMany().size());
    }

    @Test
    void update() throws Exception{
        emp2.setData_emprestimo(LocalDate.of(2023,12,30));
        Emprestimo atual = DAO.getEmprestimoDAO().update(emp2);
        assertEquals(emp2,atual);
    }

    @Test
    void findMany() {
        assertEquals(3,DAO.getEmprestimoDAO().findMany().size());
    }

    @Test
    void findById() throws Exception {
        Emprestimo esperado = new Emprestimo(junior, fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));

        Emprestimo atual = DAO.getEmprestimoDAO().findById(0);

        assertEquals(esperado, atual);
    }
}