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
 * @author Victor Williams Stafusa da Silva
 */
public final class Colecoes {

    private Colecoes() {}

    public static <E> List<E> copiarElementos(Collection<E> it) {
        List<E> lista = new ArrayList<E>(it.size());
        lista.addAll(it);
        return lista;
    }

    public static <E> List<E> copiarElementos(E... it) {
        List<E> lista = new ArrayList<E>(it.length);
        Collections.addAll(lista, it);
        return lista;
    }

    public static <E> List<E> copiarElementos(Iterable<E> it) {
        if (it instanceof Collection) {
            return copiarElementos((Collection<E>) it);
        }

        List<E> lista = new ArrayList<E>();
        for (E elem : it) {
            lista.add(elem);
        }
        return lista;
    }

    public static <E> Set<E> copiarUnicos(Collection<E> it) {
        Set<E> lista = new HashSet<E>();
        lista.addAll(it);
        return lista;
    }

    public static <E> Set<E> copiarUnicos(E... it) {
        Set<E> lista = new HashSet<E>(it.length);
        Collections.addAll(lista, it);
        return lista;
    }

    public static <E> Set<E> copiarUnicos(Iterable<E> it) {
        if (it instanceof Collection) {
            return copiarUnicos((Collection<E>) it);
        }

        Set<E> lista = new HashSet<E>();
        for (E elem : it) {
            lista.add(elem);
        }
        return lista;
    }
}