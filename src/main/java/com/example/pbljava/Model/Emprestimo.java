package com.example.pbljava.Model;

import org.biblioteca.dao.DAO;

import org.biblioteca.excepctions.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


/**
 * Classe Emprestimo é a classe responsável por manipular tudo relacionado ao empréstimo
 * multas,renovacoes de emprestimo, realizacao de emprestimo...
 */
public class Emprestimo implements Serializable {

    /**
     * Data da realização do empréstimo
     */
    private LocalDate data_emprestimo;

    /**
     * Data do dia que o usuário devolveu o livro
     */
    private LocalDate data_devolucao;

    /**
     * Data de devolução esperada, calculada com base na data de emprestimo + 7 dias
     */
    private LocalDate data_devolucao_esperada;

    /**
     * id do empréstimo
     */
    private int id_emprestimo;

    /**
     * Status do empréstimo, 0 ativo, 2 finalizado
     */
    private int status_emprestimo;

    /**
     * Usuario que está fazendo o empréstimo
     */
    private Usuario usuario;

    /**
     * Livro que está sendo emprestado
     */
    private Livro livro;

    /**
     * Quantidade de renovação do empréstimo daquele livro, no maximo 3.
     */
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
        this.data_emprestimo =emp_data;
        this.data_devolucao = null;
        this.data_devolucao_esperada=emp_data_devolve;
        this.status_emprestimo =0;
        this.usuario = usuario;
        this.livro = livro;
        this.qnt_renovacao=1;

    }


    public Emprestimo(Usuario usuario, Livro livro,LocalDate emp_data,LocalDate emp_data_devolve,int id) throws EmprestimoException, LivroException {
        this.data_emprestimo =emp_data;
        this.data_devolucao = null;
        this.data_devolucao_esperada=emp_data_devolve;
        this.status_emprestimo =0;
        this.usuario = usuario;
        this.livro = livro;
        this.qnt_renovacao=1;
        setId_emprestimo(id);

    }


    /**
     * Método responsável por calcular a multa, é chamado no momento da devolução
     * @param devolvendo data de devolução do livro, vai ser usado na comparação com a data de emprestimo
     */
    public void Calcula_multa(LocalDate devolvendo){
        setData_devolucao(devolvendo);
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


        if(getLivro().getDisponibilidade() && getUsuario().Status1(emp_data) && getLivro().getFila().isEmpty()){

            getLivro().setDisponibilidade(false);
            getUsuario().setHistorico_livro(this);
            getUsuario().setQntd_emprestimo();
            getLivro().setEmprestimo(this);
            DAO.getEmprestimoDAO().create(this);
            DAO.getLivroDAO().update(getLivro());

            DAO.getUsuarioDAO().update(getUsuario());//!NEW!


        }else if(getLivro().getDisponibilidade() && getUsuario().Status1(emp_data) && Objects.requireNonNull(getLivro().getFila().peek()).getId()==getUsuario().getId()){
            getLivro().setDisponibilidade(false);
            getLivro().setEmprestimo(this);
            getUsuario().setHistorico_livro(this);
            getUsuario().setQntd_emprestimo();
            DAO.getEmprestimoDAO().create(this);
            getLivro().getFila().poll();
            DAO.getLivroDAO().update(getLivro());
            DAO.getUsuarioDAO().update(getUsuario());//!NEW!
        }
        else{

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
     * @throws Exception é lançada uma exceção se esta tentando devolver um livro que nao foi emprestado
     */
    public void Devolucao(LocalDate devolve) throws Exception {
        Calcula_multa(devolve);
        getLivro().setDisponibilidade(true);
        getLivro().setEmprestimo(null);
        DAO.getLivroDAO().update(getLivro());

        DAO.getEmprestimoDAO().update(this);
        DAO.getUsuarioDAO().update(getUsuario());
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

        }else if(!getLivro().getFila().isEmpty() && getUsuario().getStatus()){
            getLivro().Reservar_livro(getUsuario(),emp_emprestimo_new);
        }else{
            throw new EmprestimoException(EmprestimoException.UPDATE,this);
        }
    }

    /**
     * Método responsável por fazer a verificao de atraso de um empréstimo
     * @param empres data atual
     * @return retorna 1 se o empréstimo ta ''vencido'' e 0 se ta tudo ok
     */
    public int verificando_data_emprestimo(LocalDate empres){
        int comparacao = empres.compareTo(getData_devolucao_esperada());
        if(comparacao>0 && getStatus_emprestimo()!=2){
            return 1;
        }else {
            return 0;
        }

    }


    /**
     * getter para data de empretismo
     * @return retorna data de emprestimo
     */
    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    /**
     * setter para data de emprestimo
     * @param emp_emprestimo_new data de emprestimo do livro
     * chama uma funcao para calcular a data de devolucao esperada
     */
    public void setData_emprestimo(LocalDate emp_emprestimo_new) {
        this.data_emprestimo = emp_emprestimo_new;
        setData_devolucao_esperada(emp_emprestimo_new);
    }

    /**
     * getter da data de devolucao
     * @return retorna a data de devolucao de um emprestimo
     */
    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    /**
     * setter para data de devolucao
     * @param devolvendo data de devolucao
     */
    public void setData_devolucao(LocalDate devolvendo) {
        this.data_devolucao = devolvendo;
    }

    /**
     * Getter para data de devoluaco esperada
     * @return retorna a data de devolucao calculada com base na data de emprestimo + 7
     */
    public LocalDate getData_devolucao_esperada() {
        return data_devolucao_esperada;
    }

    /**
     * setter data devolucao esperada
     * @param data_emprestimo data do emprestimo
     * calcula a data somando + 7
     */
    public void setData_devolucao_esperada(LocalDate data_emprestimo) {
        this.data_devolucao_esperada = data_emprestimo.plusDays(7);
    }

    /**
     * getter para conseguir o id do emprestimo
     * @return retorna o id do emprestimo
     */
    public int getId_emprestimo() {
        return id_emprestimo;
    }

    /**
     * setter para o id do emprestimo
     * @param id_emprestimo coloca o id do emprestimo
     */
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    /**
     * getter para o status do emprestimo
     * @return retorna o status do emprestimo
     */
    public int getStatus_emprestimo() { //ok??
        return status_emprestimo;
    }

    /**
     * setter para o status do emprestimos
     * @param status_emprestimo coloca o status do emprestimo
     */
    public void setStatus_emprestimo(int status_emprestimo) {
        this.status_emprestimo = status_emprestimo;
    }

    /**
     * getter para o usuario que fez determinado empresitmo
     * @return retorna o usuario do emprestimo
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * setter para o usuario do emprestimo
     * @param usuario usuario que fez o emprestimo
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * getter para o livro emprestado
     * @return retorna o livro emprestado
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * setter para o livro emprestado
     * @param livro livro que foi emprestado
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    /**
     * quantidade de renovacao de um dado emprestimo
     * @return retorna a quantidade de renovacao
     */
    public int getQnt_emprestimo() {
        return qnt_renovacao;
    }

    /**
     * setter para quantidade de renovacao
     */
    public void setQnt_emprestimo() {
        this.qnt_renovacao++;
    }

    /**
     * método equals, estabelece o que faz um objeto ser ''igual'' a outro
     * @param o objeto generico a ser comparado
     * @return uma lista de comparacoes, se todas forem verdadeiras, com base nas condicoes, o objeto é ''igual''
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emprestimo)) return false;
        Emprestimo emprestimo = (Emprestimo) o;
        //return Objects.equals(getStatus_emprestimo(), emprestimo.getStatus_emprestimo()) && Objects.equals(getData_emprestimo(), emprestimo.getData_emprestimo()) && Objects.equals(getData_devolucao_esperada(), emprestimo.getData_devolucao_esperada()) && Objects.equals(getUsuario(), emprestimo.getUsuario()) && Objects.equals(getLivro(), emprestimo.getLivro());
        return Objects.equals(getId_emprestimo(),emprestimo.getId_emprestimo()) && Objects.equals(getUsuario(), emprestimo.getUsuario()) && Objects.equals(getLivro(), emprestimo.getLivro());
    }

    /**
     * hash code para o emprestimo
     * @return valor inteiro que é codigo hash do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(data_emprestimo, data_devolucao, data_devolucao_esperada, id_emprestimo, status_emprestimo, usuario, livro, qnt_renovacao);
    }

    /**
     * método to string para o emprestimo
     * @return retorno dos atributos para facilitar a identificaco
     */
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
