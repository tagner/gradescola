/*
 * Problema.java
 * Criado em 2009/11/12 - 03:20
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.usp.gradescola.condicoes.CondicoesBasicas.*;
import br.usp.gradescola.utilidades.Colecoes;

/**
 * @author Victor Williams Stafusa da Silva
 */
public final class Problema implements GradeFactory {

    public static final BigDecimal LIMIAR_BOM_DEFAULT = BigDecimal.ZERO;

    public static final BigDecimal LIMIAR_RUIM_DEFAULT = BigDecimal.valueOf(1000000);

    private final Set<Horario> horarios;
    private final Set<Professor> professores;
    private final Set<Disciplina> disciplinas;
    //private final Set<Sala> salas;
    private final Condicao.Real restricao;
    private final BigDecimal limiarRuim;
    private final BigDecimal limiarBom;

    public Problema(Condicao.Real restricao,
                    Iterable<Horario> horarios,
                    Iterable<Disciplina> disciplinas,
                    Iterable<Professor> professores) {
        this(restricao, LIMIAR_RUIM_DEFAULT, LIMIAR_BOM_DEFAULT, horarios, disciplinas, professores);
    }

    public Problema(Condicao.Real restricao,
                    BigDecimal limiarRuim,
                    BigDecimal limiarBom,
                    Iterable<Horario> horarios,
                    Iterable<Disciplina> disciplinas,
                    Iterable<Professor> professores) {
        if (limiarRuim.compareTo(limiarBom) < 0) throw new IllegalArgumentException();

        this.restricao = restricao;
        this.limiarRuim = limiarRuim;
        this.limiarBom = limiarBom;

        this.horarios = Colecoes.copiarUnicos(horarios);
        this.professores = Colecoes.copiarUnicos(professores);
        this.disciplinas = Colecoes.copiarUnicos(disciplinas);
        //this.salas = new HashSet<Sala>();
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Set<Professor> getProfessores() {
        return professores;
    }

    public BigDecimal limiarRuim() {
        return limiarRuim;
    }

    public BigDecimal limiarBom() {
        return limiarBom;
    }

    public BigDecimal avaliar(Grade grade) {
        return restricao.avaliar(grade);
    }

    public static enum Avaliacao { BOM, FRACO, NAO_ADMISSIVEL }

    public Avaliacao avaliacao(Grade grade) {
        return avaliacao(restricao.avaliar(grade));
    }

    public Avaliacao avaliacao(BigDecimal a) {
        return a.compareTo(limiarBom) <= 0 ? Avaliacao.BOM : a.compareTo(limiarRuim) < 0 ? Avaliacao.FRACO : Avaliacao.NAO_ADMISSIVEL;
    }

    @Override
    public Grade novaGrade() {
        return new GradeDireta(horarios, disciplinas, professores);
    }
}