package Lesson10;

import junit.framework.*;

import java.math.*;
import java.util.*;

import static org.junit.Assert.assertNotEquals;


public class MathTest extends TestCase {
    static final double TOLERANCE = 0.05;

    public void testHypotenuse() {
        assertEquals(5.0, Math.hypot(3.0, 4.0), TOLERANCE);
    }

    public void testCoinFlips() {
        final long seed = 100L;
        final int total = 10;

        Random random1 = new Random(seed);
        List<Boolean> flips1 = new ArrayList<Boolean>();

        for(int i = 0; i < total; i++)
            flips1.add(random1.nextBoolean());

        Random random2 = new Random(seed);
        List<Boolean> flips2 = new ArrayList<Boolean>();

        for(int i = 0; i < total; i++)
            flips2.add(random2.nextBoolean());

        assertEquals(flips1, flips2);
    }

    //Exercise Lesson 10. Mathematics Topic 1
    public void testBigDecimalImmutability() {
        BigDecimal original = new BigDecimal("10.00");
        BigDecimal sum = original.add(new BigDecimal("5.00"));

        assertEquals(new BigDecimal("15.00"), sum);
        assertEquals(new BigDecimal("10.00"), original);
    }

    //Exercise Lesson 10. Mathematics Topic 2
    public void testBigDecimalNotEquals() {
        BigDecimal bigDecimalTen = new BigDecimal("10.00");
        BigDecimal bigDecimalOne = new BigDecimal("1");

        assertNotEquals("10.00 e 1 não devem ser iguais", bigDecimalTen, bigDecimalOne);

        BigDecimal transformed = bigDecimalOne.multiply(new BigDecimal("10")).setScale(2, RoundingMode.HALF_UP);
        assertEquals("Depois de multiplicar, deve ser igual a 10.00", bigDecimalTen, transformed);

        //O metodo stripTrailingZeros() remove os zeros à direita, de forma que o valor resultante seja numericamente equivalente a "1".
        BigDecimal reversed = bigDecimalTen.divide(new BigDecimal("10"), 2,RoundingMode.HALF_UP).stripTrailingZeros();
        assertEquals("Após a divisão, o valor numérico deve ser igual a 1", bigDecimalOne, reversed);

    }

    //Exercise Lesson 10. Mathematics Topic 3
    public void testTopicThree() {
        float expected = 0.9f;
        float result = (0.005f * 2.0f);
        // Verifica que, sem tolerância, os valores não são exatamente iguais.
        assertFalse("Valores float não são exatamente iguais", expected == result);
        assertEquals(expected, result, 0.89f);

        // Usando double
        double expected1 = 0.9;
        double result1 = 0.005 * 2.0;
        assertEquals(expected1, result1, 0.89);
        //Valores muito abaixo do esperado, acaba sendo indiferente a precisão dos dois nesse caso.
    }

    //Exercise Lesson 10. Mathematics Topic 4

    //public class CompilerError {
    //    float x = 0.01;
    //}

    // Para consertar esse erro as duas formas seria ou colocando o f no final do literal (0.01f) ou colocando um cast explicito para float
    // (float x = (float) 0.01;)

    //Exercise Lesson 10. Mathematics Topic 5

    public void testTopicFive() {
        int hexValue = 0xDEAD;
        //Printando no console o hexadecimal é 57005
        // System.out.println(hexValue);
        assertEquals(57005, hexValue);

        String octalString = Integer.toOctalString(hexValue);
        //Printando no console o octagonal é 157255
        //System.out.println(octalString);
        assertEquals("157255",octalString);

    }

    //Exercise Lesson 10. Mathematics Topic 6
    public void testDivisionByZero() {
        double posZero = 1.0 / 0.0;
        assertTrue(Double.isInfinite(posZero));
        assertEquals(Double.POSITIVE_INFINITY, posZero);

        double negZero = -1.0 / 0.0;
        assertTrue(Double.isInfinite(negZero));
        assertEquals(Double.NEGATIVE_INFINITY, negZero);
    }

    public void testZeroDividedByZero() {
        double nanResult = 0.0 / 0.0;
        assertTrue(Double.isNaN(nanResult));
    }

    public void testInfinityArithmetic() {
        //Soma de infinito com número finito
        assertEquals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY + 123.45);

        //Infinito menos infinito deve resultar em NaN
        assertTrue(Double.isNaN(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY));

        //Infinito vezes um número positivo é infinito positivo
        assertEquals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY * 2.0);
    }

    public void testNaNProperties() {
        //Qualquer comparação com NaN sempre resulta em false
        double nan = Double.NaN;
        assertFalse(nan > 0);
        assertFalse(nan < 0);
        assertFalse(nan == nan);
    }

    //Exercise Lesson 10. Mathematics Topic 7
    public void testNaNComparisonPrimitiveS() {
        //Para tipos primitivos, NaN não é igual a NaN utilizando ==
        float a = Float.NaN;
        float b = Float.NaN;
        assertFalse(a == b);
    }

    public void testNaNComparisonWrappers() {
        //Para wrappers, o metodo equals trata Nan como igual a NaN
        Float a = Float.NaN;
        Float b = Float.NaN;
        assertTrue(a.equals(b));
    }

    public void testAutoboxingDifference() {
        Float a = 3.14f;
        Float b = 3.14f;

        assertTrue(a.equals(b));
    }

    public void testFilterUsingModulo() {
        List<Integer> expected = Arrays.asList(3, 6, 9);
        List<Integer> result = DivisibleByThree.filterUsingModulo(1,2,3,4,5,6,7,8,9,10);
        assertEquals(expected, result);

    }

    public void testFilterUsingDivision() {
        List<Integer> expected = Arrays.asList(3,6,9);
        List<Integer> result = DivisibleByThree.filterUsingDivision(1,2,3,4,5,6,7,8,9);
        assertEquals(expected,result);
    }

    public void testcreate() {
        for(int i = 0; i < 10_000; i++) {
            int r = random1to50();
            assertTrue(r >= 1 && r <= 50);
        }

    }

    public static int random1to50() {
        return (int)(Math.random() * 50) + 1;
    }

}
