package Lesson12;

import junit.framework.TestCase;
import java.util.*;

public class ObjectDumperTest extends TestCase {
    private static class A {
        private int x = 42;
        private String s = "foo";
        private B b = new B();
        static int counter = 5;
    }
    private static class B {
        private double d = 3.14;
    }

    public void testDump() {
        A a = new A();
        String out = ObjectDumper.dump(a);

        // Deve conter linhas como:
        // A {
        //   int x = 42
        //   String s = foo
        //   B b =
        //     B {
        //       double d = 3.14
        //     }
        //   int static counter = 5
        // }
        assertTrue(out.contains("int x = 42"));
        assertTrue(out.contains("String s = foo"));
        assertTrue(out.contains("double d = 3.14"));
        assertTrue(out.contains("static"));
    }
}
