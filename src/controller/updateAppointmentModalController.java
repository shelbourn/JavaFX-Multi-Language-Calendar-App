/*
 * Controller for Update Appointment modal
 */
package controller;

import DBQueries.DBAppointment;
import DBQueries.DBAppointmentType;
import DBQueries.DBCustomer;
import DBQueries.DBUser;
import static controller.calendarScreenController.getAppointmentToUpdate;
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
    private String startTimeDisplay;
    private LocalTime initStartTime;
    private String initEndTimeDisplay;
    private String endTimeDisplay;
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
        initDate = appointmentToUpdate.getDate();

        // Retrieves appointment data for overlapping appointment check
        allAppointments = DBAppointment.getAllAppointments();

        // Initializes available dates in DatePicker & selects initial appointment date
        // I used a lambda expression here for brevity (less lines of code) and efficiency
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
        initStartTime = appointmentToUpdate.getStart();
        initStartTimeDisplay = twelveHrTime(initStartTime);
        startTimeDisplay = initStartTimeDisplay;
        startTime.getSelectionModel().select(initStartTimeDisplay);
        startTime.setValue(initStartTimeDisplay);
        convertedSelectedStartTime = initStartTime;

        // Retrieves the current appointment end time, converts it to LocalDate object then String, and selects it in start time combo box
        initEndTime = appointmentToUpdate.getEnd();
        initEndTimeDisplay = twelveHrTime(initEndTime);
        endTimeDisplay = initEndTimeDisplay;
        endTime.getSelectionModel().select(initEndTimeDisplay);
        endTime.setValue(initEndTimeDisplay);
        convertedSelectedEndTime = initEndTime;

        // Initializes End Time combo box
        endTimeValues();
    }

    // Helper method for populating start time combo box
    private void startTimeValues() {
        LocalTime startTimeRange = LocalTime.of(8, 0);
        LocalTime endTimeRange = LocalTime.of(17, 0);

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {
            startTimeDisplay = twelveHrTime(startTimeRange);
            startTime.getItems().add(startTimeDisplay);
            startTimeRange = startTimeRange.plusMinutes(15);
            startTime.setVisibleRowCount(5);
        }
    }

    // Helper method for populating end time combo box
    private void endTimeValues() {
        LocalTime startTimeRange = convertedSelectedStartTime.plusMinutes(15);
        LocalTime endTimeRange = LocalTime.of(17, 0);
        endTime.getItems().clear();

        while (startTimeRange.isBefore(endTimeRange.plusSeconds(1))) {
            endTimeDisplay = twelveHrTime(startTimeRange);
            endTime.getItems().add(endTimeDisplay);
            startTimeRange = startTimeRange.plusMinutes(15);
            endTime.setVisibleRowCount(5);
        }
    }

    // Helper method to retrieve the initial user from the database and convert to a User object
    public User initUser() {
        // I used a lambda expression here for brevity (less lines of code) and efficiency
        allUsers.stream().filter((i) -> (i.getUserName().equals(initUserName))).forEachOrdered((i) -> {
            initUser = i;
        });
        return initUser;
    }

    // Helper method to retrieve the initial customer from the database and convert to a Customer object
    public Customer initCustomer() {
        // I used a lambda expression here for brevity (less lines of code) and efficiency
        allCustomers.stream().filter((i) -> (i.getCustomerName().equals(initCustomerName))).forEachOrdered((i) -> {
            initCustomer = i;
        });
        return initCustomer;
    }

    // Helper method to retrieve the initial appointment type from the database and convert to a AppointmentType object
    public AppointmentType initType() {
        // I used a lambda expression here for brevity (less lines of code) and efficiency
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

        // Sets / Resets end time values once start time value is selected based on impossible conditions
        if (convertedSelectedEndTime != null && convertedSelectedEndTime.isBefore(convertedSelectedStartTime.plusMinutes(1))) {
            endTime.getSelectionModel().clearSelection();
            convertedSelectedEndTime = null;
            endTimeValues();
        }

        // Resets the end time values whenever a new start time is selected
        if (convertedSelectedEndTime == null && convertedSelectedStartTime != null) {
            endTimeValues();
        }
    }

    @FXML
    private void endTimeHandler(ActionEvent event) {
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

    // Saves updated appointment after validation
    @FXML
    private void saveBtnHandler(ActionEvent event) {
        appointmentId = appointmentToUpdate.getAppointmentId();
        customerId = customer.getValue().getCustomerId();
        selectedUser = consultant.getValue();
        selectedCustomer = customer.getValue();
        userId = consultant.getValue().getUserId();
        selectedDate = datePicker.getValue();
        convertedSelectedStartTime = stringToLT(startTime.getValue());
        convertedSelectedEndTime = stringToLT(endTime.getValue());
        type = appointmentType.getValue().getType();

        if (selectedDate == null || convertedSelectedStartTime == null || convertedSelectedEndTime == null || selectedUser == null || selectedCustomer == null || type == null) {
            // Throw alert if any Appointment fields are empty
            Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
            requiredFields.setTitle("REQUIRED FIELDS VIOLATION");
            requiredFields.setHeaderText("All fields are required");
            requiredFields.setContentText("Please enter values for all fields.");
            requiredFields.showAndWait();
            return;

        }

        // Checks for overlapping appointment start time, end time, or both
        // If overlapping appointment, new Exception thrown and caught
        boolean okayToSave = true;

        for (Appointment appt : allAppointments) {
            try {
                if ((userId == appt.getUserId()) && (selectedDate.equals(appt.getDate())) && ((convertedSelectedStartTime.isAfter(appt.getStart().minusSeconds(1)) && convertedSelectedStartTime.isBefore(appt.getEnd().plusSeconds(1))) || (convertedSelectedEndTime.isAfter(appt.getStart()) && convertedSelectedStartTime.isBefore(appt.getEnd())))) {
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
                return;
            }
        }

        // Saves appointment if validation passes
        if (okayToSave == true) {
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

// Closes modal
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
