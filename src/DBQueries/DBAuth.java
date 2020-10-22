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
public class DBAuth {

    public DBAuth() {
    }

    // Retrieving User record from database if entered Esername exists and password is correct
    public static boolean validateUser(String userName, String password) {

        // Initializing Validated User to false
        boolean userValidated = false;

        try {

            // Sets the SQL query template with variables for authenticating the User
            String qValidateUser = "SELECT EXISTS(SELECT * from user WHERE userName = ? AND password = ?)";

            // Sets the prepared statement
            PreparedStatement psValidateUser = DBConn.startConnection().prepareStatement(qValidateUser);

            // Assigns values to the SQL query variables
            psValidateUser.setString(1, userName);
            psValidateUser.setString(2, password);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsValidateUser = psValidateUser.executeQuery();

            // Returning value of result set
            int validatedUser;
            rsValidateUser.next();
            validatedUser = rsValidateUser.getInt(1);

            if (validatedUser == 1) {
                System.out.println(
                        "Database Query Successful!\nUser Authentication Successful!\nWelcome to ABC Co. | CalApp!");
                userValidated = true;
            } else {
                System.err.println("User Authentication Failed.\nPlease try again.");
            }

        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return userValidated;
    }

}
