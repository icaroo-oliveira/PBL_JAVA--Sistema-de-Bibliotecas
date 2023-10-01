package org.biblioteca.dao.Operadores;

import org.biblioteca.Model.Operadores;
import org.biblioteca.excepctions.OperadoresException;

import java.util.ArrayList;
import java.util.List;

public class OperadoresDAOLista implements OperadoresDAO{


    private List<Operadores> lista;

    private int proximoID;

    public OperadoresDAOLista() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;
    }

    private int getProximoID(){
        return this.proximoID++;//se ligar nisso (?)
    }

    @Override
    public Operadores create(Operadores obj) {
        obj.setId(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    @Override
    public void delete(Operadores obj) throws OperadoresException {
        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new OperadoresException(OperadoresException.DELETE,obj);
        }

    }

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();

    }

    @Override
    public Operadores update(Operadores obj) throws OperadoresException {
        int index = this.lista.indexOf(obj);
        if(index ==-1) {
            throw new OperadoresException(OperadoresException.UPDATE, obj);
        }else{
            this.lista.set(index,obj);
            return obj;
        }

    }

    @Override
    public List<Operadores> findMany() {
        return this.lista;
    }

    @Override
    public Operadores findById(int id) throws OperadoresException{
        for(Operadores operador:this.lista){
            if(operador.getId()==id){
                return operador;
            }
        }
        throw new OperadoresException(OperadoresException.FIND,id);

    }
}
