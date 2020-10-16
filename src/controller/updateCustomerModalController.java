/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBCity;
import DBQueries.DBCustomer;
import static controller.customersScreenController.getCustomerToUpdate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import model.City;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class updateCustomerModalController implements Initializable {

    @FXML
    private ComboBox<City> city;
    @FXML
    private TextField country;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField customerName;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;

    // Additional properties required for functionality
    private final Customer customerToUpdate = getCustomerToUpdate();
    private static ObservableList<City> allCities = FXCollections.observableArrayList();
    private City selectedCity;
    private String countryName;
    private final String customerCityName = customerToUpdate.getCity();
    private City initCustomerCity;
    private String initCountryName;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fetching and setting fields with data from Customer to update
        // Initializing City combo box with data from the database
        customerName.setText(customerToUpdate.getCustomerName());
        address.setText(customerToUpdate.getAddress());
        phone.setText(customerToUpdate.getPhone());
        allCities = DBCity.getAllCities();
        city.setItems(allCities);
        city.getSelectionModel().select(initCities());
        initCountryName = initCustomerCity.getCountry();
        country.setText(initCountryName);
    }

    // Helper method to set the Customer's current city in the database
    public City initCities() {
        City tempCity = null;
        for (City i : allCities) {
            if (i.getCity().equals(customerCityName)) {
                initCustomerCity = i;
            }
        }
        return initCustomerCity;
    }

    @FXML
    private void customerNameHandler(ActionEvent event) {
    }

    @FXML
    private void addressHandler(ActionEvent event) {
    }

    @FXML
    private void cityHandler(ActionEvent event) {
        selectedCity = city.getValue();
        countryName = selectedCity.getCountry();
        country.setText(countryName);
    }

    @FXML
    private void phoneHandler(ActionEvent event) {
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
