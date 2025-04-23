package Lesson10;

import junit.framework.TestCase;
import java.util.*;

/**
 * Testa ListSwapper no estilo do livro:
 *  - JUnit 3 (extends TestCase)
 *  - stub de Random como classe interna
 *  - setRandom(...) para injetar o stub
 */
public class ListSwapperTest extends TestCase {

    /**
     * Stub de Random que retorna valores predefinidos na ordem que você passar.
     */
    private static class StubRandom extends Random {
        private final int[] seq;
        private int idx;
        StubRandom(int... seq) { this.seq = seq; }
        @Override
        public int nextInt(int bound) {
            return seq[idx++];
        }
    }

    /**
     * Smoke‑test: depois de 100 swaps, o tamanho continua 100
     * e o conjunto de elementos permanece {1..100}.
     */
    public void testSwapManyMaintainsSizeAndContents() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) list.add(i);

        List<Integer> original = new ArrayList<Integer>(list);

        ListSwapper swapper = new ListSwapper();
        swapper.swapMany(list, 100);

        // tamanho não mudou
        assertEquals(100, list.size());
        // mesmos elementos, em qualquer ordem
        assertEquals(new HashSet<Integer>(original),
                new HashSet<Integer>(list));
    }

    /**
     * Teste determinístico: com stub que retorna (0,1), cada swap
     * deverá trocar exatamente os elementos nas posições 0 e 1.
     */
    public void testSingleSwapSwapsExactlyTwoElements() {
        // lista de exemplo
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) list.add(i);

        // stub força i=0, j=1
        StubRandom stub = new StubRandom(0, 1);
        ListSwapper swapper = new ListSwapper();
        swapper.setRandom(stub);

        List<Integer> before = new ArrayList<Integer>(list);
        swapper.swapMany(list, 1);

        // conta quantas posições mudaram
        int changes = 0;
        for (int k = 0; k < list.size(); k++) {
            if (!before.get(k).equals(list.get(k))) {
                changes++;
            }
        }
        // deve ser exatamente 2 posições trocadas
        assertEquals(2, changes);
    }
}

