package Lesson14;

import junit.framework.TestCase;
import java.util.NoSuchElementException;

public class RingTest extends TestCase {
    public void testAddAndGetCurrent() {
        Ring<String> ring = new Ring<>();
        ring.add("A");
        assertEquals(1, ring.size());
        assertEquals("A", ring.getCurrent());
        ring.add("B");
        // still at A, new element added after
        assertEquals("A", ring.getCurrent());
    }

    public void testAdvanceAndBack() {
        Ring<Integer> ring = new Ring<>();
        ring.add(1);
        ring.add(2);
        ring.add(3);
        // order: current=1 ->2->3->1
        assertEquals(Integer.valueOf(1), ring.getCurrent());
        ring.advance();
        assertEquals(Integer.valueOf(2), ring.getCurrent());
        ring.advance();
        assertEquals(Integer.valueOf(3), ring.getCurrent());
        ring.back();
        assertEquals(Integer.valueOf(2), ring.getCurrent());
    }

    public void testRemoveCurrent() {
        Ring<Character> ring = new Ring<>();
        ring.add('X');
        ring.add('Y');
        ring.add('Z');
        // sequence: X(current),Y,Z
        assertEquals(Character.valueOf('X'), ring.removeCurrent());
        // now current should be Y
        assertEquals(Character.valueOf('Y'), ring.getCurrent());
        assertEquals(2, ring.size());
        // remove remaining
        ring.removeCurrent(); // removes Y
        ring.removeCurrent(); // removes Z
        assertTrue(ring.isEmpty());
    }

    public void testIterationOrder() {
        Ring<String> ring = new Ring<>();
        ring.add("one");
        ring.add("two");
        ring.add("three");
        // current is "one"
        StringBuilder sb = new StringBuilder();
        for (String s : ring) {
            sb.append(s);
        }
        assertEquals("onetwothree", sb.toString());
    }

    public void testEmptyOperationsThrow() {
        Ring<Double> ring = new Ring<>();
        try {
            ring.getCurrent();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
        try {
            ring.removeCurrent();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
        try {
            ring.advance();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
        try {
            ring.back();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
    }
}

