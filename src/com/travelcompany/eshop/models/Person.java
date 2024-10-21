package com.travelcompany.eshop.models;

public abstract class Person {

    private final int id;
    private final String name;
    private final String email;
    private final String address;
    private final String nationality;

    public Person(int id, String name, String email, String address, String nationality) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

}
