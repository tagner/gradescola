/*
 * Professor.java
 * Criado em 2009/11/12 - 03:00
 */
package br.usp.gradescola.estrutura;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class Professor {
    private final String nome;

    public Professor(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}