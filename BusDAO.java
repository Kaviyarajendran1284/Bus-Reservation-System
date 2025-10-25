package com.busreservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // Get all buses
    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT bus_no, ac, capacity, booked_seats FROM buses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                buses.add(new Bus(
                        rs.getInt("bus_no"),
                        rs.getBoolean("ac"),
                        rs.getInt("capacity"),
                        rs.getInt("booked_seats")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching buses: " + e.getMessage());
        }
        return buses;
    }

    // Book a ticket
    public boolean bookTicket(int busNo) {
        String select = "SELECT capacity, booked_seats FROM buses WHERE bus_no=?";
        String update = "UPDATE buses SET booked_seats=? WHERE bus_no=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(select);
             PreparedStatement psUpdate = conn.prepareStatement(update)) {

            psSelect.setInt(1, busNo);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next()) {
                int capacity = rs.getInt("capacity");
                int booked = rs.getInt("booked_seats");
                if (booked < capacity) {
                    booked++;
                    psUpdate.setInt(1, booked);
                    psUpdate.setInt(2, busNo);
                    psUpdate.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        }
        return false;
    }

    // Cancel a ticket
    public boolean cancelTicket(int busNo) {
        String select = "SELECT booked_seats FROM buses WHERE bus_no=?";
        String update = "UPDATE buses SET booked_seats=? WHERE bus_no=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(select);
             PreparedStatement psUpdate = conn.prepareStatement(update)) {

            psSelect.setInt(1, busNo);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next()) {
                int booked = rs.getInt("booked_seats");
                if (booked > 0) {
                    booked--;
                    psUpdate.setInt(1, booked);
                    psUpdate.setInt(2, busNo);
                    psUpdate.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error cancelling ticket: " + e.getMessage());
        }
        return false;
    }
}
