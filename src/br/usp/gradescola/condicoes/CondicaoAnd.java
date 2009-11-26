/*
 * CondicaoAnd.java
 * Criado em 2009/11/12 - 03:12
 */
package br.usp.gradescola.condicoes;

import br.usp.gradescola.estrutura.Condicao;
import br.usp.gradescola.estrutura.Grade;
import java.util.Arrays;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class CondicaoAnd implements Condicao {
    private final Iterable<Condicao> condicoes;

    public CondicaoAnd(Iterable<Condicao> condicoes) {
        this.condicoes = condicoes;
    }

    public CondicaoAnd(Condicao... condicoes) {
        this(Arrays.asList(condicoes));
    }

    @Override
    public boolean avaliar(Grade grade) {
        for (Condicao condicao : condicoes) {
            if (!condicao.avaliar(grade)) return false;
        }
        return true;
    }
}