/*
 * MutadorTrocaHorario.java
 * Criado em 2009/12/03 - 10:31
 */
package br.usp.gradescola.mutacao;

import java.util.List;

import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Mutador;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorTrocaHorario implements Mutador {

    private final Sorteador sorte;

    public MutadorTrocaHorario() {
        this(new SorteadorRandom());
    }

    public MutadorTrocaHorario(Sorteador sorte) {
        this.sorte = sorte;
    }

    @Override
    public void alterar(Grade grade) {
        List<Horario> lista = sorte.sortearElementos(2, grade.getProblema().getHorarios());
        grade.permutar(lista.get(0), lista.get(1));
    }
}