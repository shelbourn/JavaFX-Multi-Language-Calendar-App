/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBAppointment;
import DBQueries.DBAppointmentType;
import DBQueries.DBCustomer;
import DBQueries.DBUser;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import model.AppointmentType;
import model.City;
import model.Customer;
import model.User;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class addAppointmentModalController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<LocalTime> startTime;
    @FXML
    private ComboBox<LocalTime> endTime;
    @FXML
    private ComboBox<User> consultant;
    @FXML
    private ComboBox<Customer> customer;
    @FXML
    private ComboBox<AppointmentType> appointmentType;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;

    // Additional properties required for functionality
    private LocalDate selectedDate;
    private LocalTime selectedStartTime;
    private LocalTime selectedEndTime;
    private User selectedUser;
    private Customer selectedCustomer;
    private AppointmentType selectedAppointmentType;
    private int customerId;
    private int userId;
    private String type;
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<AppointmentType> allAppointmentTypes = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializing Start/End Time combo boxes
        startTimeValues();
        endTimeDisable();

        // Initializing Consultant combo box
        allUsers = DBUser.getAllUsers();
        consultant.setItems(allUsers);
        consultant.setVisibleRowCount(5);

        // Initializing Customer combo box
        allCustomers = DBCustomer.getAllCustomers();
        customer.setItems(allCustomers);
        customer.setVisibleRowCount(5);

        // Initializing Appointment Type combo box
        allAppointmentTypes = DBAppointmentType.getAllAppointmentTypes();
        appointmentType.setItems(allAppointmentTypes);
        appointmentType.setVisibleRowCount(5);
    }

    // Helper method for populating start time combo box
    private void startTimeValues() {
        LocalTime startTimeRange = LocalTime.of(8, 0);
        LocalTime endTimeRange = LocalTime.of(17, 0);

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {

            startTime.getItems().add(startTimeRange);
            startTimeRange = startTimeRange.plusMinutes(15);
            startTime.setVisibleRowCount(5);
        }
    }

    // Method to disable End Time combo box if start time hasn't been selected
    private void endTimeDisable() {
        if (selectedStartTime == null) {
            endTime.setDisable(true);
        }
    }

    // Helper method for populating end time combo box
    private void endTimeValues() {
        LocalTime startTimeRange = selectedStartTime.plusMinutes(15);
        LocalTime endTimeRange = LocalTime.of(17, 0);

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {

            endTime.getItems().add(startTimeRange);
            startTimeRange = startTimeRange.plusMinutes(15);
            endTime.setVisibleRowCount(5);

        }

    }

    @FXML
    private void dateHandler(ActionEvent event) {
        // Getting the appointment date
        selectedDate = datePicker.getValue();
    }

    @FXML
    private void startTimeHandler(ActionEvent event) {
        // Getting the start time value and enabling End Time combo box
        selectedStartTime = startTime.getValue();
        if (selectedStartTime != null) {
            endTime.setDisable(false);
        }

        // Setting end time values once start time value is selected
        endTimeValues();
    }

    @FXML
    private void endTimeHandler(ActionEvent event) {
        // Getting the end time value
        selectedEndTime = endTime.getValue();
    }

    @FXML
    private void consultantHandler(ActionEvent event) {
        // Getting the consultant (user)
        selectedUser = consultant.getValue();
    }

    @FXML
    private void customerHandler(ActionEvent event) {
        // Getting the customer
        selectedCustomer = customer.getValue();
    }

    @FXML
    private void apptTypeHandler(ActionEvent event) {
        // Getting the appointment type
        selectedAppointmentType = appointmentType.getValue();
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        if (selectedDate == null || selectedStartTime == null || selectedEndTime == null || selectedUser == null || selectedCustomer == null || selectedAppointmentType == null) {
        } else {
            customerId = selectedCustomer.getCustomerId();
            userId = selectedUser.getUserId();
            type = selectedAppointmentType.getType();

            DBAppointment.createAppointment(customerId, userId, selectedDate, selectedStartTime, selectedEndTime, type);

            // Closing modal on successful submission
            Scene scene = saveBtn.getScene();
            if (scene != null) {
                Window window = scene.getWindow();
                if (window != null) {
                    window.hide();
                }
            }
        }
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
        Scene scene = cancelBtn.getScene();
        if (scene != null) {
            Window window = scene.getWindow();
            if (window != null) {
                window.hide();
            }
        }
    }

}
