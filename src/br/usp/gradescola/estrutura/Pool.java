/*
 * Pool.java
 * Criado em 2009/12/04 - 17:24
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

/**
 * @author Victor Williams Stafusa da Silva
 */
public interface Pool extends Iterable<Grade> {

    public Grade grade(int indice);

    public int tamanho();

    public void novaGeracao();
}