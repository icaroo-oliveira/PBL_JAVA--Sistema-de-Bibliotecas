package org.biblioteca.dao.Usuario;


import org.biblioteca.Model.Emprestimo;
import org.biblioteca.Model.Usuario;
import org.biblioteca.dao.DAO;
import org.biblioteca.excepctions.UsuarioException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CLASSE dao para o usário, servirá para separar a lógica de negocio do armazenamento de dados
 */
public class UsuarioDAOLista implements UsuarioDAO {

    /**
     * Lista de Usuarios, vai guardar todos os Usuarios ja adicionados ao sistema, em uma lista
     */
    private List<Usuario> lista;

    /**
     * variável que guardará o próximo ID a ser utilizado, tipo um contador.
     */
    private int proximoID;

    /**
     * Construtor para classe, quando iniciado o singleton, é criado uma nova lista e zerado o id para contagem
     */
    public UsuarioDAOLista() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * método para contagem dos id's
     * @return retorna o proximo id a ser usado
     */
    private int getProximoID(){
        return this.proximoID++;


    }

    /**
     * Método para criar um usuário na lista
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Usuario create(Usuario obj) {
        obj.setId(this.getProximoID());
        this.lista.add(obj);
        return obj;
    }

    /**
     * método para deletar um usuário da lista
     * @param obj usuario a ser deletado
     * @throws UsuarioException lança uma exceção se esse usuario não existir na lista
     */
    @Override
    public void delete(Usuario obj) throws UsuarioException {
        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new UsuarioException(UsuarioException.DELETE,obj);
        }
    }

    /**
     * método para resetar a lista e a contagem de ID
     */
    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;

    }

    /**
     * Método para atualizar um usuario que ja existe
     * @param obj usuario a ser atualizado
     * @return retorna o objeto atualizado
     * @throws UsuarioException lança uma exceção se nao encontrar o usuario
     */
    @Override
    public Usuario update(Usuario obj) throws UsuarioException{
        int index = this.lista.indexOf(obj);
        if(index ==-1) {
            throw new UsuarioException(UsuarioException.UPDATE,obj);

        }else{
            this.lista.set(index,obj);
            return obj;
        }
    }


    /**
     * Método responsavel por mandar todos os usuários cadastrados
     * @return retorna uma lista com todos usuários
     */
    @Override
    public List<Usuario> findMany() {
        return this.lista;
    }

    /**
     * método responsavel por encontrar um usuario a partir do ID
     * @param id id do usuario procurado
     * @return retorna o usuario
     * @throws UsuarioException lança uma exceção se não encontrar o usuário
     */
    @Override
    public Usuario findById(int id) throws UsuarioException {
        for(Usuario usuario:this.lista){
            if(usuario.getId()==id){
                return usuario;
            }
        }
        throw new UsuarioException(UsuarioException.FIND,id);

    }


    /**
     * Método responsável por pegar todos os empréstimos ja feito por uma pessoa
     * @param id o id do usuário que irá pegar o historico
     * @return retorna uma lista com todos emprestimos feito por aquele usuário
     * @throws Exception lança uma exceção se não encontrar o usuário
     */

    public List<Emprestimo> getUsuarioEmprestimos(int id) throws Exception {

        //if(!DAO.getUsuarioDAO().findById(id).getHistorico_livro().isEmpty())
        if(!DAO.getUsuarioDAO().findById(id).getHistorico_livro().isEmpty()){
            //return DAO.getUsuarioDAO().findById(id).getHistorico_livro();
            return DAO.getUsuarioDAO().findById(id).getHistorico_livro();
        }else{
            return Collections.emptyList();
        }

    }
}
