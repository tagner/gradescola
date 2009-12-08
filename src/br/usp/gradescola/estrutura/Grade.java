/*
 * Grade.java
 * Criado em 2009/11/12 - 03:18
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

import java.util.List;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public interface Grade extends Cloneable, Comparable<Grade> {

    public void atribuir(Disciplina disciplina, Professor professor);

    public void atribuir(Disciplina disciplina, Iterable<Horario> horarios);

    public List<Disciplina> disciplinasPorHorario(Horario horario);

    public Professor professorDaDisciplina(Disciplina disciplina);

    public List<Horario> horariosPorDisciplina(Disciplina disciplina);

    public Set<Disciplina> disciplinasPorProfessor(Professor professor);

    public List<Horario> horariosPorProfessor(Professor professor);

    public void permutar(Horario horario1, Horario horario2);

    public void permutar(Disciplina disciplina1, Disciplina disciplina2);

    public void permutar(Professor professor1, Professor professor2);

    public BigDecimal avaliar();

    public Problema getProblema();

    public Grade clone();
}