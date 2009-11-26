/*
 * MinistraAula.java
 * Criado em 2009/11/12 - 07:43
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Professor;
import java.util.Collection;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MinistraAula implements Condicao {
    private final Disciplina disciplina;
    private final Professor professor;

    public MinistraAula(Professor professor, Disciplina disciplina) {
        this.professor = professor;
        this.disciplina = disciplina;
    }

    public boolean avaliar(Grade grade) {
        return grade.professorDaDisciplina(disciplina) == professor;
    }
}