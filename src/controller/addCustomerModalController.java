/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBQueries.DBCity;
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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class addCustomerModalController implements Initializable {

    @FXML
    private ComboBox<?> city;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void customerNameHandler(ActionEvent event) {
    }

    @FXML
    private void addressHandler(ActionEvent event) {
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

}
