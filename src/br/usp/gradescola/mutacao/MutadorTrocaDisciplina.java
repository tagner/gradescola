/*
 * MutadorTrocaDisciplina.java
 * Criado em 2009/12/03 - 10:30
 */
package br.usp.gradescola.mutacao;

import java.util.List;

import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Mutador;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorTrocaDisciplina implements Mutador {

    private final Sorteador sorte;

    public MutadorTrocaDisciplina() {
        this(new SorteadorRandom());
    }

    public MutadorTrocaDisciplina(Sorteador sorte) {
        this.sorte = sorte;
    }

    @Override
    public void alterar(Grade grade) {
        List<Disciplina> lista = sorte.sortearElementos(2, grade.getDisciplinas());
        grade.permutar(lista.get(0), lista.get(1));
    }
}