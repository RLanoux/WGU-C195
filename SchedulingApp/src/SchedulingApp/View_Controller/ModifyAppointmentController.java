/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import SchedulingApp.Model.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raymond
 */
public class ModifyAppointmentController implements Initializable {
    
    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblType;

    @FXML
    private Label lblUrl;

    @FXML
    private Label lblStart;

    @FXML
    private Label lblEnd;

    @FXML
    private ChoiceBox<Customer> cbCustomer;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtStart;

    @FXML
    private TextField txtEnd;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnExit;

    @FXML
    void getExitAction(ActionEvent event) {

    }

    @FXML
    void getSaveAction(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
