/*
 * Defines the DBAuth Class
 * Contains SQL operations related to user authentication for Login Screen
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBAuth {

    public DBAuth() {
    }

    // Retrieving User record from database if entered Esername exists and password is correct
    public static boolean validateUser(String userName, String password) {

        // Instantiating the Spanish Resource Bundle and initializing local translation variables
        ResourceBundle rb;
        String querySuccessful = "";
        String systemLoginFailed = "";
        String alertTitle = "";
        String alertHeader = "";
        String alertContext = "";
        String queryFailed = "";

        // Geting the default user locale and assigning it to Resource Bundle
        try {
            rb = ResourceBundle.getBundle("i18n/Lang", Locale.getDefault());

            // Checking if default user language is Spanish
            // If Spanish, then assign all System Out messages and Dialog text to values in "es" properties file
            if (Locale.getDefault().getLanguage().equals("es")) {
                querySuccessful = rb.getString("querySuccessful");
                systemLoginFailed = rb.getString("systemLoginFailed");
                alertTitle = rb.getString("alertTitle");
                alertHeader = rb.getString("alertHeader");
                alertContext = rb.getString("alertContext");
                queryFailed = rb.getString("queryFailed");
            }
        } catch (MissingResourceException e) {
            querySuccessful = "Database Query Successful!\nUser Authentication Successful!\nWelcome to ABC Co. | CalApp!";
            systemLoginFailed = "User Authentication Failed.\nPlease try again.";
            alertTitle = "LOGIN FAILED";
            alertHeader = "Invalid Username and/or Password";
            alertContext = "Please try re-entering your Username and Password.";
            queryFailed = "Database Query Failed!";
            System.err.println("English languauge Resource Bundle not found, nor needed. You may ignore this error.");
        }

        // Initializing Validated User to false
        boolean userValidated = false;

        try {

            // Sets the SQL query template with variables for authenticating the User
            String qValidateUser = "SELECT * from user WHERE userName = ? AND password = ?";

            // Sets the prepared statement
            PreparedStatement psValidateUser = DBConn.startConnection().prepareStatement(qValidateUser);

            // Assigns values to the SQL query variables
            psValidateUser.setString(1, userName);
            psValidateUser.setString(2, password);

            // Executes the prepared statement and assigns results to a Result Set
            ResultSet rsValidateUser = psValidateUser.executeQuery();

            // If Result Set has entries, set userValidated to true
            // If Result Set does not have entries catch the SQL Exception
            try {
                rsValidateUser.next();
                int validatedUser = rsValidateUser.getInt(1);
                System.out.println(
                        querySuccessful);
                userValidated = true;

            } catch (SQLException e) {
                System.err.println(systemLoginFailed);

                // Throws error alert if incorrect Username and/or Password entered
                Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
                invalidCredentials.setTitle(alertTitle);
                invalidCredentials.setHeaderText(alertHeader);
                invalidCredentials.setContentText(alertContext);
                invalidCredentials.showAndWait();
            }

        } catch (SQLException e) {
            System.err.println(queryFailed);
            e.printStackTrace();
        }

        return userValidated;
    }

}
