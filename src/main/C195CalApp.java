/*
 * Main Application Class
 */
package main;

import controller.loginScreenController;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConn;
import utils.HelperMethods;

/**
 *
 * @author mshelbourn
 */
public class C195CalApp extends Application {

    // Additional properties required for functionality
    private static String logsFilename;
    private static String logEntry;
    private static FileWriter logsWriter;
    private static PrintWriter logsOutputFile;
    private static ZonedDateTime userZDT;
    private static String loggedInUser;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Starting MySQL Connection
        DBConn.startConnection();

        launch(args);

        // Initializing Path and Name for Authentication Log File
        logsFilename = "src/AuthLogs/calAppAuthLogs.txt";

        //Initializing the FileWriter for writing Authentication Log output
        try {
            logsWriter = new FileWriter(logsFilename, true);

        } catch (IOException ex) {
            Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Initializing the PrintWriter for writing Authentication Log output
        logsOutputFile = new PrintWriter(logsWriter);

        // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
        userZDT = HelperMethods.currentUserZDT();
        loggedInUser = loginScreenController.getLoggedInUser();
        logEntry = userZDT.toString() + ": User [" + loggedInUser + "] successfully logged out!";

        if (loggedInUser != null) {
            // Printing logout event to Authentication Logs on app exit
            System.out.println("Printing authentication event to Authentication Log File.");

            logsOutputFile.println(logEntry);

            // Closing the authentication log file after output written
            logsOutputFile.close();
            System.out.println("Entry to Authentication Log successfully written!");
        }

        // Closing MySQL Connection
        DBConn.closeConnection();
    }

}
