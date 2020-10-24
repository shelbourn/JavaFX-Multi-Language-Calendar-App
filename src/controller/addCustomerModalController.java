/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.City;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class addCustomerModalController implements Initializable {

    @FXML
    private ComboBox<City> city;
    @FXML
    private Button clearFieldsBtn;
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
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        DBCustomerName = customerName.getText();
        DBAddress = address.getText();
        DBphone = phone.getText();
        DBCityId = selectedCity.getCityId();
        DBCustomer.createCustomer(DBCustomerName, DBAddress, DBphone, DBCityId);

        Scene scene = saveBtn.getScene();
        if (scene != null) {
            Window window = scene.getWindow();
            if (window != null) {
                window.hide();
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
