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
    private TableView<?> calendarTable;
    @FXML
    private AnchorPane mainScr;
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
    private void weekViewHandler(ActionEvent event) {
    }

    @FXML
    private void monthViewHandler(ActionEvent event) {
    }

    @FXML
    private void viewCustHandler(ActionEvent event) {
    }

    @FXML
    private void reportsHandler(ActionEvent event) {
    }

    @FXML
    private void allAppointmentsHandler(ActionEvent event) {
    }

}
