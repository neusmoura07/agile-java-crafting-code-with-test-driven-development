package Lesson14x15;

import junit.framework.TestCase;

public class ToStringerTest extends TestCase {
    public void testMultipleOutputMethods() {
        Address addr = new Address("Av Sr.", "Maceio");
        Person p = new Person("Neus", 21, addr);
        String result = ToStringer.toString(p);
        assertEquals("Person [age=21, name=\"Neus\", address=Av Sr., Maceio MACEIO]", result);
    }
}
