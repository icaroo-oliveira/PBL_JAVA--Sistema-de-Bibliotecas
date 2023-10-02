package org.biblioteca.excepctions;

import org.biblioteca.Model.Usuario;

/**
 * classe que gerara mensagens de excecoes para os usuários
 */
public class UsuarioException extends Exception{

    public static final String CREATE = "Não foi possível criar um Usuario";

    public static final String DELETE = "Não foi possível deletar o Usuario";

    public static final String FIND ="Não foi possível encontrar esse Usuario";

    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    private Usuario usuario;

    private int id;

    public UsuarioException(String message,Usuario usuario){
        super(message);
        this.usuario=usuario;

    }

    public UsuarioException(String message, int id) {
        super(message);
        this.id=id;
    }
}
