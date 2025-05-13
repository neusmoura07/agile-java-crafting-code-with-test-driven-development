package Lesson12;

import junit.framework.TestCase;

public class ToStringProxyTest extends TestCase {
    // classe de teste que implementa Dumpable
    public static class Person implements PersonInfo {
        private String name;
        private int age;
        public Person() {}
        public Person(String name, int age) { this.name = name; this.age = age; }
        public String getName() { return name; }
        public int getAge() { return age; }
        @Override
        public String toString() {
            return "Person(" + name + "," + age + ")";
        }
    }

    public void testToStringIsDump() {
        Person original = new Person("Alice", 30);
        PersonInfo proxy = ToStringProxy.createProxy(PersonInfo.class, original);

        // delega getName
        assertEquals("Alice", proxy.getName());

        // dumper no toString
        String dump = proxy.toString();
        assertTrue(dump.contains("name = Alice"));
        assertTrue(dump.contains("age = 30"));
    }

}
