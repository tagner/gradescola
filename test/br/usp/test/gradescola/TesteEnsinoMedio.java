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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class TesteEnsinoMedio {

    private static int geracoes;
    private static int tamanhoPopulacao = 150;
    private static int mutacoes = 130;
    private static int cruzamentos = 4;

    private static void muitoBom(Map<Disciplina, BigDecimal> mapa, Disciplina... disciplinas) {
        for (Disciplina d : disciplinas) {
            mapa.put(d, BigDecimal.valueOf(-5));
        }
    }

    private static void bom(Map<Disciplina, BigDecimal> mapa, Disciplina... disciplinas) {
        for (Disciplina d : disciplinas) {
            mapa.put(d, BigDecimal.valueOf(-1));
        }
    }

    private static void ruim(Map<Disciplina, BigDecimal> mapa, Disciplina... disciplinas) {
        for (Disciplina d : disciplinas) {
            mapa.put(d, BigDecimal.valueOf(3));
        }
    }

    public static void main(String[] args) {

        geracoes = Integer.parseInt(args[0]);
        tamanhoPopulacao = Integer.parseInt(args[1]);
        mutacoes = Integer.parseInt(args[2]);
        cruzamentos = Integer.parseInt(args[3]);

        System.out.println("Numero de geracoes: " + geracoes);
        System.out.println("Tamanho do pool: " + tamanhoPopulacao);
        System.out.println("Numero de mutacoes por geracao: " + mutacoes);
        System.out.println("Numero de cruzamentos por geracao: " + cruzamentos);
        System.out.println();

        // Disciplinas do primeiro ano.
        Disciplina matematica1 = new DisciplinaSimples("Matematica 1a serie", 5);
        Disciplina portugues1 = new DisciplinaSimples("Portugues 1a serie", 5);
        Disciplina fisica1 = new DisciplinaSimples("Fisica 1a serie", 2);
        Disciplina quimica1 = new DisciplinaSimples("Quimica 1a serie", 2);
        Disciplina biologia1 = new DisciplinaSimples("Biologia 1a serie", 2);
        Disciplina literatura1 = new DisciplinaSimples("Literatura 1a serie", 1);
        Disciplina ingles1 = new DisciplinaSimples("Ingles 1a serie", 2);
        Disciplina historia1 = new DisciplinaSimples("Historia 1a serie", 2);
        Disciplina geografia1 = new DisciplinaSimples("Geografia 1a serie", 2);
        Disciplina educacaoFisica1 = new DisciplinaSimples("Educacao Fisica 1a serie", 2);
        List<Disciplina> disciplinas1 =
                Arrays.asList(matematica1, portugues1, fisica1, quimica1, biologia1, literatura1, ingles1, historia1, geografia1, educacaoFisica1);

        // Restrição: As disciplinas do primeiro ano não podem se chocar.
        Condicao.Numerica choquesAno1 =
                multiplicar(valor(ParametrosProblema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioDisciplina(disciplinas1));

        // Disciplinas do segundo ano.
        Disciplina matematica2 = new DisciplinaSimples("Matematica 2a serie", 5);
        Disciplina portugues2 = new DisciplinaSimples("Portugues 2a serie", 5);
        Disciplina fisica2 = new DisciplinaSimples("Fisica 2a serie", 2);
        Disciplina quimica2 = new DisciplinaSimples("Quimica 2a serie", 2);
        Disciplina biologia2 = new DisciplinaSimples("Biologia 2a serie", 2);
        Disciplina literatura2 = new DisciplinaSimples("Literatura 2a serie", 1);
        Disciplina ingles2 = new DisciplinaSimples("Ingles 2a serie", 2);
        Disciplina historia2 = new DisciplinaSimples("Historia 2a serie", 2);
        Disciplina geografia2 = new DisciplinaSimples("Geografia 2a serie", 2);
        Disciplina educacaoFisica2 = new DisciplinaSimples("Educacao Fisica 2a serie", 2);
        List<Disciplina> disciplinas2 =
                Arrays.asList(matematica2, portugues2, fisica2, quimica2, biologia2, literatura2, ingles2, historia2, geografia2, educacaoFisica2);

        // Restrição: As disciplinas do segundo ano não podem se chocar.
        Condicao.Numerica choquesAno2 =
                multiplicar(valor(ParametrosProblema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioDisciplina(disciplinas2));

        // Disciplinas do terceiro ano.
        Disciplina matematica3 = new DisciplinaSimples("Matematica 3a serie", 5);
        Disciplina portugues3 = new DisciplinaSimples("Portugues 3a serie", 5);
        Disciplina fisica3 = new DisciplinaSimples("Fisica 3a serie", 2);
        Disciplina quimica3 = new DisciplinaSimples("Quimica 3a serie", 2);
        Disciplina biologia3 = new DisciplinaSimples("Biologia 3a serie", 2);
        Disciplina literatura3 = new DisciplinaSimples("Literatura 3a serie", 1);
        Disciplina ingles3 = new DisciplinaSimples("Ingles 3a serie", 2);
        Disciplina historia3 = new DisciplinaSimples("Historia 3a serie", 2);
        Disciplina geografia3 = new DisciplinaSimples("Geografia 3a serie", 2);
        Disciplina educacaoFisica3 = new DisciplinaSimples("Educacao Fisica 3a serie", 2);
        List<Disciplina> disciplinas3 =
                Arrays.asList(matematica3, portugues3, fisica3, quimica3, biologia3, literatura3, ingles3, historia3, geografia3, educacaoFisica3);

        // Restrição: As disciplinas do terceiro ano não podem se chocar.
        Condicao.Numerica choquesAno3 =
                multiplicar(valor(ParametrosProblema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioDisciplina(disciplinas3));

        // Restrição: Apenas uma turma de cada vez pode usar a quadra para educação física.
        Condicao.Numerica choquesQuadra =
                multiplicar(valor(ParametrosProblema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioDisciplina(educacaoFisica1, educacaoFisica2, educacaoFisica3));

        // Junta todas as disciplinas.
        List<Disciplina> disciplinas = new ArrayList<Disciplina>(30);
        disciplinas.addAll(disciplinas1);
        disciplinas.addAll(disciplinas2);
        disciplinas.addAll(disciplinas3);

        // Lista de horários.
        Horario segunda1 = new HorarioSimples("Segunda-feira 07:30");
        Horario segunda2 = new HorarioSimples("Segunda-feira 08:15");
        Horario segunda3 = new HorarioSimples("Segunda-feira 09:00");
        Horario segunda4 = new HorarioSimples("Segunda-feira 10:00");
        Horario segunda5 = new HorarioSimples("Segunda-feira 10:45");

        Horario terca1 = new HorarioSimples("Terça-feira 07:30");
        Horario terca2 = new HorarioSimples("Terça-feira 08:15");
        Horario terca3 = new HorarioSimples("Terça-feira 09:00");
        Horario terca4 = new HorarioSimples("Terça-feira 10:00");
        Horario terca5 = new HorarioSimples("Terça-feira 10:45");

        Horario quarta1 = new HorarioSimples("Quarta-feira 07:30");
        Horario quarta2 = new HorarioSimples("Quarta-feira 08:15");
        Horario quarta3 = new HorarioSimples("Quarta-feira 09:00");
        Horario quarta4 = new HorarioSimples("Quarta-feira 10:00");
        Horario quarta5 = new HorarioSimples("Quarta-feira 10:45");

        Horario quinta1 = new HorarioSimples("Quinta-feira 07:30");
        Horario quinta2 = new HorarioSimples("Quinta-feira 08:15");
        Horario quinta3 = new HorarioSimples("Quinta-feira 09:00");
        Horario quinta4 = new HorarioSimples("Quinta-feira 10:00");
        Horario quinta5 = new HorarioSimples("Quinta-feira 10:45");

        Horario sexta1 = new HorarioSimples("Sexta-feira 07:30");
        Horario sexta2 = new HorarioSimples("Sexta-feira 08:15");
        Horario sexta3 = new HorarioSimples("Sexta-feira 09:00");
        Horario sexta4 = new HorarioSimples("Sexta-feira 10:00");
        Horario sexta5 = new HorarioSimples("Sexta-feira 10:45");

        // Junta todos os horários.
        List<Horario> horarios = Arrays.asList(segunda1, segunda2, segunda3, segunda4, segunda5,
                                               terca1, terca2, terca3, terca4, terca5,
                                               quarta1, quarta2, quarta3, quarta4, quarta5,
                                               quinta1, quinta2, quinta3, quinta4, quinta5,
                                               sexta1, sexta2, sexta3, sexta4, sexta5);

        // Restrição: A carga horária das disciplinas devem ser respeitadas.
        Condicao.Numerica cargaHoraria = multiplicar(valor(ParametrosProblema.LIMIAR_RUIM_DEFAULT), new CargaHorariaDisciplina(disciplinas));

        // Restrição: Evitar colocar educação física no final.
        Condicao.Numerica educacaoFisicaNoFinal =
                new DisciplinasTemHorarios(
                Arrays.asList(educacaoFisica1, educacaoFisica2, educacaoFisica3),
                Arrays.asList(segunda5, terca5, quarta5, quinta5, sexta5));

        // Professores e afinidades.
        Professor joao = new ProfessorSimples("Joao");
        Map<Disciplina, BigDecimal> afinidadesJoao = new HashMap<Disciplina, BigDecimal>();
        muitoBom(afinidadesJoao, portugues1, portugues2, portugues3);
        bom(afinidadesJoao, literatura1, literatura2, literatura3);
        Condicao.Numerica aulasJoao = new DisciplinasProfessores(joao, afinidadesJoao, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        Professor marcos = new ProfessorSimples("Marcos");
        Map<Disciplina, BigDecimal> afinidadesMarcos = new HashMap<Disciplina, BigDecimal>();
        bom(afinidadesMarcos, literatura1, literatura2, literatura3);
        muitoBom(afinidadesMarcos, ingles1, ingles2, ingles3);
        ruim(afinidadesMarcos, portugues1, portugues2, portugues3);
        Condicao.Numerica aulasMarcos = new DisciplinasProfessores(joao, afinidadesMarcos, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        Professor maria = new ProfessorSimples("Maria");
        Map<Disciplina, BigDecimal> afinidadesMaria = new HashMap<Disciplina, BigDecimal>();
        muitoBom(afinidadesMaria, matematica1, matematica2, matematica3);
        bom(afinidadesMaria, fisica1, fisica2, fisica3, quimica1);
        Condicao.Numerica aulasMaria = new DisciplinasProfessores(maria, afinidadesMaria, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        Professor carlos = new ProfessorSimples("Carlos");
        Map<Disciplina, BigDecimal> afinidadesCarlos = new HashMap<Disciplina, BigDecimal>();
        muitoBom(afinidadesCarlos, matematica1, matematica2, matematica3);
        bom(afinidadesCarlos, fisica1, fisica2, fisica3);
        ruim(afinidadesCarlos, quimica1, quimica2, quimica3);
        Condicao.Numerica aulasCarlos = new DisciplinasProfessores(carlos, afinidadesCarlos, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        Professor fernanda = new ProfessorSimples("Fernanda");
        Map<Disciplina, BigDecimal> afinidadesFernanda = new HashMap<Disciplina, BigDecimal>();
        muitoBom(afinidadesFernanda, historia1, historia2, historia3);
        bom(afinidadesFernanda, geografia1, geografia2, geografia3);
        Condicao.Numerica aulasFernanda = new DisciplinasProfessores(fernanda, afinidadesFernanda, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        Professor tatiana = new ProfessorSimples("Tatiana");
        Map<Disciplina, BigDecimal> afinidadesTatiana = new HashMap<Disciplina, BigDecimal>();
        muitoBom(afinidadesTatiana, biologia1, biologia2, biologia3);
        bom(afinidadesTatiana, quimica1, quimica2, quimica3);
        Condicao.Numerica aulasTatiana = new DisciplinasProfessores(tatiana, afinidadesTatiana, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        Professor ronaldo = new ProfessorSimples("Ronaldo");
        Map<Disciplina, BigDecimal> afinidadesRonaldo = new HashMap<Disciplina, BigDecimal>();
        muitoBom(afinidadesRonaldo, educacaoFisica1, educacaoFisica2, educacaoFisica3);
        Condicao.Numerica aulasRonaldo = new DisciplinasProfessores(ronaldo, afinidadesRonaldo, ParametrosProblema.LIMIAR_RUIM_DEFAULT);

        // Lista de todos os professores.
        List<Professor> professores = Arrays.asList(joao, marcos, maria, carlos, fernanda, tatiana, ronaldo);

        // Restrição: Os horários dos professores não podem se chocar.
        Condicao.Numerica choquesProfessores = multiplicar(valor(ParametrosProblema.LIMIAR_RUIM_DEFAULT), new ChoqueHorarioProfessor(professores));

        // Conjunto de todas as restrições.
        Condicao.Numerica restricoes =
                somar(choquesProfessores, choquesQuadra,
                      choquesAno1, choquesAno2, choquesAno3,
                      aulasJoao, aulasMarcos, aulasMaria, aulasCarlos, aulasFernanda, aulasTatiana, aulasRonaldo,
                      educacaoFisicaNoFinal, cargaHoraria);

        ParametrosProblema params = new ParametrosProblema();
        params.setRestricao(restricoes);
        params.setHorarios(horarios);
        params.setDisciplinas(disciplinas);
        params.setProfessores(professores);

        Problema problema = new Problema(params);
        Mutador mut = new MutadorRandom(problema);
        Cruzador cruz = new CruzadorRandom(problema);
        GradeFactory fac = new CriadorRandom(problema);

        PoolDireto pool = new PoolDireto(tamanhoPopulacao, fac, new EvolucionadorRandom(mutacoes, cruzamentos, mut, cruz));
        for (int i = 0; i < geracoes; i++) {
            System.out.println("Geracao " + (i + 1)
                    + " - Melhor: " + pool.grade(0).avaliar().toPlainString()
                    + " - Pior: " + pool.grade(tamanhoPopulacao - 1).avaliar().toPlainString());
            pool.novaGeracao();
        }

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println(" A melhor gerada: ");
        System.out.println();

        mostrar(problema, pool.grade(0), "A melhor");
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