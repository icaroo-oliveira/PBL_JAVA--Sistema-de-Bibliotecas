package org.biblioteca.Model.Operadorestest;

import org.biblioteca.Model.Operadores;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * classe de testes para os operadores
 */
class OperadoresTest {

    Operadores samanta,junior,joao,alice;

    Operadores marcelo;


    /**
     * seta todos os operadores que serão usados em alguns testes
     */
    @BeforeEach
    void setUp() {
        samanta = DAO.getOperadoresDAO().create(new Operadores("Samantha","feira 6","1","2342","adm",0));
        junior = DAO.getOperadoresDAO().create(new Operadores("Junior","feira 10","2","234324","bibliotecario",0));
        joao = DAO.getOperadoresDAO().create(new Operadores("Joao","feira 4","1","214124","adm",0));
    }


    /**
     * deleta as listas de operadores
     */
    @AfterEach
    void tearDown() {
        DAO.getOperadoresDAO().deleteMany();
    }

    /**
     * método para checar o cargo de um operador
     */
    @Test
    void getCargo() {
        assertEquals("adm",samanta.getCargo());
        assertEquals("bibliotecario",junior.getCargo());
        assertEquals("adm",joao.getCargo());
    }

    /**
     * método para settar o cargo de um operador
     */
    @Test
    void setCargo() {
        alice = DAO.getOperadoresDAO().create(new Operadores("Alice","feira 10","142","2342",null,0));
        alice.setCargo("bibliotecario");
        assertEquals("bibliotecario",alice.getCargo());

    }

    /**
     * método para settar senha do operador
     */
    @Test
    void get_e_set_Senha_de_acesso() {
        marcelo= DAO.getOperadoresDAO().create(new Operadores("Marcelo","feira 1","1042","2342","adm",0));
        marcelo.setSenha_de_acesso("123marcelo321");
        assertEquals("123marcelo321",marcelo.getSenha_de_acesso());


    }
}