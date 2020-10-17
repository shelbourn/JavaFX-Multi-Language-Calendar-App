/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBCustomer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class customersScreenController implements Initializable {

    @FXML
    public TableView<Customer> customersTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> cityCol;
    @FXML
    private TableColumn<Customer, String> countryCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;

    // Additional Properties required for functionality
    private static Customer customerToUpdate;
    private Customer customerToDelete;
    private int customerIdToDelete;
    private int addressIdToDelete;

    // Getters for additional private properties
    public static Customer getCustomerToUpdate() {
        return customerToUpdate;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Initializes Customers table view
        customersTable.setItems(DBCustomer.getAllCustomers());

        // Fetching and setting Customers table
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    // Helper method for Updating Customers table view
    public void updateCustomersTable() {
        customersTable.setItems(DBCustomer.getAllCustomers());
    }

    // Displays the Add Customer modal
    @FXML
    private void addCustHandler(ActionEvent event) throws IOException {
        final Stage addCustomerModal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomerModal.fxml"));
        addCustomerModal.initModality(Modality.APPLICATION_MODAL);
        Scene addCustomerScreen = new Scene(root);
        addCustomerModal.setTitle("CalApp | Add Customer");
        addCustomerModal.setScene(addCustomerScreen);
        addCustomerModal.setOnHidden((WindowEvent event1) -> {
            updateCustomersTable();
        });
        addCustomerModal.show();
    }

    @FXML
    private void updateCustHandler(ActionEvent event) throws IOException {
//        boolean noActiveSelection = customersTable.getSelectionModel().isEmpty();
        customerToUpdate = customersTable.getSelectionModel().getSelectedItem();
//        String customerUpdateName = customerToUpdate.getCustomerName();
//        int customerUpdateId = customerToUpdate.getCustomerId();

        final Stage updateCustomerModal = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/updateCustomerModal.fxml"));
        updateCustomerModal.initModality(Modality.APPLICATION_MODAL);
        Scene UpdateCustomerScreen = new Scene(root);
        updateCustomerModal.setTitle("CalApp | Update Customer");
        updateCustomerModal.setScene(UpdateCustomerScreen);
        updateCustomerModal.setOnHidden((WindowEvent event1) -> {
            updateCustomersTable();
        });
        updateCustomerModal.show();

    }

    @FXML
    private void deleteCustHandler(ActionEvent event) {

        // Deletes the currently selected Customer
        customerToDelete = customersTable.getSelectionModel().getSelectedItem();
        customerIdToDelete = customerToDelete.getCustomerId();
        addressIdToDelete = customerToDelete.getAddressId();

        DBCustomer.deleteCustomer(customerIdToDelete, addressIdToDelete);

        // Refreshes Customer table view
        updateCustomersTable();

    }

    // Changes to the Calendar View
    @FXML
    private void viewCalHandler(ActionEvent event) throws IOException {
        System.out.println("Opening CALENDAR screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/view/calendarScreen.fxml"));
        Scene calendarScreen = new Scene(root);
        Stage calendarWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        calendarWindow.setTitle("CalApp | Appointments Calendar");
        calendarWindow.setScene(calendarScreen);
        calendarWindow.show();
    }

    // Displays the Add Appointment modal
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

    // Changes to the Reports view
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

    // Changes to the Landing Screen view
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
