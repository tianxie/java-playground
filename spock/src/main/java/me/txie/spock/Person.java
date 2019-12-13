package me.txie.spock;

public class Person {
    private String firstName;
    private String lastName;
    private int iq;

    public Person(String firstName, String lastName, int iq) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.iq = iq;
    }

    public boolean isFool() {
        return iq < 70;
    }
}