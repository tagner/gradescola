/*
 * Disciplina.java
 * Criado em 2009/11/12 - 03:00
 */
package br.usp.gradescola.estrutura;

/**
 * Objeto que representa uma disciplina a ser oferecida.
 * @see DisciplinaSimples
 * @author Victor Williams Stafusa da Silva
 */
public interface Disciplina {

    /**
     * Obtém a quantidade de horários que são usados pela disciplina.
     * @return A quantidade de horários que são usados pela disciplina.
     */
    public int getCargaHoraria();

    /**
     * Obtém o nome da disciplina.
     * @return O nome da disciplina.
     */
    public String toString();
}