/*
 * Controller for the Login Screen
 */
package controller;

import DBQueries.DBAuth;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DBConn;
import utils.HelperMethods;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class loginScreenController implements Initializable {

    @FXML
    private Label appSubHeaderLabel;
    @FXML
    private Button exitBtn;
    @FXML
    private Label loginPanelLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginBtn;

    // Additional properties required for functionality
    private String userName;
    private String password;
    private String closingSQLConnection;
    private String exitingApplication;
    private String confirmExitTitle;
    private String confirmExitHeader;
    private String confirmExitContext;
    private String requiredFieldsTitle;
    private String requiredFieldsHeader;
    private String requiredFieldsContext;
    private String openingCalApp;
    private String printingToAuthLogs;
    private String logEntryWritten;
    private String logsFilename;
    private String logEntry;
    private FileWriter logsWriter;
    private PrintWriter logsOutputFile;
    private ZonedDateTime userZDT;
    private static String loggedInUser;
    @FXML
    private Label showLabel;
    @FXML
    private Label hideLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        // Geting the default user locale and assigning it to Resource Bundle
        try {
            rb = ResourceBundle.getBundle("i18n/Lang", Locale.getDefault());

            // Checking if default user language is Spanish
            // If Spanish, then assign all screen properties to values in "es" properties file
            if (Locale.getDefault().getLanguage().equals("es")) {
                appSubHeaderLabel.setText(rb.getString("appSubHeaderLabel"));
                loginPanelLabel.setText(rb.getString("loginPanelLabel"));
                usernameLabel.setText(rb.getString("usernameLabel"));
                usernameField.setPromptText(rb.getString("usernameField"));
                passwordLabel.setText(rb.getString("passwordLabel"));
                passwordField.setPromptText(rb.getString("passwordField"));
                loginBtn.setText(rb.getString("loginBtn"));
                exitBtn.setText(rb.getString("exitBtn"));
                closingSQLConnection = rb.getString("closingSQLConnection");
                exitingApplication = rb.getString("exitingApplication");
                confirmExitTitle = rb.getString("confirmExitTitle");
                confirmExitHeader = rb.getString("confirmExitHeader");
                confirmExitContext = rb.getString("confirmExitContext");
                requiredFieldsTitle = rb.getString("requiredFieldsTitle");
                requiredFieldsHeader = rb.getString("requiredFieldsHeader");
                requiredFieldsContext = rb.getString("requiredFieldsContext");
                openingCalApp = rb.getString("openingCalApp");
                printingToAuthLogs = rb.getString("printingToAuthLogs");
                logEntryWritten = rb.getString("logEntryWritten");
            }

        } catch (MissingResourceException e) {
            closingSQLConnection = "Closing MySQL connection.";
            exitingApplication = "Exiting application.\nEND.";
            confirmExitTitle = "EXIT APPLICATION CONFIRMATION";
            confirmExitHeader = "Are you sure you would like to exit CalApp?";
            confirmExitContext = "Click OK to exit or CANCEL to return to the application.";
            requiredFieldsTitle = "USERNAME & PASSWORD REQUIRED";
            requiredFieldsHeader = "Username and Password are required fields";
            requiredFieldsContext = "Please enter your Username and Password.";
            openingCalApp = "Username and Password accepted!\nOpening LANDING screen.";
            printingToAuthLogs = "Printing authentication event to Authentication Log File.";
            logEntryWritten = "Entry to Authentication Log successfully written!";
            System.err.println("English languauge Resource Bundle not found, nor needed. You may ignore this error.");
        }
    }

    // Getter for loggedInUser
    public static String getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    private void exitBtnHandler(ActionEvent event) throws IOException {
        // Exit Confirmation Dialog
        Alert confirmExit = new Alert(Alert.AlertType.CONFIRMATION);
        confirmExit.setTitle(confirmExitTitle);
        confirmExit.setHeaderText(confirmExitHeader);
        confirmExit.setContentText(confirmExitContext);
        confirmExit.showAndWait();

        // Close app on exit confirmation
        if (confirmExit.getResult() == ButtonType.OK) {
            System.out.println(closingSQLConnection);
            DBConn.closeConnection();
            System.out.println(exitingApplication);
            System.exit(0);

        } else {
            confirmExit.close();
        }
    }

    // Clears Username Field
    @FXML
    private void clearUserFieldHandler(MouseEvent event) {
        usernameField.clear();
    }

    // Clears Password and Password Text Fields
    @FXML
    private void clearPasswordFieldHandler(MouseEvent event) {
        passwordField.clear();
        passwordTextField.clear();
    }

    // Binds the passwordField to the passwordTextField
    @FXML
    private void passwordFieldHandler(KeyEvent event) {
        passwordTextField.setText(passwordField.getText());
    }

    // Binds the passwordTextField to the passwordField
    @FXML
    private void passwordTextFieldHandler(KeyEvent event) {
        passwordField.setText(passwordTextField.getText());
    }

    // Unmasks user's typed password
    @FXML
    private void showHandler(MouseEvent event) {
        passwordField.setVisible(false);
        passwordTextField.setVisible(true);
        showLabel.setVisible(false);
        hideLabel.setVisible(true);
    }

    // Masks user's typed password
    @FXML
    private void hideHandler(MouseEvent event) {
        passwordField.setVisible(true);
        passwordTextField.setVisible(false);
        showLabel.setVisible(true);
        hideLabel.setVisible(false);
    }

// Validates user credentials, throws alerts, opens app if authentication is successful
    @FXML
    private void loginBtnHandler(ActionEvent event) throws IOException {
        userName = usernameField.getText();
        password = passwordField.getText();

        // Thows alert if Username and/or Password fields are empty
        if (userName.equals("") || password.equals("")) {
            Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
            requiredFields.setTitle(requiredFieldsTitle);
            requiredFields.setHeaderText(requiredFieldsHeader);
            requiredFields.setContentText(requiredFieldsContext);
            requiredFields.showAndWait();
            return;
        }

        // Opens application on successful authentication
        if (DBAuth.validateUser(userName, password) == true) {
            System.out.println(printingToAuthLogs);

            // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
            userZDT = HelperMethods.currentUserZDT();
            logEntry = userZDT.toString() + ": User [" + userName + "] successfully logged in!";
            logsOutputFile.println(logEntry);

            // Closing the authentication log file after output written
            logsOutputFile.close();
            System.out.println(logEntryWritten);

            // Opening the Main App window
            loggedInUser = userName;
            System.out.println(openingCalApp);
            Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
            Scene landingScreen = new Scene(root);
            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginWindow.setTitle("CalApp | Main Screen");
            loginWindow.setScene(landingScreen);
            loginWindow.show();

        } else {
            System.out.println(printingToAuthLogs);

            // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
            userZDT = HelperMethods.currentUserZDT();
            logEntry = userZDT.toString() + ": User [" + userName + "] had a failed login attempt!";
            logsOutputFile.println(logEntry);

            // Closing the authentication log file after output written
            logsOutputFile.close();
            System.out.println(logEntryWritten);
        }

    }

    // Handling form submission when Enter key is pressed in Username field
    @FXML
    private void usernameEnterHandler(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            userName = usernameField.getText();
            password = passwordField.getText();

            // Thows alert if Username and/or Password fields are empty
            if (userName.equals("") || password.equals("")) {
                Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
                requiredFields.setTitle(requiredFieldsTitle);
                requiredFields.setHeaderText(requiredFieldsHeader);
                requiredFields.setContentText(requiredFieldsContext);
                requiredFields.showAndWait();
                return;
            }

            // Opens application on successful authentication
            if (DBAuth.validateUser(userName, password) == true) {
                System.out.println(printingToAuthLogs);

                // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
                userZDT = HelperMethods.currentUserZDT();
                logEntry = userZDT.toString() + ": User [" + userName + "] successfully logged in!";
                logsOutputFile.println(logEntry);

                // Closing the authentication log file after output written
                logsOutputFile.close();
                System.out.println(logEntryWritten);

                // Opening the Main App window
                System.out.println(openingCalApp);
                Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
                Scene landingScreen = new Scene(root);
                Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginWindow.setTitle("CalApp | Main Screen");
                loginWindow.setScene(landingScreen);
                loginWindow.show();

            } else {
                System.out.println(printingToAuthLogs);

                // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
                userZDT = HelperMethods.currentUserZDT();
                logEntry = userZDT.toString() + ": User [" + userName + "] had a failed login attempt!";
                logsOutputFile.println(logEntry);

                // Closing the authentication log file after output written
                logsOutputFile.close();
                System.out.println(logEntryWritten);
            }
        }
    }

    // Handling form submission when Enter key is pressed in Password field
    @FXML
    private void passwordEnterHandler(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            userName = usernameField.getText();
            password = passwordField.getText();

            // Thows alert if Username and/or Password fields are empty
            if (userName.equals("") || password.equals("")) {
                Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
                requiredFields.setTitle(requiredFieldsTitle);
                requiredFields.setHeaderText(requiredFieldsHeader);
                requiredFields.setContentText(requiredFieldsContext);
                requiredFields.showAndWait();
                return;
            }

            // Opens application on successful authentication
            if (DBAuth.validateUser(userName, password) == true) {
                System.out.println(printingToAuthLogs);

                // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
                userZDT = HelperMethods.currentUserZDT();
                logEntry = userZDT.toString() + ": User [" + userName + "] successfully logged in!";
                logsOutputFile.println(logEntry);

                // Closing the authentication log file after output written
                logsOutputFile.close();
                System.out.println(logEntryWritten);

                // Opening the Main App window
                System.out.println(openingCalApp);
                Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
                Scene landingScreen = new Scene(root);
                Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginWindow.setTitle("CalApp | Main Screen");
                loginWindow.setScene(landingScreen);
                loginWindow.show();

            } else {
                System.out.println(printingToAuthLogs);

                // Printing authentication event to authentication log file (Package: AuthLogs, File: calAppAuthLogs.txt)
                userZDT = HelperMethods.currentUserZDT();
                logEntry = userZDT.toString() + ": User [" + userName + "] had a failed login attempt!";
                logsOutputFile.println(logEntry);

                // Closing the authentication log file after output written
                logsOutputFile.close();
                System.out.println(logEntryWritten);
            }
        }
    }
}
