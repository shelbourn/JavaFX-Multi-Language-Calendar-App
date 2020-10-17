/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBCustomer {

    public DBCustomer() {
    }

    // Retrieves all customer records from the database and assigns them to an Observable Array
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> custList = FXCollections.observableArrayList();

        try {

            String qGet = "SELECT customerId, customerName, customer.addressId, address, phone, address.cityId, "
                    + "city, city.countryId, country FROM customer, address, city, country "
                    + "WHERE customer.addressId = address.addressId AND address.cityId = city.cityId "
                    + "AND city.countryId = country.countryId";

            PreparedStatement psGet = DBConn.startConnection().prepareStatement(qGet);

            ResultSet rsGet = psGet.executeQuery();

            while (rsGet.next()) {
                int customerId = rsGet.getInt("customerId");
                String customerName = rsGet.getString("customerName");
                int addressId = rsGet.getInt("addressId");
                String address = rsGet.getString("address");
                String phone = rsGet.getString("phone");
                int cityId = rsGet.getInt("cityId");
                String city = rsGet.getString("city");
                int countryId = rsGet.getInt("countryId");
                String country = rsGet.getString("country");

                Customer c = new Customer(customerId, customerName, addressId, address, phone, cityId, city, countryId, country);
                custList.add(c);
            }
            System.out.println("Database Query Successful!\nCustomer list retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return custList;
    }

    // Creates a new Customer records in the database
    public static void createCustomer(String customerName, String address, String phone, int cityId) {

        try {
            Date date = new Date();
            Object dateTime = new Timestamp(date.getTime());

            // Setting the SQL query template with variables for address component of customer record
            String qAdd = "INSERT INTO address VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Setting the prepared statement
            PreparedStatement psAdd = DBConn.startConnection().prepareStatement(qAdd, Statement.RETURN_GENERATED_KEYS);

            // Assigning values to the SQL query variables
            psAdd.setString(1, address);
            psAdd.setString(2, "N/A");
            psAdd.setInt(3, cityId);
            psAdd.setString(4, "N/A");
            psAdd.setString(5, phone);
            psAdd.setObject(6, dateTime);
            psAdd.setString(7, "N/A");
            psAdd.setObject(8, dateTime);
            psAdd.setString(9, "N/A");

            // Executing the prepared statement
            psAdd.execute();

            // Generating the addressId using the index of the Generated Keys
            ResultSet rsAdd = psAdd.getGeneratedKeys();
            rsAdd.next();
            int addressId = rsAdd.getInt(1);

            // Setting the SQL squery template with variables for creating the customer record
            String qCust = "INSERT INTO customer VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";

            // Setting the prepared statement
            PreparedStatement psCust = DBConn.startConnection().prepareStatement(qCust);

            // Assigning values to the SQL query variables
            psCust.setString(1, customerName);
            psCust.setInt(2, addressId);
            psCust.setInt(3, 1);
            psCust.setObject(4, dateTime);
            psCust.setString(5, "N/A");
            psCust.setObject(6, dateTime);
            psCust.setString(7, "N.A");

            // Executing the prepared statement
            psCust.execute();

            System.out.println("Database Query Successful!\nNew Customer Added!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

    public static void updateCustomer(int customerId, String customerName, int addressId, String address, String phone, int cityId) {

        try {
            // Setting the SQL query template with variables for updating the Customer Name component of customer record
            String qUpdateCust = "UPDATE customer set customerName = ? WHERE customerId = ?";

            // Setting the prepared statement
            PreparedStatement psUpdateCust = DBConn.startConnection().prepareStatement(qUpdateCust);

            // Assigning values to the SQL query variables
            psUpdateCust.setString(1, customerName);
            psUpdateCust.setInt(2, customerId);

            // Executing the prepared statement
            psUpdateCust.execute();

            // Setting the SQL squery template with variables for updating the Address component of customer record
            String qUpdateAdd = "UPDATE address set address = ?, cityId = ?, phone = ? WHERE addressId = ?";

            // Setting the prepared statement
            PreparedStatement psUpdateAdd = DBConn.startConnection().prepareStatement(qUpdateAdd);

            // Assigning values to the SQL query variables
            psUpdateAdd.setString(1, address);
            psUpdateAdd.setInt(2, cityId);
            psUpdateAdd.setString(3, phone);
            psUpdateAdd.setInt(4, addressId);

            // Executing the prepared statement
            psUpdateAdd.execute();

            System.out.println("Database Query Successful!\nCustomer Updated!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

    public static void deleteCustomer(int customerId, int addressId) {

        try {
            // Setting the SQL query template with variables for deleting the Customer Name component of customer record
            String qDeleteCust = "DELETE from customer WHERE customerId = ?";

            // Setting the prepared statement
            PreparedStatement psDeleteCust = DBConn.startConnection().prepareStatement(qDeleteCust);

            // Assigning values to the SQL query variables
            psDeleteCust.setInt(1, customerId);

            // Executing the prepared statement
            psDeleteCust.execute();

            // Setting the SQL squery template with variables for deleting the Address component of customer record
            String qDeleteAdd = "DELETE from address WHERE addressId = ?";

            // Setting the prepared statement
            PreparedStatement psDeleteAdd = DBConn.startConnection().prepareStatement(qDeleteAdd);

            // Assigning values to the SQL query variables
            psDeleteAdd.setInt(1, addressId);

            // Executing the prepared statement
            psDeleteAdd.execute();

            System.out.println("Database Query Successful!\nCustomer Deleted!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

    }

}
