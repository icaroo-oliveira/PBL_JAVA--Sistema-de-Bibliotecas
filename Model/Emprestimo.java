package Model;

import java.util.Date;

public class Emprestimo {
    private Date data_emprestimo;
    private Date data_devolucao;

    private String nome_usuario;

    private String id_emprestimo;

    public void Realizar_emprestimo(){

    }

    public void Registrar_devolucao(){

    }

    public void Reservar_livro(){

    }

    public void Renovar_emprestimo(){

    }
    public void Tamanho_fila(){

    }

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(String id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
}
