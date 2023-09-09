package dao.Operadores;

import Model.Livro;
import Model.Operadores;

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
    public void delete(Operadores obj) {
        this.lista.remove(obj);

    }

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();

    }

    @Override
    public Operadores update(Operadores obj, Operadores obj1) {
        int index = this.lista.indexOf(obj);
        this.lista.set(index,obj1);
        return obj1;
    }

    @Override
    public List<Operadores> findMany() {
        return this.lista;
    }

    @Override
    public Operadores findById(int id) {
        for(Operadores operador:this.lista){
            if(operador.getId()==id){
                return operador;
            }
        }
        return null;
    }
}
