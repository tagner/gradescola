/*
 * Teste.java
 * Criado em 2009/11/26 - 06:17
 */
package br.usp.gradescola.estrutura;

import br.usp.gradescola.condicoes.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class Teste {
    public static void main(String[] args) {
        Disciplina a = new Disciplina("A", 2);
        Disciplina b = new Disciplina("B", 2);
        Disciplina c = new Disciplina("C", 2);
        List<Disciplina> disciplinas = Arrays.asList(a, b, c);

        Professor p1 = new Professor("P1");
        Professor p2 = new Professor("P2");
        List<Professor> professores = Arrays.asList(p1, p2);

        Horario h1 = new Horario("H1");
        Horario h2 = new Horario("H2");
        Horario h3 = new Horario("H3");
        Horario h4 = new Horario("H4");
        Horario h5 = new Horario("H5");
        List<Horario> horarios = Arrays.asList(h1, h2, h3, h4, h5);

        Restricao r1 = new Restricao(new ChocaHorario(a, b), 0, 1);
        Restricao r2 = new Restricao(new CondicaoOr(new MinistraAula(p1, a), new MinistraAula(p1, b)), 1, 0);
        List<Restricao> restricoes = Arrays.asList(r1, r2);

        Problema problema = new Problema(false, false);
        for (Disciplina d : disciplinas) problema.add(d);
        for (Professor p : professores) problema.add(p);
        for (Horario h : horarios) problema.add(h);
        for (Restricao r : restricoes) problema.add(r);

        Mutador mut = new MutadorRandom();
        GradeFactory fac = (GradeFactory) mut;
        Grade g1 = fac.novaGrade(problema);
        Grade g2 = fac.novaGrade(problema);
        Grade g3 = fac.novaGrade(problema);
        Grade g4 = mut.derivar(g1);
        Grade g5 = mut.derivar(g1);

        mostrar(problema, g1, "g1");
        mostrar(problema, g2, "g2");
        mostrar(problema, g3, "g3");
        mostrar(problema, g4, "g4");
        mostrar(problema, g5, "g5");
    }

    private static void mostrar(Problema problema, Grade g, String nome) {
        System.out.println(nome + ": Preco = " + formata(problema.avaliar(g)));
        for (Disciplina d : g.getDisciplinas()) {
            System.out.print("Horarios de " + d + " do professor " + g.professorDaDisciplina(d) + ": ");
            for (Horario h : g.horariosPorDisciplina(d)) {
                System.out.print(h + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static String formata(double[] valores) {
        String condicao = (valores[1] > 0.0 ? "nao admissivel" : valores[0] > 0.0 ? "nao muito bom" : "bom");
        return (valores[1] > 0.0 ? valores[1] + " x infinito + " : "") + valores[0] + " - " + condicao;
    }
}