package com.travelcompany.eshop.models;

public abstract class Person {
    private int id;
    private String name;
    private String email;
    private String address;
    private String nationality;

    public Person(int id, String name, String email, String address, String nationality) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Object getAddress() {
        return address;
    }

    public Object getNationality() {
        return nationality;
    }
}
