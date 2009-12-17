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

    /**
     * Altera a grade atribuindo um professor a uma disciplina. Caso
     * a disciplina já tenha um outro professor, este é "desatribuído"
     * da disciplina.
     * @param disciplina A disciplina cujo professor é atribuído.
     * @param professor O professor que é atribuído à disciplina.
     */
    public void atribuir(Disciplina disciplina, Professor professor);

    /**
     * Altera a grade atribuindo a uma disciplina, um conjunto de
     * horários. Caso a disciplina já tenha outros horários definidos,
     * estes serão redefinidos.
     * @param disciplina A disciplina cujo conjunto de horários é
     * definido.
     * @param horarios Os horários a serem atribuídos para a
     * disciplina.
     */
    public void atribuir(Disciplina disciplina, Iterable<Horario> horarios);

    // Não suportado!
    //public void atribuir(Disciplina disciplina, Horario horario, Sala sala);

    public List<Disciplina> disciplinasPorHorario(Horario horario);

    // Não suportado!
    //public Sala salaLocada(Disciplina disciplina, Horario horario);

    public Professor professorDaDisciplina(Disciplina disciplina);

    public List<Horario> horariosPorDisciplina(Disciplina disciplina);

    public Set<Disciplina> disciplinasPorProfessor(Professor professor);

    public List<Horario> horariosPorProfessor(Professor professor);

    public void permutar(Horario horario1, Horario horario2);

    //public void permutar(Disciplina disciplina1, Disciplina disciplina2);

    public void permutar(Professor professor1, Professor professor2);

    // Não suportado!
    //public void permutar(Sala sala1, Sala sala2);

    public BigDecimal avaliar();

    public Problema getProblema();

    public Grade clone();
}