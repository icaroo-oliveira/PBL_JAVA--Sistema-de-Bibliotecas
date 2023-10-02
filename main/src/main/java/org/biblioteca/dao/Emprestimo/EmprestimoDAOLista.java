package org.biblioteca.dao.Emprestimo;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.excepctions.EmprestimoException;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE dao para o Emprestimo, servirá para separar a lógica de negocio do armazenamento de dados
 */
public class EmprestimoDAOLista implements EmprestimoDAO{

    private List<Emprestimo> lista;

    private int proximoID;


    public EmprestimoDAOLista() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;
    }

    private int getProximoID(){
        return this.proximoID++;
    }

    /**
     * Método para criar um Emprestimo na lista
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Emprestimo create(Emprestimo obj) {
        obj.setId_emprestimo(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    /**
     * Método para deletar um dado objeto
     * @param obj objeto a ser deletado
     * @throws EmprestimoException lança uma exceção caso o objeto não exista
     */
    @Override
    public void delete(Emprestimo obj) throws EmprestimoException {

        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new EmprestimoException(EmprestimoException.DELETE,obj);
        }

    }

    /**
     * método que deleta a lista toda
     */
    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;


    }

    /**
     * método de atualizacao de empréstimo
     * @param obj emprestimo a ser atualizado
     * @return retorna o emprestimo
     * @throws EmprestimoException lança uma esceção caso o emprestimo não exista
     */
    @Override
    public Emprestimo update(Emprestimo obj) throws EmprestimoException {
        //public Emprestimo update(Emprestimo obj,Emprestimo obj1) throws EmprestimoException {


        int index = this.lista.indexOf(obj);
        if (index == -1) {
            throw new EmprestimoException(EmprestimoException.UPDATE, obj);
        } else {
            this.lista.set(index, obj);
            return obj;
        }

    }


    /**
     * método para encontrar todos os empréstimos
     * @return retorna uma lista com todos os empréstimos
     */
    @Override
    public List<Emprestimo> findMany() {
        return this.lista;
    }

    /**
     * método para encontrar um empréstimo pelo ID
     * @param id id do empréstimo a ser encontrado
     * @return retorna o empréstimo de um dado ID
     * @throws EmprestimoException lança uma mensagem de exceção caso o emprestimo não seja encontrado
     */
    @Override
    public Emprestimo findById(int id) throws EmprestimoException {

        for(Emprestimo emprestimo:this.lista){
            if(emprestimo.getId_emprestimo()==id){
                return emprestimo;
            }
        }
        throw new EmprestimoException(EmprestimoException.FIND,id);
    }
}



