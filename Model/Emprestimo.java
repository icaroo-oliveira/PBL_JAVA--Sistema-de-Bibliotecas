package Model;

import java.util.Date;

public class Emprestimo {
    private Date data_emprestimo;
    private Date data_devolucao;

    private String nome_usuario;

    private int id_emprestimo;

    private int id_usuario;

    private int id;
    
    public void Registrar_devolucao(){

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

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
}
