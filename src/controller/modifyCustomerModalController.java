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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class modifyCustomerModalController implements Initializable {

    @FXML
    private AnchorPane mainScr;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastNight;
    @FXML
    private TextField streetAddress;
    @FXML
    private TextField city;
    @FXML
    private TextField zipcode;
    @FXML
    private ComboBox<?> state;
    @FXML
    private TextField phoneNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitBtnHandler(ActionEvent event) {
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
    private void lastNameHandler(ActionEvent event) {
    }

    @FXML
    private void streetHandler(ActionEvent event) {
    }

    @FXML
    private void cityHandler(ActionEvent event) {
    }

    @FXML
    private void zipHandler(ActionEvent event) {
    }

    @FXML
    private void stateHandler(ActionEvent event) {
    }

    @FXML
    private void phoneHandler(ActionEvent event) {
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
    }

}
