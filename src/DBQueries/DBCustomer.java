/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.City;
import model.Customer;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBCustomer {

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> custList = FXCollections.observableArrayList();

        try {

            String q = "SELECT customerId, customerName, customer.addressId, address, phone, address.cityId, "
                    + "city, city.countryId, country FROM customer, address, city, country "
                    + "WHERE customer.addressId = address.addressId AND address.cityId = city.cityId "
                    + "AND city.countryId = country.countryId";

            PreparedStatement ps = DBConn.startConnection().prepareStatement(q);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                int addressId = rs.getInt("addressId");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int cityId = rs.getInt("cityId");
                String city = rs.getString("city");
                int countryId = rs.getInt("countryId");
                String country = rs.getString("country");

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

    public static void createCustomer() {

    }

    public static void updateCustomer(int customerId, String customerName, int addressId, String address, String phone, int cityId, String city, int countryId, String country) {

    }

    public static void deleteCustomer(int customerId, int addressId) {

    }

}
