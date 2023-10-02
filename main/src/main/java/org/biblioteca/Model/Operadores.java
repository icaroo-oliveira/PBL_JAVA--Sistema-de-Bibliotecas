package org.biblioteca.Model;

import java.util.Objects;

/**
 * Classe responsável pela criação de operadores
 */
public class Operadores extends Pessoa{

    private String cargo;
    private String senha_de_acesso;


    public Operadores(String nome,String endereco,String num_identifica,String telefone, String cargo,int id) {
        super(nome,endereco,telefone,id);
        this.cargo = cargo;

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha_de_acesso() {
        return senha_de_acesso;
    }

    public void setSenha_de_acesso(String senha_de_acesso) {
        this.senha_de_acesso = senha_de_acesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operadores)) return false;
        Operadores operadores = (Operadores) o;
        return Objects.equals(getId(), operadores.getId()) && Objects.equals(getNome(), operadores.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargo, senha_de_acesso);
    }
}
