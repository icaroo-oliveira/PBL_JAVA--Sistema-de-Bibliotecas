package org.biblioteca.Model.Emprestimotest;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.*;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class EmprestimoTest {

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
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
        DAO.getUsuarioDAO().deleteMany();
    }



    @Test
    void realizar_empresitmo() throws Exception {


        emp_1 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,8,1),LocalDate.of(2023,8,8));
        emp_4 = new Emprestimo(gabriel,Os_tres,LocalDate.of(2023,11,1),LocalDate.of(2023,11,8));
        emp_3 =new Emprestimo(junior,sidarta,LocalDate.of(2023,8,3),LocalDate.of(2023,8,10));

        emp_5 = new Emprestimo(gabriel,fome,LocalDate.of(2023,8,3),LocalDate.of(2023,8,10));


        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        emp_3.Realizar_empresitmo(emp_3.getData_emprestimo());
        emp_4.Realizar_empresitmo(emp_4.getData_emprestimo());

        //esse último empréstimo não pode ser feito, por que o livro ja ta ocupado, sendo assim tem 4 emprestimos
        emp_5.Realizar_empresitmo(emp_5.getData_emprestimo());

        assertEquals(4,DAO.getEmprestimoDAO().findMany().size());

    }

    @Test
    void realizando_muitos_emprestimos_uma_pessoa() throws LivroException,EmprestimoException{
        //limite de emprestimos ativos simultaneamente = 3
        try{
            Emprestimo emp_11 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
            Emprestimo emp_22 = new Emprestimo(felipe,fome,LocalDate.of(2023,10,2),LocalDate.of(2023,10,9));
            Emprestimo emp_44 = new Emprestimo(felipe,Os_tres,LocalDate.of(2023,10,5),LocalDate.of(2023,10,11));
            Emprestimo emp_33 = new Emprestimo(felipe,sidarta,LocalDate.of(2023,10,5),LocalDate.of(2023,10,11));

        }catch (EmprestimoException e){
            System.out.println(e.getMessage());
            assertEquals(EmprestimoException.CREATE,e.getMessage());
        }
    }

    @Test
    void tentando_fazer_emprestimo_de_livro_ocupado() throws Exception{
        Emprestimo emp_111 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_111.Realizar_empresitmo(emp_111.getData_emprestimo());

        //Junior e Gabriel tenta fazer empréstimo desse livro, e cai na fila de reserva
        Emprestimo emp_112 = new Emprestimo(junior,demian,LocalDate.of(2023,10,2),LocalDate.of(2023,10,9));
        Emprestimo emp_113 = new Emprestimo(gabriel,demian,LocalDate.of(2023,10,3),LocalDate.of(2023,10,10));
        emp_112.Realizar_empresitmo(emp_112.getData_emprestimo());
        emp_113.Realizar_empresitmo(emp_113.getData_emprestimo());

        assertEquals(2,demian.getFila().size());
    }

    @Test
    void failFazendoemprestimo() throws Exception{

        try{
            Emprestimo emp_111 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
            emp_111.Realizar_empresitmo(emp_111.getData_emprestimo());
            //Devolvendo muito tarde...
            emp_111.Devolucao(LocalDate.of(2023,10,30));
            //tentadno fazer novo emprestimo, enquanto multado
            Emprestimo emp_222 = new Emprestimo(felipe,Os_tres,LocalDate.of(2023,11,4),LocalDate.of(2023,11,11));
            emp_111.Realizar_empresitmo(emp_222.getData_emprestimo());
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.CREATE,e.getMessage());
        }


    }

    @Test
    void calcula_multa() throws Exception {

        emp_1 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        //calculando multa, o calculo é feito com a data de devolucao e a data prevista para devolução, se entregar antes ou na data, sem multas
        emp_1.Calcula_multa(LocalDate.of(2023,10,5));
        //não foi multado, status igual true
        assertTrue(emp_1.getUsuario().getStatus());

    }

    @Test
    void calcula_multa_multado() throws Exception {
        //multa se acumulando
        emp_1 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_2 = new Emprestimo(felipe,Os_tres,LocalDate.of(2023,10,5),LocalDate.of(2023,10,12));

        //devolvendo 2 dias depois
        emp_1.Calcula_multa(LocalDate.of(2023,10,10));//4
        //devolvendo 8 dias depois
        emp_2.Calcula_multa(LocalDate.of(2023,10,20));//16

        //MULTA ACUMULADA
        assertEquals(20,emp_2.getUsuario().getMulta());
        //SALVE A DATA DA ULTIMA DEVOLUCAO
        assertEquals(LocalDate.of(2023,10,20),emp_2.getUsuario().getData_multa());

    }

    @Test
    void renovar_emprestimo() throws Exception {
        //Fazendo um emprestimo e renovando, o assert verifica a quantidade de renovacoes, sendo que qnd faz um emprestimo ja começa com o valor 1
        emp_1 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
        emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
        DAO.getEmprestimoDAO().create(emp_1);
        emp_1.Renovar_emprestimo(LocalDate.of(2023,10,7),LocalDate.of(2023,10,7));
        assertEquals(2,emp_1.getQnt_emprestimo());

        //tentando renovar um emprestimo quando já tem algúem na fila esperando...

        emp_2 = new Emprestimo(junior,demian,LocalDate.of(2023,10,12),LocalDate.of(2023,10,19));
        emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
        DAO.getEmprestimoDAO().create(emp_2);
        emp_1.Renovar_emprestimo(LocalDate.of(2023,10,13),LocalDate.of(2023,10,20));
        assertEquals(2,demian.getFila().size());

    }
    @Test
    void failRenovando_emprestimo() throws Exception{
        try{
            //criando emprestimos
            emp_1 = new Emprestimo(felipe,demian,LocalDate.of(2023,10,1),LocalDate.of(2023,10,8));
            emp_1.Realizar_empresitmo(emp_1.getData_emprestimo());
            DAO.getEmprestimoDAO().create(emp_1);

            //criando novo emprestimo
            emp_2 = new Emprestimo(junior,fome,LocalDate.of(2023,10,6),LocalDate.of(2023,10,13));
            emp_2.Realizar_empresitmo(emp_2.getData_emprestimo());
            DAO.getEmprestimoDAO().create(emp_2);

            //devolvendo o livro muito tarde...
            emp_1.Devolucao(LocalDate.of(2023,10,11));

            //tentando renovar o segundo emprestimo enquanto ta multado...
            emp_2.Renovar_emprestimo(LocalDate.of(2023,10,12),LocalDate.of(2023,10,19));

        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.UPDATE,e.getMessage());
        }
    }

    @Test
    void verificando_data_emprestimo() {
    }
}