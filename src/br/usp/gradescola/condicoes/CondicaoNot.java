/*
 * CondicaoNot.java
 * Criado em 2009/11/12 - 03:15
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Grade;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class CondicaoNot implements Condicao {
    private final Condicao condicao;

    public CondicaoNot(Condicao condicao) {
        this.condicao = condicao;
    }

    @Override
    public boolean avaliar(Grade grade) {
        return !condicao.avaliar(grade);
    }
}