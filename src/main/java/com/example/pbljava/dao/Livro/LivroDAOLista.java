package com.example.pbljava.dao.Livro;

import org.biblioteca.Model.Livro;
import org.biblioteca.dao.arquivos.arquivos_manipulacao;
import org.biblioteca.excepctions.LivroException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * CLASSE dao para os livros, servirá para separar a lógica de negocio do armazenamento de dados
 */
public class LivroDAOLista implements LivroDAO{


    /**
     * instanciacao da classe arquivos_manipulacao para o arquivo dos livros
     */
    private final arquivos_manipulacao<Livro> arquivo_livros=new arquivos_manipulacao<Livro>();


    /**
     *Lista de livros, vai guardar todos os livros ja adicionados ao sistema, em uma lista
     */
    private List<Livro> lista;

    /**
     * variável que guardará o próximo ID a ser utilizado, tipo um contador.
     */
    private int proximoID;

    /**
     * Construtor para classe, quando iniciado o singleton, é criado uma nova lista, um arquivo e é zerado o id para contagem
     * Se ja existir o arquivo, abre e carrega os conteudo + ultimo id usado
     */
    public LivroDAOLista() throws IOException {

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);

        if(!this.lista.isEmpty()){
            Livro ultimoLivro = lista.get(lista.size() - 1);
            this.proximoID=ultimoLivro.getId()+1;

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
     * Método para criar um livro na lista/arquivo
     * @param obj objeto ja instanciado que será criado
     * @return retorna o mesmo objeto
     */
    @Override
    public Livro create(Livro obj) {
        obj.setId(this.getProximoID());

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);

        this.lista.add(obj);
        arquivo_livros.Salvando("arquivo1.dat",this.lista);

        return obj;
    }

    /**
     * método para deletar um livro da lista/arquivo
     * @param obj livro a ser deletado
     * @throws LivroException lança uma exceção se esse livro não existir na lista
     */
    @Override
    public void delete(Livro obj) throws LivroException {

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);
        boolean remover = this.lista.remove(obj);
        if(!remover){
            throw new LivroException(LivroException.DELETE,obj);
        }else{
            arquivo_livros.Salvando("arquivo1.dat",this.lista);
        }


    }
    /**
     * deleta toda a lista de livros do arquivo
     */
    @Override
    public void deleteMany() {

        this.lista = new ArrayList<>();
        this.proximoID = 0;

        arquivo_livros.Salvando("arquivo1.dat",this.lista);


    }

    /**
     * método para atualizar um livro no arquivo/lista
     * @param obj livro a ser atualizado
     * @return retorna o livro
     * @throws LivroException lança uma exceção caso o livro não exista
     */
    @Override
    public Livro update(Livro obj) throws LivroException {

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        int index = this.lista.indexOf(obj);

        if (index == -1) {

            throw new LivroException(LivroException.UPDATE, obj);
        } else {
            this.lista.set(index, obj);
            arquivo_livros.Salvando("arquivo1.dat",this.lista);

        }

        return obj;

    }

    /**
     * Método para encontrar todos os livros na lista/arquivo
     * @return retorna uma lista com todos os livros
     */
    @Override
    public List<Livro> findMany() {
        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);

        return this.lista;
    }

    /**
     * Método para encontrar um livro pelo ID na lista/arquivo
     * @param id o id do livro procurado
     * @return retorna o livro dado certo id
     * @throws LivroException lanã uma mensagem de exceção caso o livro não seja encontrado
     */
    @Override
    public Livro findById(int id) throws LivroException {

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);

        for(Livro livro:this.lista){
            if(livro.getId()==id){
                return livro;
            }
        }
        throw new LivroException(LivroException.FIND,id);
    }



    /**
     * Pesquisa de livros pelo autor no arquivo/lista
     * @param autor nome do autor do livro
     * @return uma lista com todos os livros desse autor
     */
    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> lista_autor = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        for(Livro livro: this.lista){
            if(livro.getAutor().equalsIgnoreCase(autor)) {
                lista_autor.add(livro);
            }
        }

        if (!lista_autor.isEmpty()){
            return lista_autor;
        }else{
            return Collections.emptyList();
        }
    }

    /**
     * Pesquisa de livros por categoria no arquivo/lista
     * @param categoria o tipo de categoria do livro
     * @return uma lista de livros dessa determinada categoria
     */
    public List<Livro> pesquisarPorCategoria(String categoria) {
        List<Livro> lista_categoria = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        for(Livro livro:this.lista){
            if(livro.getCategoria().equalsIgnoreCase(categoria)) {
                lista_categoria.add(livro);
            }
        }

        if (!lista_categoria.isEmpty()){
            return lista_categoria;
        }else{
            return Collections.emptyList();
        }
        // Lógica de pesquisa por categoria.
    }


    /**
     * Pesquia de livros por titulo no arquivo/lista
     * @param titulo o nome do livro(s) pesquisado
     * @return uma lista de todos os livros com esse titulo
     */
    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> lista_titulo = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        for(Livro livro:this.lista){
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                lista_titulo.add(livro);
            }
        }

        if (!lista_titulo.isEmpty()){
            return lista_titulo;
        }else{
            return Collections.emptyList();
        }

        // Lógica de pesquisa por título.
    }


    /**
     * Uma pesquisa pelo numero de ISBN dos livros no arquivo/lista
     * @param ISBN isbn é uma identidade do livro
     * @return retorna todos os livros com esse mesmo ISBN
     */
    public List<Livro> pesquisarPorISBN(int ISBN) {
        List<Livro> lista_ISBN = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        for(Livro livro:this.lista){
            if(livro.getISBN()==ISBN) {
                lista_ISBN.add(livro);
            }
        }

        if (!lista_ISBN.isEmpty()){
            return lista_ISBN;
        }else{
            return Collections.emptyList();
        }

        // Lógica de pesquisa por título.
    }


    /**
     * Filtra livros pela popularidades, mais emprestados (busca no arquivo e lista)
     * @return retorna uma lista com todos os livros, da ordem do mais popular para o menos popular
     */
    public List<Livro> TopLivros(){


        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        List<Livro> livros = this.lista;

        if(!livros.isEmpty()){
            livros.sort(new Comparator<Livro>() {
                @Override
                public int compare(Livro l1, Livro l2) {
                    return Integer.compare(l2.getPopularity(), l1.getPopularity());
                }
            });
            return livros;
        }else{
            return Collections.emptyList();
        }
    }


    /**
     * método responsável por conseguir somente os livros que estão emprestados (busca no arquivo/lista)
     * @return retorna uma lista com livros que estão emprestados
     */
    public List<Livro> getLista_emprestados() {
        List<Livro> lista_emprestados = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);


        for(Livro livro: this.lista){
            if(!livro.getDisponibilidade()) {
                lista_emprestados.add(livro);
            }
        }

        if (!lista_emprestados.isEmpty()){
            return lista_emprestados;
        }else{
            return Collections.emptyList();
        }
    }


    /**
     * Método responsável por conseguir os livros atrasdos (busca no arquivo/lista)
     * @param parametro passa uma data para servir como base no cálculo dos livros atrasados
     * @return retorna uma lista com livros atrasados
     */
    public List<Livro> getLista_atrasados(LocalDate parametro) {
        List<Livro> lista_atrasados = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);



        for(Livro livro: this.lista){

            if(livro.getEmprestimo()!=null){
                int unidade = (int) ChronoUnit.DAYS.between(livro.getEmprestimo().getData_emprestimo(), parametro);

                if(!livro.getDisponibilidade() && unidade>7) {
                    lista_atrasados.add(livro);
                }
            }


        }

        if (!lista_atrasados.isEmpty()){
            return lista_atrasados;
        }else{
            return Collections.emptyList();
        }
    }


    /**
     * Métodos responsável por conseguir somente os livros reservados (busca no arquivo/lista)
     * @return retorna uma lista com todos os livros que foram reservados
     */
    public List<Livro> getLista_reservados() {
        List<Livro> lista_reservados = new ArrayList<>();

        List<Livro> l = new ArrayList<>();

        this.lista=arquivo_livros.Carregando("arquivo1.dat",l);



        for(Livro livro: this.lista){
            if(!livro.getFila().isEmpty()) {
                lista_reservados.add(livro);
            }
        }

        if (!lista_reservados.isEmpty()){
            return lista_reservados;
        }else{
            return Collections.emptyList();
        }
    }

}
