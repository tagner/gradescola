/*
 * ChoqueHorarioDisciplina.java
 * Criado em 2009/11/12 - 07:41
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class ChoqueHorarioDisciplina implements Condicao.Real {

    private final Iterable<Disciplina> disciplinas;

    public ChoqueHorarioDisciplina(Iterable<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public ChoqueHorarioDisciplina(Disciplina... disciplinas) {
        this(Arrays.asList(disciplinas));
    }

    @Override
    public BigDecimal avaliar(Grade grade) {
        int choques = 0;
        for (Horario horario : grade.getProblema().getHorarios()) {
            boolean usado = false;
            List<Disciplina> disciplinasNoHorario = grade.disciplinasPorHorario(horario);

            for (Disciplina disciplina : disciplinas) {
                if (disciplinasNoHorario.contains(disciplina)) {
                    if (usado) {
                        choques++;
                    } else {
                        usado = true;
                    }
                }
            }
        }
        return BigDecimal.valueOf(choques);
    }
}