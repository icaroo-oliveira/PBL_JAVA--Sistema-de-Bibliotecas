package org.biblioteca.Model;

import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Queue;

import org.biblioteca.Model.Usuario;


import java.util.LinkedList;

/**
 * Classe Livro, responsável por servir de criação dos livros
 */
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

    public Livro(String titulo, String autor, int ISBN,String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.disponibilidade=true;
        this.popularity=0;
        this.categoria=categoria;
    }

    public Livro(String titulo, String autor, int ISBN,String categoria,int id) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.disponibilidade=true;
        this.popularity=0;
        this.categoria=categoria;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para Reservar um livro que ja está emprestado, ele vai verificar o status do usuario, se o usuario estiver com status negativo é lançada uma esceção
     * @param usuario usuario que quer reservar um livro
     * @param empr data do pedido da reserva
     * @throws Exception Exceção lançada caso o usuario esteja negativado
     */
    public void Reservar_livro(Usuario usuario,LocalDate empr) throws Exception {//se lgiar nessa logica aqui dps tbm

        if(!getDisponibilidade() && usuario.Status1(empr) && usuario.getStatus()){//getFila().isEmpty() //usuario.getsatus foi novo pro teste
            this.fila_pelo_livro.add(usuario);//se ligar nisso...
            DAO.getLivroDAO().update(this);//ISSO É MUITO NOVO<<
        }else{

            throw new LivroException(LivroException.RESERVA,this);
            //lançar outra exceção "nao foi possível reservar o livro..."<<<<<<<<,AMARELO>>>>>>>>>>>>.
        }

    }

    /**
     * Método pra fazer empréstimo do livro, ja dentro do livro mesmo
     * @param usuario usuario que quer fazer emprestimo
     * @param emp_data data de emprestimo
     * @param emp_data_dev data de devolucao
     * @throws EmprestimoException exceção lançada nos casos que não é possíve fazer emprestimo
     * @throws LivroException
     */
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

    public void setPopularity() {
        this.popularity +=1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;


        return Objects.equals(getISBN(), livro.getISBN()) && Objects.equals(getId(), livro.getId()) && Objects.equals(getTitulo(), livro.getTitulo()) && Objects.equals(getAutor(), livro.getAutor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, ISBN, id);
    }
}
