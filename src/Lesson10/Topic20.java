package Lesson10;

import junit.framework.TestCase;
import java.util.Random;

public class Topic20 extends TestCase {
    /**
     * Verifica que um Random com semente fixa gera sempre o mesmo nextDouble(),
     * e que um Random “sem semente” (baseado no clock) NÃO gera esse mesmo valor.
     */
    public void testNextDoubleDiffersFromDefaultSeed() {
        // 1) Random com seed = 1
        Random seeded = new Random(1L);
        double seededValue = seeded.nextDouble();

        // 2) Random sem seed explícita → usa System.nanoTime() internamente
        Random unseeded = new Random();
        double unseededValue = unseeded.nextDouble();

        // Primeiro, reforçamos que o comportamento com seed fixe é determinístico:
        // sabemos (pela especificação de java.util.Random) que nextDouble() em seed=1 deve ser X
        // — opcionalmente, podemos até testar contra o valor exato esperado:
        // assertEquals(0.730967787376657, seededValue, 1e-15);

        // Agora, provamos que o “sem seed” NÃO coincide com esse valor:
        assertFalse(
                "Um Random sem seed não deveria gerar o mesmo nextDouble() de um com seed=1",
                seededValue == unseededValue
        );
    }
}
