/*
 * TesteBasico.java
 * Criado em 2009/12/10 - 12:06
 */
package br.usp.test.gradescola;

import static org.junit.Assert.*;
import org.junit.Test;

import br.usp.gradescola.estrutura.*;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class TesteBasico {

    @Test
    public void testarNomeDoHorario() {
        Horario h = new HorarioSimples("Hora teste");
        assertEquals("Hora teste", h.toString());
    }

    @Test
    public void testarNomeDaDisciplina() {
        Disciplina d = new DisciplinaSimples("Quimica", 2);
        assertEquals("Quimica", d.toString());
    }

    @Test
    public void testarCargaHorariaDaDisciplina() {
        Disciplina d = new DisciplinaSimples("Quimica", 2);
        assertEquals(2, d.getCargaHoraria());
    }

    @Test
    public void testarNomeDoProfessor() {
        Professor p = new ProfessorSimples("Alfredo");
        assertEquals("Alfredo", p.toString());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarNomeDoHorarioNull() {
        Horario h = new HorarioSimples(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarNomeDaDisciplinaNull() {
        Disciplina d = new DisciplinaSimples(null, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarCargaHorariaDaDisciplinaZero() {
        Disciplina d = new DisciplinaSimples("Quimica", 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarCargaHorariaDaDisciplinaNegativa() {
        Disciplina d = new DisciplinaSimples("Quimica", -42);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarNomeDoProfessorNull() {
        Professor p = new ProfessorSimples(null);
    }
}