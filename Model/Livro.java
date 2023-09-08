package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Queue;

public class Livro {
    private String titulo;
    private String autor;
    private int ISBN;
    private LocalDate ano_publicacao;
    private String categoria;
    private String localizao;
    private boolean disponibilidade;
    private Queue<String> fila_pelo_livro;
    private int id;


    private Emprestimo emprestimo;


    public void Reservar_livro(){

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

    public Queue<String> getFila() {
        return fila_pelo_livro;
    }

    public void setFila(Queue<String> fila) {
        this.fila_pelo_livro = fila;
    }
}
