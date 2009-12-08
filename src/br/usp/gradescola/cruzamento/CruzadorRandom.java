/*
 * CruzadorRandom.java
 * Criado em 2009/12/03 - 22:29
 */
package br.usp.gradescola.cruzamento;

import java.util.List;

import br.usp.gradescola.estrutura.Cruzador;
import br.usp.gradescola.estrutura.Grade;
import br.usp.gradescola.estrutura.Horario;
import br.usp.gradescola.estrutura.Problema;

import br.usp.gradescola.utilidades.Colecoes;
import br.usp.gradescola.utilidades.Sorteador;
import br.usp.gradescola.utilidades.SorteadorRandom;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class CruzadorRandom implements Cruzador {

    private final Sorteador sorte;

    private final List<Cruzador> cruzadores;

    public CruzadorRandom(Sorteador sorte, Cruzador... cruzadores) {
        this.sorte = sorte;
        this.cruzadores = Colecoes.copiarElementos(cruzadores);
    }

    public CruzadorRandom(Sorteador sorte, Iterable<Cruzador> cruzadores) {
        this.sorte = sorte;
        this.cruzadores = Colecoes.copiarElementos(cruzadores);
    }

    public CruzadorRandom(Cruzador... cruzadores) {
        this(new SorteadorRandom(), cruzadores);
    }

    public CruzadorRandom(Iterable<Cruzador> cruzadores) {
        this(new SorteadorRandom(), cruzadores);
    }

    public CruzadorRandom(Problema problema, Sorteador sorte) {
        this(sorte, new CruzadorProfessor(problema, sorte),
                    new CruzadorDisciplina(problema, sorte),
                    new CruzadorHorario(problema, sorte));
    }

    public CruzadorRandom(Problema problema) {
        this(problema, new SorteadorRandom());
    }

    @Override
    public Grade cruzar(Grade grade1, Grade grade2) {
        return sorte.sortearElemento(cruzadores).cruzar(grade1, grade2);
    }
}