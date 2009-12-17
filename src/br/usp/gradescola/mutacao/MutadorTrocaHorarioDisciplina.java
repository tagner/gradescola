/*
 * MutadorTrocaHorarioDisciplina.java
 * Criado em 2009/12/17 - 01:02
 */
package br.usp.gradescola.mutacao;

import java.util.List;

import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Mutador;

import br.usp.gradescola.utilidades.Colecoes;
import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorTrocaHorarioDisciplina implements Mutador {

    private final Sorteador sorte;

    public MutadorTrocaHorarioDisciplina() {
        this(new SorteadorRandom());
    }

    public MutadorTrocaHorarioDisciplina(Sorteador sorte) {
        this.sorte = sorte;
    }

    @Override
    public void alterar(Grade grade) {
        List<Disciplina> lista = sorte.sortearElementos(2, grade.getProblema().getDisciplinas());

        Disciplina d1 = lista.get(0);
        Disciplina d2 = lista.get(1);

        List<Horario> horarios1 = Colecoes.copiarElementos(grade.horariosPorDisciplina(d1));
        List<Horario> horarios2 = Colecoes.copiarElementos(grade.horariosPorDisciplina(d2));

        if (horarios1.isEmpty() || horarios2.isEmpty()) return;

        Horario h1 = sorte.sortearElemento(horarios1);
        Horario h2 = sorte.sortearElemento(horarios2);
        horarios1.remove(h1);
        horarios2.remove(h2);
        horarios1.add(h2);
        horarios2.add(h1);

        grade.atribuir(d1, horarios1);
        grade.atribuir(d2, horarios2);
    }
}