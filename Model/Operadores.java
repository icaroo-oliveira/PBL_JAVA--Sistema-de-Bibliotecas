package Model;

public class Operadores extends Pessoa{
    private String cargo;
    private String senha_de_acesso;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha_de_acesso() {
        return senha_de_acesso;
    }

    public void setSenha_de_acesso(String senha_de_acesso) {
        this.senha_de_acesso = senha_de_acesso;
    }
}
