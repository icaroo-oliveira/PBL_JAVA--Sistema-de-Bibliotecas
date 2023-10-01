package org.biblioteca.dao.Emprestimo;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.excepctions.EmprestimoException;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public Emprestimo create(Emprestimo obj) {
        obj.setId_emprestimo(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    @Override
    public void delete(Emprestimo obj) throws EmprestimoException {

        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new EmprestimoException(EmprestimoException.DELETE,obj);
        }

    }

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();


    }

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




    @Override
    public List<Emprestimo> findMany() {
        return this.lista;
    }

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



