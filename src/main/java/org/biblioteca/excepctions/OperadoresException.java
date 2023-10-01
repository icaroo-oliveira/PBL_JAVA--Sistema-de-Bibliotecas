package org.biblioteca.excepctions;


import org.biblioteca.Model.Operadores;

public class OperadoresException extends Exception{

    public static final String CREATE = "Não foi possível criar o Operador";

    public static final String DELETE = "Não foi possível deletar o Operador";

    public static final String FIND ="Não foi possível encontrar o Operador";

    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";


    private Operadores operadores;

    private int id;
    public OperadoresException(String message, Operadores operadores) {
        super(message);
        this.operadores = operadores;
    }

    public OperadoresException(String message, int id) {
        super(message);
        this.id=id;
    }
}
