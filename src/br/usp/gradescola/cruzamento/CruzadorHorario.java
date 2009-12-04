/*
 * CruzadorHorario.java
 * Criado em 2009/12/03 - 10:51
 */
package br.usp.gradescola.cruzamento;

import br.usp.gradescola.estrutura.Cruzador;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.GradeFactory;
import br.usp.gradescola.estrutura.Horario;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CruzadorHorario implements Cruzador {

    private final Sorteador sorte;

    private final GradeFactory factory;

    public CruzadorHorario(GradeFactory factory, Sorteador sorte) {
        this.factory = factory;
        this.sorte = sorte;
    }

    public CruzadorHorario(GradeFactory factory) {
        this(factory, new SorteadorRandom());
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        Grade g = factory.novaGrade();
        for (Disciplina d : g.getDisciplinas()) {
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).professorDaDisciplina(d));
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).horariosPorDisciplina(d));
        }
        return g;
    }
}