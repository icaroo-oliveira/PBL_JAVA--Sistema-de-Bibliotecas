package com.example.pbljava.excepctions;

import com.example.pbljava.Model.Emprestimo;

/**
 * Classe que gerará mensagens de exceções para o emprestimo
 */
public class EmprestimoException extends Exception{

    /**
     * Mensagem estatica relacionada a falha na criação de um empréstimo, na listaDAO ou no momento de empréstimo
     */
    public static final String CREATE = "Operação de Emprestimo não realizada.";

    /**
     * Mensagem estatica relacionada a falha na criação de um empréstimo, 3 emprestimos
     */
    public static final String CREATE_mais_3 = "Operação de Emprestimo não realizada, 3 Empréstimos ativos";

    /**
     * Mensagem relacioanda a falha na atualizao de um empréstimo, listaDAO ou na renovacao do emprestimo
     */
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";

    /**
     * mensagem relacionada a falha no delete de um empréstimo na listaDAO
     */
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";

    /**
     * mensagem estatica relacionada a falha na procura por um emprestimo, na listaDAO
     */
    public static final String FIND = "Não foi possível encontrar o emprestimo.";


    /**
     * objeto emprestimo que sofrerá o ''CRUD falho''
     */
    private Emprestimo emprestimo;

    /**
     * id do objeto que terá falha no CRUD
     */
    private int id;

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param emprestimo objeto que ocorreu a exceção
     */
    public EmprestimoException(String message,Emprestimo emprestimo) {
        super(message);
        this.emprestimo = emprestimo;
    }

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param id id do objeto que ocrreu a exceção
     */
    public EmprestimoException(String message, int id) {
        super(message);
        this.id=id;
    }
}
