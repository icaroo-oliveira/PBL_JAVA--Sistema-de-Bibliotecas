package dao;

import java.util.List;

public interface CRUD<T,E extends Exception> {

    public T create(T obj);

    public void delete(T obj) throws E;

    public void deleteMany();

    public T update(T obj,T obj1) throws E;//EmprestimoException, LivroException??)

    public List<T> findMany();

    public T findById(int id) throws E;

}