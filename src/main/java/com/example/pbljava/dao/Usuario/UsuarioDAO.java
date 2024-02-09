package com.example.pbljava.dao.Usuario;

import com.example.pbljava.Model.Emprestimo;
import com.example.pbljava.Model.Usuario;
import com.example.pbljava.dao.CRUD;

import java.util.List;

/**
 * interface pra o UsuarioDAOlista ela herda da interface CRUD um tipo de classe generica e a exceção
 */
public interface UsuarioDAO extends CRUD<Usuario,Exception> {

    public List<Emprestimo> getUsuarioEmprestimos(int id) throws Exception;
}
