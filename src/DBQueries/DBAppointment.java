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
import static utils.HelperMethods.convertToLDT;
import static utils.HelperMethods.ldtToTimestamp;
import utils.TimeConverters;
import static utils.TimeConverters.ldtToUTCTimestamp;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBAppointment {

    public DBAppointment() {
    }

    // Retrieves all appointments records from the database and assigns them to an Observable Array
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> allAppointmentsList = FXCollections.observableArrayList();

        try {

            String qGetAppointments = "SELECT appointmentId, appointment.customerId, customerName, appointment.userId, userName, type, start, end FROM appointment, customer, user WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId";

            PreparedStatement psGetAppointments = DBConn.startConnection().prepareStatement(qGetAppointments);

            ResultSet rsGetAppointments = psGetAppointments.executeQuery();

            // Building out the Appointment Objects
            while (rsGetAppointments.next()) {
                int appointmentId = rsGetAppointments.getInt("appointmentId");
                int customerId = rsGetAppointments.getInt("customerId");
                int userId = rsGetAppointments.getInt("userId");
                String type = rsGetAppointments.getString("type");
                Timestamp start = rsGetAppointments.getTimestamp("start");
                Timestamp end = rsGetAppointments.getTimestamp("end");
                String customerName = rsGetAppointments.getString("customerName");
                String userName = rsGetAppointments.getString("userName");

                // Converting SQL Timestamp properties to Local Date/Time Objects (in user's timezone)
                LocalDateTime zonedLDTStart = TimeConverters.utcTimestampToLDT(start);
                LocalDateTime zonedLTDEnd = TimeConverters.utcTimestampToLDT(end);

                LocalDate zonedDate = zonedLDTStart.toLocalDate();
                LocalTime zonedStart = zonedLDTStart.toLocalTime();
                LocalTime zonedEnd = zonedLTDEnd.toLocalTime();

                // Create Appointment Object with data
                Appointment a = new Appointment(appointmentId, customerId, userId, type, zonedDate, zonedStart, zonedEnd, userName, customerName);
                allAppointmentsList.add(a);
            }
            System.out.println("Database Query Successful!\nAppointment list retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return allAppointmentsList;
    }

    // Retrieves appointments records for current week from the database and assigns them to an Observable Array
    public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weeklyAppointmentsList = FXCollections.observableArrayList();

        try {

            String qGetAppointments = "SELECT appointmentId, appointment.customerId, customerName, appointment.userId, userName, type, start, end FROM appointment, customer, user WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId AND WEEK(start) = WEEK(NOW())";

            PreparedStatement psGetAppointments = DBConn.startConnection().prepareStatement(qGetAppointments);

            ResultSet rsGetAppointments = psGetAppointments.executeQuery();

            // Building out the Appointment Objects
            while (rsGetAppointments.next()) {
                int appointmentId = rsGetAppointments.getInt("appointmentId");
                int customerId = rsGetAppointments.getInt("customerId");
                int userId = rsGetAppointments.getInt("userId");
                String type = rsGetAppointments.getString("type");
                Timestamp start = rsGetAppointments.getTimestamp("start");
                Timestamp end = rsGetAppointments.getTimestamp("end");
                String customerName = rsGetAppointments.getString("customerName");
                String userName = rsGetAppointments.getString("userName");

                // Converting SQL Timestamp properties to Local Date/Time Objects (in user's timezone)
                LocalDateTime zonedLDTStart = TimeConverters.utcTimestampToLDT(start);
                LocalDateTime zonedLTDEnd = TimeConverters.utcTimestampToLDT(end);

                LocalDate zonedDate = zonedLDTStart.toLocalDate();
                LocalTime zonedStart = zonedLDTStart.toLocalTime();
                LocalTime zonedEnd = zonedLTDEnd.toLocalTime();

                // Create Appointment Object with data
                Appointment a = new Appointment(appointmentId, customerId, userId, type, zonedDate, zonedStart, zonedEnd, userName, customerName);
                weeklyAppointmentsList.add(a);
            }
            System.out.println("Database Query Successful!\nAppointment list retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return weeklyAppointmentsList;
    }

    // Retrieves appointments records for current month from the database and assigns them to an Observable Array
    public static ObservableList<Appointment> getMonthlyAppointments() {
        ObservableList<Appointment> monthlyAppointmentsList = FXCollections.observableArrayList();

        try {

            String qGetAppointments = "SELECT appointmentId, appointment.customerId, customerName, appointment.userId, userName, type, start, end FROM appointment, customer, user WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId AND MONTH(start) = MONTH(NOW())";

            PreparedStatement psGetAppointments = DBConn.startConnection().prepareStatement(qGetAppointments);

            ResultSet rsGetAppointments = psGetAppointments.executeQuery();

            // Building out the Appointment Objects
            while (rsGetAppointments.next()) {
                int appointmentId = rsGetAppointments.getInt("appointmentId");
                int customerId = rsGetAppointments.getInt("customerId");
                int userId = rsGetAppointments.getInt("userId");
                String type = rsGetAppointments.getString("type");
                Timestamp start = rsGetAppointments.getTimestamp("start");
                Timestamp end = rsGetAppointments.getTimestamp("end");
                String customerName = rsGetAppointments.getString("customerName");
                String userName = rsGetAppointments.getString("userName");

                // Converting SQL Timestamp properties to Local Date/Time Objects (in user's timezone)
                LocalDateTime zonedLDTStart = TimeConverters.utcTimestampToLDT(start);
                LocalDateTime zonedLTDEnd = TimeConverters.utcTimestampToLDT(end);

                LocalDate zonedDate = zonedLDTStart.toLocalDate();
                LocalTime zonedStart = zonedLDTStart.toLocalTime();
                LocalTime zonedEnd = zonedLTDEnd.toLocalTime();

                // Create Appointment Object with data
                Appointment a = new Appointment(appointmentId, customerId, userId, type, zonedDate, zonedStart, zonedEnd, userName, customerName);
                monthlyAppointmentsList.add(a);
            }
            System.out.println("Database Query Successful!\nAppointment list retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return monthlyAppointmentsList;
    }

    // Creates a new Appointment record in the database
    public static void createAppointment(int customerId, int userId, LocalDate dateParam, LocalTime startTime, LocalTime endTime, String type) {

        // Converting LocalDate and LocalTime Objects into SQL Timestamp properties (in UTC)
        Timestamp zonedDateTS = ldtToUTCTimestamp(dateParam, startTime);
        Timestamp zonedStartTS = ldtToUTCTimestamp(dateParam, startTime);
        Timestamp zonedEndTS = ldtToUTCTimestamp(dateParam, endTime);

        try {

            // Sets the SQL query template with variables for creation of appointment record
            String qAddAppointment = "INSERT INTO appointment VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Sets the prepared statement
            PreparedStatement psAddAppointment = DBConn.startConnection().prepareStatement(qAddAppointment);

            // Assigns values to the SQL query variables
            psAddAppointment.setInt(1, customerId);
            psAddAppointment.setInt(2, userId);
            psAddAppointment.setString(3, "N/A");
            psAddAppointment.setString(4, "N/A");
            psAddAppointment.setString(5, "N/A");
            psAddAppointment.setString(6, "N/A");
            psAddAppointment.setString(7, type);
            psAddAppointment.setString(8, "N/A");
            psAddAppointment.setTimestamp(9, zonedStartTS);
            psAddAppointment.setTimestamp(10, zonedEndTS);
            psAddAppointment.setTimestamp(11, zonedDateTS);
            psAddAppointment.setString(12, "N/A");
            psAddAppointment.setTimestamp(13, zonedDateTS);
            psAddAppointment.setString(14, "N/A");

            // Executes the prepared statement
            psAddAppointment.execute();

            System.out.println("Database Query Successful!\nNew Appointment Added!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

    // Updates an Appointment record in the database
    public static void updateAppointment(int appointmentId, int customerId, int userId, LocalDate dateParam, LocalTime startTime, LocalTime endTime, String type) {

        // Converting LocalDate and LocalTime Objects into SQL Timestamp properties (in UTC)
        Timestamp zonedStartTS = ldtToUTCTimestamp(dateParam, startTime);
        Timestamp zonedEndTS = ldtToUTCTimestamp(dateParam, endTime);

        try {
            // Sets the SQL query template with variables for updating the Appointment Record
            String qUpdateAppt = "UPDATE appointment set customerId = ?, userId = ?, start = ?, end = ?, type  = ? WHERE appointmentId = ?";

            // Sets the prepared statement
            PreparedStatement psUpdateAppt = DBConn.startConnection().prepareStatement(qUpdateAppt);

            // Assigns values to the SQL query variables
            psUpdateAppt.setInt(1, customerId);
            psUpdateAppt.setInt(2, userId);
            psUpdateAppt.setTimestamp(3, zonedStartTS);
            psUpdateAppt.setTimestamp(4, zonedEndTS);
            psUpdateAppt.setString(5, type);
            psUpdateAppt.setInt(6, appointmentId);

            // Executes the prepared statement
            psUpdateAppt.execute();

            System.out.println("Database Query Successful!\nAppointment Updated!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

    // Deletes an Appointment record in the database
    public static void deleteAppointment(int appointmentId) {

        try {
            // Sets the SQL query template with variables for deleting the Appointment Record
            String qDeleteAppt = "DELETE FROM appointment WHERE appointmentId = ?";

            // Sets the prepared statement
            PreparedStatement psDeleteAppt = DBConn.startConnection().prepareStatement(qDeleteAppt);

            // Assigns values to the SQL query variables
            psDeleteAppt.setInt(1, appointmentId);

            // Executes the prepared statement
            psDeleteAppt.execute();

            System.out.println("Database Query Successful!\nAppointment Deleted!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

}
