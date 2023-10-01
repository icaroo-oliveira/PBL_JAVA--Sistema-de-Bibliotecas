package org.biblioteca.Model;

public class Pessoa {
    private String nome;
    private String endereco;
    //private String num_identifica;
    private String telefone;

    public Pessoa(String nome,String endereco,String telefone,int id) {
        this.nome = nome;
        this.endereco=endereco;
        //this.num_identifica=num_identifica;
        this.telefone=telefone;
        this.id=id;
    }

    private int id;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /*public String getNum_identifica() {
        return num_identifica;
    }*/

    /*public void setNum_identifica(String num_identifica) {
        this.num_identifica = num_identifica;
    }*/

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
