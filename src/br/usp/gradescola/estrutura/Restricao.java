/*
 * Restricao.java
 * Criado em 2009/11/12 - 03:25
 */
 package br.usp.gradescola.estrutura;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class Restricao implements Condicao {
    private final Condicao condicao;
    private final double custoReal;
    private final double custoInfinito;

    public Restricao(Condicao condicao, double custoReal, double custoInfinito) {
        this.condicao = condicao;
        this.custoReal = custoReal;
        this.custoInfinito = custoInfinito;
    }

    public double getCustoReal() {
        return custoReal;
    }

    public double getCustoInfinito() {
        return custoInfinito;
    }

    @Override
    public boolean avaliar(Grade grade) {
        return condicao.avaliar(grade);
    }
}