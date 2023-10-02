package org.biblioteca.dao.Livro;

import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroDAOListaTest {

    Livro demian,sidarta,fome;
    @BeforeEach
    void setUp() {

        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123,"null",0));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124,"null",0));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6,"null",0));

    }

    @AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getOperadoresDAO().deleteMany();
    }

    @Test
    void create() {
        Livro esperado = new Livro("casa", "felipe", 123,"aventura",3);

        Livro atual = DAO.getLivroDAO().create(new Livro("casa", "felipe", 123,"aventura",0));

        assertEquals(esperado,atual);
    }

    @Test
    void delete() throws Exception {
        DAO.getLivroDAO().delete(demian);

        int tamanho_esperado = 2;
        assertEquals(tamanho_esperado, DAO.getLivroDAO().findMany().size());
    }

    @Test
    void deleteMany() {
        DAO.getLivroDAO().deleteMany();
        assertEquals(0, DAO.getLivroDAO().findMany().size());
    }

    @Test
    void update() throws Exception {
        demian.setTitulo("DEMIAN");
        Livro atual = DAO.getLivroDAO().update(demian);
        assertEquals(demian,atual);
    }

    @Test
    void findMany() {
        assertEquals(3,DAO.getLivroDAO().findMany().size());
    }

    @Test
    void findById() throws Exception {

        Livro esperado = new Livro("Fome","Knut Hamsun",123,"null",0);

        Livro atual = DAO.getLivroDAO().findById(0);

        assertEquals(esperado, atual);
    }
}