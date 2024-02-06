package com.example.pbljava.dao.Operadores;

import org.biblioteca.Model.Operadores;
import org.biblioteca.dao.arquivos.arquivos_manipulacao;
import org.biblioteca.excepctions.OperadoresException;

import java.io.IOException;
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
     * instanciacao da classe arquivos_manipulacao para o arquivo dos operadores
     */
    private final arquivos_manipulacao<Operadores> arquivo_operadores=new arquivos_manipulacao<Operadores>();


    /**
     * Construtor para classe, quando iniciado o singleton, é criado uma nova lista, um arquivo e é zerado o id para contagem
     * Se ja existir o arquivo, abre e carrega os conteudo + ultimo id usado
     */
    public OperadoresDAOLista() throws IOException {

        List<Operadores> l = new ArrayList<>();

        this.lista=arquivo_operadores.Carregando("arquivo2.dat",l);


        if(!this.lista.isEmpty()){
            Operadores ultimooperador = lista.get(lista.size() - 1);
            this.proximoID=ultimooperador.getId()+1;

        }else{
            this.proximoID=0;
        }

    }

    /**
     * método para contagem dos id's
     * @return retorna o próximo id a ser usado
     */
    private int getProximoID(){
        return this.proximoID++;
    }

    /**
     * Método para criar um operador na lista/arquivo
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Operadores create(Operadores obj) {
        obj.setId(this.getProximoID());


        List<Operadores> l = new ArrayList<>();

        this.lista=arquivo_operadores.Carregando("arquivo2.dat",l);


        this.lista.add(obj);
        arquivo_operadores.Salvando("arquivo2.dat",this.lista);

        return obj;
    }

    /**
     *  método para deletar um operador da lista/arquivo
     *  @param obj operador a ser deletado
     *  @throws OperadoresException lança uma exceção se esse operador não existir na lista
     */
    @Override
    public void delete(Operadores obj) throws OperadoresException {
        List<Operadores> l = new ArrayList<>();

        this.lista=arquivo_operadores.Carregando("arquivo2.dat",l);


        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new OperadoresException(OperadoresException.DELETE,obj);
        }else{
            arquivo_operadores.Salvando("arquivo2.dat",this.lista);

        }
    }

    /**
     * método para resetar a lista(e arquivo) e a contagem de ID
     */
    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;

        arquivo_operadores.Salvando("arquivo2.dat",this.lista);


    }

    /**
     * Método para atualizar um operador que ja existe na lista/arquivo
     * @param obj operador a ser atualizado
     * @return retorna o operador
     * @throws OperadoresException lança uma exceção se esse operador não existir
     */
    @Override
    public Operadores update(Operadores obj) throws OperadoresException {

        List<Operadores> l = new ArrayList<>();

        this.lista=arquivo_operadores.Carregando("arquivo2.dat",l);

        int index = this.lista.indexOf(obj);

        if (index == -1) {

            throw new OperadoresException(OperadoresException.UPDATE, obj);
        } else {
            this.lista.set(index, obj);
            arquivo_operadores.Salvando("arquivo2.dat",this.lista);
        }

        return obj;

    }

    /**
     * Método para encontrar todos os operadores no arquivo/lista
     * @return retorna uma lista com todos os operadores
     */
    @Override
    public List<Operadores> findMany() {
        List<Operadores> l = new ArrayList<>();

        this.lista=arquivo_operadores.Carregando("arquivo2.dat",l);

        return this.lista;
    }

    /**
     * Método para encontrar operadores pelo id na lista/arquivo
     * @param id id do operador procurado
     * @return retorna o operador
     * @throws OperadoresException lança uma exxceção se o operador não foi encontrado
     */
    @Override
    public Operadores findById(int id) throws OperadoresException{

        List<Operadores> l = new ArrayList<>();

        this.lista=arquivo_operadores.Carregando("arquivo2.dat",l);



        for(Operadores operador:this.lista){
            if(operador.getId()==id){
                return operador;
            }
        }
        throw new OperadoresException(OperadoresException.FIND,id);
}
}
