package Lesson14x15;

public class Address {
    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String shortFormat() {
        return street + ", " + city;
    }

    public String uppercaseCity() {
        return city.toUpperCase();
    }

    @Override
    public String toString() {
        return "Address(" + street + "-" + city + ")";
    }
}
