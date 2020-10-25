/*
 * Controller for the Add Appointment modal
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Window;
import model.Appointment;
import model.AppointmentType;
import model.Customer;
import model.User;
import static utils.HelperMethods.stringToLT;
import static utils.HelperMethods.twelveHrTime;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class addAppointmentModalController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> startTime;
    @FXML
    private ComboBox<String> endTime;
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
    private String selectedStartTime;
    private String selectedEndTime;
    private LocalTime convertedSelectedStartTime;
    private LocalTime convertedSelectedEndTime;
    private User selectedUser;
    private Customer selectedCustomer;
    private int customerId;
    private int userId;
    private String type;
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<AppointmentType> allAppointmentTypes = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes Start Time combo box
        startTimeValues();
        endTimeDisable();

        // Retrieves Appointment data needed for overlapping appointment checks
        allAppointments = DBAppointment.getAllAppointments();

        // Initializes Consultant combo box
        allUsers = DBUser.getAllUsers();
        consultant.setItems(allUsers);
        consultant.setVisibleRowCount(5);

        // Initializes Customer combo box
        allCustomers = DBCustomer.getAllCustomers();
        customer.setItems(allCustomers);
        customer.setVisibleRowCount(5);

        // Initializes Appointment Type combo box
        allAppointmentTypes = DBAppointmentType.getAllAppointmentTypes();
        appointmentType.setItems(allAppointmentTypes);
        appointmentType.setVisibleRowCount(5);

        // Initializes available dates in DatePicker & selects current date by default
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean isEmpty) {
                super.updateItem(date, isEmpty);
                LocalDate today = LocalDate.now();

                setDisable(isEmpty || date.compareTo(today) < 0);
            }
        });

        datePicker.setValue(LocalDate.now());
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

    // Method to disable End Time combo box if start time hasn't been selected
    private void endTimeDisable() {
        if (selectedStartTime == null) {
            endTime.setDisable(true);
        }
    }

    // Helper method for populating end time combo box
    private void endTimeValues() {
        LocalTime startTimeRange = convertedSelectedStartTime.plusMinutes(15);
        LocalTime endTimeRange = LocalTime.of(17, 0);
        String endTimeDisplay;
        endTime.getItems().clear();

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {
            endTimeDisplay = twelveHrTime(startTimeRange);
            endTime.getItems().add(endTimeDisplay);
            startTimeRange = startTimeRange.plusMinutes(15);
            endTime.setVisibleRowCount(5);

        }

    }

    @FXML
    private void startTimeHandler(ActionEvent event
    ) {
        // Gets the start time value and enabling End Time combo box
        selectedStartTime = startTime.getValue();
        convertedSelectedStartTime = stringToLT(selectedStartTime);

        if (convertedSelectedStartTime != null) {
            endTime.setDisable(false);
            endTimeValues();
        }

        // Sets/Resets end time values once start time value is selected
        if (convertedSelectedEndTime != null && convertedSelectedEndTime.isBefore(convertedSelectedStartTime.plusSeconds(1))) {
            convertedSelectedEndTime = null;
            endTime.getSelectionModel().clearSelection();
            endTimeValues();
        }
    }

    @FXML
    private void endTimeHandler(ActionEvent event
    ) {
        // Gets the end time value
        if (endTime.getValue() != null) {
            selectedEndTime = endTime.getValue();
            convertedSelectedEndTime = stringToLT(selectedEndTime);
        } else {
            try {
                selectedEndTime = endTime.getValue();
                convertedSelectedEndTime = stringToLT(selectedEndTime);
            } catch (NullPointerException e) {
            }
        }

    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        if (selectedDate == null || convertedSelectedStartTime == null || convertedSelectedEndTime == null || selectedUser == null || selectedCustomer == null || type == null) {
            // Throw alert if any Appointment fields are empty
            Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
            requiredFields.setTitle("REQUIRED FIELDS VIOLATION");
            requiredFields.setHeaderText("All fields are required");
            requiredFields.setContentText("Please enter values for all fields.");
            requiredFields.showAndWait();
            return;

        } else {
            boolean okayToSave = true;

            // Checks for overlapping appointment start time, end time, or both
            // If overlapping appointment, new Exception thrown and caught
            for (Appointment appt : allAppointments) {
                customerId = customer.getValue().getCustomerId();
                selectedUser = consultant.getValue();
                selectedCustomer = customer.getValue();
                userId = consultant.getValue().getUserId();
                selectedDate = datePicker.getValue();
                convertedSelectedStartTime = stringToLT(startTime.getValue());
                convertedSelectedEndTime = stringToLT(endTime.getValue());
                type = appointmentType.getValue().getType();

                try {
                    if ((userId == (appt.getUserId())) && selectedDate.equals(appt.getDate()) && ((convertedSelectedStartTime.isAfter(appt.getStart().minusSeconds(1)) && convertedSelectedStartTime.isBefore(appt.getEnd().plusSeconds(1))) || (convertedSelectedEndTime.isAfter(appt.getStart()) && convertedSelectedStartTime.isBefore(appt.getEnd())))) {
                        okayToSave = false;
                        throw new Exception("Overlapping appointment error!");
                    }

                } catch (Exception e) {
                    System.err.println("Overlapping appointment error!");

                    // Throw alert if any Appointments for a user overlap
                    Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
                    requiredFields.setTitle("OVERLAPPING APPOINTMENT VIOLATION");
                    requiredFields.setHeaderText("Appointment Start and/or End time overlap another appointment for this user");
                    requiredFields.setContentText("Please select new Start and/or End times for this appointment.");
                    requiredFields.showAndWait();
                    e.printStackTrace();
                }
            }

            // Saves appointment if validation passes
            if (okayToSave == true) {
                DBAppointment.createAppointment(customerId, userId, selectedDate, convertedSelectedStartTime, convertedSelectedEndTime, type);

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
    }

    // Closes modal
    @FXML
    private void cancelBtnHandler(ActionEvent event
    ) {
        Scene scene = cancelBtn.getScene();
        if (scene != null) {
            Window window = scene.getWindow();
            if (window != null) {
                window.hide();
            }
        }
    }

}
