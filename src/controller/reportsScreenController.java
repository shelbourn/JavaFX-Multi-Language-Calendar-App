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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class reportsScreenController implements Initializable {

    @FXML
    private AnchorPane mainScr;
    @FXML
    private TableView<?> apptConsultantResults;
    @FXML
    private ComboBox<?> apptConsultant;
    @FXML
    private Button apptRun;
    @FXML
    private ComboBox<?> apptSummConsultant;
    @FXML
    private Button apptSummRun;
    @FXML
    private TextArea apptSummResults;
    @FXML
    private ComboBox<?> apptTypesConsultant;
    @FXML
    private Button apptTypesRun;
    @FXML
    private TextArea apptTypesResults;

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
    private void apptConsultantHandler(ActionEvent event) {
    }

    @FXML
    private void apptRunHandler(ActionEvent event) {
    }

    @FXML
    private void apptSummConsultantHandler(ActionEvent event) {
    }

    @FXML
    private void apptSummRunHandler(ActionEvent event) {
    }

    @FXML
    private void apptTypesConsultantHandler(ActionEvent event) {
    }

    @FXML
    private void apptTypsRunHandler(ActionEvent event) {
    }

}
