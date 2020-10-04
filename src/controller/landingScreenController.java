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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class landingScreenController implements Initializable {

    @FXML
    private AnchorPane mainScr;
    @FXML
    private Label appSubHeaderLabel;
    @FXML
    private Button exitBtn;
    @FXML
    private AnchorPane partPanel;
    @FXML
    private Button partsAddBtn;
    @FXML
    private Button partsModifyBtn;
    @FXML
    private Button partsDeleteBtn;
    @FXML
    private AnchorPane partPanel2;
    @FXML
    private Button partsAddBtn1;
    @FXML
    private Button partsModifyBtn1;
    @FXML
    private Button partsDeleteBtn1;
    @FXML
    private AnchorPane partPanel1;
    @FXML
    private Button loginBtn1;
    @FXML
    private Button loginBtn;

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
    private void partsAddBtnHandler(ActionEvent event) {
    }

    @FXML
    private void partsModifyBtnHandler(ActionEvent event) {
    }

    @FXML
    private void deleteAppointBtnHandler(ActionEvent event) {
    }

    @FXML
    private void addCustBtnHandler(ActionEvent event) {
    }

    @FXML
    private void updateCustBtnHandler(ActionEvent event) {
    }

    @FXML
    private void deleteCustBtnHandler(ActionEvent event) {
    }

    @FXML
    private void viewReportsBtnHandler(ActionEvent event) {
    }

    @FXML
    private void viewCalBtnHandler(ActionEvent event) {
    }

}
