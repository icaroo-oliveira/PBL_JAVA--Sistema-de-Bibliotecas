package org.biblioteca.excepctions;

import org.biblioteca.Model.Livro;

public class LivroException extends Exception{

    public static final String CREATE = "Não foi possível criar um livro";

    public static final String RESERVA = "Não foi possível reservar o livro";

    public static final String DELETE = "Não foi possível deletar o livro";

    public static final String FIND ="Não foi possível encontrar esse livro";

    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";

    private Livro livro;
    private int id;
    public LivroException(String message, Livro livro) {
        super(message);
        this.livro = livro;
    }

    public LivroException(String message, int id) {
        super(message);
        this.id = id;

    }
}
