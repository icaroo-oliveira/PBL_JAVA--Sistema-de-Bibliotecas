package org.biblioteca.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe responável pela criação de usuários
 */
public class Usuario extends Pessoa {
    /**
     * status do usuario
     */
    private boolean status;
    /**
     * lista com os emprestimo do usuario
     */
    private List<Emprestimo> historico_livro;

    /**
     * multa do usuario, em dias
     */
    private int multa;

    /**
     * data da multa do usuario
     */
    private LocalDate data_multa;

    /**
     * quantidade de emprestimo simultaneamente
     */
    private int qntd_emprestimo;

    /**
     *
     * @param nome nome do usuario
     * @param endereco endereco do usuario
     * @param telefone telefone do usuario
     * @param id id do usuario
     */
    public Usuario(String nome,String endereco,String telefone,int id) {
        super(nome,endereco,telefone,id);
        this.status = true;
        this.historico_livro = new ArrayList<Emprestimo>();
        this.multa = 0;
        this.qntd_emprestimo=0;
        this.data_multa=null;


    }


    /**
     *
     * @param empres data da tentativa de fazer emprestimo ou reservar um livro, sera usada para saber a situacao atual do usuario
     * @return retorna true se tudo ta ok com usuario, false caso contrario
     */
    public boolean Status1(LocalDate empres){
        int n = getHistorico_livro().size();
        int cont=0;

        if(!getHistorico_livro().isEmpty()){

            for (int i = 1; i < 4; i++) {
                try {
                    cont+=getHistorico_livro().get(n-i).verificando_data_emprestimo(empres);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }

        if(getQntd_emprestimo()==3 || cont>0){
            setStatus(false);
        }
        else if(getMulta()!=0){
            if((int) ChronoUnit.DAYS.between(getData_multa(),empres)>getMulta()){
                setStatus(true);
                setMulta_1(0);
                setData_multa(null);
            }else{
                setStatus(false);
            }
        }
        return getStatus();
    }

    /**
     * método para bloquear o usuario
     */
    public void Bloqueando(){
        setStatus(false);
    }

    /**
     * getter para o status do usuario
     * @return retorna o status do usuario
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * setter para o status do usuario
     * @param status status do usuario
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * getter para o historico de emprestimo do usuario
     * @return retorna o historico de emprestimo
     */
    public List<Emprestimo> getHistorico_livro() {
        return historico_livro;
    }

    /**
     * método para adicionar emprestimo no historico do usuario
     * @param emprestimo emprestimo a ser adicionado
     */
    public void setHistorico_livro(Emprestimo emprestimo) {
        this.historico_livro.add(emprestimo);
    }

    /**
     * getter para multa do usuario
     * @return retorna a multa do usuario
     */
    public int getMulta() {
        return multa;
    }

    /**
     * método para acumular a multa do usuario
     * @param multa multa do usuario
     */
    public void setMulta(int multa) {
        this.multa += multa;
    }

    /**
     * setter para multa do usuario
     * @param multa multa do usuario
     */
    public void setMulta_1(int multa) {
        this.multa = multa;
    }

    /**
     * getter para data de multa do usuario
     * @return retorna a data de multa do usuario(ultima devolucao)
     */
    public LocalDate getData_multa() {
        return data_multa;
    }

    /**
     * setter para data de multa do usuario
     * @param data_multa data da ultima devolucao do usuario(se foi multado)
     */
    public void setData_multa(LocalDate data_multa) {
        this.data_multa = data_multa;
    }

    /**
     * getter para quantidade de empréstimo
     * @return retorna quantidade de emprestimo simultanea
     */
    public int getQntd_emprestimo() {
        return qntd_emprestimo;
    }

    /**
     * método to string
     * @return os atributos do usuario para faciliar no print
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nome=" + getNome() +
                ", endereco=" + getEndereco() +
                ", telefone=" + getTelefone() +
                ", id=" + getId() +
                '}';
    }


    /**
     * método equals, o que faz um objeto usuario ser igual a outro
     * @param o objeto generico
     * @return retorna a comparacao
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(getId(), usuario.getId());

         //return Objects.equals(getId(), usuario.getId()) && Objects.equals(getNome(), usuario.getNome()) && Objects.equals(getEndereco(), usuario.getEndereco()) && Objects.equals(getTelefone(), usuario.getTelefone());
    }


    /**
     * hash code
     * @return numero hash do usuario
     */
    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getHistorico_livro(), getMulta(), getData_multa(), getQntd_emprestimo());
    }

    /**
     * métodos para acumular empréstimos
     */
    public void setQntd_emprestimo() {
        this.qntd_emprestimo++;
    }
}

