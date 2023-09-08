package dao.Emprestimo;

import dao.DAO;
import Model.Emprestimo;

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
    public void delete(Emprestimo obj) {
        this.lista.remove(obj);

    }

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();


    }

    @Override
    public Emprestimo update(Emprestimo obj,Emprestimo obj1) {
        int index = this.lista.indexOf(obj);
        this.lista.set(index,obj1);
        return obj1;
    }

    @Override
    public List<Emprestimo> findMany() {
        return this.lista;
    }

    @Override
    public Emprestimo findById(int id) {
        for(Emprestimo emprestimo:this.lista){
            if(emprestimo.getId_emprestimo()==id){
                return emprestimo;
            }
        }
        return null;
    }
}



