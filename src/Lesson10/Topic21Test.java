package Lesson10;

import junit.framework.TestCase;

public class Topic21Test extends TestCase {
    public void testSwapXor() {
        int[] xy = new int[] { 3, 7 };
        Topic21.swap(xy);
        // Depois do swap, o primeiro elemento deve valer 7 e o segundo 3
        assertEquals(7, xy[0]);
        assertEquals(3, xy[1]);
    }

    public void testSwapXorWithNegatives() {
        int[] xy = new int[] { -5, 12 };
        Topic21.swap(xy);
        assertEquals(12,  xy[0]);
        assertEquals(-5,  xy[1]);
    }
}
