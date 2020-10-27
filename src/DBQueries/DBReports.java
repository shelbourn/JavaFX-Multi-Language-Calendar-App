/*
 * Defines the DBReports Class
 * Contains SQL operations related to Reports Screen
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
import model.AppointmentType;
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
            User all = new User(999, "All Consultants", "1234");
            userList.add(all);

            while (rsGetUsers.next()) {
                int userId = rsGetUsers.getInt("userId");
                String userName = rsGetUsers.getString("userName");
                String password = rsGetUsers.getString("password");

                // Creating User Object with data
                User u = new User(userId, userName, password);
                userList.add(u);
            }
            System.out.println("Database Query Successful!\nUser list retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
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
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return userAppointmentsList;
    }

    // Retrieves Appointment summary data for specified User (or all users) from database
    // Retrieves number of appointments by user for current month
    public static int getNumUserAppointments(int consultantId) {

        int numAppointments = 0;

        try {

            String qGetNumUserAppointments = "SELECT COUNT(*) FROM appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date()) AND userId = ?";

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
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numAppointments;
    }

    // Retrieves number of appointments for all users for current month
    public static int getNumAppointments() {

        int numAppointments = 0;

        try {

            String qGetNumUserAppointments = "SELECT COUNT(*) FROM appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date())";

            PreparedStatement psGetNumUserAppointments = DBConn.startConnection().prepareStatement(qGetNumUserAppointments);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetNumUserAppointments = psGetNumUserAppointments.executeQuery();

            // Returning value of result set
            rsGetNumUserAppointments.next();
            numAppointments = rsGetNumUserAppointments.getInt(1);

            System.out.println("Database Query Successful!\nNumber of appointments for all Consultants retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numAppointments;
    }

    // Retrieves total Appointment time per user for current month
    public static int[] getTotalAppointmentTime(int consultantId) {

        int[] splitTime = new int[3];

        try {

            String qGetTotalAppointmentTime = "SELECT SUM(timestampdiff(SECOND,start,end)) from appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date()) AND userId = ?";

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

            System.out.println("Database Query Successful!\nTotal appointment time by Consultant retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return splitTime;
    }

    // Retrieves total Appointment time for all users for current month
    public static int[] getTotalAppointmentTimeAll() {

        int[] splitTime = new int[3];

        try {

            String qGetTotalAppointmentTime = "SELECT SUM(timestampdiff(SECOND,start,end)) from appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date())";

            PreparedStatement psGetTotalAppointmentTime = DBConn.startConnection().prepareStatement(qGetTotalAppointmentTime);

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

            System.out.println("Database Query Successful!\nTotal appointment time for all Consultants retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return splitTime;
    }

    // Retrieves total number of distinct Customers the user has meeting with for current month
    public static int getDistinctCustomers(int consultantId) {

        int numberOfCustomers = 0;

        try {

            String qGetDistinctCustomers = "SELECT COUNT(DISTINCT customerId) from appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date()) AND userId = ?";

            PreparedStatement psGetDistinctCustomers = DBConn.startConnection().prepareStatement(qGetDistinctCustomers);

            // Assigns values to the SQL query variables
            psGetDistinctCustomers.setInt(1, consultantId);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetDistinctCustomers = psGetDistinctCustomers.executeQuery();

            // Returning value of result set
            rsGetDistinctCustomers.next();
            int numCustomers = rsGetDistinctCustomers.getInt(1);
            numberOfCustomers = numCustomers;

            System.out.println("Database Query Successful!\nTotal number of distinct Customers by Consultant retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numberOfCustomers;
    }

    // Retrieves total number of distinct Customers all users have meetings with for current month
    public static int getDistinctCustomersAll() {

        int numberOfCustomers = 0;

        try {

            String qGetDistinctCustomers = "SELECT COUNT(DISTINCT customerId) from appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date())";

            PreparedStatement psGetDistinctCustomers = DBConn.startConnection().prepareStatement(qGetDistinctCustomers);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetDistinctCustomers = psGetDistinctCustomers.executeQuery();

            // Returning value of result set
            rsGetDistinctCustomers.next();
            int numCustomers = rsGetDistinctCustomers.getInt(1);
            numberOfCustomers = numCustomers;

            System.out.println("Database Query Successful!\nTotal number of distinct Customers for all Consultants retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numberOfCustomers;
    }

    // Retrieves total number of distinct Appointment Types the user has for current month
    public static int getDistinctTypes(int consultantId) {

        int distinctAppointmentTypes = 0;

        try {

            String qGetDistinctTypes = "SELECT COUNT(DISTINCT type) from appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date()) AND userId = ?";

            PreparedStatement psGetDistinctTypes = DBConn.startConnection().prepareStatement(qGetDistinctTypes);

            // Assigns values to the SQL query variables
            psGetDistinctTypes.setInt(1, consultantId);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetDistinctTypes = psGetDistinctTypes.executeQuery();

            // Returning value of result set
            rsGetDistinctTypes.next();
            int distinctTypes = rsGetDistinctTypes.getInt(1);
            distinctAppointmentTypes = distinctTypes;

            System.out.println("Database Query Successful!\nTotal number of distinct Appointment Types by Consultant retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return distinctAppointmentTypes;
    }

    // Retrieves total number of distinct Appointment Types all users have for current month
    public static int getDistinctTypesAll() {

        int distinctAppointmentTypes = 0;

        try {

            String qGetDistinctTypes = "SELECT COUNT(DISTINCT type) from appointment WHERE month(start) = month(current_date()) AND year(start) = year(current_date())";

            PreparedStatement psGetDistinctTypes = DBConn.startConnection().prepareStatement(qGetDistinctTypes);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsGetDistinctTypes = psGetDistinctTypes.executeQuery();

            // Returning value of result set
            rsGetDistinctTypes.next();
            int distinctTypes = rsGetDistinctTypes.getInt(1);
            distinctAppointmentTypes = distinctTypes;

            System.out.println("Database Query Successful!\nTotal number of distinct Appointment Types for all Consultants retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return distinctAppointmentTypes;
    }

    // Retrieves total number of meetings for each distinct Appointment Type per user for the current month
    public static ObservableList<AppointmentType> getNumAppointmentTypes(int consultantId) {
        ObservableList<AppointmentType> numAppointmentTypesList = FXCollections.observableArrayList();

        try {

            String qGetNumAppointmentTypes = "SELECT type, count(type) FROM appointment WHERE userId = ? GROUP BY type";

            PreparedStatement psNumAppointmentTypes = DBConn.startConnection().prepareStatement(qGetNumAppointmentTypes);

            // Assigns values to the SQL query variables
            psNumAppointmentTypes.setInt(1, consultantId);

            ResultSet rsNumAppointmentTypes = psNumAppointmentTypes.executeQuery();

            // Building out the AppointmentType Objects
            while (rsNumAppointmentTypes.next()) {
                String type = rsNumAppointmentTypes.getString(1);
                int typeCount = rsNumAppointmentTypes.getInt(2);

                // Create AppointmentType Object with data
                AppointmentType aT = new AppointmentType(type, typeCount);
                numAppointmentTypesList.add(aT);
            }
            System.out.println("Database Query Successful!\nNumber of appointments by Appointment Type for consultant retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numAppointmentTypesList;
    }

    // Retrieves total number of meetings for each distinct Appointment Type for all users for the current month
    public static ObservableList<AppointmentType> getNumAppointmentTypesAll() {
        ObservableList<AppointmentType> numAppointmentTypesList = FXCollections.observableArrayList();

        try {

            String qGetNumAppointmentTypes = "SELECT type, count(type) FROM appointment GROUP BY type";

            PreparedStatement psNumAppointmentTypes = DBConn.startConnection().prepareStatement(qGetNumAppointmentTypes);

            ResultSet rsNumAppointmentTypes = psNumAppointmentTypes.executeQuery();

            // Building out the AppointmentType Objects
            while (rsNumAppointmentTypes.next()) {
                String type = rsNumAppointmentTypes.getString(1);
                int typeCount = rsNumAppointmentTypes.getInt(2);

                // Create AppointmentType Object with data
                AppointmentType aT = new AppointmentType(type, typeCount);
                numAppointmentTypesList.add(aT);
            }
            System.out.println("Database Query Successful!\nNumber of appointments by Appointment Type for all consultants retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return numAppointmentTypesList;
    }

}
