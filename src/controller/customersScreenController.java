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

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class customersScreenController implements Initializable {

    @FXML
    private TableView<?> customersTable;

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
    private void addCustHandler(ActionEvent event) {
    }

    @FXML
    private void updateCustHandler(ActionEvent event) {
    }

    @FXML
    private void deleteCustHandler(ActionEvent event) {
    }

    @FXML
    private void viewCalHandler(ActionEvent event) {
    }

    @FXML
    private void addApptHandler(ActionEvent event) {
    }

    @FXML
    private void reportsHandler(ActionEvent event) {
    }

}
