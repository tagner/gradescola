/*
 * CruzadorHorario.java
 * Criado em 2009/12/03 - 10:51
 */
package br.usp.gradescola.cruzamento;

import br.usp.gradescola.estrutura.Cruzador;
import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Problema;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CruzadorHorario implements Cruzador {

    private final Sorteador sorte;

    private final Problema problema;

    public CruzadorHorario(Problema problema, Sorteador sorte) {
        this.problema = problema;
        this.sorte = sorte;
    }

    public CruzadorHorario(Problema problema) {
        this(problema, new SorteadorRandom());
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        Grade g = problema.novaGrade();
        for (Disciplina d : problema.getDisciplinas()) {
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).professorDaDisciplina(d));
            g.atribuir(d, sorte.sortearElemento(grade1, grade2).horariosPorDisciplina(d));
        }
        return g;
    }
}