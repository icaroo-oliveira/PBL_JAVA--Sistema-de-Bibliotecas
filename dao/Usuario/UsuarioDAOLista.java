package dao.Usuario;


import Model.Usuario;
import excepctions.UsuarioException;

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
    public void delete(Usuario obj) throws UsuarioException {
        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new UsuarioException(UsuarioException.DELETE,obj);
        }
    }

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();

    }

    @Override
    public Usuario update(Usuario obj, Usuario obj1) throws UsuarioException{
        int index = this.lista.indexOf(obj);
        if(index ==-1) {
            throw new UsuarioException(UsuarioException.UPDATE, obj);
        }else{
            this.lista.set(index,obj1);
            return obj1;
        }
    }

    @Override
    public List<Usuario> findMany() {
        return this.lista;
    }

    @Override
    public Usuario findById(int id) throws UsuarioException {
        for(Usuario usuario:this.lista){
            if(usuario.getId()==id){
                return usuario;
            }
        }
        throw new UsuarioException(UsuarioException.FIND,id);

    }
}
