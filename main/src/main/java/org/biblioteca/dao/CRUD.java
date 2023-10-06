package org.biblioteca.dao;

import java.util.List;

/**
 * interface para o CRUD genérica que será implementada pelos DAOS
 * @param <T> tipo de dado de classe genérico
 * @param <E> tipo de exceção genérica
 */
public interface CRUD<T,E extends Exception> {

    /**
     * Assinatura do método create, criação de dados no dao
     * @param obj T é um parametro do tipo genérico em java, o obj é um objeto a ser criado no DAO
     * @return retorna o objeto
     */
    public T create(T obj);

    /**
     * Assinatura do método delete, exclusão de daos no DAO
     * @param obj T é um parametro do tipo genérico em java, obj é o dado a ser deletado do DAO
     * @throws E E é exceçao generica, esse método pode lançar uma exceçao, que pode ser especificada depois
     */
    public void delete(T obj) throws E;

    /**
     * Assinatura do método para deletar tudo, exclusao de todos os dadaos do DAO
     * Deleta a lista DAO toda
     */
    public void deleteMany();

    /**
     * Assinatura para o método de atualização, atualiza dados no DAO
     * @param obj objeto a ser atualizado no DAO
     * @return retorna esse objeto que foi atualizado no DAO
     * @throws E pode lançar uma exceção, que pode ser especificada depois
     */
    public T update(T obj) throws E;

    /**
     * Assinatura do método para conseguir todos os dados guardados na lista
     * @return retorna a lista com objetos da classe T(generica)
     */
    public List<T> findMany();

    /**
     * Assinatura do método para encontrar um objeto/dado em uma lista/armazenamento
     * @param id id do objeto a ser procurado
     * @return retorna o objeto encontrado se achar
     * @throws E pode lançar uma exceção, se não encontrar o dado. Essa exceção pode ser especificada dps
     */
    public T findById(int id) throws E;

}