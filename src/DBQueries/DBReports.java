/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import utils.DBConn;
import utils.TimeConverters;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBReports {

    public DBReports() {
    }

    // Retrieves appointment data for specified User from database
    public static ObservableList<Appointment> getUserAppointments(int consultantId) {
        ObservableList<Appointment> userAppointmentsList = FXCollections.observableArrayList();

        try {

            String qGetUserAppointments = "SELECT appointmentId, appointment.customerId, customerName, appointment.userId, userName, type, start, end FROM appointment, customer, user WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId AND appointment.userId = ?";

            PreparedStatement psGetUserAppointments = DBConn.startConnection().prepareStatement(qGetUserAppointments);

            // Assigns values to the SQL query variables
            psGetUserAppointments.setInt(1, consultantId);

            ResultSet rsGetUserAppointments = psGetUserAppointments.executeQuery();

            // Building out the Appointment Objects
            while (rsGetUserAppointments.next()) {
                int appointmentId = rsGetUserAppointments.getInt("appointmentId");
                int customerId = rsGetUserAppointments.getInt("customerId");
                int userId = rsGetUserAppointments.getInt("userId");
                String type = rsGetUserAppointments.getString("type");
                Timestamp start = rsGetUserAppointments.getTimestamp("start");
                Timestamp end = rsGetUserAppointments.getTimestamp("end");
                String customerName = rsGetUserAppointments.getString("customerName");
                String userName = rsGetUserAppointments.getString("userName");

                // Converting SQL Timestamp properties to Local Date/Time Objects (in user's timezone)
                LocalDateTime zonedLDTStart = TimeConverters.utcTimestampToLDT(start);
                LocalDateTime zonedLTDEnd = TimeConverters.utcTimestampToLDT(end);

                LocalDate zonedDate = zonedLDTStart.toLocalDate();
                LocalTime zonedStart = zonedLDTStart.toLocalTime();
                LocalTime zonedEnd = zonedLTDEnd.toLocalTime();

                // Create Appointment Object with data
                Appointment a = new Appointment(appointmentId, customerId, userId, type, zonedDate, zonedStart, zonedEnd, userName, customerName);
                userAppointmentsList.add(a);
            }
            System.out.println("Database Query Successful!\nConsultant appointment list retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return userAppointmentsList;
    }

}
