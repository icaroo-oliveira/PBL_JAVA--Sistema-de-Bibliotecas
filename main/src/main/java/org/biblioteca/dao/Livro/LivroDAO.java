package org.biblioteca.dao.Livro;

import org.biblioteca.Model.Emprestimo;
import org.biblioteca.dao.CRUD;
import org.biblioteca.Model.Livro;

import java.time.LocalDate;
import java.util.List;

/**
 * interface pra o LivroDAOLista ela herda da interface CRUD um tipo de classe generica e a exceção
 */
public interface LivroDAO extends CRUD<Livro,Exception> {

    /**
     * assinatura para o método de pesquisa por autor
     * @param autor autor que será usado para pesquisa
     * @return lista com todos os livros daquele autor
     */
    public List<Livro> pesquisarPorAutor(String autor);

    /**
     * assinatura para o método de pesquisa por categoria
     * @param categoria categoria que sera usada para pesquisa
     * @return lista com todos os livros daquela categoria
     */
    public List<Livro> pesquisarPorCategoria(String categoria);

    /**
     * assinatura para o método de pesquisa usanado a o titulo do livro
     * @param titulo titulo do livro
     * @return retorna uma lista composta por livros daquele mesmo titulo
     */
    public List<Livro> pesquisarPorTitulo(String titulo);

    /**
     * assinatura para o métodod de pesquisa por isbn
     * @param ISBN isbn dos livros
     * @return retorna uma lista de livros com aquele isbn
     */
    public List<Livro> pesquisarPorISBN(int ISBN);

    /**
     * assinatura para o método de geração de top livros
     * @return retorna uma lista de livros em ordem de popularidade, do mais popular pro menos popular
     */
    public List<Livro> TopLivros();

    ///////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<

    public List<Livro> getLista_emprestados();
    public List<Livro> getLista_atrasados(LocalDate parametro);
    public List<Livro> getLista_reservados();

}
