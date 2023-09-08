package dao;

import java.util.List;

public interface CRUD<T> {

    public T create(T obj);

    public void delete(T obj);

    public void deleteMany();

    public T update(T obj,T obj1);

    public List<T> findMany();

    public T findById(int id);

}