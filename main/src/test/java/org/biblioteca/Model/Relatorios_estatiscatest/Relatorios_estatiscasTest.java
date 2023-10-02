package org.biblioteca.Model.Relatorios_estatiscatest;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Livro;
import org.biblioteca.Model.Sistema;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Relatorios_estatiscasTest {
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

    Livro o_principe,o_cortico,equacoes_diferencais,tudo_sobre_poo,edo_3,edo_2;
    Emprestimo emp_5,emp_6;

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

        o_principe = DAO.getLivroDAO().create(new Livro("O príncipe","Niccolò Machiavelli",2,"Não ficção"));
        o_cortico = DAO.getLivroDAO().create(new Livro("O cortiço","Aluísio Azevedo",3,"Romance"));
        equacoes_diferencais = DAO.getLivroDAO().create(new Livro("Equações Diferenciais","Dennis G.Zill",4,"Matemática"));
        tudo_sobre_poo =DAO.getLivroDAO().create(new Livro("Tudo sobre programação orientadas a objetos","Rafael Tosta",100,"Ciência da Computação"));
        edo_2 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));
        edo_3 = DAO.getLivroDAO().create(new Livro("equações diferenciais","Dennis G.Zill",4,"Matemática"));


    }

    @AfterEach
    void tearDown() {
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getUsuarioDAO().deleteMany();
    }

    @Test
    void getLista_emprestados() throws Exception {
        emp_1 = new Emprestimo(felipe,demian, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,8,1),LocalDate.of(2023,8,8));
        emp_4 = new Emprestimo(gabriel,Os_tres,LocalDate.of(2023,11,1),LocalDate.of(2023,11,8));
        emp_3 =new Emprestimo(junior,sidarta,LocalDate.of(2023,8,3),LocalDate.of(2023,8,10));
        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        emp_3.Realizar_empresitmo(emp_3.getData_emprestimo());
        emp_4.Realizar_empresitmo(emp_4.getData_emprestimo());

        assertEquals(4, Sistema.getRelatorio().getLista_emprestados().size());
    }

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

        //System.out.println(DAO.getEmprestimoDAO().findMany().size());

        assertEquals(4,Sistema.getRelatorio().getLista_atrasados(LocalDate.of(2023,12,25)).size());


    }

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

        assertEquals(2,Sistema.getRelatorio().getLista_reservados().size());
    }

    @Test
    void getUsuarioEmprestimos() throws Exception {

        emp_1 = new Emprestimo(felipe,demian, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_4 = new Emprestimo(gabriel,Os_tres,LocalDate.of(2023,10,2),LocalDate.of(2023,10,9));
        emp_3 =new Emprestimo(junior,sidarta,LocalDate.of(2023,10,3),LocalDate.of(2023,10,10));
        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        emp_3.Realizar_empresitmo(emp_3.getData_emprestimo());
        emp_4.Realizar_empresitmo(emp_4.getData_emprestimo());

        emp_5 = new Emprestimo(felipe,edo_2, LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_6 = new Emprestimo(felipe,o_cortico,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));

        emp_5.Realizar_empresitmo(emp_5.getData_emprestimo());
        emp_6.Realizar_empresitmo(emp_6.getData_emprestimo());

        assertEquals(3,Sistema.getRelatorio().getUsuarioEmprestimos(1).size());

        System.out.println(Sistema.getRelatorio().getUsuarioEmprestimos(1).size());

    }
}