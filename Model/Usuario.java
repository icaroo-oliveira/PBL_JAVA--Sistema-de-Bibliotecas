package Model;

import java.util.ArrayList;
import java.util.Date;


public class Usuario extends Pessoa {
    private String status;
    private ArrayList<Emprestimo> historico_livro;

    private String multa;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Emprestimo> getHistorico_livro() {
        return historico_livro;
    }

    public void setHistorico_livro(ArrayList<Emprestimo> historico_livro) {
        this.historico_livro = historico_livro;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }
}
