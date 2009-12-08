/*
 * Grade.java
 * Criado em 2009/11/12 - 03:18
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class GradeDireta implements Grade {

    private class GeneHorarioDisciplina implements Cloneable {
        private Horario horario;
        private Disciplina disciplina;

        public GeneHorarioDisciplina() {}

        public Horario getHorario() {
            return horario;
        }

        public Disciplina getDisciplina() {
            return disciplina;
        }

        public void setHorario(Horario horario) {
            this.horario = horario;
        }

        public void setDisciplina(Disciplina disciplina) {
            this.disciplina = disciplina;
        }

        @Override
        public GeneHorarioDisciplina clone() {
            try {
                return (GeneHorarioDisciplina) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    private final Problema problema;
    private final List<GeneHorarioDisciplina> celas;
    private final Map<Disciplina, Professor> professorPorDisciplina;

    public GradeDireta(Problema problema) {
        this.problema = problema;
        this.celas = new LinkedList<GeneHorarioDisciplina>();
        Set<Disciplina> disciplinas = problema.getDisciplinas();
        this.professorPorDisciplina = new HashMap<Disciplina, Professor>(disciplinas.size());

        for (Disciplina d : disciplinas) {
            professorPorDisciplina.put(d, null);
        }
    }

    @Override
    public void atribuir(Disciplina disciplina, Professor professor) {
        professorPorDisciplina.put(disciplina, professor);
    }

    @Override
    public void atribuir(Disciplina disciplina, Iterable<Horario> horarios) {
        Iterator<GeneHorarioDisciplina> it = this.celas.iterator();
        while (it.hasNext()) {
            GeneHorarioDisciplina c = it.next();
            if (c.getDisciplina() == disciplina) it.remove();
        }
        for (Horario h : horarios) {
            GeneHorarioDisciplina c = new GeneHorarioDisciplina();
            c.setHorario(h);
            c.setDisciplina(disciplina);
            celas.add(c);
        }
    }

    @Override
    public List<Disciplina> disciplinasPorHorario(Horario horario) {
        List<Disciplina> resposta = new ArrayList<Disciplina>();
        for (GeneHorarioDisciplina c : celas) {
            if (c.getHorario() == horario) resposta.add(c.getDisciplina());
        }
        return resposta;
    }

    @Override
    public Professor professorDaDisciplina(Disciplina disciplina) {
        return professorPorDisciplina.get(disciplina);
    }

    @Override
    public List<Horario> horariosPorDisciplina(Disciplina disciplina) {
        List<Horario> resposta = new ArrayList<Horario>();
        for (GeneHorarioDisciplina c : celas) {
            if (c.getDisciplina() == disciplina) resposta.add(c.getHorario());
        }
        return resposta;
    }

    @Override
    public Set<Disciplina> disciplinasPorProfessor(Professor professor) {
        Set<Disciplina> resposta = new HashSet<Disciplina>();
        for (Map.Entry<Disciplina, Professor> entry : professorPorDisciplina.entrySet()) {
            if (entry.getValue() == professor) resposta.add(entry.getKey());
        }
        return resposta;
    }

    @Override
    public List<Horario> horariosPorProfessor(Professor professor) {
        List<Horario> resposta = new ArrayList<Horario>();
        for (GeneHorarioDisciplina c : celas) {
            Disciplina disciplina = c.getDisciplina();
            if (professorPorDisciplina.get(disciplina) == professor) resposta.add(c.getHorario());
        }
        return resposta;
    }

    @Override
    public void permutar(Horario horario1, Horario horario2) {
        for (GeneHorarioDisciplina c : celas) {
            if (c.getHorario() == horario1) {
                c.setHorario(horario2);
            } else if (c.getHorario() == horario2) {
                c.setHorario(horario1);
            }
        }
    }

    @Override
    public void permutar(Disciplina disciplina1, Disciplina disciplina2) {
        for (GeneHorarioDisciplina c : celas) {
            if (c.getDisciplina() == disciplina1) {
                c.setDisciplina(disciplina2);
            } else if (c.getDisciplina() == disciplina2) {
                c.setDisciplina(disciplina1);
            }
        }
    }

    @Override
    public void permutar(Professor professor1, Professor professor2) {
        Set<Disciplina> disciplinasDoProfessor1 = new HashSet<Disciplina>();
        Set<Disciplina> disciplinasDoProfessor2 = new HashSet<Disciplina>();
        for (Map.Entry<Disciplina, Professor> entry : professorPorDisciplina.entrySet()) {
            Professor p = entry.getValue();
            if (p == professor1) {
                disciplinasDoProfessor1.add(entry.getKey());
            } else if (p == professor2) {
                disciplinasDoProfessor2.add(entry.getKey());
            }
        }
        for (Disciplina d : disciplinasDoProfessor1) {
            professorPorDisciplina.put(d, professor2);
        }
        for (Disciplina d : disciplinasDoProfessor2) {
            professorPorDisciplina.put(d, professor1);
        }
    }

    @Override
    public Problema getProblema() {
        return problema;
    }

    @Override
    public BigDecimal avaliar() {
        return problema.avaliar(this);
    }

    @Override
    public int compareTo(Grade obj) {
        return avaliar().compareTo(obj.avaliar());
    }

    @Override
    public GradeDireta clone() {
        GradeDireta grade = new GradeDireta(problema);
        for (GeneHorarioDisciplina h : this.celas) {
            grade.celas.add(h.clone());
        }
        for (Map.Entry<Disciplina, Professor> entry : professorPorDisciplina.entrySet()) {
            grade.professorPorDisciplina.put(entry.getKey(), entry.getValue());
        }
        return grade;
    }
}