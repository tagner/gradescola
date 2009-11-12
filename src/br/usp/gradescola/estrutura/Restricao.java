/*
 * Grade.java
 * Criado em 2009/11/12 - 03:18
 */
public final class Restricao {
    private final Condicao condicao;
    private final double custoReal;
    private final double custoInfinito;

    public Restricao(Condicao condicao, double custoReal, double custoInfinito) {
        this.condicao = condicao;
        this.custoReal = custoReal;
        this.custoInfinito = custoInfinito;
    }
}