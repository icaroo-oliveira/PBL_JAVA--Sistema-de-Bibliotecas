package Model;

import dao.DAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;
    private LocalDate data_devolucao_esperada;
    private int id_emprestimo;
    private int status_emprestimo;//0 = tudo de boa, 1=atrasado
    private Usuario usuario;
    private Livro livro;

    private int qnt_renovacao;


    public Emprestimo(Usuario usuario, Livro livro) {
        this.data_emprestimo = LocalDate.now();
        this.data_devolucao = null;
        setData_devolucao_esperada(data_emprestimo);
        this.status_emprestimo =0;
        this.usuario = usuario;
        this.livro = livro;
        this.qnt_renovacao=1;

        Realizar_empresitmo();
    }

    public Emprestimo(Usuario usuario,Livro livro, int qnt_renovacao,int id_emprestimo){
        this.usuario=usuario;
        this.livro=livro;
        this.qnt_renovacao=qnt_renovacao;
        this.id_emprestimo=id_emprestimo;
        this.data_devolucao = null;
        this.data_emprestimo = LocalDate.now();
        setData_devolucao_esperada(data_emprestimo);
        this.status_emprestimo =0;

    }

    public void Calcula_multa(LocalDate devolvendo){
        int diferenca_devolucao= (int) ChronoUnit.DAYS.between(getData_emprestimo(), devolvendo);
        if(diferenca_devolucao>7){
            getUsuario().setMulta((diferenca_devolucao-7)*2);
            getUsuario().setData_multa(devolvendo);
            getUsuario().setStatus(false);
        }else{
            getUsuario().setMulta(0);
            getUsuario().setStatus(true);
        }
    }
    public void Realizar_empresitmo(){//(AMARELO)QUANDO UM USUARIO Q JA TA COM UM LIVRO, TENTA FAZER O EMPRESTIMO DAQUELE MESMO LIVRO, ESSE MESMO USUARIO TA SENDO ADICONADO A FILA...
        if(getLivro().getDisponibilidade() && getUsuario().Status1() && getLivro().getFila().isEmpty()){
            System.out.println("emprestimo feito");
            getLivro().setDisponibilidade(false);
            getUsuario().setHistorico_livro(this);
            getUsuario().setQntd_emprestimo();
            DAO.getEmprestimoDAO().create(this);//add agora

        }else if(getLivro().getDisponibilidade() && getUsuario().Status1() && getLivro().getFila().peek().getId()==getUsuario().getId()){
            System.out.println("emprestimo feito opcao da fila e id's iguais");
            getLivro().setDisponibilidade(false);
            getUsuario().setHistorico_livro(this);
            getUsuario().setQntd_emprestimo();
            DAO.getEmprestimoDAO().create(this);//add agora
            getLivro().getFila().poll();
        }
        else{
            //uma excessao do mesmo cara ta tentando fazer emprestimo que ele ja fez... ou... redirecionar para renovação, quem sabe


            System.out.println("Algo errado...");

            if (!getLivro().getDisponibilidade() && getUsuario().Status1()){

                getLivro().Reservar_livro(getUsuario());
            }
            else if(getLivro().getDisponibilidade() && !getUsuario().Status1()){
                System.out.println("bora melhorar ai primo...");
            }



        }
    }
    public void Devolucao(){
        Calcula_multa(LocalDate.now());
        getLivro().setDisponibilidade(true);
    }

    public void Renovar_emprestimo(){
        if (getLivro().getFila().isEmpty() && getQnt_emprestimo()<=2 && getUsuario().getStatus()){

            setData_emprestimo();
            setQnt_emprestimo();
            Emprestimo copia = new Emprestimo(this.getUsuario(),this.getLivro(),this.getQnt_emprestimo(),this.getId_emprestimo());
            DAO.getEmprestimoDAO().update(DAO.getEmprestimoDAO().findById(this.getId_emprestimo()),copia);//o update apaga o original e coloca uma cópia do original no mesmo local.

        }else if(!getLivro().getFila().isEmpty() && getUsuario().getStatus()){
            getLivro().Reservar_livro(getUsuario());
        }else{
            System.out.println("bora melhorar meu velho, ta com umas multas ae...");
        }
    }


    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }
    public void setData_emprestimo() {
        this.data_emprestimo = LocalDate.now();
        setData_devolucao_esperada(LocalDate.now());
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

    public int getStatus_emprestimo() { //ok??
        return status_emprestimo;
    }

    public void setStatus_emprestimo(int status_emprestimo) {
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

    public int getQnt_emprestimo() {
        return qnt_renovacao;
    }

    public void setQnt_emprestimo() {
        this.qnt_renovacao++;
    }
}
