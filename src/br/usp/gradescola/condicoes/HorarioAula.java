/*
 * HorarioAula.java
 * Criado em 2009/11/16 - 11:32
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Grade;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class HorarioAula implements Condicao.Booleana {

    private final Disciplina disciplina;

    private final Horario horario;

    public HorarioAula(Disciplina disciplina, Horario horario) {
        this.disciplina = disciplina;
        this.horario = horario;
    }

    public boolean avaliar(Grade grade) {
        return grade.horariosPorDisciplina(disciplina).contains(horario);
    }
}