package com.example.pbljava.components;

import com.example.pbljava.Model.Livro;
import com.example.pbljava.Model.Operadores;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.controller.Operadormain;

public class ControladorDados {

    private static ControladorDados instancia = new ControladorDados();
    private Operadormain operadorr ;
    private Livro livro;

    private String t;
    private Operadores operador ;

    public Operadores getOperador() {
        return operador;
    }

    public void setOperador(Operadores operador) {
        this.operador = operador;
    }

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private ControladorDados() { }

    public Operadormain getOperadorr() {
        return operadorr;
    }

    public void setOperadorr(Operadormain operadorr) {
        this.operadorr = operadorr;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro(){
        return this.livro;

    }

    public static ControladorDados getInstancia() {
        return instancia;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getT() {
        return t;
    }
}
