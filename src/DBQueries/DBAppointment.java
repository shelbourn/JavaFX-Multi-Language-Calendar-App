/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBAppointment {

    public DBAppointment() {
    }

    // Retrieves all appointments records from the database and assigns them to an Observable Array
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {

            String qGetAppointments = "SELECT appointmentId, appointment.customerId, customerName, appointment.userId, userName, type, start, end FROM appointment, customer, user WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId";

            PreparedStatement psGetAppointments = DBConn.startConnection().prepareStatement(qGetAppointments);

            ResultSet rsGetAppointments = psGetAppointments.executeQuery();

            while (rsGetAppointments.next()) {
                int appointmentId = rsGetAppointments.getInt("appointmentId");
                int customerId = rsGetAppointments.getInt("customerId");
                int userId = rsGetAppointments.getInt("userId");
                String type = rsGetAppointments.getString("type");
                Timestamp start = rsGetAppointments.getTimestamp("start");
                Timestamp end = rsGetAppointments.getTimestamp("end");
                String customerName = rsGetAppointments.getString("customerName");
                String userName = rsGetAppointments.getString("userName");

                Appointment a = new Appointment(appointmentId, customerId, userId, type, start, end, userName, customerName);
                appointmentList.add(a);
            }
            System.out.println("Database Query Successful!\nAppointment list retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return appointmentList;
    }

    // Creates a new Appointment record in the database
    public static void createAppointment(int customerId, int userId, LocalDate dateParam, LocalTime startTime, LocalTime endTime, String type) {

        try {
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            String localDateTime = localDate + " " + localTime;

            String formattedStartTime = dateParam + " " + startTime;
            String formattedEndTime = dateParam + " " + endTime;

            // Setting the SQL query template with variables for creation of appointment record
            String qAddAppointment = "INSERT INTO appointment VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Setting the prepared statement
            PreparedStatement psAddAppointment = DBConn.startConnection().prepareStatement(qAddAppointment, Statement.RETURN_GENERATED_KEYS);

            // Assigning values to the SQL query variables
            psAddAppointment.setInt(1, customerId);
            psAddAppointment.setInt(2, userId);
            psAddAppointment.setString(3, "N/A");
            psAddAppointment.setString(4, "N/A");
            psAddAppointment.setString(5, "N/A");
            psAddAppointment.setString(6, "N/A");
            psAddAppointment.setString(7, type);
            psAddAppointment.setString(8, "N/A");
            psAddAppointment.setString(9, formattedStartTime);
            psAddAppointment.setString(10, formattedEndTime);
            psAddAppointment.setString(11, localDateTime);
            psAddAppointment.setString(12, "N/A");
            psAddAppointment.setString(13, localDateTime);
            psAddAppointment.setString(14, "N/A");

            // Executing the prepared statement
            psAddAppointment.execute();

            System.out.println("Database Query Successful!\nNew Appointment Added!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

}
