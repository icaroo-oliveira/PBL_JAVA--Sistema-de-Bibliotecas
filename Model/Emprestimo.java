package Model;

import dao.DAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;
    private LocalDate data_devolucao_esperada;
    private int id_emprestimo;
    private String status_emprestimo;
    private Usuario usuario;
    private Livro livro;



    public void Calcula_multa(){
        int diferenca_devolucao= (int) ChronoUnit.DAYS.between(data_emprestimo, data_devolucao);
        if(diferenca_devolucao>7){
            usuario.setMulta((diferenca_devolucao-7)*2);
        }else{
            usuario.setMulta(0);
        }
    }

    public Emprestimo Realizar_empresitmo(){
        if(getLivro().getDisponibilidade()){
            getLivro().setDisponibilidade(false);
            getUsuario().setHistorico_livro(this);
            return DAO.getEmprestimoDAO().create(this);//??
        }
        else{
            System.out.println("Não foi possível realizar o empréstimo");
            return null;


        }




    }





    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }
    public void setData_emprestimo(int dia,int mes,int ano) {
        this.data_emprestimo = LocalDate.of(ano,mes,dia);
    }
    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(int dia,int mes,int ano) {
        this.data_devolucao = LocalDate.of(ano,mes,dia);
    }

    public LocalDate getData_devolucao_esperada() {
        return data_devolucao_esperada;
    }

    public void setData_devolucao_esperada(LocalDate data_emprestimo) {
        this.data_devolucao_esperada = data_emprestimo.plusDays(7);
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public String getStatus_emprestimo() {
        return status_emprestimo;
    }

    public void setStatus_emprestimo(String status_emprestimo) {
        this.status_emprestimo = status_emprestimo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }


}
