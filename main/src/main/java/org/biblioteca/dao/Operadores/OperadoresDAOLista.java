package org.biblioteca.dao.Operadores;

import org.biblioteca.Model.Operadores;
import org.biblioteca.excepctions.OperadoresException;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE dao para os operados, servirá para separar a lógica de negocio do armazenamento de dados
 */
public class OperadoresDAOLista implements OperadoresDAO{


    /**
     * Lista de Operadores, vai guardar todos os Operadores ja adicionados ao sistema, em uma lista
     */
    private List<Operadores> lista;
    /**
     * variável que guardará o próximo ID a ser utilizado, tipo um contador.
     */
    private int proximoID;

    /**
     * Construtor para classe, quando iniciado o singleton, é criado uma nova lista e zerado o id para contagem
     */
    public OperadoresDAOLista() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * método para contagem dos id's
     * @return retorna o próximo id a ser usado
     */
    private int getProximoID(){
        return this.proximoID++;
    }

    /**
     * Método para criar um operador na lista
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Operadores create(Operadores obj) {
        obj.setId(this.getProximoID());
        lista.add(obj);
        return obj;
    }

    /**
     *  método para deletar um operador da lista
     *  @param obj operador a ser deletado
     *  @throws OperadoresException lança uma exceção se esse operador não existir na lista
     */
    @Override
    public void delete(Operadores obj) throws OperadoresException {
        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new OperadoresException(OperadoresException.DELETE,obj);
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
     * Método para atualizar um operador que ja existe
     * @param obj operador a ser atualizado
     * @return retorna o operador
     * @throws OperadoresException lança uma exceção se esse operador não existir
     */
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

    /**
     * Método para encontrar todos os operadores
     * @return retorna uma lista com todos os operadores
     */
    @Override
    public List<Operadores> findMany() {
        return this.lista;
    }

    /**
     * Método para encontrar operadores pelo id
     * @param id id do operador procurado
     * @return retorna o operador
     * @throws OperadoresException lança uma exxceção se o operador não foi encontrado
     */
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
