/*
 * MutadorHorario.java
 * Criado em 2009/12/03 - 10:34
 */
package br.usp.gradescola.mutacao;

import java.util.List;
import java.util.Set;

import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Mutador;
import br.usp.gradescola.estrutura.Problema;

import br.usp.gradescola.utilidades.Colecoes;
import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorHorario implements Mutador {

    private final Sorteador sorte;

    public MutadorHorario() {
        this(new SorteadorRandom());
    }

    public MutadorHorario(Sorteador sorte) {
        this.sorte = sorte;
    }

    @Override
    public void alterar(Grade grade) {
        Problema problema = grade.getProblema();
        Disciplina disciplina = sorte.sortearElemento(problema.getDisciplinas());

        List<Horario> horarios = grade.horariosPorDisciplina(disciplina);
        Set<Horario> todos = problema.getHorarios();
        List<Horario> substitutos = Colecoes.copiarElementos(todos);
        List<Horario> novos = Colecoes.copiarElementos(horarios);

        if (horarios.isEmpty()) return;

        substitutos.removeAll(horarios);

        novos.remove(sorte.sortearElemento(horarios));
        novos.add(sorte.sortearElemento(substitutos));

        grade.atribuir(disciplina, novos);
    }
}