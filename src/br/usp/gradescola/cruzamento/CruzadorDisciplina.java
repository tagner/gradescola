/*
 * CruzadorDisciplina.java
 * Criado em 2009/12/03 - 19:28
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
public class CruzadorDisciplina implements Cruzador {

    private final Sorteador sorte;

    private final GradeFactory factory;

    public CruzadorDisciplina(GradeFactory factory, Sorteador sorte) {
        this.factory = factory;
        this.sorte = sorte;
    }

    public CruzadorDisciplina(GradeFactory factory) {
        this(factory, new SorteadorRandom());
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        Grade base = sorte.sortearElemento(grade1, grade2);
        Grade g = factory.novaGrade();
        for (Disciplina d : g.getDisciplinas()) {
            g.atribuir(d, base.professorDaDisciplina(d));
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).horariosPorDisciplina(d));
        }
        return g;
    }
}