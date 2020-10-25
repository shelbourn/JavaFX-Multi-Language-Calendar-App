/*
 * Controller for the Add Customer modal
 */
package controller;

import DBQueries.DBCity;
import DBQueries.DBCustomer;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Window;
import model.City;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class addCustomerModalController implements Initializable {

    @FXML
    private ComboBox<City> city;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField country;
    @FXML
    private TextField customerName;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;

    // Additional properties required for functionality
    private static ObservableList<City> allCities = FXCollections.observableArrayList();
    private City selectedCity;
    private String countryName;
    private int DBCityId;
    private String DBCustomerName;
    private String DBAddress;
    private String DBphone;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes City combo box with data from the database
        allCities = DBCity.getAllCities();
        city.setItems(allCities);
        city.setVisibleRowCount(5);
    }

    @FXML
    private void cityHandler(ActionEvent event) {
        selectedCity = city.getValue();
        countryName = selectedCity.getCountry();
        country.setText(countryName);
    }

    @FXML
    private void clearFieldsHandler(ActionEvent event) {
        customerName.clear();
        address.clear();
        phone.clear();
        city.getSelectionModel().clearSelection();
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        if (DBCustomerName == null || DBAddress == null || DBphone == null || city.getValue() == null) {
            // Throw alert if any Appointment fields are empty
            Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
            requiredFields.setTitle("REQUIRED FIELDS VIOLATION");
            requiredFields.setHeaderText("All fields are required");
            requiredFields.setContentText("Please enter values for all fields.");
            requiredFields.showAndWait();
            return;

        } else {
            // Saves customer if validation passes
            DBCustomerName = customerName.getText();
            DBAddress = address.getText();
            DBphone = phone.getText();
            DBCityId = city.getValue().getCityId();

            DBCustomer.createCustomer(DBCustomerName, DBAddress, DBphone, DBCityId);

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
