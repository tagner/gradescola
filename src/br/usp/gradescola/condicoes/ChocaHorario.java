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
import java.util.Collection;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class ChocaHorario implements Condicao {
    private final Collection<Disciplina> disciplinas;

    public ChocaHorario(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public ChocaHorario(Disciplina... disciplinas) {
        this.disciplinas = Arrays.asList(disciplinas);
    }

    @Override
    public boolean avaliar(Grade grade) {
        Iterable<Horario> horarios = grade.getHorariosUsados();
        for (Horario horario : horarios) {
            int usado = 0;
            for (Disciplina disciplina : disciplinas) {
                if (grade.tem(disciplina, horario)) usado++;
                if (usado == 2) return true;
            }
        }
        return false;
    }
}