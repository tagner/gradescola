/*
 * ChoqueHorarioProfessor.java
 * Criado em 2009/12/04 - 14:17
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Problema;
import br.usp.gradescola.estrutura.Professor;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class ChoqueHorarioProfessor implements Condicao.Numerica {

    private final Iterable<Professor> professores;

    public ChoqueHorarioProfessor(Problema problema) {
        this.professores = problema.getProfessores();
    }

    public ChoqueHorarioProfessor(Iterable<Professor> professores) {
        this.professores = professores;
    }

    public ChoqueHorarioProfessor(Professor... professores) {
        this(Arrays.asList(professores));
    }

    @Override
    public BigDecimal avaliar(Grade grade) {
        int contagem = 0;
        for (Professor p : professores) {
            List<Horario> horarios = grade.horariosPorProfessor(p);
            Set<Horario> contados = new HashSet<Horario>(horarios.size());
            for (Horario h : horarios) {
                if (contados.contains(h)) {
                    contagem++;
                } else {
                    contados.add(h);
                }
            }
        }
        return BigDecimal.valueOf(contagem);
    }
}