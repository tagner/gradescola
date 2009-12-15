/*
 * Colecoes.java
 * Criado em 2009/12/03 - 10:15
 */
package br.usp.gradescola.utilidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe utilitária contendo métodos úteis para manipular coleções.
 * @author Victor Williams Stafusa da Silva
 */
public final class Colecoes {

    /**
     * Construtor privado para impedir a instanciação. Esta classe não
     * deve ser instanciável.
     */
    private Colecoes() {}

    /**
     * Cria uma lista contendo os elementos informados nos parâmetros.
     * Este método, diferentemente do método
     * {@linkplain java.util.Arrays.asList(E...)} retorna uma lista
     * que pode ser modificada sem afetar o array original e que não
     * tem tamanho fixo. O array correspondente aos parâmetros é
     * independente à lista retornada, o que significa que alterações
     * em um não se refletem no outro.
     * @param it Os elementos a serem colocados na lista.
     * @param <E> O tipo dos elementos.
     * @return Uma lista contendo os elementos informados nos
     * parâmetros, ou {@code null} se o parâmetro for {@code null}.
     */
    public static <E> List<E> copiarElementos(E... it) {
        // Verifica se é nulo antes de tudo.
        if (it == null) return null;

        // Cria uma lista e adiciona todos os elementos do array.
        List<E> lista = new ArrayList<E>(it.length);
        Collections.addAll(lista, it);
        return lista;
    }

    /**
     * Copia uma lista dada. Para fazer esta cópia, cria uma lista e
     * insere nela os mesmos elementos do {@code Iterable} informado
     * no parâmetro. O {@code Iterable} informado no parâmetro é
     * independente à lista retornada, o que significa que alterações
     * em um não se refletem no outro.
     * @param it O {@code Iterable} de onde os elementos serão
     * copiados. Pode ser {@code null}.
     * @param <E> O tipo dos elementos.
     * @return Uma lista contendo os mesmos elementos do
     * {@code Iterable} do parâmetro {@code it}, ou {@code null} se
     * o parâmetro {@code it} for {@code null}.
     */
    public static <E> List<E> copiarElementos(Iterable<E> it) {
        // Verifica se é nulo antes de tudo.
        if (it == null) return null;

        // Verifica se é uma Collection e usa o addAll se for.
        if (it instanceof Collection) {
            List<E> lista = new ArrayList<E>(((Collection<E>) it).size());
            lista.addAll((Collection<E>) it);
            return lista;
        }

        // Não é Collection, então acrescenta os elementos um a um.
        List<E> lista = new ArrayList<E>();
        for (E elem : it) {
            lista.add(elem);
        }
        return lista;
    }

    /**
     * Cria um conjunto contendo os elementos informados nos
     * parâmetros, não permitindo repetições no conjunto retornado.
     * Este método, diferentemente do método
     * {@linkplain java.util.Arrays.asList(E...)} retorna um conjunto
     * que pode ser modificado sem afetar o array original e que não
     * tem tamanho fixo. O array correspondente aos parâmetros é
     * independente ao conjunto retornado, o que significa que
     * alterações em um não se refletem no outro.
     * A ordem dos elementos não é garantida, o que significa que no
     * conjunto retornado, os elementos podem estar em qualquer ordem
     * arbitrária e aleatória.
     * @param it Os elementos a serem colocados na lista.
     * @param <E> O tipo dos elementos.
     * @return Um conjunto contendo todos os elementos informados nos
     * parâmetros, ou {@code null} se o parâmetro for {@code null}.
     * Tal conjunto não contém itens repetidos. A ordem dos elementos
     * no conjunto retornado não é garantida.
     */
    public static <E> Set<E> copiarUnicos(E... it) {
        // Verifica se é nulo antes de tudo.
        if (it == null) return null;

        // Cria um HashSet e adiciona todos os elementos do array.
        Set<E> lista = new HashSet<E>(it.length);
        Collections.addAll(lista, it);
        return lista;
    }

    /**
     * Cria uma cópia de uma lista dada e a retorna em um conjunto,
     * evitando-se elementos repetidos. Para fazer esta cópia, cria
     * uma lista e insere nela os mesmos elementos do {@code Iterable}
     * informado no parâmetro. O {@code Iterable} informado no
     * parâmetro é independente à lista retornada, o que significa que
     * alterações em um não se refletem no outro.
     * A ordem dos elementos não é garantida, o que significa que no
     * conjunto retornado, os elementos podem estar em qualquer ordem
     * arbitrária e aleatória.
     * @param it O {@code Iterable} de onde os elementos serão
     * copiados. Pode ser {@code null}.
     * @param <E> O tipo dos elementos.
     * @return Um conjunto contendo os mesmos elementos do
     * {@code Iterable} do parâmetro {@code it}, ou {@code null} se
     * o parâmetro {@code it} for {@code null}. Tal conjunto não
     * contém itens repetidos. A ordem dos elementos no conjunto
     * retornado não é garantida.
     */
    public static <E> Set<E> copiarUnicos(Iterable<E> it) {
        // Verifica se é nulo antes de tudo.
        if (it == null) return null;

        Set<E> lista = new HashSet<E>();

        // Verifica se é uma Collection e usa o addAll se for.
        if (it instanceof Collection) {
            lista.addAll((Collection<E>) it);
            return lista;
        }

        // Não é Collection, então acrescenta os elementos um a um.
        for (E elem : it) {
            lista.add(elem);
        }
        return lista;
    }
}