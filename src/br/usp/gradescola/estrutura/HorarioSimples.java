/*
 * HorarioSimples.java
 * Criado em 2009/12/10 - 12:03
 */
package br.usp.gradescola.estrutura;

/**
 * Implementação simples da interface {@linkplain Horario}, que
 * consiste apenas do nome do horário, informado por meio do parâmetro
 * do construtor.
 * @author Victor Williams Stafusa da Silva
 */
public final class HorarioSimples implements Horario {

    /**
     * Guarda o nome do horário, a ser retornado pelo método
     * {@linkplain #toString()}.
     */
    private final String nome;

    /**
     * Cria uma instância com o nome informado.
     * @param nome O nome do horário, a ser retornado pelo método
     * {@linkplain #toString()}.
     * @throws IllegalArgumentException Se o nome do horário for
     * {@code null}.
     */
    public HorarioSimples(String nome) {
        if (nome == null) throw new IllegalArgumentException("O nome não pode ser nulo.");
        this.nome = nome;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return nome;
    }
}