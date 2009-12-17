/*
 * DisciplinasProfessores.java
 * Criado em 2009/12/16 - 12:20
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Professor;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class DisciplinasProfessores implements Condicao.Numerica {

    private final Map<Disciplina, BigDecimal> disciplinas;

    private final Professor professor;

    private final BigDecimal valorDefault;

    public DisciplinasProfessores(Professor professor, Map<Disciplina, BigDecimal> disciplinas, BigDecimal valorDefault) {
        this.professor = professor;
        this.disciplinas = disciplinas;
        this.valorDefault = valorDefault;
    }

    @Override
    public BigDecimal avaliar(Grade grade) {
        BigDecimal valor = BigDecimal.ZERO;
        Set<Disciplina> alocadas = grade.disciplinasPorProfessor(professor);
        for (Disciplina d : alocadas) {
            BigDecimal v = disciplinas.get(d);
            valor = valor.add(v == null ? valorDefault : v);
        }
        return valor;
    }
}