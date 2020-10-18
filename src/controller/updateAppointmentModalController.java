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
import static controller.calendarScreenController.getAppointmentToUpdate;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import model.Appointment;
import model.AppointmentType;
import model.City;
import model.Customer;
import model.User;
import static utils.HelperMethods.stringToLT;
import static utils.HelperMethods.twelveHrTime;

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
    private ComboBox<String> startTime;
    @FXML
    private ComboBox<String> endTime;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    // Additional Properties required for functionality
    private final Appointment appointmentToUpdate = getAppointmentToUpdate();
    private final String initUserName = appointmentToUpdate.getUserName();
    private final String initCustomerName = appointmentToUpdate.getCustomerName();
    private final String initAppointmentType = appointmentToUpdate.getType();
    private User initUser;
    private Customer initCustomer;
    private AppointmentType initType;
    private LocalDate initDate;
    private String initStartTimeDisplay;
    private LocalTime initStartTime;
    private String initEndTimeDisplay;
    private LocalTime initEndTime;
    private LocalDate selectedDate;
    private String selectedStartTime;
    private String selectedEndTime;
    private LocalTime convertedSelectedStartTime;
    private LocalTime convertedSelectedEndTime;
    private User selectedUser;
    private Customer selectedCustomer;
    private AppointmentType selectedAppointmentType;
    private int appointmentId;
    private int customerId;
    private int userId;
    private String type;
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<AppointmentType> allAppointmentTypes = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes Start/End Time combo boxes
        startTimeValues();

        // Initializes fields with appointment data from database
        // Get & Select Current User
        allUsers = DBUser.getAllUsers();
        consultant.setItems(allUsers);
        consultant.setVisibleRowCount(5);
        consultant.getSelectionModel().select(initUser());

        // Get & Select Current Customer
        allCustomers = DBCustomer.getAllCustomers();
        customer.setItems(allCustomers);
        customer.setVisibleRowCount(5);
        customer.getSelectionModel().select(initCustomer());

        // Get & Select Current Appointment Type
        allAppointmentTypes = DBAppointmentType.getAllAppointmentTypes();
        appointmentType.setItems(allAppointmentTypes);
        appointmentType.setVisibleRowCount(5);
        appointmentType.getSelectionModel().select(initType());

        // Retrieves the current appointment date and convert to LocalDate object
        initDate = appointmentToUpdate.getStart().toLocalDateTime().toLocalDate();

        // Initializes available dates in DatePicker & selects initial appointment date
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean isEmpty) {
                super.updateItem(date, isEmpty);
                LocalDate today = LocalDate.now();

                setDisable(isEmpty || date.compareTo(today) < 0);
            }
        });

        datePicker.setValue(initDate);

        // Retrieves the current appointment start time, converts it to LocalDate object then String, and selects it in start time combo box
        initStartTime = appointmentToUpdate.getStart().toLocalDateTime().toLocalTime();
        initStartTimeDisplay = twelveHrTime(initStartTime);
        startTime.getSelectionModel().select(initStartTimeDisplay);
        convertedSelectedStartTime = initStartTime;

        // Retrieves the current appointment end time, converts it to LocalDate object then String, and selects it in start time combo box
        initEndTime = appointmentToUpdate.getEnd().toLocalDateTime().toLocalTime();
        initEndTimeDisplay = twelveHrTime(initEndTime);
        endTime.getSelectionModel().select(initEndTimeDisplay);
    }

    // Helper method for populating start time combo box
    private void startTimeValues() {
        LocalTime startTimeRange = LocalTime.of(8, 0);
        LocalTime endTimeRange = LocalTime.of(17, 0);
        String startTimeDisplay;

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {
            startTimeDisplay = twelveHrTime(startTimeRange);
            startTime.getItems().add(startTimeDisplay);
            startTimeRange = startTimeRange.plusMinutes(15);
            startTime.setVisibleRowCount(5);
        }
    }

//    // Method to disable End Time combo box if start time hasn't been selected
//    private void endTimeDisable() {
//        if (selectedStartTime == null) {
//            endTime.setDisable(true);
//        }
//    }
    // Helper method for populating end time combo box
    private void endTimeValues() {
        LocalTime startTimeRange = convertedSelectedStartTime.plusMinutes(15);
        LocalTime endTimeRange = LocalTime.of(17, 0);
        String endTimeDisplay;

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {
            initEndTimeDisplay = twelveHrTime(startTimeRange);
            endTime.getItems().add(initEndTimeDisplay);
            startTimeRange = startTimeRange.plusMinutes(15);
            endTime.setVisibleRowCount(5);

        }

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
    private void startTimeHandler(ActionEvent event) {
        // Gets the start time value and enabling End Time combo box
        selectedStartTime = startTime.getValue();
        convertedSelectedStartTime = stringToLT(selectedStartTime);
//        if (convertedSelectedStartTime != null) {
//            endTime.setDisable(false);
//        }

        // Sets/Resets end time values once start time value is selected
        endTime.getItems().clear();
        endTimeValues();
    }

    @FXML
    private void endTimeHandler(ActionEvent event) {
        // Gets the end time value
        selectedEndTime = endTime.getValue();
        convertedSelectedEndTime = stringToLT(selectedEndTime);
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {

        appointmentId = appointmentToUpdate.getAppointmentId();
        customerId = customer.getValue().getCustomerId();
        userId = consultant.getValue().getUserId();
        selectedDate = datePicker.getValue();
        convertedSelectedStartTime = stringToLT(startTime.getValue());
        convertedSelectedEndTime = stringToLT(endTime.getValue());
        type = appointmentType.getValue().getType();

        if (selectedDate == null) {

        } else {

            DBAppointment.updateAppointment(appointmentId, customerId, userId, selectedDate, convertedSelectedStartTime, convertedSelectedEndTime, type);

            // Closes modal on successful submission
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

    private LocalDate tsToLTD(Timestamp start) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
