/*
 * EvolucionadorRandom.java
 * Criado em 2009/12/07 - 21:54
 */
package br.usp.gradescola.estrutura;

import java.util.ArrayList;
import java.util.List;

import br.usp.gradescola.utilidades.Colecoes;
import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class EvolucionadorRandom implements Evolucionador {

    private final int numeroMutacoes;

    private final int numeroCruzamentos;

    private final Mutador mutador;

    private final Cruzador cruzador;

    private final Sorteador sorte;

    public EvolucionadorRandom(Sorteador sorte, int numeroMutacoes, int numeroCruzamentos, Mutador mutador, Cruzador cruzador) {
        this.sorte = sorte;
        this.numeroMutacoes = numeroMutacoes;
        this.numeroCruzamentos = numeroCruzamentos;
        this.mutador = mutador;
        this.cruzador = cruzador;
    }

    public EvolucionadorRandom(int numeroMutacoes, int numeroCruzamentos, Mutador mutador, Cruzador cruzador) {
        this(new SorteadorRandom(), numeroMutacoes, numeroCruzamentos, mutador, cruzador);
    }

    @Override
    public List<Grade> populacaoNova(List<Grade> populacaoAntiga) {
        List<Grade> nova = new ArrayList<Grade>(numeroMutacoes + numeroCruzamentos);

        for (int i = 0; i < numeroMutacoes; i++) {
            Grade grade = sorte.sortearElemento(populacaoAntiga).clone();
            mutador.alterar(grade);
            nova.add(grade);
        }

        for (int i = 0; i < numeroCruzamentos; i++) {
            List<Grade> pais = sorte.sortearElementos(2, populacaoAntiga);
            nova.add(cruzador.cruzar(pais.get(0), pais.get(1)));
        }

        return nova;
    }
}