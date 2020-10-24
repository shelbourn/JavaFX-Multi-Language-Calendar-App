/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBAppointment;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.MINUTES;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointment;
import utils.DBConn;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class landingScreenController implements Initializable {

    // Additional properties required for functionality
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> alertAppointments = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // 15-Minute Warning: Retrieves all appointments for comparison to user's time
        appointments = DBAppointment.getAllAppointments();

        // Calls the 15-Minute Warning helper function
        fifteenMinuteWarning();
    }

    // Helper function for 15-Minute Warning
    private void fifteenMinuteWarning() {
        // 15-Minute Warning: Retrieves user's Local Date/Time
        LocalDate userDate = LocalDate.now();
        LocalTime userTime = LocalTime.now();

        // 15-Minute Warning: Iterates through appointments and check whether an appointment start within 15 minutes
        // If appointment is within 15 minutes then is added to list of appointments to be alerted
        appointments.forEach((appt) -> {
            if ((appt.getDate().equals(userDate))
                    && (((Duration.between(userTime, appt.getStart()).toMinutes()) >= 0)
                    && ((Duration.between(userTime, appt.getStart()).toMinutes()) <= 15))) {
                alertAppointments.add(appt);
            }
        });

        // 15-Minute Warning: Alerts user of appointments starting within 15 minutes
        alertAppointments.forEach((appt) -> {
            System.err.println(appt.getCustomerName());
        });
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
    private void addCustHandler(ActionEvent event) throws IOException {
        final Stage addCustomerModal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomerModal.fxml"));
        addCustomerModal.initModality(Modality.APPLICATION_MODAL);
        Scene addCustomerScreen = new Scene(root);
        addCustomerModal.setTitle("CalApp | Add Customer");
        addCustomerModal.setScene(addCustomerScreen);
        addCustomerModal.show();
    }

    @FXML
    private void addApptHandler(ActionEvent event) throws IOException {
        final Stage addAppointmentModal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointmentModal.fxml"));
        addAppointmentModal.initModality(Modality.APPLICATION_MODAL);
        Scene addAppointmentScreen = new Scene(root);
        addAppointmentModal.setTitle("CalApp | Add Appointment");
        addAppointmentModal.setScene(addAppointmentScreen);
        addAppointmentModal.show();
    }

    @FXML
    private void viewCustomersHandler(ActionEvent event) throws IOException {
        System.out.println("Opening CUSTOMERS screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/customersScreen.fxml"));
        Scene customersScreen = new Scene(root);
        Stage customersWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customersWindow.setTitle("CalApp | Customers");
        customersWindow.setScene(customersScreen);
        customersWindow.show();
    }

    @FXML
    private void viewCalendarHandler(ActionEvent event) throws IOException {
        System.out.println("Opening CALENDAR screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/calendarScreen.fxml"));
        Scene calendarScreen = new Scene(root);
        Stage calendarWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        calendarWindow.setTitle("CalApp | Appointments Calendar");
        calendarWindow.setScene(calendarScreen);
        calendarWindow.show();
    }

    @FXML
    private void viewReportsHandler(ActionEvent event) throws IOException {
        System.out.println("Opening CALENDAR screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportsScreen.fxml"));
        Scene reportsScreen = new Scene(root);
        Stage reportsWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        reportsWindow.setTitle("CalApp | Reports");
        reportsWindow.setScene(reportsScreen);
        reportsWindow.show();
    }

}
