/*
 * MutadorRandom.java
 * Criado em 2009/11/26 - 02:17
 */
package br.usp.gradescola.mutacao;

import java.util.ArrayList;
import java.util.List;

import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Mutador;
import br.usp.gradescola.estrutura.Problema;

import br.usp.gradescola.utilidades.Colecoes;
import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorRandom implements Mutador {

    private final Sorteador sorte;

    private final List<Mutador> mutadores;

    private static List<Mutador> mutadoresPadrao(Problema problema, Sorteador sorte) {
        List<Mutador> lista = new ArrayList<Mutador>();

        lista.add(new MutadorTrocaHorarioDisciplina(sorte));
        lista.add(new MutadorTrocaHorario(sorte));
        lista.add(new MutadorHorario(sorte));

        if (problema.isOtimizarProfessores()) {
            lista.add(new MutadorTrocaProfessor(sorte));
            lista.add(new MutadorProfessor(sorte));
        }

        return lista;
    }

    public MutadorRandom(Sorteador sorte, Mutador... mutadores) {
        this.sorte = sorte;
        this.mutadores = Colecoes.copiarElementos(mutadores);
    }

    public MutadorRandom(Sorteador sorte, Iterable<Mutador> mutadores) {
        this.sorte = sorte;
        this.mutadores = Colecoes.copiarElementos(mutadores);
    }

    public MutadorRandom(Mutador... mutadores) {
        this(new SorteadorRandom(), mutadores);
    }

    public MutadorRandom(Iterable<Mutador> mutadores) {
        this(new SorteadorRandom(), mutadores);
    }

    public MutadorRandom(Problema problema, Sorteador sorte) {
        this(sorte, mutadoresPadrao(problema, sorte));
    }

    public MutadorRandom(Problema problema) {
        this(problema, new SorteadorRandom());
    }

    @Override
    public void alterar(Grade grade) {
        sorte.sortearElemento(mutadores).alterar(grade);
    }
}