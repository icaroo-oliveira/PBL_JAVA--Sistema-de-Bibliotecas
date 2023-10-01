package org.biblioteca.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Usuario extends Pessoa {
    private boolean status;
    private List<Emprestimo> historico_livro;
    private int multa;

    private LocalDate data_multa;

    private int qntd_emprestimo;

    public Usuario(String nome,String endereco,String num_identifica,String telefone) {
        super(nome,endereco,num_identifica,telefone);
        this.status = true;
        this.historico_livro = new ArrayList<Emprestimo>();
        this.multa = 0;
        this.qntd_emprestimo=0;
        this.data_multa=null;

    }




    public boolean Status1(LocalDate empres){
        int n = getHistorico_livro().size();
        int cont=0;

        if(!getHistorico_livro().isEmpty()){

            //  \/ (???????????????????)
            //Emprestimo ultimo = getHistorico_livro().get(n-1);
            //Emprestimo penultimo = getHistorico_livro().get(n-2);
            //Emprestimo antepenultimo = getHistorico_livro().get(n-3);


            for (int i = 1; i < 4; i++) {
                try {
                    cont+=getHistorico_livro().get(n-i).verificando_data_emprestimo(empres);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }
        System.out.println(cont);

        /*if(cont==0){
            System.out.println("ddeboa");
        }else {
            System.out.println("its over");
        }*/


        if(getQntd_emprestimo()==3 || cont>0){
            setStatus(false);
        } //else if(getData_multa()!=null){ <<<<<<<<<<<<ANTES ERA ASSIM
        else if(getMulta()!=0){
            if((int) ChronoUnit.DAYS.between(getData_multa(),LocalDate.now())>getMulta()){
                setStatus(true);
                setMulta(0);// essa linha n existia antes<<<
                setData_multa(null);
            }else{
                setStatus(false);
            }
        }
        return getStatus();
    }

    public void Bloqueando(){
        setStatus(false);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Emprestimo> getHistorico_livro() {
        return historico_livro;
    }

    public void setHistorico_livro(Emprestimo emprestimo) {//se ligar nisso
        this.historico_livro.add(emprestimo); //antes
        //depois \/
        //getHistorico_livro().add(emprestimo);
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa += multa;
    }

    public LocalDate getData_multa() {
        return data_multa;
    }

    public void setData_multa(LocalDate data_multa) {
        this.data_multa = data_multa;
    }

    public int getQntd_emprestimo() {
        return qntd_emprestimo;
    }

    public void setQntd_emprestimo() {
        this.qntd_emprestimo++;
    }
}

