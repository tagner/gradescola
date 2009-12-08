/*
 * Teste.java
 * Criado em 2009/11/26 - 06:17
 */
package br.usp.test.gradescola;

import br.usp.gradescola.condicoes.*;
import br.usp.gradescola.estrutura.*;
import br.usp.gradescola.mutacao.*;
import br.usp.gradescola.cruzamento.*;
import br.usp.gradescola.criacao.*;

import static br.usp.gradescola.condicoes.CondicoesBasicas.*;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class Teste {

    private static final BigDecimal UM_MILHAO = BigDecimal.valueOf(1000000);
    private static final int GERACOES = 50;
    private static final int TAMANHO_POPULACAO = 100;
    private static final int MUTACOES = 10;
    private static final int CRUZAMENTOS = 80;

    @Test
    public void main() {
        Disciplina a = new Disciplina("A", 2);
        Disciplina b = new Disciplina("B", 2);
        Disciplina c = new Disciplina("C", 2);
        List<Disciplina> disciplinas = Arrays.asList(a, b, c);

        Professor p1 = new Professor("Joao");
        Professor p2 = new Professor("Maria");
        List<Professor> professores = Arrays.asList(p1, p2);

        Horario h1 = new Horario("Segunda-feira");
        Horario h2 = new Horario("Terca-feira");
        Horario h3 = new Horario("Quarta-feira");
        Horario h4 = new Horario("Quinta-feira");
        Horario h5 = new Horario("Sexta-feira");
        List<Horario> horarios = Arrays.asList(h1, h2, h3, h4, h5);

        Condicao.Real choqueRuim = multiplicar(valor(Problema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioDisciplina(a, b));
        Condicao.Real choqueProfessor = multiplicar(valor(Problema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioProfessor(professores));
        Condicao.Booleana coisaChata = or(new MinistraAula(p1, a), new MinistraAula(p1, b));
        Condicao.Real restricoes = somar(choqueRuim, valor(coisaChata), choqueProfessor);

        Problema problema = new Problema(restricoes, horarios, disciplinas, professores);
        Mutador mut = new MutadorRandom();
        Cruzador cruz = new CruzadorRandom(problema);
        GradeFactory fac = new CriadorRandom(problema);

        Grade g1 = fac.novaGrade();
        Grade g2 = fac.novaGrade();
        Grade g3 = fac.novaGrade();
        Grade g4 = g1.clone();
        mut.alterar(g4);
        Grade g5 = g1.clone();
        mut.alterar(g5);
        Grade g6 = cruz.cruzar(g4, g5);

        mostrar(problema, g1, "g1");
        mostrar(problema, g2, "g2");
        mostrar(problema, g3, "g3");
        mostrar(problema, g4, "g4");
        mostrar(problema, g5, "g5");
        mostrar(problema, g6, "g6");

        PoolDireto pool = new PoolDireto(TAMANHO_POPULACAO, fac, new EvolucionadorRandom(MUTACOES, CRUZAMENTOS, mut, cruz));
        for (int i = 0; i < GERACOES; i++) {
            System.out.println("Geracao " + (i + 1));
            pool.novaGeracao();
        }

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println(" 10 melhores geradas: ");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            mostrar(problema, pool.grade(i), i + "a melhor");
        }
    }

    private static void mostrar(Problema problema, Grade g, String nome) {
        BigDecimal a = g.avaliar();

        String qualidade;
        switch (problema.avaliacao(a)) {
            case BOM: qualidade = "bom"; break;
            case FRACO: qualidade = "ruim"; break;
            case NAO_ADMISSIVEL: qualidade = "nao admissivel"; break;
            default: throw new AssertionError();
        }

        System.out.println(nome + ": Preco = " + a.toPlainString() + " - " + qualidade);

        for (Disciplina d : problema.getDisciplinas()) {
            System.out.print("Horarios de " + d + " do professor " + g.professorDaDisciplina(d) + ": ");
            for (Horario h : g.horariosPorDisciplina(d)) {
                System.out.print(h + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}