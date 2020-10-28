/*
 * Controller for Update Customer modal
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private Button saveBtn;
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
    private String updatedCustomerName;
    private String updatedAddress;
    private String updatedPhone;
    private int customerId;
    private int addressId;
    private int updatedCityId;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fetches and sets fields with data from Customer to update
        // Initializes City combo box with data from the database
        customerName.setText(customerToUpdate.getCustomerName());
        address.setText(customerToUpdate.getAddress());
        phone.setText(customerToUpdate.getPhone());
        allCities = DBCity.getAllCities();
        city.setItems(allCities);
        city.getSelectionModel().select(initCities());
        city.setVisibleRowCount(5);
        initCountryName = initCustomerCity.getCountry();
        country.setText(initCountryName);
    }

    // Helper method to retrieve the Customer's current city from the database and convert to a City object
    public City initCities() {
        allCities.stream().filter((i) -> (i.getCity().equals(customerCityName))).forEachOrdered((i) -> {
            initCustomerCity = i;
        });
        return initCustomerCity;
    }

    // Sets Country value upon city selection
    @FXML
    private void cityHandler(ActionEvent event) {
        selectedCity = city.getValue();
        countryName = selectedCity.getCountry();
        country.setText(countryName);
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        updatedCustomerName = customerName.getText();
        updatedAddress = address.getText();
        updatedPhone = phone.getText();
        customerId = customerToUpdate.getCustomerId();
        addressId = customerToUpdate.getAddressId();
        updatedCityId = city.getValue().getCityId();

        if (updatedCustomerName.equals("") || updatedAddress.equals("") || updatedPhone.equals("") || city.getSelectionModel().isEmpty()) {
            // Throw alert if any Appointment fields are empty
            Alert requiredFields = new Alert(Alert.AlertType.INFORMATION);
            requiredFields.setTitle("REQUIRED FIELDS VIOLATION");
            requiredFields.setHeaderText("All fields are required");
            requiredFields.setContentText("Please enter values for all fields.");
            requiredFields.showAndWait();
            return;

        } else {
            // Saves updated customer if validation passes
            DBCustomer.updateCustomer(customerId, updatedCustomerName, addressId, updatedAddress, updatedPhone, updatedCityId);

            // Closes modal and refreshes customer table view
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
