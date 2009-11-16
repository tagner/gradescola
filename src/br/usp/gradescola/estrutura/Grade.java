/*
 * Grade.java
 * Criado em 2009/11/12 - 03:18
 */
package br.usp.gradescola.estrutura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class Grade {
    private final Map<Disciplina, Professor> ministrantes;
    private final Map<Horario, Map<Sala, Disciplina>> aulas;
    private final Map<Horario, Collection<Disciplina>> disciplinasPorHorario;
    private final Map<Disciplina, Collection<Horario>> horariosPorDisciplina;
    private final Map<Professor, Collection<Horario>> horariosPorProfessor;
    private final Set<Horario> horariosUsados;
    private final Set<Horario> horariosUsadosEx;

    public Grade() {
        ministrantes = new HashMap<Disciplina, Professor>();
        aulas = new HashMap<Horario, Map<Sala, Disciplina>>();
        disciplinasPorHorario = new HashMap<Horario, Collection<Disciplina>>();
        horariosPorDisciplina = new HashMap<Disciplina, Collection<Horario>>();
        horariosPorProfessor = new HashMap<Professor, Collection<Horario>>();
        horariosUsados = new HashSet<Horario>();
        horariosUsadosEx = Collections.unmodifiableSet(horariosUsados);
    }

    public void atribuir(Disciplina disciplina, Professor professor) {
        ministrantes.put(disciplina, professor);
    }

    public void atribuir(Horario horario, Sala sala, Disciplina disciplina) {
        horariosUsados.add(horario);

        Map<Sala, Disciplina> pelaSala = aulas.get(horario);
        if (pelaSala == null) {
            pelaSala = new HashMap<Sala, Disciplina>();
            aulas.put(horario, pelaSala);
        }
        pelaSala.put(sala, disciplina);

        Collection<Disciplina> porHorario = disciplinasPorHorario.get(horario);
        if (porHorario == null) {
            porHorario = new ArrayList<Disciplina>();
            disciplinasPorHorario.put(horario, porHorario);
        }
        porHorario.add(disciplina);

        Collection<Horario> porDisciplina = horariosPorDisciplina.get(disciplina);
        if (porDisciplina == null) {
            porDisciplina = new ArrayList<Horario>();
            horariosPorDisciplina.put(disciplina, porDisciplina);
        }
        porDisciplina.add(horario);

        Professor professor = ministrantes.get(disciplina);
        Collection<Horario> porProfessor = horariosPorProfessor.get(professor);
        if (porProfessor == null) {
            porProfessor = new ArrayList<Horario>();
            horariosPorProfessor.put(professor, porProfessor);
        }
        porProfessor.add(horario);
    }

    public boolean tem(Professor professor, Disciplina disciplina) {
        return ministrantes.get(disciplina) == professor;
    }

    public boolean tem(Professor professor, Horario horario) {
        return horariosPorProfessor.get(professor).contains(horario);
    }

    public boolean tem(Disciplina disciplina, Horario horario) {
        return horariosPorDisciplina.get(disciplina).contains(horario);
    }

    public Set<Horario> getHorariosUsados() {
        return horariosUsadosEx;
    }
}