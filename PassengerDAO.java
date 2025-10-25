package com.busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PassengerDAO {

    // Add passenger
    public boolean addPassenger(String name, int age, int busNo) {
        String sql = "INSERT INTO passengers(name, age, bus_no) VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, busNo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding passenger: " + e.getMessage());
            return false;
        }
    }
}
