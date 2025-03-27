package sis.studentinfo;

import junit.framework.*;
import java.util.*;

public class PerfomanceTest extends TestCase {
    private static final double tolerance = 0.005;

    public void testInitialization() {
        Perfomance perfomance = new Perfomance();
        perfomance.setScores(75, 72, 90, 60);
        assertEquals(74.25, perfomance.average(), tolerance);
        perfomance.setScores(100,90);
        assertEquals(95.0,perfomance.average(), tolerance);
    }

    public void testAverage() {
        Perfomance perfomance = new Perfomance();
        perfomance.setNumberOfTests(4);
        perfomance.set(0, 98);
        perfomance.set(1, 92);
        perfomance.set(2, 81);
        perfomance.set(3, 72);

        assertEquals(92, perfomance.get(1));
        assertEquals(85.75, perfomance.average(), tolerance);
    }

    // 0 1 2 3
    // 4 5 6 7
    // 8 9 10 11
    public void testTwoDimensionalArrays() {
        final int rows = 3;
        final int cols = 4;
        int count = 0;
        int[][] matrix = new int[rows][cols];
        for(int x = 0; x < rows; x++)
            for (int y = 0; y < cols; y++)
                matrix[x][y] = count++;
        assertEquals(11, matrix[2][3]);
        assertEquals(6, matrix[1][2]);

    }
    // 0
    // 1 2
    // 3 4 5
    public void testPartialDimensions() {
        final int rows = 3;
        int[][] matrix = new int[rows][];
        matrix[0] = new int[]{0 };
        matrix[1] = new int[]{1, 2 };
        matrix[2] = new int[]{3, 4, 5 };
        assertEquals(1, matrix[1][0]);
        assertEquals(5, matrix[2][2]);
    }

    public void testArraysEquals() {
        int[] a = {1, 2, 3};
        int[] b = {1,2,3};
        assertTrue(Arrays.equals(a,b));
    }

}
