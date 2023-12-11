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

/**
 * classe teste para a classe EmprestimoDAOLista
 */
class EmprestimoDAOListaTest {

    /**
     * abaixo declarando 3 objetos do tipo usuario
     */
    Usuario samanta;
    Usuario junior;
    Usuario joao;

    /**
     * 3 objetos livros
     */
    Livro demian,sidarta,fome;

    /**
     * 3 objetos emprestimos
     */
    Emprestimo emp1,emp2,emp3;

    /**
     * setUp seta todos os objetos abaixo cada vez que um teste é rodado
     * @throws LivroException pode lançar uma exceção do livro
     * @throws EmprestimoException pode lançar uma exceção de emprestimo
     */
    @BeforeEach
    void setUp() throws LivroException, EmprestimoException {


        /**
         * criação dos objetos usuários e criação deles na lista DAO, é retornado para os objetos declarados anteriormente
         */
        samanta = DAO.getUsuarioDAO().create(new Usuario("Samantha","feira 6","1",0));
        junior = DAO.getUsuarioDAO().create(new Usuario("Junior","feira 10","2",0));
        joao = DAO.getUsuarioDAO().create(new Usuario("Joao","feira 4","1",0));

        /**
         * criação dos livros e criação deles na lista DAO, é retornado para os objetos declarados anteriormente
         */
        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6));

        /**
         * criação dos emprestimos e criação deles na lista DAO, é retornado para os objetos declarados anteriormente
         */
        emp1 = DAO.getEmprestimoDAO().create(new Emprestimo(junior,fome, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8)));
        emp2 = DAO.getEmprestimoDAO().create(new Emprestimo(samanta,demian, LocalDate.of(2023,10,2),LocalDate.of(2023,10,9)));
        emp3 = DAO.getEmprestimoDAO().create(new Emprestimo(joao,sidarta, LocalDate.of(2023,10,3),LocalDate.of(2023,10,10)));


    }

    /**
     * teardown, depois de cada teste unitario apaga as listas do DAO de usuario, livro e emprestimo
     */
    @AfterEach
    void tearDown() {

        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();

    }

    /**
     * Método para testar o método create do EmprestimoDAOlista
     * @throws LivroException pode lançar uma exceção do tipo livro
     * @throws EmprestimoException pode lançar uma exceção do tipo empréstimo
     */
    @Test
    void create() throws LivroException, EmprestimoException {//OLHAR O EQUALS...
        Emprestimo esperado = new Emprestimo(junior, fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8),3);
        Emprestimo atual = DAO.getEmprestimoDAO().create(new Emprestimo(junior,fome, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8)));

        assertEquals(esperado,atual);

    }

    /**
     * Método para testar o método delete do EmprestimoDAOlista
     * @throws Exception pode lançar uma exceção se o delete não for possível
     */
    @Test
    void delete() throws Exception{
        DAO.getEmprestimoDAO().delete(emp1);

        int tamanho_esperado = 2;
        assertEquals(tamanho_esperado, DAO.getEmprestimoDAO().findMany().size());
    }

    /**
     * Método para testar deletar da lista completa do EmprestimoDAOlista
     */
    @Test
    void deleteMany() {
        DAO.getEmprestimoDAO().deleteMany();
        assertEquals(0, DAO.getEmprestimoDAO().findMany().size());
    }

    /**
     * método para testar a atualização de objetos na lista do EmprestimoDAOlista
     * @throws Exception pode lançar uma exceção se o objeto não for encontrado, por exemplo
     */
    @Test
    void update() throws Exception{
        emp2.setData_emprestimo(LocalDate.of(2023,12,30));

        Emprestimo atual = DAO.getEmprestimoDAO().update(emp2);

        assertEquals(emp2,atual);
    }

    /**
     * Método para testar o retorno da lista do EmprestimoDAOLista, que deveria ser toda lista e seus objetos
     */
    @Test
    void findMany() {
        assertEquals(3,DAO.getEmprestimoDAO().findMany().size());
    }

    /**
     * Método para testar o método findbyid, encontrando o emprestimo pelo o id
     * @throws Exception pode lançar uma exceção se não encontrar o emprestimo
     */
    @Test
    void findById() throws Exception {
        Emprestimo esperado = new Emprestimo(junior, fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));

        Emprestimo atual = DAO.getEmprestimoDAO().findById(0);

        assertEquals(esperado, atual);
    }
}