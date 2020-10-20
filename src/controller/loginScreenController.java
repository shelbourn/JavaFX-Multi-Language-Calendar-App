/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBAuth;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DBConn;
import utils.TimeConverters;

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
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginBtn;

    // Additional properties required for functionality
    private String userName;
    private String password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitBtnHandler(ActionEvent event) throws IOException {
        // Add alert for confirmation
        System.out.println("Closing MySQL connection.");
        DBConn.closeConnection();
        System.out.println("Exiting application.\nEND.");
        System.exit(0);
    }

    @FXML
    private void clearUserFieldHandler(MouseEvent event) {
    }

    @FXML
    private void clearPasswordFieldHandler(MouseEvent event) {

    }

    @FXML
    private void loginBtnHandler(ActionEvent event) throws IOException {
        userName = usernameField.getText();
        password = passwordField.getText();

        if (userName.equals("") || password.equals("")) {
            System.err.println("Username and Password are required fields!");
            return;
        }

        if (DBAuth.validateUser(userName, password) == true) {
            System.out.println("Username and Password accepted!\nOpening LANDING screen.");
            Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
            Scene landingScreen = new Scene(root);
            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginWindow.setTitle("CalApp | Main Screen");
            loginWindow.setScene(landingScreen);
            loginWindow.show();
        }

    }

    // Handling form submission when Enter key is pressed in Username field
    @FXML
    private void usernameEnterHandler(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            userName = usernameField.getText();
            password = passwordField.getText();

            if (userName.equals("") || password.equals("")) {
                System.err.println("Username and Password are required fields!");
                return;
            }

            if (DBAuth.validateUser(userName, password) == true) {
                System.out.println("Username and Password accepted!\nOpening LANDING screen.");
                Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
                Scene landingScreen = new Scene(root);
                Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginWindow.setTitle("CalApp | Main Screen");
                loginWindow.setScene(landingScreen);
                loginWindow.show();
            }
        }
    }

    // Handling form submission when Enter key is pressed in Password field
    @FXML
    private void passwordEnterHandler(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            userName = usernameField.getText();
            password = passwordField.getText();

            if (userName.equals("") || password.equals("")) {
                System.err.println("Username and Password are required fields!");
                return;
            }

            if (DBAuth.validateUser(userName, password) == true) {
                System.out.println("Username and Password accepted!\nOpening LANDING screen.");
                Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
                Scene landingScreen = new Scene(root);
                Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginWindow.setTitle("CalApp | Main Screen");
                loginWindow.setScene(landingScreen);
                loginWindow.show();
            }
        }
    }

}
