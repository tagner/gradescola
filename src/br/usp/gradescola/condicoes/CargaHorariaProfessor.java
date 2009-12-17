/*
 * CargaHorariaDisciplina.java
 * Criado em 2009/12/04 - 00:26
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Professor;

import java.math.BigDecimal;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CargaHorariaProfessor implements Condicao.Numerica {

    private final Professor professor;

    public CargaHorariaProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public BigDecimal avaliar(Grade grade) {
        return BigDecimal.valueOf(grade.horariosPorProfessor(professor).size());
    }
}