/*
 * CruzadorProfessor.java
 * Criado em 2009/12/03 - 19:31
 */
package br.usp.gradescola.cruzamento;

import br.usp.gradescola.estrutura.Cruzador;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.GradeFactory;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CruzadorProfessor implements Cruzador {

    private final Sorteador sorte;

    private final GradeFactory factory;

    public CruzadorProfessor(GradeFactory factory, Sorteador sorte) {
        this.factory = factory;
        this.sorte = sorte;
    }

    public CruzadorProfessor(GradeFactory factory) {
        this(factory, new SorteadorRandom());
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        Grade base = sorte.sortearElemento(grade1, grade2);
        Grade g = factory.novaGrade();
        for (Disciplina d : g.getDisciplinas()) {
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).professorDaDisciplina(d));
            g.atribuir(d, base.horariosPorDisciplina(d));
        }
        return g;
    }
}