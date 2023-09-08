package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Usuario extends Pessoa {
    private String status;
    private List<Emprestimo> historico_livro;
    private int multa;

    public Usuario() {
        this.status = "livre";
        this.historico_livro = new ArrayList<Emprestimo>();
        this.multa = 0;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Emprestimo> getHistorico_livro() {
        return historico_livro;
    }

    public void setHistorico_livro(Emprestimo emprestimo) {
        this.historico_livro.add(emprestimo);
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }
}
