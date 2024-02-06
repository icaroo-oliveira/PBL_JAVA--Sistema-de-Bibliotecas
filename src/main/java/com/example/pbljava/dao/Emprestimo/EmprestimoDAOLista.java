package com.example.pbljava.dao.Emprestimo;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.dao.arquivos.arquivos_manipulacao;
import org.biblioteca.excepctions.EmprestimoException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE dao para o Emprestimo, servirá para separar a lógica de negocio do armazenamento de dados
 */
public class EmprestimoDAOLista implements EmprestimoDAO,Serializable{


    /**
     * instanciacao da classe arquivos_manipulacao para o arquivo do emprestimo
     */
    private final arquivos_manipulacao<Emprestimo> arquivo_emprestimos=new arquivos_manipulacao<Emprestimo>();


    /**
     * Lista de empréstimo, vai guardar todos os empréstimos feitos em uma lista
     */
    private List<Emprestimo> lista;

    /**
     * variável que guardará o próximo ID a ser utilizado, tipo um contador.
     */
    private int proximoID;


    /**
     * Construtor para classe, quando iniciado o singleton, é criado uma nova lista, arquivo zerado o id para contagem
     * Se ja existir o arquivo, abre e carrega os conteudo + ultimo id usado
     */
    public EmprestimoDAOLista() throws IOException {

            List<Emprestimo> l = new ArrayList<>();

            this.lista=arquivo_emprestimos.Carregando("arquivo.dat",l);

            if(!this.lista.isEmpty()){
                Emprestimo ultimoEmprestimo = lista.get(lista.size() - 1);
                this.proximoID=ultimoEmprestimo.getId_emprestimo()+1;

            }else{
                this.proximoID=0;

            }

    }

    /**
     * método para contagem dos id's
     * @return retorna o próximo id a ser usado
     */
    public int getProximoID(){
        return this.proximoID++;
    } //tava private

    /**
     * Método para criar um Emprestimo na lista/arquivo
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Emprestimo create(Emprestimo obj) {
        obj.setId_emprestimo(this.getProximoID());


        List<Emprestimo> l = new ArrayList<>();

        this.lista=arquivo_emprestimos.Carregando("arquivo.dat",l);
        this.lista.add(obj);

        arquivo_emprestimos.Salvando("arquivo.dat",this.lista);

        return obj;

    }

    /**
     * Método para deletar um dado objeto da lista/arquivo
     * @param obj objeto a ser deletado
     * @throws EmprestimoException lança uma exceção caso o objeto não exista
     */
    @Override
    public void delete(Emprestimo obj) throws EmprestimoException {


        List<Emprestimo> l = new ArrayList<>();

        this.lista=arquivo_emprestimos.Carregando("arquivo.dat",l);

        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new EmprestimoException(EmprestimoException.DELETE,obj);
        }else{
            arquivo_emprestimos.Salvando("arquivo.dat",this.lista);

        }



    }

    /**
     * método que deleta a lista/conteudo de todo o arquivo
     */

    @Override
    public void deleteMany() {
        this.lista = new ArrayList<>();
        this.proximoID = 0;

        arquivo_emprestimos.Salvando("arquivo.dat",this.lista);

    }




    /**
     * método de atualizacao de empréstimo na lista/arquivo
     * @param obj emprestimo a ser atualizado
     * @return retorna o emprestimo
     * @throws EmprestimoException lança uma exceção caso o emprestimo não exista
     */
    @Override
    public Emprestimo update(Emprestimo obj) throws EmprestimoException {

            List<Emprestimo> l = new ArrayList<>();

            this.lista=arquivo_emprestimos.Carregando("arquivo.dat",l);

            int index = this.lista.indexOf(obj);

            if (index == -1) {

                throw new EmprestimoException(EmprestimoException.UPDATE, obj);
            } else {
                this.lista.set(index, obj);
                arquivo_emprestimos.Salvando("arquivo.dat",this.lista);

            }

        return obj;




    }


    /**
     * método para encontrar todos os empréstimos
     * @return retorna uma lista com todos os empréstimos
     */

    @Override
    public List<Emprestimo> findMany() {

        List<Emprestimo> l = new ArrayList<>();

        this.lista=arquivo_emprestimos.Carregando("arquivo.dat",l);

        return this.lista;
    }




        /**
     * método para encontrar um empréstimo pelo ID na lista/arquivo
     * @param id id do empréstimo a ser encontrado
     * @return retorna o empréstimo de um dado ID
     * @throws EmprestimoException lança uma mensagem de exceção caso o emprestimo não seja encontrado
     */
    @Override
    public Emprestimo findById(int id) throws EmprestimoException {

        List<Emprestimo> l = new ArrayList<>();

        this.lista=arquivo_emprestimos.Carregando("arquivo.dat",l);

        for(Emprestimo emprestimo:this.lista){
            if(emprestimo.getId_emprestimo()==id){
                return emprestimo;
            }
        }
        throw new EmprestimoException(EmprestimoException.FIND,id);
    }
}



