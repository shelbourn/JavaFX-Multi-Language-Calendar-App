/*
 * Utility file to compose the JDBC connection URL to the MrSQL database
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBConn {

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
        try {
            Class.forName(MYSQLJDBCDRIVER);
            conn = (Connection) DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
            System.out.print("MySQL database connection successful!\n");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Method to close the MySQL DB Connection
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("MySQL database connection closed!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
