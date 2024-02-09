package org.biblioteca.dao.Usuario;

import com.example.pbljava.Model.Emprestimo;
import com.example.pbljava.Model.Livro;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.dao.DAO;
import com.example.pbljava.excepctions.UsuarioException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes para o UsuarioDaolista
 */
class UsuarioDAOListaTest {
    Usuario samanta;
    Usuario junior;
    Usuario joao;

    Emprestimo emp_1,emp_2,emp_3,emp_4,emp_5,emp_6;

    /**
     * ''seta'' todos os usuarios antes de cada teste
     */
    @BeforeEach
    void setUp() {
        samanta = DAO.getUsuarioDAO().create(new Usuario("Samantha","feira 6","1",0));
        junior = DAO.getUsuarioDAO().create(new Usuario("Junior","feira 10","2",0));
        joao = DAO.getUsuarioDAO().create(new Usuario("Joao","feira 4","1",0));
    }

    /**
     * deleta as lista de usuario, livro e emprestimo dps de cada teste
     */
    @AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getEmprestimoDAO().deleteMany();
    }

    /**
     * testar o método de criação na lista de usuarios
     */
    @Test
    void create() {
        Usuario esperado = new Usuario("m", "F", "3",3);
        Usuario atual = DAO.getUsuarioDAO().create(new Usuario("m", "F", "3",3));

        assertEquals(esperado,atual);


    }

    /**
     * testa o método de exclusão na lista de usuarios
     * @throws Exception laça exceção se não encontrar o objt a ser excluido
     */
    @Test
    void delete() throws Exception {
        DAO.getUsuarioDAO().delete(junior);

        int tamanho_esperado = 2;
        assertEquals(tamanho_esperado, DAO.getUsuarioDAO().findMany().size());
    }

    /**
     * teste de delete falho
     * @throws Exception exceção lançada se nao encontrar o usuario a ser deletado
     */
    @Test
    void failDelete() throws Exception{
        try {
            Usuario rebeca = new Usuario("rebeka", "bairro sim", "000001",6);

            DAO.getUsuarioDAO().delete(rebeca);

            fail("Uma exceção deveria ser gerada!!");
        } catch (UsuarioException e) {
            assertEquals(UsuarioException.DELETE, e.getMessage());
        }
    }

    /**
     * teste de zerar a lista dos usuarios
     */
    @Test
    void deleteMany() {
        DAO.getUsuarioDAO().deleteMany();
        assertEquals(0, DAO.getUsuarioDAO().findMany().size());
    }

    /**
     * teste para atualizacao de usuario
     * @throws Exception lança exceção se o usuario a ser atualizado não existe
     */
    @Test
    void update() throws Exception {
        samanta.setNome("Samanta da Silva Pereira");
        samanta.setTelefone("988123123");
        Usuario atual = DAO.getUsuarioDAO().update(samanta);
        assertEquals(samanta,atual);
    }

    /**
     * teste para a atualizacao falha
     * @throws Exception o usuario a ser atualizado não existe
     */
    @Test
    void failUpdate() throws Exception{
        try {
            Usuario rebeca = new Usuario("rebeka", "bairro sim", "000001",0);
            DAO.getUsuarioDAO().update(rebeca);

        } catch (UsuarioException e) {
            assertEquals(UsuarioException.UPDATE, e.getMessage());
        }


    }


    /**
     * teste para a lista com todos os usuarios cadastrados
     */
    @Test
    void findMany() {
        assertEquals(3,DAO.getUsuarioDAO().findMany().size());
    }


    /**
     * teste para encontrar o usuario por id
     * @throws Exception lança uma exceção se não encontrar o usuario
     */
    @Test
    void findById() throws Exception{
        Usuario esperado = new Usuario("Samantha","feira 6","1",0);

        Usuario atual = DAO.getUsuarioDAO().findById(0);
        assertEquals(esperado, atual);

    }

    /**
     * teste falho de encontrar o usuario por id
     * @throws Exception lança a exceção que não encontrou
     */
    @Test
    void failfindById() throws Exception{
        try {
            Usuario esperado = new Usuario("Samantha","feira 6","1",0);

            DAO.getUsuarioDAO().findById(esperado.getId());

        } catch (UsuarioException e) {
            System.out.println(e.getMessage());
            assertEquals(UsuarioException.FIND, e.getMessage());
        }

    }

    /**
     * teste para os métodos de conseguir a lista de emprestimos/livros de um usuário
     * @throws Exception exceção do empréstimo
     */
    @Test
    void getUsuarioEmprestimos() throws Exception {

        Usuario feelipe = DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino","feira 2","75-99999998",0));
        Usuario gabriel = DAO.getUsuarioDAO().create(new Usuario("Gabriel","feira 4","75-99999999",0));

        Livro fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123));
        Livro demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124));
        Livro Os_tres = DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1));
        Livro sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6));

        Livro o_cortico = DAO.getLivroDAO().create(new Livro("O cortiço","Aluísio Azevedo",3,"Romance"));
        Livro edo_2 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));


        emp_1 = new Emprestimo(feelipe,demian, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));

        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());

        emp_5 = new Emprestimo(feelipe,edo_2, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_6 = new Emprestimo(feelipe,o_cortico,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));

        emp_5.Realizar_empresitmo(emp_5.getData_emprestimo());
        emp_6.Realizar_empresitmo(emp_6.getData_emprestimo());



        assertEquals(3, DAO.getUsuarioDAO().getUsuarioEmprestimos(3).size());


    }
}