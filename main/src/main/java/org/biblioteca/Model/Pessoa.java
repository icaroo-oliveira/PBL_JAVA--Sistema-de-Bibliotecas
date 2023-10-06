package org.biblioteca.Model;

/**
 * Classe pai de uusuários e operadores, os atributos de nome,endereco etc sao herdados pelas classes filhas
 */
public class Pessoa {
    /**
     * nome da pessoa
     */
    private String nome;
    /**
     * endereco da pessoa
     */
    private String endereco;

    /**
     * telefone da pessoa
     */
    private String telefone;

    /**
     * construtor para pessoa
     * @param nome nome da pessoa
     * @param endereco endereco da pessoa
     * @param telefone telefone da pessoa
     * @param id id da pessoa
     */
    public Pessoa(String nome,String endereco,String telefone,int id) {
        this.nome = nome;
        this.endereco=endereco;
        this.telefone=telefone;
        this.id=id;
    }

    /**
     * id da pessoa
     */
    private int id;

    /**
     * getter para o nome da pessoa
     * @return retorna o nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * setter para o nome da pessoa
     * @param nome nome da pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * getter para o endereco da pessoa
     * @return retorna o endereco da pessoa
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * setter para o endereco da pessoa
     * @param endereco endereco da pessoa
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * getter para o telefone da pessoa
     * @return retorna o telefone da pessoa
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * setter para o telefone da pessoa
     * @param telefone telefone da pessoa
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * getter para o id da pessoa
     * @return o id da pessoa
     */
    public int getId() {
        return id;
    }

    /**
     * setter para o id da pessoa
     * @param id da pessoa
     */
    public void setId(int id) {
        this.id = id;
    }
}
