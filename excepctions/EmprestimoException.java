package excepctions;

import Model.Emprestimo;

public class EmprestimoException extends Exception{

    public static final String CREATE = "Operação de CRIAÇÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";

    private Emprestimo emprestimo;


    public EmprestimoException(String message,Emprestimo emprestimo) {
        super(message);
        this.emprestimo = emprestimo;
    }
}
