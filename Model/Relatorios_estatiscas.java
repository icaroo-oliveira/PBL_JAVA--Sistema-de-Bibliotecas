package Model;

import dao.DAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Relatorios_estatiscas {

    final private List<Livro> lista_emprestados = new ArrayList<>();
    final private List<Livro> lista_atrasados = new ArrayList<>();
    final private List<Livro> lista_reservados = new ArrayList<>();


    public List<Livro> getLista_emprestados() {

        for(Livro livro: DAO.getLivroDAO().findMany()){
            if(!livro.getDisponibilidade()) {
                this.lista_emprestados.add(livro);
            }
        }

        if (!this.lista_emprestados.isEmpty()){
            return this.lista_emprestados;
        }else{
            return Collections.emptyList();
        }
    }


    public List<Livro> getLista_atrasados() {


        for(Livro livro: DAO.getLivroDAO().findMany()){
            if(!livro.getDisponibilidade() && (int) ChronoUnit.DAYS.between(livro.getEmprestimo().getData_devolucao_esperada(), LocalDate.now())<0) {
                this.lista_atrasados.add(livro);
            }
        }

        if (!this.lista_atrasados.isEmpty()){
            return this.lista_atrasados;
        }else{
            return Collections.emptyList();
        }
    }

    public List<Livro> getLista_reservados() {


        for(Livro livro: DAO.getLivroDAO().findMany()){
            if(!livro.getFila().isEmpty()) {
                this.lista_reservados.add(livro);
            }
        }

        if (!this.lista_reservados.isEmpty()){
            return this.lista_reservados;
        }else{
            return Collections.emptyList();
        }
    }


    public List<Emprestimo> getUsuarioEmprestimos(int id) throws Exception {

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
