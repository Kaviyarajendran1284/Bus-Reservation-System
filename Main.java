package com.busreservation;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static BusDAO busDAO = new BusDAO();
    static PassengerDAO passengerDAO = new PassengerDAO();

    public static void main(String[] args) {
        System.out.println("✅ Bus Reservation System Started!");

        while (true) {
            System.out.println("\n==== Menu ====");
            System.out.println("1. View Bus Availability");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: viewBuses(); break;
                case 2: bookTicket(); break;
                case 3: cancelTicket(); break;
                case 4: System.out.println("Exiting..."); System.exit(0);
                default: System.out.println("Invalid option!");
            }
        }
    }

    public static void viewBuses() {
        List<Bus> buses = busDAO.getAllBuses();
        System.out.println("\n--- Bus Availability ---");
        for (Bus bus : buses) {
            System.out.println("Bus No: " + bus.getBusNo() + 
                               " | AC: " + bus.isAc() + 
                               " | Available Seats: " + bus.getAvailableSeats());
        }
    }

    public static void bookTicket() {
        System.out.print("Enter Bus No to book: ");
        int busNo = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Passenger Age: ");
        int age = sc.nextInt();

        if (busDAO.bookTicket(busNo)) {
            if (passengerDAO.addPassenger(name, age, busNo)) {
                System.out.println("✅ Ticket booked successfully for " + name);
            } else {
                System.out.println("❌ Ticket booked, but passenger info not saved!");
            }
        } else {
            System.out.println("❌ Booking failed or no seats available.");
        }
    }

    public static void cancelTicket() {
        System.out.print("Enter Bus No to cancel: ");
        int busNo = sc.nextInt();
        if (busDAO.cancelTicket(busNo)) {
            System.out.println("✅ Ticket cancelled successfully!");
            // Optional: delete passenger from passengers table (not implemented)
        } else {
            System.out.println("❌ Cancellation failed or no bookings.");
        }
    }
}
