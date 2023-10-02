package org.biblioteca.Model.Usuariotest;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class UsuarioTest {

    Usuario robson,marcela,janaina,petra;
    Livro fome,demian,Os_tres,sidarta;

    Emprestimo e1,e2,e3,e4;
    @BeforeEach
    void setUp() {
        robson = new Usuario("Robson","parque ipe","7544453216",0);
        marcela= new Usuario("Marcela","tomba","7544453566",0);
        janaina = new Usuario("Janaina","parque ipe","7584453216",0);
        petra= new Usuario("Petra","feira 10","759553566",0);

        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        Os_tres = DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6));



    }

    @AfterEach
    void tearDown() {

        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();


    }

    @Test
    void status1() {//TESTE USUARIO STATUS NEGATIVO ---> FICANDO POSITIVO
        //usuario com status negativo:
        robson.setStatus(false);


        //ele ta multado em 20 dias, e data de contagem da multa foi a ultima data de devolucao, isso é 28/09/2023 como abaixo:
        robson.setMulta(20);
        robson.setData_multa(LocalDate.of(2023,9,28));

        //Passa-se dias e ele tenta fazer novo emprestimo, para isso é feito uma checagem com o método Status1() da classe Usuário
        boolean status_value = robson.Status1(LocalDate.of(2023,10,20));// passou-se muito mais de 20 dias aqui<<
        assertTrue(status_value);
        assertEquals(0,robson.getMulta());
    }
    @Test
    void status_2() throws Exception {//TESTE USUARIO COM STATUS POSITIVO --> FICANDO NEGATIVO

        //Realizando dois emprestimos, tudo tranquilo
        e1 = new Emprestimo(robson,demian,LocalDate.of(2023,9,15),LocalDate.of(2023,9,22));
        e2 = new Emprestimo(robson,sidarta,LocalDate.of(2023,9,18),LocalDate.of(2023,9,25));
        e1.Realizar_empresitmo(e1.getData_emprestimo());
        e2.Realizar_empresitmo(e2.getData_emprestimo());

        //é chamado o método Status1(provavelmente ele tentou fazer outro emprestimo"), no dia 02/10/2023
        boolean value_status = robson.Status1(LocalDate.of(2023,10,2));
        //Como ele não devolveu os dois livros o status ficou negativo
        assertFalse(value_status);

        //assertEquals(2,robson.getHistorico_livro().size());

    }

    @Test
    void status_2_dando_certo() throws Exception {//TESTE USUARIO COM STATUS POSITIVO --> Mantendo-se positivo

        //Realizando dois emprestimos, tudo tranquilo
        e1 = new Emprestimo(robson,demian,LocalDate.of(2023,9,15),LocalDate.of(2023,9,22));
        e2 = new Emprestimo(robson,sidarta,LocalDate.of(2023,9,18),LocalDate.of(2023,9,25));
        e1.Realizar_empresitmo(e1.getData_emprestimo());
        e2.Realizar_empresitmo(e2.getData_emprestimo());

        //Agora ele vai devolver um dos livros e fazer o emprestimo de um novo
        e1.Devolucao(LocalDate.of(2023,9,21));

        //é chamado o método Status1(provavelmente ele tentou fazer outro emprestimo"), no dia 22/09/2023
        boolean value_status = robson.Status1(LocalDate.of(2023,9,22));

        //ele devolveu um dos livros, estava sem multa, e o outro ainda tinha tempo para devolver
        assertTrue(value_status);

        //assertEquals(2,robson.getHistorico_livro().size());

    }

    @Test
    void bloqueando() {
    }
}