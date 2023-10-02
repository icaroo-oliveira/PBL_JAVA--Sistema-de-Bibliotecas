package org.biblioteca.dao;

import java.util.List;

/**
 * interface para o CRUD genérica que será implementada pelos DAOS
 * @param <T> tipo de dado de classe genérico
 * @param <E> tipo de exceção genérica
 */
public interface CRUD<T,E extends Exception> {

    public T create(T obj);

    public void delete(T obj) throws E;

    public void deleteMany();

    public T update(T obj) throws E;//EmprestimoException, LivroException??)
    // public T update(T obj,T obj1) throws E;//EmprestimoException, LivroException??)

    public List<T> findMany();

    public T findById(int id) throws E;

}