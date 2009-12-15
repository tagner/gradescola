/*
 * Condicao.java
 * Criado em 2009/11/12 - 03:10
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class Condicao {

    private Condicao() {}

    public static interface Booleana {
        public boolean avaliar(Grade grade);
    }

    public static interface Numerica {
        public BigDecimal avaliar(Grade grade);
    }
}