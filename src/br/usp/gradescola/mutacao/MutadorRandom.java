/*
 * MutadorRandom.java
 * Criado em 2009/11/26 - 02:17
 */
package br.usp.gradescola.mutacao;

import java.util.Collection;
import java.util.List;

import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Mutador;
import br.usp.gradescola.estrutura.Professor;

import br.usp.gradescola.utilidades.Colecoes;
import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorRandom implements Mutador {

    private final Sorteador sorte;

    private final List<Mutador> mutadores;

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

    public MutadorRandom(Sorteador sorte) {
        this(sorte, new MutadorTrocaProfessor(sorte),
                    new MutadorTrocaDisciplina(sorte),
                    new MutadorTrocaHorario(sorte),
                    new MutadorProfessor(sorte),
                    new MutadorHorario(sorte));
    }

    public MutadorRandom() {
        this(new SorteadorRandom());
    }

    @Override
    public void alterar(Grade grade) {
        sorte.sortearElemento(mutadores).alterar(grade);
    }
}