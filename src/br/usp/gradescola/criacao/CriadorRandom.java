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

    private final Problema problema;

    public CriadorRandom(Problema problema, Sorteador sorte) {
        this.problema = problema;
        this.sorte = sorte;
    }

    public CriadorRandom(Problema problema) {
        this(problema, new SorteadorRandom());
    }

    @Override
    public Grade novaGrade() {
        Grade g = problema.novaGrade();
        Set<Horario> horarios = problema.getHorarios();
        Set<Professor> professores = problema.getProfessores();
        for (Disciplina d : problema.getDisciplinas()) {
            g.atribuir(d, sorte.sortearElementos(d.getCargaHoraria(), horarios));
            g.atribuir(d, sorte.sortearElemento(professores));
        }
        return g;
    }
}