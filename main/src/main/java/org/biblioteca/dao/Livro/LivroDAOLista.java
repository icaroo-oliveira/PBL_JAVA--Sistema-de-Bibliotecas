package org.biblioteca.dao.Livro;

import org.biblioteca.Model.Livro;
import org.biblioteca.excepctions.LivroException;

import java.util.ArrayList;
import java.util.List;

/**
 * LASSE dao para os livros, servirá para separar a lógica de negocio do armazenamento de dados
 */
public class LivroDAOLista implements LivroDAO{

    private List<Livro> lista;

    private int proximoID;

    public LivroDAOLista() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;
    }

    private int getProximoID(){
        return this.proximoID++;
    }

    /**
     * Método para criar um livro na lista
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Livro create(Livro obj) {
        obj.setId(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    /**
     * método para deletar um livro da lista
     * @param obj livro a ser deletado
     * @throws LivroException lança uma exceção se esse livro não existir na lista
     */
    @Override
    public void delete(Livro obj) throws LivroException {
        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new LivroException(LivroException.DELETE,obj);
        }

    }
    /**
     * deleta toda a lista de livros
     */
    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;

    }

    /**
     * método para atualizar um livro
     * @param obj livro a ser atualizado
     * @return retorna o livro
     * @throws LivroException lança uma exceção caso o livro não exista
     */
    @Override
    public Livro update(Livro obj) throws LivroException {
        int index = this.lista.indexOf(obj);
        if(index ==-1) {
            throw new LivroException(LivroException.UPDATE, obj);
        }else{
            this.lista.set(index,obj);
            return obj;
        }
    }

    /**
     * Método para encontrar todos os livros
     * @return retorna uma lista com todos os livros
     */
    @Override
    public List<Livro> findMany() {
        return this.lista;
    }

    /**
     * Método para encontrar um livro pelo ID
     * @param id o id do livro procurado
     * @return retorna o livro dado certo id
     * @throws LivroException lanã uma mensagem de exceção caso o livro não seja encontrado
     */
    @Override
    public Livro findById(int id) throws LivroException {

        for(Livro livro:this.lista){
            if(livro.getId()==id){
                return livro;
            }
        }
        throw new LivroException(LivroException.FIND,id);


    }
}
