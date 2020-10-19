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

                LocalDate date = start.toLocalDateTime().toLocalDate();
                LocalTime convertedStart = start.toLocalDateTime().toLocalTime();
                LocalTime convertedEnd = end.toLocalDateTime().toLocalTime();

                Appointment a = new Appointment(appointmentId, customerId, userId, type, date, convertedStart, convertedEnd, userName, customerName);
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

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime ldt = convertToLDT(localDate, localTime);
        Timestamp dateTime = ldtToTimestamp(ldt);

        LocalDateTime ldtStart = convertToLDT(dateParam, startTime);
        LocalDateTime ldtEnd = convertToLDT(dateParam, endTime);
        Timestamp tsStart = ldtToTimestamp(ldtStart);
        Timestamp tsEnd = ldtToTimestamp(ldtEnd);

        try {

            // Sets the SQL query template with variables for creation of appointment record
            String qAddAppointment = "INSERT INTO appointment VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Sets the prepared statement
            PreparedStatement psAddAppointment = DBConn.startConnection().prepareStatement(qAddAppointment, Statement.RETURN_GENERATED_KEYS);

            // Assigns values to the SQL query variables
            psAddAppointment.setInt(1, customerId);
            psAddAppointment.setInt(2, userId);
            psAddAppointment.setString(3, "N/A");
            psAddAppointment.setString(4, "N/A");
            psAddAppointment.setString(5, "N/A");
            psAddAppointment.setString(6, "N/A");
            psAddAppointment.setString(7, type);
            psAddAppointment.setString(8, "N/A");
            psAddAppointment.setTimestamp(9, tsStart);
            psAddAppointment.setTimestamp(10, tsEnd);
            psAddAppointment.setTimestamp(11, dateTime);
            psAddAppointment.setString(12, "N/A");
            psAddAppointment.setTimestamp(13, dateTime);
            psAddAppointment.setString(14, "N/A");

            // Executes the prepared statement
            psAddAppointment.execute();

            System.out.println("Database Query Successful!\nNew Appointment Added!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

    // Updates an Appointment record in the database
    public static void updateAppointment(int appointmentId, int customerId, int userId, LocalDate dateParam, LocalTime startTime, LocalTime endTime, String type) {

        LocalDateTime ldtStart = convertToLDT(dateParam, startTime);
        LocalDateTime ldtEnd = convertToLDT(dateParam, endTime);
        Timestamp tsStart = ldtToTimestamp(ldtStart);
        Timestamp tsEnd = ldtToTimestamp(ldtEnd);

        try {
            // Sets the SQL query template with variables for updating the Appointment Record
            String qUpdateAppt = "UPDATE appointment set customerId = ?, userId = ?, start = ?, end = ?, type  = ? WHERE appointmentId = ?";

            // Sets the prepared statement
            PreparedStatement psUpdateAppt = DBConn.startConnection().prepareStatement(qUpdateAppt);

            // Assigns values to the SQL query variables
            psUpdateAppt.setInt(1, customerId);
            psUpdateAppt.setInt(2, userId);
            psUpdateAppt.setTimestamp(3, tsStart);
            psUpdateAppt.setTimestamp(4, tsEnd);
            psUpdateAppt.setString(5, type);
            psUpdateAppt.setInt(6, appointmentId);

            // Executes the prepared statement
            psUpdateAppt.execute();

            System.out.println("Database Query Successful!\nAppointment Updated!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
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
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

}
