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
import java.time.LocalTime;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBUser {

    public DBUser() {
    }

    // Retrieves all user records from the database and assigns them to an Observable Array
    public static ObservableList<User> getAllUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {

            String qGetUsers = "SELECT userId, userName, password from user";

            PreparedStatement psGetUsers = DBConn.startConnection().prepareStatement(qGetUsers);

            ResultSet rsGetUsers = psGetUsers.executeQuery();

            while (rsGetUsers.next()) {
                int userId = rsGetUsers.getInt("userId");
                String userName = rsGetUsers.getString("userName");
                String password = rsGetUsers.getString("password");

                User u = new User(userId, userName, password);
                userList.add(u);
            }
            System.out.println("Database Query Successful!\nUser list retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return userList;
    }

//    // Creates a new Customer records in the database
//    public static void createCustomer(String customerName, String address, String phone, int cityId) {
//
//        try {
//            Date date = new Date();
//            Object dateTime = new Timestamp(date.getTime());
//
//            LocalDate localDate = LocalDate.now();
//            LocalTime localTime = LocalTime.now();
//            String localDateTime = localDate + " " + localTime;
//
//            // Setting the SQL query template with variables for address component of customer record
//            String qAdd = "INSERT INTO address VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            // Setting the prepared statement
//            PreparedStatement psAdd = DBConn.startConnection().prepareStatement(qAdd, Statement.RETURN_GENERATED_KEYS);
//
//            // Assigning values to the SQL query variables
//            psAdd.setString(1, address);
//            psAdd.setString(2, "N/A");
//            psAdd.setInt(3, cityId);
//            psAdd.setString(4, "N/A");
//            psAdd.setString(5, phone);
//            psAdd.setObject(6, localDateTime);
//            psAdd.setString(7, "N/A");
//            psAdd.setObject(8, localDateTime);
//            psAdd.setString(9, "N/A");
//
//            // Executing the prepared statement
//            psAdd.execute();
//
//            // Generating the addressId using the index of the Generated Keys
//            ResultSet rsAdd = psAdd.getGeneratedKeys();
//            rsAdd.next();
//            int addressId = rsAdd.getInt(1);
//
//            // Setting the SQL squery template with variables for creating the customer record
//            String qCust = "INSERT INTO customer VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
//
//            // Setting the prepared statement
//            PreparedStatement psCust = DBConn.startConnection().prepareStatement(qCust);
//
//            // Assigning values to the SQL query variables
//            psCust.setString(1, customerName);
//            psCust.setInt(2, addressId);
//            psCust.setInt(3, 1);
//            psCust.setObject(4, localDateTime);
//            psCust.setString(5, "N/A");
//            psCust.setObject(6, localDateTime);
//            psCust.setString(7, "N.A");
//
//            // Executing the prepared statement
//            psCust.execute();
//
//            System.out.println("Database Query Successful!\nNew Customer Added!");
//        } catch (SQLException e) {
//            System.out.println("Database Query Failed!");
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void updateCustomer(int customerId, String customerName, int addressId, String address, String phone, int cityId) {
//
//        try {
//            // Setting the SQL query template with variables for updating the Customer Name component of customer record
//            String qUpdateCust = "UPDATE customer set customerName = ? WHERE customerId = ?";
//
//            // Setting the prepared statement
//            PreparedStatement psUpdateCust = DBConn.startConnection().prepareStatement(qUpdateCust);
//
//            // Assigning values to the SQL query variables
//            psUpdateCust.setString(1, customerName);
//            psUpdateCust.setInt(2, customerId);
//
//            // Executing the prepared statement
//            psUpdateCust.execute();
//
//            // Setting the SQL squery template with variables for updating the Address component of customer record
//            String qUpdateAdd = "UPDATE address set address = ?, cityId = ?, phone = ? WHERE addressId = ?";
//
//            // Setting the prepared statement
//            PreparedStatement psUpdateAdd = DBConn.startConnection().prepareStatement(qUpdateAdd);
//
//            // Assigning values to the SQL query variables
//            psUpdateAdd.setString(1, address);
//            psUpdateAdd.setInt(2, cityId);
//            psUpdateAdd.setString(3, phone);
//            psUpdateAdd.setInt(4, addressId);
//
//            // Executing the prepared statement
//            psUpdateAdd.execute();
//
//            System.out.println("Database Query Successful!\nCustomer Updated!");
//        } catch (SQLException e) {
//            System.out.println("Database Query Failed!");
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void deleteCustomer(int customerId, int addressId) {
//
//        try {
//            // Setting the SQL query template with variables for deleting the Customer Name component of customer record
//            String qDeleteCust = "DELETE from customer WHERE customerId = ?";
//
//            // Setting the prepared statement
//            PreparedStatement psDeleteCust = DBConn.startConnection().prepareStatement(qDeleteCust);
//
//            // Assigning values to the SQL query variables
//            psDeleteCust.setInt(1, customerId);
//
//            // Executing the prepared statement
//            psDeleteCust.execute();
//
//            // Setting the SQL squery template with variables for deleting the Address component of customer record
//            String qDeleteAdd = "DELETE from address WHERE addressId = ?";
//
//            // Setting the prepared statement
//            PreparedStatement psDeleteAdd = DBConn.startConnection().prepareStatement(qDeleteAdd);
//
//            // Assigning values to the SQL query variables
//            psDeleteAdd.setInt(1, addressId);
//
//            // Executing the prepared statement
//            psDeleteAdd.execute();
//
//            System.out.println("Database Query Successful!\nCustomer Deleted!");
//        } catch (SQLException e) {
//            System.out.println("Database Query Failed!");
//            e.printStackTrace();
//        }
//
//    }
}
