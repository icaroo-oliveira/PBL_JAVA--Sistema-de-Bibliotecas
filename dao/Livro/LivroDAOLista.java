package dao.Livro;

import Model.Emprestimo;
import Model.Livro;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Livro create(Livro obj) {
        obj.setId(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    @Override
    public void delete(Livro obj) {
        this.lista.remove(obj);

    }
    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();

    }

    @Override
    public Livro update(Livro obj, Livro obj1) {
        int index = this.lista.indexOf(obj);
        this.lista.set(index,obj1);
        return obj1;
    }

    @Override
    public List<Livro> findMany() {
        return this.lista;
    }

    @Override
    public Livro findById(int id) {

        for(Livro livro:this.lista){
            if(livro.getId()==id){
                return livro;
            }
        }
        return null;
    }
}
