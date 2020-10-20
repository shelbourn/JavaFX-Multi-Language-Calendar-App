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

            // Building out the User Objects
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
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return userList;
    }
}
