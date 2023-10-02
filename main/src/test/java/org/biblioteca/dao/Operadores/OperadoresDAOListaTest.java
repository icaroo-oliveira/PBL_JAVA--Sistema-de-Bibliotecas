package org.biblioteca.dao.Operadores;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Operadores;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OperadoresDAOListaTest {

    Operadores samanta;
    Operadores junior;
    Operadores joao;


    @BeforeEach
    void setUp() {
        samanta = DAO.getOperadoresDAO().create(new Operadores("Samantha","feira 6","1","2342","adm",0));
        junior = DAO.getOperadoresDAO().create(new Operadores("Junior","feira 10","2","234324","bibliotecario",0));
        joao = DAO.getOperadoresDAO().create(new Operadores("Joao","feira 4","1","214124","adm",0));
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
        Operadores esperado = new Operadores("rafael", "feira x", "323","213213214","adm",3);
        Operadores atual = DAO.getOperadoresDAO().create(new Operadores("rafael", "feira x", "323","213213214","adm",0));

        assertEquals(esperado,atual);
    }

    @Test
    void delete() throws Exception {
        DAO.getOperadoresDAO().delete(junior);

        int tamanho_esperado = 2;

        assertEquals(tamanho_esperado, DAO.getOperadoresDAO().findMany().size());
    }

    @Test
    void deleteMany() {
        DAO.getOperadoresDAO().deleteMany();
        assertEquals(0, DAO.getOperadoresDAO().findMany().size());
    }

    @Test
    void update() throws Exception {
        junior.setTelefone("75-999889976");
        Operadores atual = DAO.getOperadoresDAO().update(junior);
        assertEquals(junior,atual);
    }

    @Test
    void findMany() {
        assertEquals(3,DAO.getOperadoresDAO().findMany().size());
    }

    @Test
    void findById() throws Exception {
        Operadores esperado = new Operadores("Samantha","feira 6","1","8282828","adm",0);

        Operadores atual = DAO.getOperadoresDAO().findById(0);

        assertEquals(esperado, atual);
    }
}