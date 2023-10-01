package excepctions;

import Model.Emprestimo;

public class EmprestimoException extends Exception{

    public static final String CREATE = "Operação de Emprestimo não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";

    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";

    public static final String FIND = "Não foi possível encontrar o usuário.";


    private Emprestimo emprestimo;

    private int id;

    public EmprestimoException(String message,Emprestimo emprestimo) {
        super(message);
        this.emprestimo = emprestimo;
    }

    public EmprestimoException(String message, int id) {
        super(message);
        this.id=id;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
