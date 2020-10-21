/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;
import utils.DBConn;
import utils.TimeConverters;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBReports {

    public DBReports() {
    }

    // Retrieves all user records from the database and assigns them to an Observable Array
    public static ObservableList<User> getAllUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {

            String qGetUsers = "SELECT userId, userName, password from user";

            PreparedStatement psGetUsers = DBConn.startConnection().prepareStatement(qGetUsers);

            ResultSet rsGetUsers = psGetUsers.executeQuery();

            // Building out the User Objects
            while (rsGetUsers.next()) {
                int userId = rsGetUsers.getInt("userId");
                String userName = rsGetUsers.getString("userName");
                String password = rsGetUsers.getString("password");

                // Creating User Object with data
                User u = new User(userId, userName, password);
                userList.add(u);
            }
            User all = new User(999, "All Consultants", "1234");
            userList.add(all);
            System.out.println("Database Query Successful!\nUser list retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return userList;
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

    // Retrieves Appointment summary data for specified User (or all users) from database
    // Retrieves number of appointments by user
    public static int getNumUserAppointments(int consultantId) {

        int numAppointments = 0;

        try {

            String qGetNumUserAppointments = "SELECT COUNT(*) FROM appointment where userId = ?";

            PreparedStatement psGetNumUserAppointments = DBConn.startConnection().prepareStatement(qGetNumUserAppointments);

            // Assigns values to the SQL query variables
            psGetNumUserAppointments.setInt(1, consultantId);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetNumUserAppointments = psGetNumUserAppointments.executeQuery();

            // Returning value of result set
            rsGetNumUserAppointments.next();
            numAppointments = rsGetNumUserAppointments.getInt(1);

            System.out.println("Database Query Successful!\nNumber of appointments by Consultant retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numAppointments;
    }

    // Retrieves number of appointments for all users
    public static int getNumAppointments() {

        int numAppointments = 0;

        try {

            String qGetNumUserAppointments = "SELECT COUNT(*) FROM appointment";

            PreparedStatement psGetNumUserAppointments = DBConn.startConnection().prepareStatement(qGetNumUserAppointments);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetNumUserAppointments = psGetNumUserAppointments.executeQuery();

            // Returning value of result set
            rsGetNumUserAppointments.next();
            numAppointments = rsGetNumUserAppointments.getInt(1);

            System.out.println("Database Query Successful!\nNumber of appointments for all Consultants retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numAppointments;
    }

    // Retrieves total Appointment time per user
    public static int[] getTotalAppointmentTime(int consultantId) {

        int[] splitTime = new int[3];

        try {

            String qGetTotalAppointmentTime = "SELECT SUM(timestampdiff(SECOND,start,end)) from appointment where userId = ?";

            PreparedStatement psGetTotalAppointmentTime = DBConn.startConnection().prepareStatement(qGetTotalAppointmentTime);

            // Assigns values to the SQL query variables
            psGetTotalAppointmentTime.setInt(1, consultantId);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetTotalAppointmentTime = psGetTotalAppointmentTime.executeQuery();

            // Returning value of result set
            rsGetTotalAppointmentTime.next();
            int seconds = rsGetTotalAppointmentTime.getInt(1);

            // Splits time into hours, minutes, seconds and puts them in array
            int t1 = seconds % 60;
            int t2 = seconds / 60;
            int t3 = t2 % 60;
            t2 = t2 / 60;

            splitTime[0] = t2;
            splitTime[1] = t3;
            splitTime[2] = t1;

            System.out.println("Database Query Successful!\nTotal appointment time by Consultant retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return splitTime;
    }

}
