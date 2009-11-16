/*
 * Problema.java
 * Criado em 2009/11/12 - 03:20
 */
package br.usp.gradescola.estrutura;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class Problema implements GradeFactory {
    private final Collection<Horario> horarios;
    private final Collection<Professor> professores;
    private final Collection<Disciplina> disciplinas;
    private final Collection<Sala> salas;
    private final Collection<Restricao> restricoes;

    private final boolean problemaConsideraSala;
    private final boolean problemaConsideraProfessor;
    private final GradeFactory decorated;

    public Problema(boolean problemaConsideraSala, boolean problemaConsideraProfessor, GradeFactory decorated) {
        if (decorated == null) throw new IllegalArgumentException();
        this.problemaConsideraSala = problemaConsideraSala;
        this.problemaConsideraProfessor = problemaConsideraProfessor;
        this.horarios = new ArrayList<Horario>();
        this.professores = new ArrayList<Professor>();
        this.disciplinas = new ArrayList<Disciplina>();
        this.salas = new ArrayList<Sala>();
        this.restricoes = new ArrayList<Restricao>();
        this.decorated = decorated;
    }

    public void add(Horario horario) {
        horarios.add(horario);
    }

    public void add(Professor professor) {
        if (!problemaConsideraProfessor) throw new UnsupportedOperationException();
        professores.add(professor);
    }

    public void add(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void add(Sala sala) {
        if (!problemaConsideraSala) throw new UnsupportedOperationException();
        salas.add(sala);
    }

    public void add(Restricao restricao) {
        restricoes.add(restricao);
    }

    @Override
    public Grade novaGrade() {
        return decorated.novaGrade();
    }
}