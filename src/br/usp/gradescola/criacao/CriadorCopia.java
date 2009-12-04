/*
 * CriadorCopia.java
 * Criado em 2009/12/03 - 11:11
 */
package br.usp.gradescola.criacao;

import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.GradeFactory;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CriadorCopia implements GradeFactory {

    private final GradeFactory decorado;

    public CriadorCopia(GradeFactory decorado) {
        this.decorado = decorado;
    }

    @Override
    public Grade novaGrade() {
        return decorado.novaGrade().clone();
    }
}