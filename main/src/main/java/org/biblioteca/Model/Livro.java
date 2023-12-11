package org.biblioteca.Model;

import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.EmprestimoException;
import org.biblioteca.excepctions.LivroException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Classe Livro, responsável por servir de criação dos livros
 */
public class Livro implements Serializable {
    /**
     * titulo do livro
     */
    private String titulo;
    /**
     * autor do livro
     */
    private String autor;
    /**
     * isbn do livro
     */
    private int ISBN;
    /**
     * ano de publicacao do livro
     */
    private LocalDate ano_publicacao;
    /**
     * categoria do livro
     */
    private String categoria;
    /**
     * localizacao do livro
     */
    private String localizao;
    /**
     * disponibilidade do livro
     */
    private boolean disponibilidade;
    /**
     * fila pelo livro
     */
    private Queue<Usuario> fila_pelo_livro = new LinkedList<>();
    /**
     * id do livro
     */
    private int id;
    /**
     * popularidade do livro
     */
    private int popularity;
    /**
     * a que emprestimo aquele livro ta ligado momentaneamente
     */
    private Emprestimo emprestimo;

    /**
     * construtor do livro
     * @param titulo titulo do livro
     * @param autor autor do livro
     * @param ISBN isbn do livro
     */
    public Livro(String titulo, String autor, int ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.disponibilidade=true;
        this.popularity=0;
    }

    /**
     * Construtor do livro
     * @param titulo titulo do livro
     * @param autor autor do livro
     * @param ISBN isbn do livro
     * @param categoria categoria do livro
     */
    public Livro(String titulo, String autor, int ISBN,String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.disponibilidade=true;
        this.popularity=0;
        this.categoria=categoria;
    }

    /**
     * construtor do livro
     * @param titulo titulo do livro
     * @param autor autor do livro
     * @param ISBN isbn do livro
     * @param categoria categoria do livro
     * @param id id do livro
     */
    public Livro(String titulo, String autor, int ISBN,String categoria,int id) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.disponibilidade=true;
        this.popularity=0;
        this.categoria=categoria;
        this.id=id;
    }

    /**
     * getter para o id do livro
     * @return retorna o id do livro
     */
    public int getId() {
        return id;
    }

    /**
     * setter para o id do livro
     * @param id o id do livro
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para Reservar um livro que ja está emprestado, ele vai verificar o status do usuario, se o usuario estiver com status negativo é lançada uma esceção
     * @param usuario usuario que quer reservar um livro
     * @param empr data do pedido da reserva
     * @throws Exception Exceção lançada caso o usuario esteja negativado
     */
    public void Reservar_livro(Usuario usuario,LocalDate empr) throws Exception {

        if(!getDisponibilidade() && usuario.Status1(empr) && usuario.getStatus()){
            this.fila_pelo_livro.add(usuario);
            DAO.getLivroDAO().update(this);
        }else{

            throw new LivroException(LivroException.RESERVA,this);
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
        this.emprestimo = new Emprestimo(usuario,this,emp_data,emp_data_dev);
        this.popularity++;
    }


    /**
     * getter para o emprestimo que aquele livro ta ligado
     * @return retorna o objeto emrpestimo
     */
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    /**
     * setter para o emprestim do livro
     * @param emprestimo empreestimo ligado ao livro naquele momento
     */
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    /**
     * getter para popularidade do livro
     * @return retorna a populraidade do livro
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * setter para popularidade do livro
     */
    public void setPopularity() {
        this.popularity +=1;
    }

    /**
     * getter para o titulo do livro
     * @return o titulo do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * stter para o titulo do livro
     * @param titulo o titulo do livro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * getter para o autor do livro
     * @return o autor do livro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * setter para o autor do livro
     * @param autor autor do livro
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * getter para o isbn
     * @return retorna o isbn do livro
     */
    public int getISBN() {
        return ISBN;
    }

    /**
     * setter para o isbn do livro
     * @param ISBN o isbn do livro
     */
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * getter para o ano de publicacao
     * @return retorna o ano de publicacao
     */
    public LocalDate getAno_publicacao() {
        return ano_publicacao;
    }

    /**
     * setter para o ano de publicacoo
     * @param ano_publicacao ano de publicao do livro
     */
    public void setAno_publicacao(LocalDate ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    /**
     * getter para categoria do livro
     * @return retorna a categoria do livro
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * setter para categoria do livro
     * @param categoria categoria do livro
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * getter para localizacao do livro
     * @return retorna a localizacao do livro
     */
    public String getLocalizao() {
        return localizao;
    }

    /**
     * setter para localziacao do livro
     * @param localizao localizacao do livro
     */
    public void setLocalizao(String localizao) {
        this.localizao = localizao;
    }

    /**
     * getter para disponibilidade do livro
     * @return retorna a disponibilidade do livro
     */
    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    /**
     * setter para disponibilidade do livro
     * @param disponibilidade disponibilidade do livro
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    /**
     * getter para a fila de espera pelo livro
     * @return retorna fila de espera pelo livro
     */
    public Queue<Usuario> getFila() {//aqui tbm e queue
        return fila_pelo_livro;
    }

    /**
     * método equals, o que faz um ''livro ser igual a outro''
     * @param o objeto generico para comparacao
     * @return comparacao de determinado atributos
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;


        return Objects.equals(getISBN(), livro.getISBN()) && Objects.equals(getId(), livro.getId()) && Objects.equals(getTitulo(), livro.getTitulo()) && Objects.equals(getAutor(), livro.getAutor());
    }

    /**
     * método hascode
     * @return um numero hash daquele objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, ISBN, id);
    }
}
