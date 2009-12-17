/*
 * DisciplinasTemHorarios.java
 * Criado em 2009/11/16 - 11:32
 */
package br.usp.gradescola.condicoes;

import java.math.BigDecimal;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Grade;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class DisciplinasTemHorarios implements Condicao.Numerica {

    private final Iterable<Disciplina> disciplinas;

    private final Iterable<Horario> horarios;

    public DisciplinasTemHorarios(Iterable<Disciplina> disciplinas, Iterable<Horario> horarios) {
        this.disciplinas = disciplinas;
        this.horarios = horarios;
    }

    public BigDecimal avaliar(Grade grade) {
        int contador = 0;
        for (Disciplina d : disciplinas) {
            for (Horario h : horarios) {
                if (grade.horariosPorDisciplina(d).contains(h)) contador++;
            }
        }
        return BigDecimal.valueOf(contador);
    }
}