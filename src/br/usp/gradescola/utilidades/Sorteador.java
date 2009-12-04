/*
 * Sorteador.java
 * Criado em 2009/12/03 - 10:02
 */
package br.usp.gradescola.utilidades;

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
public interface Sorteador {

    public <E> E sortearElemento(Iterable<E> elementos);

    public <E> E sortearElemento(E... elementos);

    public <E> List<E> sortearElementos(int n, Iterable<E> elementos);

    public <E> List<E> sortearElementos(int n, E... elementos);
}