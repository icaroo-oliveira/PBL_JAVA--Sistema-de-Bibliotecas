package Model;

public class Sistema {
    private static PesquisaLivro pesquisa;
    private static Relatorios_estatiscas relatorio;

    private Sistema() {}

    public static PesquisaLivro getPesquisa() {
        if (pesquisa == null) {
            pesquisa = new PesquisaLivro();
        }
        return pesquisa;
    }

    public static Relatorios_estatiscas getRelatorio() {
        if (relatorio == null) {
            relatorio = new Relatorios_estatiscas();
        }
        return relatorio;
    }
}
