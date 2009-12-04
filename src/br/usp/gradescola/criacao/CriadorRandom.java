/*
 * CriadorRandom.java
 * Criado em 2009/12/03 - 10:45
 */
package br.usp.gradescola.criacao;

import java.util.Set;

import br.usp.gradescola.estrutura.Disciplina;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.GradeDireta;
import br.usp.gradescola.estrutura.GradeFactory;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Problema;
import br.usp.gradescola.estrutura.Professor;

import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CriadorRandom implements GradeFactory {

    private final Sorteador sorte;

    private final GradeFactory factory;

    public CriadorRandom(GradeFactory factory, Sorteador sorte) {
        this.factory = factory;
        this.sorte = sorte;
    }

    public CriadorRandom(GradeFactory factory) {
        this(factory, new SorteadorRandom());
    }

    @Override
    public Grade novaGrade() {
        Grade g = factory.novaGrade();
        Set<Horario> horarios = g.getHorarios();
        Set<Professor> professores = g.getProfessores();
        for (Disciplina d : g.getDisciplinas()) {
            g.atribuir(d, sorte.sortearElementos(d.getCargaHoraria(), horarios));
            g.atribuir(d, sorte.sortearElemento(professores));
        }
        return g;
    }
}