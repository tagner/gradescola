/*
 * SorteadorRandom.java
 * Criado em 2009/12/03 - 10:03
 */
package br.usp.gradescola.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class SorteadorRandom implements Sorteador {

    private final Random random;

    public SorteadorRandom() {
        this(new Random());
    }

    public SorteadorRandom(Random random) {
        this.random = random;
    }

    @Override
    public <E> E sortearElemento(Iterable<E> elementos) {
        List<E> copia = Colecoes.copiarElementos(elementos);
        Collections.shuffle(copia, random);
        return copia.iterator().next();
    }

    @Override
    public <E> E sortearElemento(E... elementos) {
        return elementos[random.nextInt(elementos.length)];
    }

    @Override
    public <E> List<E> sortearElementos(int n, Iterable<E> elementos) {
        List<E> copia = Colecoes.copiarElementos(elementos);
        Collections.shuffle(copia, random);
        List<E> resposta = new ArrayList<E>(n);
        for (E elem : copia) {
            if (n == 0) break;
            resposta.add(elem);
            n--;
        }
        return resposta;
    }

    @Override
    public <E> List<E> sortearElementos(int n, E... elementos) {
        return this.sortearElementos(n, Arrays.asList(elementos));
    }
}