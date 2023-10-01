package org.biblioteca.dao.Usuario;

import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.UsuarioException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDAOListaTest {
    Usuario samanta;
    Usuario junior;
    Usuario joao;

    @BeforeEach
    void setUp() {
        samanta = DAO.getUsuarioDAO().create(new Usuario("Samantha","feira 6","1",0));
        junior = DAO.getUsuarioDAO().create(new Usuario("Junior","feira 10","2",0));
        joao = DAO.getUsuarioDAO().create(new Usuario("Joao","feira 4","1",0));
    }

    @AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
    }

    @Test
    void create() {
        Usuario esperado = new Usuario("m", "F", "3",5);
        Usuario atual = DAO.getUsuarioDAO().create(new Usuario("m", "F", "3",3));
        System.out.println(atual);
        System.out.println(esperado);

        assertEquals(esperado,atual);



    }

    @Test
    void delete() throws Exception {
        DAO.getUsuarioDAO().delete(junior);

        int tamanho_esperado = 2;
        assertEquals(tamanho_esperado, DAO.getUsuarioDAO().findMany().size());
    }

    @Test
    void failDelete() throws Exception{
        try {
            DAO.getUsuarioDAO().delete(new Usuario("rebeka", "bairro sim", "000001",0));
            fail("Uma exceção deveria ser gerada!!");
        } catch (UsuarioException e) {
            assertEquals(UsuarioException.DELETE, e.getMessage());
        }
    }
    @Test
    void deleteMany() {
        DAO.getUsuarioDAO().deleteMany();
        assertEquals(0, DAO.getUsuarioDAO().findMany().size());
    }

    @Test
    void update() throws Exception {
        samanta.setNome("Samanta da Silva Pereira");
        samanta.setTelefone("988123123");
        Usuario atual = DAO.getUsuarioDAO().update(samanta);
        assertEquals(samanta,atual);
    }

    @Test
    void failUpdate() throws Exception{
        try {
            Usuario rebeca = new Usuario("rebeka", "bairro sim", "000001",0);
            DAO.getUsuarioDAO().update(rebeca);

        } catch (UsuarioException e) {
            assertEquals(UsuarioException.UPDATE, e.getMessage());
        }


    }


    @Test
    void findMany() {
        assertEquals(3,DAO.getUsuarioDAO().findMany().size());
    }


    @Test
    void findById() throws Exception{
        Usuario esperado = new Usuario("Samantha","feira 6","1",0);

        Usuario atual = DAO.getUsuarioDAO().findById(1);
        assertEquals(esperado, atual);

    }

    @Test
    void failfindById() throws Exception{
        try {
            Usuario esperado = new Usuario("Robert","feira 7","1",5);

            DAO.getUsuarioDAO().findById(esperado.getId());

        } catch (UsuarioException e) {
            assertEquals(UsuarioException.FIND, e.getMessage());
        }

    }
}