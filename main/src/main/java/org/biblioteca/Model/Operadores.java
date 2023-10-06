package org.biblioteca.Model;

import java.util.Objects;

/**
 * Classe responsável pela criação de operadores
 */
public class Operadores extends Pessoa{

    /**
     * cargo do operador, adm ou bibliotecario
     */
    private String cargo;
    /**
     * senha de acesso do operador
     */
    private String senha_de_acesso;


    /**
     * construtor para o operador
     * @param nome nome do operador
     * @param endereco endereco do operador
     * @param num_identifica numero identificao do operador
     * @param telefone telefone do operador
     * @param cargo cargo do operador
     * @param id id do operador
     */
    public Operadores(String nome,String endereco,String num_identifica,String telefone, String cargo,int id) {
        super(nome,endereco,telefone,id);
        this.cargo = cargo;

    }

    /**
     * getter para o cargo do operador
     * @return retorna o cargo do operador
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * setter para o cargo do operador
     * @param cargo cargo do operador
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * getter para a senha de acesso
     * @return retorna a senha de acesso daquele operador
     */
    public String getSenha_de_acesso() {
        return senha_de_acesso;
    }

    /**
     * setter para senha de acesso
     * @param senha_de_acesso senha de acesso do operador
     */
    public void setSenha_de_acesso(String senha_de_acesso) {
        this.senha_de_acesso = senha_de_acesso;
    }

    /**
     * método equals, para saber o mesmo operador
     * @param o objeto generico para comparacao
     * @return retorna a comparacao se é igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operadores)) return false;
        Operadores operadores = (Operadores) o;
        return Objects.equals(getId(), operadores.getId()) && Objects.equals(getNome(), operadores.getNome());
    }

    /**
     * hash code para o oeprador
     * @return numero hash do operador
     */
    @Override
    public int hashCode() {
        return Objects.hash(cargo, senha_de_acesso);
    }
}
