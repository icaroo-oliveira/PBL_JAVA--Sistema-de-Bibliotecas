package org.biblioteca.dao.Usuario;

import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioDAOListaTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Usuario marcela = DAO.getUsuarioDAO().create(new Usuario("marcela","feira de santana","123","123456789"));
        Usuario rebeka = DAO.getUsuarioDAO().create(new Usuario("rebeka","feira de santana","1223","987654321"));
        Usuario jonata = DAO.getUsuarioDAO().create(new Usuario("jonata","feira de santana","321","0000000000"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
    }

    @org.junit.jupiter.api.Test
    void create() {
        Usuario esperado = new Usuario("maikon","coite","000","75-99999999");
        Usuario atual = DAO.getUsuarioDAO().create(new Usuario("maikon","coite","000","75-99999999"));
        assertEquals(esperado,atual);

    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void deleteMany() {
    }

    @org.junit.jupiter.api.Test
    void update() {
    }

    @org.junit.jupiter.api.Test
    void findMany() {
    }

    @org.junit.jupiter.api.Test
    void findById() {
    }
}