/*
 * ChocaHorario.java
 * Criado em 2009/11/12 - 07:41
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class ChocaHorario implements Condicao {
    private final Iterable<Disciplina> disciplinas;

    public ChocaHorario(Iterable<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public ChocaHorario(Disciplina... disciplinas) {
        this(Arrays.asList(disciplinas));
    }

    @Override
    public boolean avaliar(Grade grade) {
        for (Horario horario : grade.getHorarios()) {
            int usado = 0;
            List<Disciplina> disciplinasNoHorario = grade.disciplinasPorHorario(horario);

            for (Disciplina disciplina : disciplinas) {
                if (disciplinasNoHorario.contains(disciplina)) usado++;
                if (usado == 2) return true;
            }
        }
        return false;
    }
}