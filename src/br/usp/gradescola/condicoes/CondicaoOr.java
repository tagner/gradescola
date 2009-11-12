/*
 * CondicaoOr.java
 * Criado em 2009/11/12 - 03:14
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Grade;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class CondicaoOr implements Condicao {
    private final Collection<Condicao> condicoes;

    public CondicaoOr(Collection<Condicao> condicoes) {
        this.condicoes = condicoes;
    }

    public CondicaoOr(Condicao... condicoes) {
        this.condicoes = Arrays.asList(condicoes);
    }

    @Override
    public boolean avaliar(Grade grade) {
        for (Condicao condicao : condicoes) {
            if (condicao.avaliar(grade)) return true;
        }
        return false;
    }
}