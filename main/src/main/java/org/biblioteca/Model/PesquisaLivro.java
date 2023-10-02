package org.biblioteca.Model;

import org.biblioteca.dao.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

/**
 * Classe responsável pela pesquisa de livros, por autor,categoria... também retorna o top livro
 * de uma pessoa
 */
public class PesquisaLivro {


    /**
     * Pesquisa de livros pelo autor
     * @param autor nome do autor do livro
     * @return uma lista com todos os livros desse autor
     */
    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> lista_autor = new ArrayList<>();

        for(Livro livro:DAO.getLivroDAO().findMany()){
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
     * Pesquisa de livros por categoria
     * @param categoria o tipo de categoria do livro
     * @return uma lista de livros dessa determinada categoria
     */
    public List<Livro> pesquisarPorCategoria(String categoria) {
        List<Livro> lista_categoria = new ArrayList<>();
        for(Livro livro:DAO.getLivroDAO().findMany()){
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
     * Pesquia de livros por titulo
     * @param titulo o nome do livro(s) pesquisado
     * @return uma lista de todos os livros com esse titulo
     */
    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> lista_titulo = new ArrayList<>();
        for(Livro livro:DAO.getLivroDAO().findMany()){
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                lista_titulo.add(livro);
            }
        }

        if (!lista_titulo.isEmpty()){
            return lista_titulo;
        }else{
            return null;
        }

        // Lógica de pesquisa por título.
    }

    /**
     * Uma pesquisa pelo numero de ISBN dos livros
     * @param ISBN isbn é uma identidade do livro
     * @return retorna todos os livros com esse mesmo ISBN
     */
    public List<Livro> pesquisarPorISBN(int ISBN) {
        List<Livro> lista_ISBN = new ArrayList<>();


        for(Livro livro:DAO.getLivroDAO().findMany()){
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
     * Filtra livros pela popularidades, mais emprestados
     * @return retorna uma lista com todos os livros, da ordem do mais popular para o menos popular
     */
    public List<Livro> TopLivros(){

        List<Livro> livros = DAO.getLivroDAO().findMany();

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



}
