/*
 * Mutador.java
 * Criado em 2009/11/25 - 20:54
 */
package br.usp.gradescola.estrutura;

/**
 * @author Victor Williams Stafusa da Silva
 */
public interface Mutador {
    public Grade derivar(Grade grade);

    public Grade cruzar(Grade grade1, Grade grade2);
}