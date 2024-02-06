package com.example.pbljava.dao.Usuario;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.CRUD;

import java.util.List;

/**
 * interface pra o UsuarioDAOlista ela herda da interface CRUD um tipo de classe generica e a exceção
 */
public interface UsuarioDAO extends CRUD<Usuario,Exception> {

    public List<Emprestimo> getUsuarioEmprestimos(int id) throws Exception;
}
