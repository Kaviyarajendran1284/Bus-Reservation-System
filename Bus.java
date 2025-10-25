package com.busreservation;

public class Bus {
    private int busNo;
    private boolean ac;
    private int capacity;
    private int bookedSeats;

    public Bus(int busNo, boolean ac, int capacity, int bookedSeats) {
        this.busNo = busNo;
        this.ac = ac;
        this.capacity = capacity;
        this.bookedSeats = bookedSeats;
    }

    // Getters & Setters
    public int getBusNo() { return busNo; }
    public boolean isAc() { return ac; }
    public int getCapacity() { return capacity; }
    public int getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(int bookedSeats) { this.bookedSeats = bookedSeats; }

    public int getAvailableSeats() {
        return capacity - bookedSeats;
    }
}
