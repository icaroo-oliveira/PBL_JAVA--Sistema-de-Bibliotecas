package org.biblioteca.Model;

import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;

import java.time.LocalDate;
import java.util.Queue;

import java.util.LinkedList;


public class Livro {
    private String titulo;
    private String autor;
    private int ISBN;
    private LocalDate ano_publicacao;
    private String categoria;
    private String localizao;
    private boolean disponibilidade;
    private Queue<Usuario> fila_pelo_livro = new LinkedList<>();
    private int id;

    private int popularity;
    private Emprestimo emprestimo;

    //creio que nao tenha necessidade ed um campo para usuario atual, por conta do emprestimo, mas... nunca se sabe, ok.


    public Livro(String titulo, String autor, int ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.disponibilidade=true;
        this.popularity=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void Reservar_livro(Usuario usuario,LocalDate empr) throws LivroException {//se lgiar nessa logica aqui dps tbm

        if(!getDisponibilidade() && usuario.Status1(empr)){//getFila().isEmpty()
            this.fila_pelo_livro.add(usuario);//se ligar nisso...
        }else{

            throw new LivroException(LivroException.RESERVA,this);
            //lançar outra exceção "nao foi possível reservar o livro..."<<<<<<<<,AMARELO>>>>>>>>>>>>.
        }

    }


    public void Fazer_emprestimo(Usuario usuario,LocalDate emp_data,LocalDate emp_data_dev) throws EmprestimoException, LivroException {
        this.emprestimo = new Emprestimo(usuario,this,emp_data,emp_data_dev);//MODIFIQUEI ISSO, ANTES ERA :this.emprestimo = new Emprestimo(usuario,this)
        this.popularity++;
        //Emprestimo emprestimo = new Emprestimo(usuario,this);

    }

    public void Editando_livro(){//fazer isso dps<<<

    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }



    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(LocalDate ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocalizao() {
        return localizao;
    }

    public void setLocalizao(String localizao) {
        this.localizao = localizao;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Queue<Usuario> getFila() {//aqui tbm e queue
        return fila_pelo_livro;
    }

}
