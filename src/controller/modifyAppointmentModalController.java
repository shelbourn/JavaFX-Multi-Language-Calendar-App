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
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class modifyAppointmentModalController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<?> appointmentTime;
    @FXML
    private ComboBox<?> consultant;
    @FXML
    private ComboBox<?> customer;
    @FXML
    private ComboBox<?> appointmentType;
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
    private void dateHandler(ActionEvent event) {
    }

    @FXML
    private void timeHandler(ActionEvent event) {
    }

    @FXML
    private void consultantHandler(ActionEvent event) {
    }

    @FXML
    private void customerHandler(ActionEvent event) {
    }

    @FXML
    private void apptTypeHandler(ActionEvent event) {
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
