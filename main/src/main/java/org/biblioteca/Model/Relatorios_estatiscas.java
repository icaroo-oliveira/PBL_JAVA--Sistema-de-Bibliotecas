package org.biblioteca.Model;

import org.biblioteca.dao.DAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por estatitiscas relacionados aos livros, emprestados, atrasados, reservados
 * também consegue uma lista com os empréstimos/ livros daquele usuário
 */
public class Relatorios_estatiscas {


    /**
     * método responsável por conseguir somente os livros que estão emprestados
     * @return retorna uma lista com livros que estão emprestados
     */
    public List<Livro> getLista_emprestados() {
        List<Livro> lista_emprestados = new ArrayList<>();

        for(Livro livro: DAO.getLivroDAO().findMany()){
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
     * Método responsável por conseguir os livros atrasdos
     * @param parametro passa uma data para servir como base no cálculo dos livros atrasados
     * @return retorna uma lista com livros atrasados
     */
    public List<Livro> getLista_atrasados(LocalDate parametro) {
        List<Livro> lista_atrasados = new ArrayList<>();

        //System.out.println(DAO.getLivroDAO().findMany().size());

        for(Livro livro: DAO.getLivroDAO().findMany()){

            System.out.println(livro.getEmprestimo());
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
     * Métodos responsável por conseguir somente os livros reservados
     * @return retorna uma lista com todos os livros que foram reservados
     */
    public List<Livro> getLista_reservados() {
        List<Livro> lista_reservados = new ArrayList<>();


        for(Livro livro: DAO.getLivroDAO().findMany()){
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

    /**
     * Método responsável por pegar todos os empréstimos ja feito por uma pessoa
     * @param id o id do usuário que irá pegar o historico
     * @return retorna uma lista com todos emprestimos feito por aquele usuário
     * @throws Exception lança uma exceção se não encontrar o usuário
     */
    public List<Emprestimo> getUsuarioEmprestimos(int id) throws Exception {


        System.out.println(id);

        if(!DAO.getUsuarioDAO().findById(id).getHistorico_livro().isEmpty()){
            return DAO.getUsuarioDAO().findById(id).getHistorico_livro();
        }else{
            return Collections.emptyList();
        }

    }

    /*public List<Livro> getLivrosMaisPopu(){

        List<Livro> listae= DAO.getLivroDAO().findMany();

    }*/





}
