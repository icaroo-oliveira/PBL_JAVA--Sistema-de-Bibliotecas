package org.biblioteca.excepctions;

import org.biblioteca.Model.Livro;

/**
 * Classe que gerará mensagens de exceções para os livros
 */
public class LivroException extends Exception{

    /**
     * Mensagem estatica relacionada a falha na criação de um livro, na listaDAO ou no momento criação do livro
     */
    public static final String CREATE = "Não foi possível criar um livro";

    /**
     * Mensagem estatica relacionada a falha reserva
     */
    public static final String RESERVA = "Não foi possível reservar o livro";

    /**
     * mensagem relacionada a falha no delete de um livro na listaDAO
     */
    public static final String DELETE = "Não foi possível deletar o livro";

    /**
     * mensagem estatica relacionada a falha na procura por um livro, na listaDAO
     */
    public static final String FIND ="Não foi possível encontrar esse livro";

    /**
     * Mensagem relacioanda a falha na atualizao de um livro, listaDAO
     */
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";

    /**
     * objeto livro que sofrerá o ''CRUD falho''
     */
    private Livro livro;

    /**
     * id do objeto que terá falha no CRUD
     */
    private int id;

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param livro objeto que ocorreu a exceção
     */
    public LivroException(String message, Livro livro) {
        super(message);
        this.livro = livro;
    }

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param id id do objeto que ocrreu a exceção
     */
    public LivroException(String message, int id) {
        super(message);
        this.id = id;

    }
}
