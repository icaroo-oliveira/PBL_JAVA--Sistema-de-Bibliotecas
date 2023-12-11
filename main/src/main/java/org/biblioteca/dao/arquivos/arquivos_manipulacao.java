package org.biblioteca.dao.arquivos;

import java.io.*;
import java.util.List;

/**
 * Classe para manipulação de arquivos, ela é generica e pode ser aplciada para um dos tipos de dados: Emprestimo, Livro, Usuario e Operadores
 * @param <T> parametro que pode ser qualquer uma das classes ja citadas
 */
public class arquivos_manipulacao<T> {

    /**
     * Classe para salvar o arquivo/criar um arquivo
     * @param obj nome do arquivo
     * @param lista Lista contendo todos objetos que serão salvos no arquivo
     */
    public void Salvando(String obj, List<T> lista) {

        ObjectOutputStream file = null;
        try {
            file = new ObjectOutputStream(new FileOutputStream(obj));
            file.writeObject(lista);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para para carregar/abrir arquivos, também usa generics, retorna um detemrinado tipo de lista, podendo ser:
     * Emprestimo, Livro, Usuario e Operadores
     * @param obj nome do arquivo a ser aberto/carregado
     * @param lista lista em branco passada para o caso daquele arquivo n existir ser retornado uma lista vazia
     * @return os retornos sao listas vazias ou contendo o determinado tipo de objeto
     */
    public List<T> Carregando(String obj,List<T> lista) {
        ObjectInputStream objectIn = null;
        try {
            objectIn = new ObjectInputStream(new FileInputStream(obj));
            List<T> listar = (List<T>) objectIn.readObject();
            objectIn.close();
            return listar;

        } catch (IOException | ClassNotFoundException e) {
            Salvando(obj,lista);
            return lista;
        }
    }

}
