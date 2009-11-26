/*
 * Problema.java
 * Criado em 2009/11/12 - 03:20
 */
package br.usp.gradescola.estrutura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class Problema {
    private final Set<Horario> horarios;
    private final Set<Professor> professores;
    private final Set<Disciplina> disciplinas;
    private final Set<Sala> salas;
    private final List<Restricao> restricoes;

    private final boolean problemaConsideraSala;
    private final boolean problemaConsideraProfessor;

    public Problema(boolean problemaConsideraSala, boolean problemaConsideraProfessor) {
        this.problemaConsideraSala = problemaConsideraSala;
        this.problemaConsideraProfessor = problemaConsideraProfessor;
        this.horarios = new HashSet<Horario>();
        this.professores = new HashSet<Professor>();
        this.disciplinas = new HashSet<Disciplina>();
        this.salas = new HashSet<Sala>();
        this.restricoes = new ArrayList<Restricao>();
    }

    public void add(Horario horario) {
        horarios.add(horario);
    }

    public void add(Professor professor) {
        professores.add(professor);
    }

    public void add(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void add(Sala sala) {
        salas.add(sala);
    }

    public void add(Restricao restricao) {
        restricoes.add(restricao);
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Set<Professor> getProfessores() {
        return professores;
    }

    public double[] avaliar(Grade grade) {
        double[] avaliacao = new double[2];
        for (Restricao r : restricoes) {
            if (!r.avaliar(grade)) continue;
            avaliacao[0] += r.getCustoReal();
            avaliacao[1] += r.getCustoInfinito();
        }
        return avaliacao;
    }
}