/*
 * CondicaoAnd.java
 * Criado em 2009/11/12 - 03:12
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Grade;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class CondicaoAnd implements Condicao {
    private final Collection<Condicao> condicoes;

    public CondicaoAnd(Collection<Condicao> condicoes) {
        this.condicoes = condicoes;
    }

    public CondicaoAnd(Condicao... condicoes) {
        this.condicoes = Arrays.asList(condicoes);
    }

    @Override
    public boolean avaliar(Grade grade) {
        for (Condicao condicao : condicoes) {
            if (!condicao.avaliar(grade)) return false;
        }
        return true;
    }
}