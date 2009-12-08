/*
 * PoolDireto.java
 * Criado em 2009/12/04 - 17:34
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class PoolDireto implements Pool {

    private final List<Grade> pool;

    private final Evolucionador evolucionador;

    private final int tamanho;

    private final GradeComparator comparator;

    private class GradeComparator implements Comparator<Grade> {
        @Override
        public int compare(Grade grade1, Grade grade2) {
            return grade1.compareTo(grade2);
        }
    }

    public PoolDireto(int tamanho, GradeFactory factory, Evolucionador evolucionador) {
        this.evolucionador = evolucionador;
        this.tamanho = tamanho;

        this.pool = new ArrayList<Grade>(tamanho);
        this.comparator = new GradeComparator();

        for (int i = 0; i < tamanho; i++) {
            pool.add(factory.novaGrade());
        }
    }

    @Override
    public Grade grade(int indice) {
        if (indice < 0 || indice > tamanho) throw new IllegalArgumentException();
        return pool.get(indice);
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public void novaGeracao() {
        pool.addAll(evolucionador.populacaoNova(pool));
        Collections.sort(pool, comparator);
        pool.subList(tamanho, pool.size()).clear();
    }

    @Override
    public Iterator<Grade> iterator() {
        return pool.iterator();
    }
}