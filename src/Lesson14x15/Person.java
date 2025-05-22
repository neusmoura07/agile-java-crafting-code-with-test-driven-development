package Lesson14x15;

public class Person {
    @Dump(order = 2, quote = true)
    private String name;

    @Dump(order = 1)
    private int age;

    @Dump(order = 3, outputMethods = {"shortFormat", "uppercaseCity"})
    private Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
