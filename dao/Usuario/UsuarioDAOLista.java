package dao.Usuario;


import Model.Livro;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOLista implements UsuarioDAO {

    private List<Usuario> lista;

    private int proximoID;

    public UsuarioDAOLista() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;
    }

    private int getProximoID(){
        return this.proximoID++;
    }

    @Override
    public Usuario create(Usuario obj) {
        obj.setId(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    @Override
    public void delete(Usuario obj) {
        this.lista.remove(obj);
    }

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();

    }

    @Override
    public Usuario update(Usuario obj, Usuario obj1) {
        int index = this.lista.indexOf(obj);
        this.lista.set(index,obj1);
        return obj1;
    }

    @Override
    public List<Usuario> findMany() {
        return this.lista;
    }

    @Override
    public Usuario findById(int id) {
        for(Usuario usuario:this.lista){
            if(usuario.getId()==id){
                return usuario;
            }
        }
        return null;
    }
}
