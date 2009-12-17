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

    private final Set<Horario> horarios;
    private final Set<Professor> professores;
    private final Set<Disciplina> disciplinas;
    //private final Set<Sala> salas;
    private final Condicao.Numerica restricao;
    private final BigDecimal limiarRuim;
    private final BigDecimal limiarBom;
    private final boolean otimizarProfessores;
    //private final boolean otimizarSalas;

    public Problema(ParametrosProblema params) {
        params.checarConsistecia();

        this.restricao = params.getRestricao();
        this.limiarRuim = params.getLimiarRuim();
        this.limiarBom = params.getLimiarBom();
        this.otimizarProfessores = params.isOtimizarProfessores();
        //this.otimizarSalas = params.isOtimizarSalas();

        this.horarios = Colecoes.copiarUnicos(params.getHorarios());
        this.professores = Colecoes.copiarUnicos(params.getProfessores());
        this.disciplinas = Colecoes.copiarUnicos(params.getDisciplinas());
        //this.salas = Colecoes.copiarUnicos(params.getSalas());
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

    /*public Set<Sala> getSalas() {
        return salas;
    }*/

    public BigDecimal limiarRuim() {
        return limiarRuim;
    }

    public BigDecimal limiarBom() {
        return limiarBom;
    }

    public Condicao.Numerica getRestricao() {
        return restricao;
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

    public boolean isOtimizarProfessores() {
        return otimizarProfessores;
    }

    /*public boolean isOtimizarSalas() {
        return otimizarSalas;
    }*/

    @Override
    public Grade novaGrade() {
        return new GradeDireta(this);
    }
}