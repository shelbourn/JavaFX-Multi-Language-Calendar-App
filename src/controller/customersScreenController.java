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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class customersScreenController implements Initializable {

    @FXML
    private AnchorPane mainScr;
    @FXML
    private Label appSubHeaderLabel;
    @FXML
    private Button exitBtn;
    @FXML
    private AnchorPane partPanel1;
    @FXML
    private TableView<?> customersTable;
    @FXML
    private AnchorPane partPanel11;
    @FXML
    private AnchorPane partPanel111;
    @FXML
    private AnchorPane partPanel1111;

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
