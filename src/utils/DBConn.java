/*
 * Utility file to compose the JDBC connection URL to the MrSQL database
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBConn {

    // Geting the default user locale and assigning it to Resource Bundle
    private static ResourceBundle rb;
    private static String sqlConnectionSuccessful = "";
    private static String sqlConnectionClosed = "";

    private static void rb() {

        try {
            rb = ResourceBundle.getBundle("i18n/Lang", Locale.getDefault());

            // Checking if default user language is Spanish
            // If Spanish, then assign all screen properties to values in "es" properties file
            if (Locale.getDefault().getLanguage().equals("es")) {
                sqlConnectionSuccessful = rb.getString("sqlConnectionSuccessful");
                sqlConnectionClosed = rb.getString("sqlConnectionClosed");
            }

        } catch (MissingResourceException e) {
            sqlConnectionSuccessful = "MySQL database connection successful!";
            sqlConnectionClosed = "MySQL database connection closed!";
            System.err.println("English languauge Resource Bundle not found, nor needed. You may ignore this error.");
        }
    }

    // JDBC URL Components
    private static final String PROTOCOL = "jdbc";
    private static final String VENDOR = ":mysql:";
    private static final String IPADDRESS = "//3.227.166.251/U06BSb";

    // Concatenated JDBC URL
    private static final String JDBCURL = PROTOCOL + VENDOR + IPADDRESS;

    // Reference to MySQL JDBC Driver Interface
    private static final String MYSQLJDBCDRIVER = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    // MySQL DB Username
    private static final String USERNAME = "U06BSb";

    // MySQL DB Password
    private static final String PASSWORD = "53688716173";

    // Method to start connection to MySQL DB
    public static Connection startConnection() {
        rb();

        try {
            Class.forName(MYSQLJDBCDRIVER);
            conn = (Connection) DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
            System.out.println(sqlConnectionSuccessful);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    // Method to close the MySQL DB Connection
    public static void closeConnection() {
        rb();

        try {
            conn.close();
            System.out.println(sqlConnectionClosed);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
