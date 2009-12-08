/*
 * Evolucionador.java
 * Criado em 2009/12/07 - 21:51
 */
package br.usp.gradescola.estrutura;

import java.util.List;

/**
 * @author Victor Williams Stafusa da Silva
 */
public interface Evolucionador {

    public List<Grade> populacaoNova(List<Grade> populacaoAntiga);

}