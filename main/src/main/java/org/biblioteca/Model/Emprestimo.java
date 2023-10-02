package org.biblioteca.Model;

import org.biblioteca.dao.DAO;

import org.biblioteca.excepctions.*;



import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


/**
 * Classe Emprestimo é a classe responsável por manipular tudo relacionado ao empréstimo
 */
public class Emprestimo {
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;
    private LocalDate data_devolucao_esperada;
    private int id_emprestimo;
    private int status_emprestimo;//0 = tudo de boa, 1=atrasado,2=finalizado
    private Usuario usuario;
    private Livro livro;

    private int qnt_renovacao;

    /**
     * Construtor do Empréstimo
     * @param usuario objeto do usuário que vai fazer empréstimo
     * @param livro  objeto do livro do empréstimo
     * @param emp_data data de empréstimo
     * @param emp_data_devolve data de devolucao esperada
     * @throws EmprestimoException //Exceção de empréstimo lançada quando não é possível fazer empréstimo
     * @throws LivroException //Exceção de livro lançada
     */
    public Emprestimo(Usuario usuario, Livro livro,LocalDate emp_data,LocalDate emp_data_devolve) throws EmprestimoException, LivroException {
        this.data_emprestimo =emp_data; //LocalDate.now();
        this.data_devolucao = null;
        //setData_devolucao_esperada(data_emprestimo);
        this.data_devolucao_esperada=emp_data_devolve;
        this.status_emprestimo =0;
        this.usuario = usuario;
        this.livro = livro;
        this.qnt_renovacao=1;

        //Realizar_empresitmo(emp_data);
    }

    /*public Emprestimo(Usuario usuario,Livro livro, int qnt_renovacao,int id_emprestimo,LocalDate emp_data,LocalDate emp_data_devolve) {
        this.usuario=usuario;
        this.livro=livro;
        this.qnt_renovacao=qnt_renovacao;
        this.id_emprestimo=id_emprestimo;
        this.data_devolucao = null;
        this.data_emprestimo = emp_data;//LocalDate.now();
        //setData_devolucao_esperada(data_emprestimo);
        this.data_devolucao_esperada=emp_data_devolve;
        this.status_emprestimo =0;

    }*/


    /**
     * Método responsável por calcular a multa, é chamado no momento da devolução
     * @param devolvendo data de devolução do livro, vai ser usado na comparação com a data de emprestimo
     */
    public void Calcula_multa(LocalDate devolvendo){//não sei se esse método seria aqui...
        int diferenca_devolucao= (int) ChronoUnit.DAYS.between(getData_emprestimo(), devolvendo);
        if(diferenca_devolucao>7){
            getUsuario().setMulta((diferenca_devolucao-7)*2);
            getUsuario().setData_multa(devolvendo);
            getUsuario().setStatus(false);
            setStatus_emprestimo(2);//emprestimo como finalizado

        }else{
            getUsuario().setMulta(0);
            getUsuario().setStatus(true);
            setStatus_emprestimo(2);
        }
    }

    /**
     * Classe que realiza o empréstimo propriamente dito, ela verifica o estado do livro, do usuário e se tudo ta ok, o empréstimo é feito
     * @param emp_data data do empréstimo
     * @throws Exception exceção lançada caso o usuário não consiga fazer empréstimos
     */
    public void Realizar_empresitmo(LocalDate emp_data) throws Exception {


        //(AMARELO)QUANDO UM USUARIO Q JA TA COM UM LIVRO, TENTA FAZER O EMPRESTIMO DAQUELE MESMO LIVRO, ESSE MESMO USUARIO TA SENDO ADICONADO A FILA...
        if(getLivro().getDisponibilidade() && getUsuario().Status1(emp_data) && getLivro().getFila().isEmpty()){

            //System.out.println("emprestimo feito");
            getLivro().setDisponibilidade(false);
            getUsuario().setHistorico_livro(this);
            getUsuario().setQntd_emprestimo();
            getLivro().setEmprestimo(this);
            DAO.getEmprestimoDAO().create(this);//add agora por enquanto não é necessário, ja q eu to testando
            DAO.getLivroDAO().update(getLivro());

        }else if(getLivro().getDisponibilidade() && getUsuario().Status1(emp_data) && Objects.requireNonNull(getLivro().getFila().peek()).getId()==getUsuario().getId()){// era assim: getLivro().getFila().peek().getId()==getUsuario().getId()
            //System.out.println("emprestimo feito opcao da fila e id's iguais");

            getLivro().setDisponibilidade(false);
            getLivro().setEmprestimo(this);
            getUsuario().setHistorico_livro(this);
            getUsuario().setQntd_emprestimo();
            DAO.getEmprestimoDAO().create(this);//add agora
            getLivro().getFila().poll();
            DAO.getLivroDAO().update(getLivro());//NOVO<<
        }
        else{


            //System.out.println("Algo errado...");

            if (!getLivro().getDisponibilidade() && getUsuario().Status1(emp_data)){


                getLivro().Reservar_livro(getUsuario(),emp_data);
            }
            else if(getLivro().getDisponibilidade() && !getUsuario().Status1(emp_data)){
                throw new EmprestimoException(EmprestimoException.CREATE,this);
            }



        }
    }

    /**
     * Método de devolução
     * @param devolve data da devolução
     * @throws Exception é lançada uma exceção se esta tentando devolver um livro que nao foi emprestado, por exemplo
     */
    public void Devolucao(LocalDate devolve) throws Exception {
        Calcula_multa(devolve);
        getLivro().setDisponibilidade(true);
        getLivro().setEmprestimo(null);
        DAO.getLivroDAO().update(getLivro());//add agora tbm
        //DIMINUIR A QUANTIDADE DE EMPRESTIMO
    }

    /**
     * Método responsável pela Renovação de empréstimo
     * @param emp_emprestimo_new data de renovação
     * @param emp_emprestimo_dev data de devolucao esperada da renovação
     * @throws Exception exceção lançada caso o usário não esteja apto a renovar o livro
     */
    public void Renovar_emprestimo(LocalDate emp_emprestimo_new, LocalDate emp_emprestimo_dev) throws Exception {////OLHAR ISSO AQUI<< KKK

        if (getLivro().getFila().isEmpty() && getQnt_emprestimo()<3 && getUsuario().Status1(emp_emprestimo_new)){

            setData_emprestimo(emp_emprestimo_new);
            setQnt_emprestimo();
            setData_devolucao_esperada(emp_emprestimo_dev);
            DAO.getEmprestimoDAO().update(this);
            DAO.getLivroDAO().update(getLivro());


            //ABAIXO PROBLEMA
            //(1) Emprestimo copia = new Emprestimo(this.getUsuario(),this.getLivro(),this.getQnt_emprestimo(),this.getId_emprestimo(),this.getData_emprestimo(),emp_emprestimo_dev);// n tinha o emp_data...


            //(1) DAO.getEmprestimoDAO().update(DAO.getEmprestimoDAO().findById(this.getId_emprestimo()),copia);//o update apaga o original e coloca uma cópia do original no mesmo local.

        }else if(!getLivro().getFila().isEmpty() && getUsuario().getStatus()){
            getLivro().Reservar_livro(getUsuario(),emp_emprestimo_new);
        }else{
            throw new EmprestimoException(EmprestimoException.CREATE,this);
        }
    }

    /**
     * Método responsável por fazer a verificao de atraso de um empréstimo
     * @param empres data atual
     * @return retorna 1 se o empréstimo ta ''vencido'' e 0 se ta tudo ok
     */
    public int verificando_data_emprestimo(LocalDate empres){

        //int comparacao = LocalDate.now().compareTo(getData_devolucao_esperada());
        int comparacao = empres.compareTo(getData_devolucao_esperada());
        if(comparacao>0 && getStatus_emprestimo()!=2){
            return 1;
        }else {
            return 0;
        }

    }


    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }
    public void setData_emprestimo(LocalDate emp_emprestimo_new) {
        //this.data_emprestimo = LocalDate.now();
        this.data_emprestimo = emp_emprestimo_new;
        setData_devolucao_esperada(emp_emprestimo_new);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emprestimo)) return false;
        Emprestimo emprestimo = (Emprestimo) o;
        return Objects.equals(getStatus_emprestimo(), emprestimo.getStatus_emprestimo()) && Objects.equals(getData_emprestimo(), emprestimo.getData_emprestimo()) && Objects.equals(getData_devolucao_esperada(), emprestimo.getData_devolucao_esperada()) && Objects.equals(getUsuario(), emprestimo.getUsuario()) && Objects.equals(getLivro(), emprestimo.getLivro());
    }

    @Override
    public int hashCode() {
        return Objects.hash(data_emprestimo, data_devolucao, data_devolucao_esperada, id_emprestimo, status_emprestimo, usuario, livro, qnt_renovacao);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "data_emprestimo=" + data_emprestimo +
                ", data_devolucao=" + data_devolucao +
                ", data_devolucao_esperada=" + data_devolucao_esperada +
                ", id_emprestimo=" + id_emprestimo +
                ", status_emprestimo=" + status_emprestimo +
                ", usuario=" + usuario +
                ", livro=" + livro +
                ", qnt_renovacao=" + qnt_renovacao +
                '}';
    }
}
