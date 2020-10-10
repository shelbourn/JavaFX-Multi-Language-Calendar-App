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
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class calendarScreenController implements Initializable {

    @FXML
    private AnchorPane mainScr;
    @FXML
    private Label appSubHeaderLabel;
    @FXML
    private Button exitBtn;
    @FXML
    private AnchorPane partPanel1;
    @FXML
    private AnchorPane partPanel11;
    @FXML
    private AnchorPane partPanel111;
    @FXML
    private TableView<?> calendarTable;
    @FXML
    private AnchorPane partPanel112;
    @FXML
    private Label appSubHeaderLabel1;
    @FXML
    private ToggleGroup calViewToggleGroup;

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
    private void addApptHandler(ActionEvent event) {
    }

    @FXML
    private void updateApptHandler(ActionEvent event) {
    }

    @FXML
    private void deleteApptHandler(ActionEvent event) {
    }

    @FXML
    private void addCustHandler(ActionEvent event) {
    }

    @FXML
    private void updateCustHandler(ActionEvent event) {
    }

    @FXML
    private void deleteCustHandler(ActionEvent event) {
    }

    @FXML
    private void weekViewHandler(ActionEvent event) {
    }

    @FXML
    private void monthViewHandler(ActionEvent event) {
    }

}
