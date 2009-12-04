/*
 * MutadorProfessor.java
 * Criado em 2009/12/03 - 10:32
 */
package br.usp.gradescola.mutacao;

import java.util.List;

import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Mutador;
import br.usp.gradescola.estrutura.Professor;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorProfessor implements Mutador {

    private final Sorteador sorte;

    public MutadorProfessor() {
        this(new SorteadorRandom());
    }

    public MutadorProfessor(Sorteador sorte) {
        this.sorte = sorte;
    }

    @Override
    public void alterar(Grade grade) {
        Disciplina disciplina = sorte.sortearElemento(grade.getDisciplinas());
        Professor professor = sorte.sortearElemento(grade.getProfessores());
        grade.atribuir(disciplina, professor);
    }
}