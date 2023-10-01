package Model;

import dao.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class PesquisaLivro {

    final private List<Livro> lista_autor = new ArrayList<>();
    final private List<Livro> lista_categoria = new ArrayList<>();
    final private List<Livro> lista_titulo = new ArrayList<>();

    final private List<Livro> lista_ISBN = new ArrayList<>();


    public List<Livro> pesquisarPorAutor(String autor) {

        for(Livro livro:DAO.getLivroDAO().findMany()){
            if(livro.getAutor().equalsIgnoreCase(autor)) {
                this.lista_autor.add(livro);
            }
        }

        if (!this.lista_autor.isEmpty()){
            return this.lista_autor;
        }else{
            return Collections.emptyList();
        }
    }

    public List<Livro> pesquisarPorCategoria(String categoria) {
        for(Livro livro:DAO.getLivroDAO().findMany()){
            if(livro.getCategoria().equalsIgnoreCase(categoria)) {
                this.lista_categoria.add(livro);
            }
        }

        if (!this.lista_autor.isEmpty()){
            return this.lista_categoria;
        }else{
            return Collections.emptyList();
        }
        // Lógica de pesquisa por categoria.
    }
    public List<Livro> pesquisarPorTitulo(String titulo) {
        for(Livro livro:DAO.getLivroDAO().findMany()){
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                this.lista_titulo.add(livro);
            }
        }

        if (!this.lista_autor.isEmpty()){
            return this.lista_titulo;
        }else{
            return null;
        }



        // Lógica de pesquisa por título.
    }

    public List<Livro> pesquisarPorISBN(int ISBN) {

        for(Livro livro:DAO.getLivroDAO().findMany()){
            if(livro.getISBN()==ISBN) {
                this.lista_ISBN.add(livro);
            }
        }

        if (!this.lista_autor.isEmpty()){
            return this.lista_ISBN;
        }else{
            return Collections.emptyList();
        }

        // Lógica de pesquisa por título.
    }


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
