/*
 * MutadorTrocaProfessor.java
 * Criado em 2009/12/03 - 10:28
 */
package br.usp.gradescola.mutacao;

import java.util.List;

import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Mutador;
import br.usp.gradescola.estrutura.Professor;
import br.usp.gradescola.estrutura.Problema;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorTrocaProfessor implements Mutador {

    private final Sorteador sorte;

    public MutadorTrocaProfessor() {
        this(new SorteadorRandom());
    }

    public MutadorTrocaProfessor(Sorteador sorte) {
        this.sorte = sorte;
    }

    @Override
    public void alterar(Grade grade) {
        List<Professor> lista = sorte.sortearElementos(2, grade.getProblema().getProfessores());
        grade.permutar(lista.get(0), lista.get(1));
    }
}