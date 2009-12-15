/*
 * TesteColecoes.java
 * Criado em 2009/12/10 - 12:50
 */
package br.usp.test.gradescola;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Test;

import br.usp.gradescola.utilidades.Colecoes;

/**
 * @author Victor Williams Stafusa da Silva
 */
public class TesteColecoes {

    private class ImplementaApenasIterable<E> implements Iterable<E> {
        private final Iterable<E> original;

        public ImplementaApenasIterable(Iterable<E> original) {
            this.original = original;
        }

        @Override
        public Iterator<E> iterator() {
            return original.iterator();
        }
    }

    private void contemAbcd(Iterable<String> elems) {
        Iterator<String> it = elems.iterator();
        String a = it.next();
        String b = it.next();
        String c = it.next();
        String d = it.next();
        assertEquals("a", a);
        assertEquals("b", b);
        assertEquals("c", c);
        assertEquals("d", d);
        assertFalse(it.hasNext());
    }

    private void contemAbcdEmQualquerOrdem(Set<String> elems) {
        Iterator<String> it = elems.iterator();
        String a = it.next();
        String b = it.next();
        String c = it.next();
        String d = it.next();
        assertTrue(elems.contains("a"));
        assertTrue(elems.contains("b"));
        assertTrue(elems.contains("c"));
        assertTrue(elems.contains("d"));
        assertFalse(it.hasNext());
    }

    private String[] strAbcd() {
        return new String[] {"a", "b", "c", "d"};
    }

    private String[] strAabbccdd() {
        return new String[] {"a", "b", "c", "d", "a", "c", "d", "b", "a", "c", "b", "d"};
    }

    private void fazerX(String[] mudar) {
        for (int i = 0; i < mudar.length; i++) {
            mudar[i] = "x";
        }
    }

    @Test
    public void testarCopiarElementosCollection() {
        String[] str = strAbcd();
        List<String> teste = Arrays.asList(str);
        List<String> nova = Colecoes.copiarElementos(teste);
        assertNotSame(teste, nova);
        assertEquals(4, nova.size());
        contemAbcd(nova);
        fazerX(str);
        contemAbcd(nova);
    }

    @Test
    public void testarCopiarElementosIterable() {
        String[] str = strAbcd();
        List<String> teste = Arrays.asList(str);
        List<String> nova = Colecoes.copiarElementos(new ImplementaApenasIterable<String>(teste));
        assertNotSame(teste, nova);
        assertEquals(4, nova.size());
        contemAbcd(nova);
        fazerX(str);
        contemAbcd(nova);
    }

    @Test
    public void testarCopiarElementosArray() {
        String[] str = strAbcd();
        List<String> nova = Colecoes.copiarElementos(str);
        assertEquals(4, nova.size());
        contemAbcd(nova);
        fazerX(str);
        contemAbcd(nova);
    }

    @Test
    public void testarCopiarElementosCollectionNull() {
        Collection<String> lixo = null;
        assertNull(Colecoes.copiarElementos(lixo));
    }

    @Test
    public void testarCopiarElementosIterableNull() {
        Iterable<String> lixo = null;
        assertNull(Colecoes.copiarElementos(lixo));
    }

    @Test
    public void testarCopiarElementosArrayNull() {
        String[] lixo = null;
        assertNull(Colecoes.copiarElementos(lixo));
    }

    @Test
    public void testarCopiarUnicosCollection() {
        String[] str = strAabbccdd();
        List<String> teste = Arrays.asList(str);
        Set<String> nova = Colecoes.copiarUnicos(teste);
        assertNotSame(teste, nova);
        assertEquals(4, nova.size());
        contemAbcdEmQualquerOrdem(nova);
        fazerX(str);
        contemAbcdEmQualquerOrdem(nova);
    }

    @Test
    public void testarCopiarUnicosIterable() {
        String[] str = strAabbccdd();
        List<String> teste = Arrays.asList(str);
        Set<String> nova = Colecoes.copiarUnicos(new ImplementaApenasIterable<String>(teste));
        assertNotSame(teste, nova);
        assertEquals(4, nova.size());
        contemAbcdEmQualquerOrdem(nova);
        fazerX(str);
        contemAbcdEmQualquerOrdem(nova);
    }

    @Test
    public void testarCopiarUnicosArray() {
        String[] str = strAabbccdd();
        Set<String> nova = Colecoes.copiarUnicos(str);
        assertEquals(4, nova.size());
        contemAbcdEmQualquerOrdem(nova);
        fazerX(str);
        contemAbcdEmQualquerOrdem(nova);
    }

    @Test
    public void testarCopiarUnicosCollectionNull() {
        Collection<String> lixo = null;
        assertNull(Colecoes.copiarUnicos(lixo));
    }

    @Test
    public void testarCopiarUnicosIterableNull() {
        Iterable<String> lixo = null;
        assertNull(Colecoes.copiarUnicos(lixo));
    }

    @Test
    public void testarCopiarUnicosArrayNull() {
        String[] lixo = null;
        assertNull(Colecoes.copiarUnicos(lixo));
    }
}