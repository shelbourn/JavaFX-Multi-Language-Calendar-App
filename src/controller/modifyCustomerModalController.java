/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class modifyCustomerModalController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField streetAddress;
    @FXML
    private ComboBox<?> city;
    @FXML
    private TextField phoneNumber;
    @FXML
    private ComboBox<?> country;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clearUserFieldHandler(MouseEvent event) {
    }

    @FXML
    private void clearPasswordFieldHandler(MouseEvent event) {
    }

    @FXML
    private void firstNameHandler(ActionEvent event) {
    }

    @FXML
    private void streetHandler(ActionEvent event) {
    }

    @FXML
    private void cityHandler(ActionEvent event) {
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

    @FXML
    private void countryHandler(ActionEvent event) {
    }
}
