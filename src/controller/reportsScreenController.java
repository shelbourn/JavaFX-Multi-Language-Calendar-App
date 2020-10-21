/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBAppointment;
import DBQueries.DBReports;
import DBQueries.DBUser;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class reportsScreenController implements Initializable {

    @FXML
    private ComboBox<User> apptConsultant;
    @FXML
    private Button apptRun;
    @FXML
    private ComboBox<User> apptSummConsultant;
    @FXML
    private Button apptSummRun;
    @FXML
    private TextArea apptSummResults;
    @FXML
    private ComboBox<User> apptTypesConsultant;
    @FXML
    private Button apptTypesRun;
    @FXML
    private TextArea apptTypesResults;
    @FXML
    private TableView<Appointment> apptByConsTable;
    @FXML
    private TableColumn<Appointment, Integer> custIdCol;
    @FXML
    private TableColumn<Appointment, String> custNameCol;
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
    private Button homeBtn;

    // Additional properties required for functionality
    private int selectedUserAppt;
    private int selectedUserSumm;
    private int selectedUserTypes;
    private int numberOfAppointmentsSumm;
    private int numberOfAppointmentTypes;
    private int[] totalAppointmentTime;
    private int appointmentDuration;
    private int numberOfCustomers;
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes Consultant combo boxes
        allUsers = DBReports.getAllUsers();
        apptSummConsultant.setItems(allUsers);
        apptSummConsultant.setVisibleRowCount(5);
        apptTypesConsultant.setItems(allUsers);
        apptTypesConsultant.setVisibleRowCount(5);
        apptConsultant.setItems(allUsers);
        apptConsultant.setVisibleRowCount(5);

        // Initializes the Appointments table view (without populating table cells)
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("MMM dd, YYYY");

        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        consultantCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(col -> new TableCell<Appointment, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format(item.format(dtfDate)));
                }
            }
        });
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        startTimeCol.setCellFactory(col -> new TableCell<Appointment, LocalTime>() {
            @Override
            protected void updateItem(LocalTime item, boolean empty) {

                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format(item.format(dtfTime)));
                }
            }
        });
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        endTimeCol.setCellFactory(col -> new TableCell<Appointment, LocalTime>() {
            @Override
            protected void updateItem(LocalTime item, boolean empty) {

                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format(item.format(dtfTime)));
                }
            }
        });
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @FXML
    private void apptRunHandler(ActionEvent event) {
        // Retrieves the Consultant for repot generation
        selectedUserAppt = apptConsultant.getValue().getUserId();

        // Fetches data for Consultant from database and populates Appointments table view with data
        apptByConsTable.setItems(DBReports.getUserAppointments(selectedUserAppt));
    }

    @FXML
    private void apptSummRunHandler(ActionEvent event) {
        selectedUserSumm = apptSummConsultant.getValue().getUserId();
        numberOfAppointmentsSumm = DBReports.getNumUserAppointments(selectedUserSumm);
        totalAppointmentTime = DBReports.getTotalAppointmentTime(selectedUserSumm);

        apptSummResults.setText("Number of Appointments: " + numberOfAppointmentsSumm);
        apptSummResults.appendText("\n\nTotal Appointment Duration: " + totalAppointmentTime[0] + " hours, " + totalAppointmentTime[1] + " minutes, " + totalAppointmentTime[2] + " seconds");
        apptSummResults.appendText("\n\nDistinct Customer Meetings: " + numberOfCustomers);
        apptSummResults.appendText("\n\nDistinct Appointment Types: " + numberOfAppointmentTypes);
    }

    @FXML
    private void apptTypsRunHandler(ActionEvent event) {
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
