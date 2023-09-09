package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Usuario extends Pessoa {
    private boolean status;
    private List<Emprestimo> historico_livro;
    private int multa;

    private LocalDate data_multa;

    private int qntd_emprestimo;

    public Usuario(String nome) {
        super(nome);
        this.status = true;
        this.historico_livro = new ArrayList<Emprestimo>();
        this.multa = 0;
        this.qntd_emprestimo=0;
        this.data_multa=null;

    }




    public boolean Status1(){
        if(getQntd_emprestimo()==3){
            setStatus(false);
        } else if(getData_multa()!=null){
            if((int) ChronoUnit.DAYS.between(getData_multa(),LocalDate.now())>getMulta()){
                setStatus(true);
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
        this.multa = multa;
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

