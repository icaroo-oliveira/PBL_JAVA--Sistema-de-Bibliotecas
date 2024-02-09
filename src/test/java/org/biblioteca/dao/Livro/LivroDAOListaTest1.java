package org.biblioteca.dao.Livro;

import com.example.pbljava.Model.Emprestimo;
import com.example.pbljava.Model.Livro;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de teste para testar a classe LivroDAOLista
 * que é onde fica armazenado os livros
 */
class LivroDAOListaTest1 {

    Livro o_principe,o_cortico,equacoes_diferencais,tudo_sobre_poo,fome,demian,Os_tres,sidarta,edo_2,edo_3;

    Usuario junior,felipe,fabio,gabriel;

    Emprestimo emp_1,emp_2,emp_3,emp_4,emp_5,emp_6;

    /**
     * método que antes de cada teste, cria instancia das classes usuario e livro, antes disso criando os objetos nas suas
     * Listas DAO correspondente
     */
    @BeforeEach
    void setUp() {

        junior = DAO.getUsuarioDAO().create(new Usuario("Junior Macedo","feira 1","75-99999999",0));
        felipe = DAO.getUsuarioDAO().create(new Usuario("Felipe souza marcelino","feira 2","75-99999998",0));
        fabio = DAO.getUsuarioDAO().create(new Usuario("Fabio","feira 3","75-99999988",0));
        gabriel = DAO.getUsuarioDAO().create(new Usuario("Gabriel","feira 4","75-99999999",0));

        fome = DAO.getLivroDAO().create(new Livro("Fome","Knut Hamsun",123,"Romance"));
        demian = DAO.getLivroDAO().create(new Livro("DEMIAN","Herman Hesse",124,"Romance"));
        Os_tres = DAO.getLivroDAO().create(new Livro("Os três porquinhos","desconhecido",1,"Infanto-Juvenil"));
        sidarta = DAO.getLivroDAO().create(new Livro("Sidarta","Herman Hesse",6,"Romance"));

        o_principe = DAO.getLivroDAO().create(new Livro("O príncipe","Niccolò Machiavelli",2,"Não ficção"));
        o_cortico = DAO.getLivroDAO().create(new Livro("O cortiço","Aluísio Azevedo",3,"Romance"));
        equacoes_diferencais = DAO.getLivroDAO().create(new Livro("Equações Diferenciais","Dennis G.Zill",4,"Matemática"));
        tudo_sobre_poo =DAO.getLivroDAO().create(new Livro("Tudo sobre programação orientadas a objetos","Rafael Tosta",100,"Ciência da Computação"));
        edo_2 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));
        edo_3 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));
    }

    /**
     * Delete todas as listas DAO depois de cada teste
     */
    @AfterEach
    void tearDown() {
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getUsuarioDAO().deleteMany();
        DAO.getOperadoresDAO().deleteMany();
    }

    /**
     * Método para testar o método create do EmprestimoDAOlista
     */
    @Test
    void create() {

        Livro esperado = new Livro("Casa nobre", "Felipe alt", 123,"Aventura",10);

        Livro atual = DAO.getLivroDAO().create(new Livro("Casa nobre", "Felipe alt", 123,"Aventura",0));

        assertEquals(esperado,atual);
    }

    /**
     * Método para testar o método deçete do LivroDAOlista
     * @throws Exception pode lançar uma exceção mediante delete falho, ou seja, nao encontrar o objeto pra deletar
     */
    @Test
    void delete() throws Exception{
        DAO.getLivroDAO().delete(demian);

        int tamanho_esperado = 9;
        assertEquals(tamanho_esperado, DAO.getLivroDAO().findMany().size());
    }

    /**
     * Método para testar deletar da lista completa do EmprestimoDAOlista
     */
    @Test
    void deleteMany() {
        DAO.getLivroDAO().deleteMany();
        assertEquals(0, DAO.getLivroDAO().findMany().size());
    }

    /**
     * método para testar a atualização de objetos na lista do LivroDAOlista
     * @throws Exception pode lançar uma exceção se o objeto a ser atualizado não for encontrado
     */
    @Test
    void update() throws Exception{
        demian.setTitulo("DEMIAN");
        Livro atual = DAO.getLivroDAO().update(demian);
        assertEquals(demian,atual);
    }

    /**
     * Método para testar o retorno da lista do LivroDAOLista, que deveria ser toda lista e seus objetos
     */
    @Test
    void findMany() {

        assertEquals(10,DAO.getLivroDAO().findMany().size());
    }

    /**
     * Método para testar o método findbyid, encontrando o livro pelo o id
     * @throws Exception pode lançar uma exceção se não encontrar o livro
     */
    @Test
    void findById() throws Exception {
        Livro esperado = new Livro("Fome","Knut Hamsun",123,"Romance",0);

        Livro atual = DAO.getLivroDAO().findById(0);
        assertEquals(esperado, atual);
    }

    /**
     * método de teste para pesquisa por autor
     */
    @Test
    void pesquisarPorAutor() {
        assertEquals(2, DAO.getLivroDAO().pesquisarPorAutor("Herman Hesse").size());
        assertEquals(1,DAO.getLivroDAO().pesquisarPorAutor("Knut Hamsun").size());
    }

    /**
     * método de teste para pesquisa por categoria
     */
    @Test
    void pesquisarPorCategoria() {
        assertEquals(4,DAO.getLivroDAO().pesquisarPorCategoria("Romance").size());

        assertEquals(3,DAO.getLivroDAO().pesquisarPorCategoria("Matemática").size());
    }

    /**
     * método de teste para pesquisa por titulo
     */
    @Test
    void pesquisarPorTitulo() {
        assertEquals(3,DAO.getLivroDAO().pesquisarPorTitulo("equações diferenciais").size());
    }

    /**
     * método de teste para pesquisa por isbn
     */
    @Test
    void pesquisarPorISBN() {
        assertEquals(3,DAO.getLivroDAO().pesquisarPorISBN(4).size()); //exceção de ponteiro nulo<< qnd escreve :equacoes diferencias
    }

    /**
     * método de teste que verifica os 3 primeiros mais populares
     * @throws Exception pode lançar excecao no caso do update, se nao existir
     */
    @Test
    void topLivros() throws Exception {
        demian.setPopularity();
        demian.setPopularity();
        demian.setPopularity();
        demian.setPopularity();

        DAO.getLivroDAO().update(demian);
        sidarta.setPopularity();
        sidarta.setPopularity();
        sidarta.setPopularity();

        DAO.getLivroDAO().update(sidarta);

        tudo_sobre_poo.setPopularity();
        tudo_sobre_poo.setPopularity();

        DAO.getLivroDAO().update(tudo_sobre_poo);


        List<Livro> livros = DAO.getLivroDAO().TopLivros();
        assertEquals(demian,livros.get(0));
        assertEquals(sidarta,livros.get(1));
        assertEquals(tudo_sobre_poo,livros.get(2));
    }

    /**
     * método de teste para os livros emprestados
     * @throws Exception excecao do emprestimo
     */
    @Test
    void getLista_emprestados()throws Exception {

        emp_1 = new Emprestimo(felipe,demian, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,8,1),LocalDate.of(2023,8,8));
        emp_4 = new Emprestimo(gabriel,Os_tres,LocalDate.of(2023,11,1),LocalDate.of(2023,11,8));
        emp_3 =new Emprestimo(junior,sidarta,LocalDate.of(2023,8,3),LocalDate.of(2023,8,10));
        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        emp_3.Realizar_empresitmo(emp_3.getData_emprestimo());
        emp_4.Realizar_empresitmo(emp_4.getData_emprestimo());

        assertEquals(4, DAO.getLivroDAO().getLista_emprestados().size());
    }

    /**
     * Teste para o método de pegar somente os livros atrasados
     * @throws Exception excecao do emprestimo
     */
    @Test
    void getLista_atrasados() throws Exception {

        emp_1 = new Emprestimo(felipe,demian, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_4 = new Emprestimo(gabriel,Os_tres,LocalDate.of(2023,10,2),LocalDate.of(2023,10,9));
        emp_3 =new Emprestimo(junior,sidarta,LocalDate.of(2023,10,3),LocalDate.of(2023,10,10));


        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        emp_3.Realizar_empresitmo(emp_3.getData_emprestimo());
        emp_4.Realizar_empresitmo(emp_4.getData_emprestimo());


        assertEquals(4,DAO.getLivroDAO().getLista_atrasados(LocalDate.of(2023,12,25)).size());
    }

    /**
     * Teste para o método de pegar somente os livros reservados
     * @throws Exception excecao do emprestimo
     */
    @Test
    void getLista_reservados() throws Exception {
        emp_1 = new Emprestimo(felipe,demian, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_4 = new Emprestimo(gabriel,Os_tres,LocalDate.of(2023,10,2),LocalDate.of(2023,10,9));
        emp_3 =new Emprestimo(junior,sidarta,LocalDate.of(2023,10,3),LocalDate.of(2023,10,10));
        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        emp_3.Realizar_empresitmo(emp_3.getData_emprestimo());
        emp_4.Realizar_empresitmo(emp_4.getData_emprestimo());

        emp_5 =new Emprestimo(felipe,fome, LocalDate.of(2023,10,3),LocalDate.of(2023,10,10));
        emp_5.Realizar_empresitmo(emp_5.getData_emprestimo());

        emp_6 =new Emprestimo(felipe,sidarta, LocalDate.of(2023,10,3),LocalDate.of(2023,10,10));
        emp_6.Realizar_empresitmo(emp_6.getData_emprestimo());

        assertEquals(2,DAO.getLivroDAO().getLista_reservados().size());
    }
}