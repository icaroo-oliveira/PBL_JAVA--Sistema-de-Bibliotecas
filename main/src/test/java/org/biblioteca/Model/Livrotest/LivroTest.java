package org.biblioteca.Model.Livrotest;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.LivroException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {
    Usuario junior;
    Usuario felipe;
    Usuario fabio;
    Usuario gabriel;


    Livro fome;
    Livro demian;

    Livro Os_tres;
    Livro sidarta;


    Emprestimo emp_1;
    Emprestimo emp_2;
    Emprestimo emp_3;
    Emprestimo emp_4;

    Emprestimo emp_5;


    @BeforeEach
    void setUp() {
        junior = DAO.getUsuarioDAO().create(new Usuario("Junior Macedo","feira 1","75-99999999",0));
        felipe = DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino","feira 2","75-99999998",0));
        fabio = DAO.getUsuarioDAO().create(new Usuario("Fabio","feira 3","75-99999988",0));
        gabriel = DAO.getUsuarioDAO().create(new Usuario("Gabriel","feira 4","75-99999999",0));

        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        Os_tres = DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6));
    }

    @AfterEach
    void tearDown() {
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getUsuarioDAO().deleteMany();

    }

    @Test
    void reservar_livro() throws Exception {
        demian.setDisponibilidade(false);
        demian.Reservar_livro(fabio, LocalDate.of(2023,10,1));
        demian.Reservar_livro(felipe, LocalDate.of(2023,10,2));
        demian.Reservar_livro(gabriel, LocalDate.of(2023,10,3));

        assertEquals(3,demian.getFila().size());

    }

    @Test
    void failReservar_livro() throws Exception{
        try{
            felipe.setStatus(false);
            demian.setDisponibilidade(false);
            demian.Reservar_livro(felipe,LocalDate.of(2023,10,1));
        }catch (LivroException e){
            assertEquals(LivroException.RESERVA,e.getMessage());
        }

    }

}