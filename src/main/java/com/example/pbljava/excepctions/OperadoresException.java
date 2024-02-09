package com.example.pbljava.excepctions;

import com.example.pbljava.Model.Operadores;

/**
 * Classe que gerará mensagens de exceções para os operadores
 */
public class OperadoresException extends Exception{

    /**
     * Mensagem estatica relacionada a falha na criação de um Operadorr, na listaDAO
     */
    public static final String CREATE = "Não foi possível criar o Operador";

    /**
     * mensagem relacionada a falha no delete de um Operador na listaDAO
     */
    public static final String DELETE = "Não foi possível deletar o Operador";

    /**
     * mensagem estatica relacionada a falha na procura por um operador, na listaDAO
     */
    public static final String FIND ="Não foi possível encontrar o Operador";

    /**
     * Mensagem relacioanda a falha na atualizao de um operador, listaDAO
     */
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";


    /**
     * objeto operador que sofrerá o ''CRUD falho''
     */
    private Operadores operadores;

    /**
     * id do objeto que terá falha no CRUD
     */
    private int id;

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param operadores objeto que ocorreu a exceção
     */
    public OperadoresException(String message, Operadores operadores) {
        super(message);
        this.operadores = operadores;
    }

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param id id do objeto que ocrreu a exceção
     */
    public OperadoresException(String message, int id) {
        super(message);
        this.id=id;
    }
}
