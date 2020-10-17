/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBAppointment;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class calendarScreenController implements Initializable {

    @FXML
    private TableView<Appointment> calendarTable;
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;
    @FXML
    private TableColumn<Appointment, String> customerNameCol;
    @FXML
    private TableColumn<Appointment, String> consultantCol;
    @FXML
    private TableColumn<Appointment, LocalDate> dateCol;
    @FXML
    private TableColumn<Appointment, LocalTime> startTimeCol;
    @FXML
    private TableColumn<Appointment, LocalTime> endTimeCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private ToggleGroup calViewToggleGroup;
    @FXML
    private Button updateApptBtn;
    @FXML
    private Button homeBtn;

    // Additional Properties required for functionality
    private static Appointment appointmentToUpdate;
    private Appointment appointmentToDelete;
    private int appointmentIdToDelete;

    // Getters for additional private properties
    public static Appointment getAppointmentToUpdate() {
        return appointmentToUpdate;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes the Appointments table view
        calendarTable.setItems(DBAppointment.getAllAppointments());

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        consultantCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    // Helper method for Updating Customers table view
    public void updateAppointmentsTable() {
        calendarTable.setItems(DBAppointment.getAllAppointments());
    }

    @FXML
    private void addApptHandler(ActionEvent event) throws IOException {
        final Stage addAppointmentModal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointmentModal.fxml"));
        addAppointmentModal.initModality(Modality.APPLICATION_MODAL);
        Scene addAppointmentScreen = new Scene(root);
        addAppointmentModal.setTitle("CalApp | Add Appointment");
        addAppointmentModal.setScene(addAppointmentScreen);
        addAppointmentModal.setOnHidden((WindowEvent event1) -> {
            updateAppointmentsTable();
        });
        addAppointmentModal.show();
    }

    @FXML
    private void updateApptHandler(ActionEvent event) throws IOException {
        // Gets the appointment to update
        appointmentToUpdate = calendarTable.getSelectionModel().getSelectedItem();

        final Stage updateAppointmentModal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/updateAppointmentModal.fxml"));
        updateAppointmentModal.initModality(Modality.APPLICATION_MODAL);
        Scene updateAppointmentScreen = new Scene(root);
        updateAppointmentModal.setTitle("CalApp | Update Appointment");
        updateAppointmentModal.setScene(updateAppointmentScreen);
        updateAppointmentModal.setOnHidden((WindowEvent event1) -> {
            updateAppointmentsTable();
        });
        updateAppointmentModal.show();
    }

    @FXML
    private void deleteApptHandler(ActionEvent event) {
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
    private void weekViewHandler(ActionEvent event) {
    }

    @FXML
    private void monthViewHandler(ActionEvent event) {
    }

    @FXML
    private void viewCustHandler(ActionEvent event) throws IOException {
        System.out.println("Opening CUSTOMERS screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/customersScreen.fxml"));
        Scene customersScreen = new Scene(root);
        Stage customersWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customersWindow.setTitle("CalApp | Customers");
        customersWindow.setScene(customersScreen);
        customersWindow.show();
    }

    @FXML
    private void reportsHandler(ActionEvent event) throws IOException {
        System.out.println("Opening CALENDAR screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportsScreen.fxml"));
        Scene reportsScreen = new Scene(root);
        Stage reportsWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        reportsWindow.setTitle("CalApp | Reports");
        reportsWindow.setScene(reportsScreen);
        reportsWindow.show();
    }

    @FXML
    private void allAppointmentsHandler(ActionEvent event) {
    }

    @FXML
    private void homeHandler(ActionEvent event) throws IOException {
        System.out.println("Username and Password accepted!\nOpening LANDING screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/landingScreen.fxml"));
        Scene landingScreen = new Scene(root);
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginWindow.setTitle("CalApp | Main Screen");
        loginWindow.setScene(landingScreen);
        loginWindow.show();
    }

}
