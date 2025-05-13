package Lesson12;

import junit.framework.TestCase;

public class ReflectiveClonerTest extends TestCase {
    static class Point {
        private int x, y;
        public Point() {}          // construtor sem args
        public Point(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    public void testCloneShallow() {
        Point p1 = new Point(7, 13);
        Point p2 = ReflectiveCloner.cloneShallow(p1);

        // não é a mesma instância, mas tem mesmos valores
        assertNotSame(p1, p2);
        assertEquals(7,  p2.getX());
        assertEquals(13, p2.getY());
    }
}
