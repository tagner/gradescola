/*
 * MutadorRandom.java
 * Criado em 2009/11/26 - 02:17
 */
package br.usp.gradescola.estrutura;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class MutadorRandom implements Mutador, GradeFactory {

    private final Random random;

    public MutadorRandom() {
        this(new Random());
    }

    public MutadorRandom(Random random) {
        this.random = random;
    }

    @Override
    public Grade novaGrade(Problema problema) {
        Set<Horario> horarios = problema.getHorarios();
        Set<Disciplina> disciplinas = problema.getDisciplinas();
        Set<Professor> professores = problema.getProfessores();
        Grade g = new GradeDireta(horarios, disciplinas, professores);
        for (Disciplina d : disciplinas) {
            g.atribuir(d, sortearElementos(horarios, d.getCargaHoraria()));
            g.atribuir(d, sortearUmElemento(professores));
        }
        return g;
    }

    @Override
    public Grade derivar(Grade grade) {
        Grade nova = grade.clone();
        switch (random.nextInt(5)) {
            case 0: disciplinasTrocadas(nova); break;
            case 1: horariosTrocados(nova); break;
            case 2: professoresTrocados(nova); break;
            case 3: mudaUmProfessor(nova); break;
            case 4: mudaUmHorario(nova); break;
            default: throw new AssertionError();
        }
        return nova;
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        throw new UnsupportedOperationException();
    }

    private void disciplinasTrocadas(Grade grade) {
        List<Disciplina> lista = sortearElementos(grade.getDisciplinas(), 2);
        grade.trocar(lista.get(0), lista.get(1));
    }

    private void horariosTrocados(Grade grade) {
        List<Horario> lista = sortearElementos(grade.getHorarios(), 2);
        grade.trocar(lista.get(0), lista.get(1));
    }

    private void professoresTrocados(Grade grade) {
        List<Professor> lista = sortearElementos(grade.getProfessores(), 2);
        grade.trocar(lista.get(0), lista.get(1));
    }

    private void mudaUmProfessor(Grade grade) {
        Disciplina disciplina = sortearUmElemento(grade.getDisciplinas());
        Professor professor = sortearUmElemento(grade.getProfessores());
        grade.atribuir(disciplina, professor);
    }

    private void mudaUmHorario(Grade grade) {
        Disciplina disciplina = sortearUmElemento(grade.getDisciplinas());

        List<Horario> horarios = grade.horariosPorDisciplina(disciplina);
        Set<Horario> todos = grade.getHorarios();
        List<Horario> substitutos = copiarElementos(todos);
        List<Horario> novos = copiarElementos(horarios);

        substitutos.removeAll(horarios);

        novos.remove(sortearUmElemento(novos));
        novos.add(sortearUmElemento(substitutos));

        grade.atribuir(disciplina, novos);
    }

    private <E> E sortearUmElemento(Iterable<E> elementos) {
        List<E> copia = copiarElementos(elementos);
        Collections.shuffle(copia, random);
        return copia.iterator().next();
    }

    private <E> List<E> sortearElementos(Iterable<E> elementos, int n) {
        List<E> copia = copiarElementos(elementos);
        Collections.shuffle(copia, random);
        List<E> resposta = new ArrayList<E>(n);
        for (E elem : copia) {
            if (n == 0) break;
            resposta.add(elem);
            n--;
        }
        return resposta;
    }

    private static <E> List<E> copiarElementos(Iterable<E> it) {
        List<E> lista = new ArrayList<E>();
        for (E elem : it) {
            lista.add(elem);
        }
        return lista;
    }
}