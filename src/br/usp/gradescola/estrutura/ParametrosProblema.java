/*
 * ParametrosProblema.java
 * Criado em 2009/12/16 - 02:32
 */
package br.usp.gradescola.estrutura;

import java.math.BigDecimal;

public final class ParametrosProblema implements Cloneable {

    public static final BigDecimal LIMIAR_BOM_DEFAULT = BigDecimal.ZERO;

    public static final BigDecimal LIMIAR_RUIM_DEFAULT = BigDecimal.valueOf(1000000);

    private Condicao.Numerica restricao = null;

    private BigDecimal limiarRuim = LIMIAR_RUIM_DEFAULT;

    private BigDecimal limiarBom = LIMIAR_BOM_DEFAULT;

    private Iterable<Horario> horarios = null;

    private Iterable<Disciplina> disciplinas = null;

    private Iterable<Professor> professores = null;

    //private Iterable<Sala> salas = null;

    //private boolean otimizarSalas = false;

    private boolean otimizarProfessores = true;

    public ParametrosProblema() {}

    public Condicao.Numerica getRestricao() {
        return restricao;
    }

    public void setRestricao(Condicao.Numerica restricao) {
        this.restricao = restricao;
    }

    public BigDecimal getLimiarRuim() {
        return limiarRuim;
    }

    public void setLimiarRuim(BigDecimal limiarRuim) {
        this.limiarRuim = limiarRuim;
    }

    public BigDecimal getLimiarBom() {
        return limiarBom;
    }

    public void setLimiarBom(BigDecimal limiarBom) {
        this.limiarBom = limiarBom;
    }

    public Iterable<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(Iterable<Horario> horarios) {
        this.horarios = horarios;
    }

    public Iterable<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Iterable<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Iterable<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(Iterable<Professor> professores) {
        this.professores = professores;
    }

    /*public Iterable<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Iterable<Sala> salas) {
        this.salas = salas;
    }

    public boolean isOtimizarSalas() {
        return otimizarSalas;
    }

    public void setOtimizarSalas(boolean otimizarSalas) {
        this.otimizarSalas = otimizarSalas;
    }*/

    public boolean isOtimizarProfessores() {
        return otimizarProfessores;
    }

    public void setOtimizarProfessores(boolean otimizarProfessores) {
        this.otimizarProfessores = otimizarProfessores;
    }

    public void checarConsistecia() {
        if (restricao == null) {
            throw new IllegalStateException("Deve-se definir uma função de avaliação.");
        }
        if (otimizarProfessores && (professores == null || !professores.iterator().hasNext())) {
            throw new IllegalStateException("Não há professores a serem otimizados.");
        }
        /*if (otimizarSalas && (salas == null || !salas.iterator().hasNext())) {
            throw new IllegalStateException("Não há salas a serem otimizadas.");
        }*/
        if (disciplinas == null || !disciplinas.iterator().hasNext()) {
            throw new IllegalStateException("Não há disciplinas.");
        }
        if (horarios == null || !horarios.iterator().hasNext()) {
            throw new IllegalStateException("Não há horários.");
        }
        if (limiarBom != null && limiarRuim != null && limiarBom.compareTo(limiarRuim) > 0) {
            throw new IllegalStateException("Os limiares estão trocados.");
        }
    }

    @Override
    public ParametrosProblema clone() {
        try {
            return (ParametrosProblema) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}