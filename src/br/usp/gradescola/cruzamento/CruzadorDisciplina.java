/*
 * CruzadorDisciplina.java
 * Criado em 2009/12/03 - 19:28
 */
package br.usp.gradescola.cruzamento;

import br.usp.gradescola.estrutura.Cruzador;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Problema;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CruzadorDisciplina implements Cruzador {

    private final Sorteador sorte;

    private final Problema problema;

    public CruzadorDisciplina(Problema problema, Sorteador sorte) {
        this.problema = problema;
        this.sorte = sorte;
    }

    public CruzadorDisciplina(Problema problema) {
        this(problema, new SorteadorRandom());
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        Grade base = sorte.sortearElemento(grade1, grade2);
        Grade g = problema.novaGrade();
        for (Disciplina d : problema.getDisciplinas()) {
            g.atribuir(d, base.professorDaDisciplina(d));
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).horariosPorDisciplina(d));
        }
        return g;
    }
}