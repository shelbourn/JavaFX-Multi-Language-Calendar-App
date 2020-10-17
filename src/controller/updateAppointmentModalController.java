/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBAppointmentType;
import DBQueries.DBCustomer;
import DBQueries.DBUser;
import static controller.calendarScreenController.getAppointmentToUpdate;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import model.Appointment;
import model.AppointmentType;
import model.City;
import model.Customer;
import model.User;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class updateAppointmentModalController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<User> consultant;
    @FXML
    private ComboBox<Customer> customer;
    @FXML
    private ComboBox<AppointmentType> appointmentType;
    @FXML
    private ComboBox<LocalTime> startTime;
    @FXML
    private ComboBox<LocalTime> endTime;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    // Additional Properties required for functionality
    private final Appointment appointmentToUpdate = getAppointmentToUpdate();
    private final String initUserName = appointmentToUpdate.getUserName();
    private final String initCustomerName = appointmentToUpdate.getCustomerName();
    private final String initAppointmentType = appointmentToUpdate.getType();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<AppointmentType> allAppointmentTypes = FXCollections.observableArrayList();
    private User initUser;
    private Customer initCustomer;
    private AppointmentType initType;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializing fields with appointment data from database
        // Get & Select User
        allUsers = DBUser.getAllUsers();
        consultant.setItems(allUsers);
        consultant.getSelectionModel().select(initUser());

        // Get & Select Customer
        allCustomers = DBCustomer.getAllCustomers();
        customer.setItems(allCustomers);
        customer.getSelectionModel().select(initCustomer());

        // Get & Select Appointment Type
        allAppointmentTypes = DBAppointmentType.getAllAppointmentTypes();
        appointmentType.setItems(allAppointmentTypes);
        appointmentType.getSelectionModel().select(initType());
    }

    // Helper method to retrieve the initial user from the database and convert to a User object
    public User initUser() {
        allUsers.stream().filter((i) -> (i.getUserName().equals(initUserName))).forEachOrdered((i) -> {
            initUser = i;
        });
        return initUser;
    }

    // Helper method to retrieve the initial customer from the database and convert to a Customer object
    public Customer initCustomer() {
        allCustomers.stream().filter((i) -> (i.getCustomerName().equals(initCustomerName))).forEachOrdered((i) -> {
            initCustomer = i;
        });
        return initCustomer;
    }

    // Helper method to retrieve the initial appointment type from the database and convert to a AppointmentType object
    public AppointmentType initType() {
        allAppointmentTypes.stream().filter((i) -> (i.getType().equals(initAppointmentType))).forEachOrdered((i) -> {
            initType = i;
        });
        return initType;
    }

    @FXML
    private void dateHandler(ActionEvent event) {
    }

    @FXML
    private void startTimeHandler(ActionEvent event) {
    }

    @FXML
    private void endTimeHandler(ActionEvent event) {
    }

    @FXML
    private void consultantHandler(ActionEvent event) {
    }

    @FXML
    private void customerHandler(ActionEvent event) {
    }

    @FXML
    private void apptTypeHandler(ActionEvent event) {
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
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
