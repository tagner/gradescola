/*
 * CargaHorariaDisciplina.java
 * Criado em 2009/12/04 - 00:26
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
public class CargaHorariaDisciplina implements Condicao.Real {

    private final Iterable<Disciplina> disciplinas;

    public CargaHorariaDisciplina(Iterable<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public CargaHorariaDisciplina(Disciplina... disciplinas) {
        this(Arrays.asList(disciplinas));
    }

    @Override
    public BigDecimal avaliar(Grade grade) {
        int erro = 0;
        for (Disciplina d : grade.getDisciplinas()) {
            int h = grade.horariosPorDisciplina(d).size();
            erro += (h < 0 ? -h : h);
        }
        return BigDecimal.valueOf(erro);
    }
}