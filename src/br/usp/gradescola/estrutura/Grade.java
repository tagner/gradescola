/*
 * Grade.java
 * Criado em 2009/11/12 - 03:18
 */
package br.usp.gradescola.estrutura;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class Grade {
    private final Problema problema;

    private Map<Disciplina, Professor> ministrantes;
    private Map<Horario, Map<Sala, Disciplina>> aulas;
    private Map<Horario, List<Disciplina>> disciplinasPorHorario;
    private Map<Disciplina, List<Horario>> horariosPorDisciplina;
    private Map<Professor, List<Horario>> horariosPorProfessor;

    public Grade(Problema problema) {
        this.problema = problema;
    }

    public boolean tem(Professor professor, Disciplina disciplina) {
        return ministrantes.get(disciplina) == professor;
    }

    public boolean tem(Professor professor, Horario horario) {
        return horariosPorProfessor.get(professor).contains(horario);
    }

    public boolean tem(Disciplina disciplina, Horario horario) {
        return horariosPorDisciplina.get(disciplina).contains(horario);
    }
}