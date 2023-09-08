package Model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    @org.junit.jupiter.api.Test
    void calcula_multa() {

        Usuario usuario =new Usuario();
        Livro livro = new Livro();
        Emprestimo emprestimo = new Emprestimo(usuario,livro);

        livro.setTitulo("FOME");
        livro.setAutor("Knut Hamsum");
        livro.setDisponibilidade(true);


        usuario.setNome("joão");
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setData_emprestimo();
        emprestimo.setData_devolucao(15,9,2023);

        //Emprestimo ok=emprestimo.Realizar_empresitmo();
        //System.out.println(ok.getUsuario().getNome());



        //emprestimo.Calcula_multa();
        //assertEquals(usuario.getMulta(),0);*/









    }
}