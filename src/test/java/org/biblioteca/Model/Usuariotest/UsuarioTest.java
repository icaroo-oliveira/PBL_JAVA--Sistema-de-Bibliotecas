package org.biblioteca.Model.Usuariotest;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teses para os usuarios
 */
class UsuarioTest {

    Usuario robson,marcela,janaina,petra;
    Livro fome,demian,Os_tres,sidarta;

    Emprestimo e1,e2,e3,e4;

    /**
     * seta todos os usuarios e livros que serão usados em alguns testes
     */
    @BeforeEach
    void setUp() {


        robson = DAO.getUsuarioDAO().create(new Usuario("Robson","parque ipe","7544453216",0));
        marcela = DAO.getUsuarioDAO().create(new Usuario("Marcela","tomba","7544453566",0));
        janaina = DAO.getUsuarioDAO().create(new Usuario("Janaina","parque ipe","7584453216",0));
        petra = DAO.getUsuarioDAO().create(new Usuario("Petra","feira 10","759553566",0));


        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        Os_tres = DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6));



    }

    /**
     * deleta as listas de usuario, livro e emprestimo
     */
    @AfterEach
    void tearDown() {

        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();


    }

    /**
     * teste onde o usuário sai de um status ''negativo''(false) para um positivo(true)
     * teste onde o usário ta multado, e depois que a multa acaba ele tenta fazer um emprestimo e consegue
     */
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

    /**
     * Teste onde o usuário esta com status true e fica false
     * Realiza emprestimos e dps tenta fazer um terceiro, mas n devolveu os livros e ja passou da data de devolucao
     * @throws Exception exceccao do emprestimo
     */
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


    }

    /**
     * status positivo se mantém positivo
     * faz emprestimos, devolve, e faz um novo
     * @throws Exception excecao do emprestimo
     */
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



    }

}