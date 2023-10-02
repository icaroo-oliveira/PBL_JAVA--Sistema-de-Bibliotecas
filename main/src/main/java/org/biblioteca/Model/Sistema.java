package org.biblioteca.Model;

/**
 * Classe para implementar o padrão singleton para as classes PesquisaLivro e Relatorios_estatisticas
 */
public class Sistema {
    private static PesquisaLivro pesquisa;
    private static Relatorios_estatiscas relatorio;

    private Sistema() {}

    /**
     * somente uma instancia de PesquiasLivro
     * @return retorna a instancia
     */
    public static PesquisaLivro getPesquisa() {
        if (pesquisa == null) {
            pesquisa = new PesquisaLivro();
        }
        return pesquisa;
    }

    /**
     * somente uma instancia de Relatorios_estatisticas
     * @return retorna a instancia
     */
    public static Relatorios_estatiscas getRelatorio() {
        if (relatorio == null) {
            relatorio = new Relatorios_estatiscas();
        }
        return relatorio;
    }
}
