/*
 * CriadorMutacao.java
 * Criado em 2009/12/03 - 10:47
 */
package br.usp.gradescola.criacao;

import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.GradeFactory;
import br.usp.gradescola.estrutura.Mutador;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CriadorMutacao implements GradeFactory {

    private final GradeFactory decorado;

    private final Mutador mutador;

    public CriadorMutacao(GradeFactory decorado, Mutador mutador) {
        this.decorado = decorado;
        this.mutador = mutador;
    }

    @Override
    public Grade novaGrade() {
        Grade grade = decorado.novaGrade();
        mutador.alterar(grade);
        return grade;
    }
}