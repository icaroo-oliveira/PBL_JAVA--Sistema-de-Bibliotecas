package org.biblioteca.excepctions;

import org.biblioteca.Model.Usuario;

/**
 * classe que gerara mensagens de excecoes para os usuários
 */
public class UsuarioException extends Exception{

    /**
     * Mensagem estatica relacionada a falha na criação de um usuario, na listaDAO
     */
    public static final String CREATE = "Não foi possível criar um Usuario";

    /**
     * mensagem relacionada a falha no delete de um usuario na listaDAO
     */
    public static final String DELETE = "Não foi possível deletar o Usuario";

    /**
     * mensagem estatica relacionada a falha na procura por um usuario, na listaDAO
     */
    public static final String FIND ="Não foi possível encontrar esse Usuario";

    /**
     * Mensagem relacioanda a falha na atualizao de um usuario, listaDAO
     */
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";

    /**
     * objeto usuario que sofrerá o ''CRUD falho''
     */
    private Usuario usuario;

    /**
     * id do objeto que terá falha no CRUD
     */
    private int id;

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param usuario objeto que ocorreu a exceção
     */
    public UsuarioException(String message,Usuario usuario){
        super(message);
        this.usuario=usuario;

    }

    /**
     * construtor para a classe de exceção
     * @param message mensagem a ser passada no momento da exceção
     * @param id id do objeto que ocrreu a exceção
     */
    public UsuarioException(String message, int id) {
        super(message);
        this.id=id;
    }
}
