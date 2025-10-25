package com.busreservation;

public class Passenger {
    private int id;
    private String name;
    private int age;
    private int busNo;

    public Passenger(int id, String name, int age, int busNo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.busNo = busNo;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getBusNo() { return busNo; }
}
