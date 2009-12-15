/*
 * DisciplinaSimples.java
 * Criado em 2009/12/10 - 12:02
 */
package br.usp.gradescola.estrutura;

/**
 * Implementação simples da interface {@linkplain Disciplina}, que
 * consiste apenas do nome da disciplina e da carga horária, que são
 * informados por meio dos parâmteros do construtor.
 * @author Victor Williams Stafusa da Silva
 */
public final class DisciplinaSimples implements Disciplina {

    /**
     * Guarda o nome da disciplina, a ser retornado pelo método
     * {@linkplain #toString()}.
     */
    private final String nome;

    /**
     * Guarda a carga horária disciplina, a ser retornada pelo método
     * {@linkplain #getCargaHoraria()}.
     */
    private final int cargaHoraria;

    /**
     * Cria uma instância com o nome e a carga horária informadas.
     * @param nome O nome da disciplina, a ser retornado pelo método
     * {@linkplain #toString()}.
     * @param cargaHoraria A carga horária da disciplina, a ser
     * retornada pelo método {@linkplain #getCargaHoraria()}.
     * @throws IllegalArgumentException Se o nome da disciplina for
     * {@code null} ou a carga horária for menor ou igual a zero.
     */
    public DisciplinaSimples(String nome, int cargaHoraria) {
        if (nome == null) throw new IllegalArgumentException("O nome não pode ser nulo.");
        if (cargaHoraria <= 0) throw new IllegalArgumentException("A carga horária da disciplina deve ser positiva.");
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getCargaHoraria() {
        return cargaHoraria;
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