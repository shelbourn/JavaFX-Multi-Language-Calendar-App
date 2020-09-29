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
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    // MySQL DB Username
    private static final String username = "U06BSb";

    // MySQL DB Password
    private static final String password = "53688716173";

    // Method to start connection to MySQL DB
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection) DriverManager.getConnection(JDBCURL, username, password);
            System.out.print("Connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
