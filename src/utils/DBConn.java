/*
 * Utility file to compose the JDBC connection URL to the MrSQL database
 */
package utils;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBConn {

    // JDBC URL Components
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U06BSb";

    // Concatenated JDBC URL
    private static final String jdbcURL = protocol + vendor + ipAddress;

}
