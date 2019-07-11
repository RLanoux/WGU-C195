/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author P00306944
 */
public class AppointmentCalendarController implements Initializable {
    
    @FXML
    private Button btnNewAppt;

    @FXML
    private Button btnModifyAppt;

    @FXML
    private Button btnDeleteAppt;

    @FXML
    private Button btnNewCust;

    @FXML
    private Button btnModifyCust;

    @FXML
    private Button btnDeleteCust;

    @FXML
    private Button btnReports;

    @FXML
    private Button btnUserLogs;

    @FXML
    private Button btnExit;

    @FXML
    private Tab tpWeeklyAppts;

    @FXML
    private TableView<?> tvWeeklyAppts;

    @FXML
    private TableColumn<?, ?> tcWeeklyCustName;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptTitle;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptDescription;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptLocation;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptContact;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptType;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptURL;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptStart;

    @FXML
    private TableColumn<?, ?> tcWeeklyApptEnd;

    @FXML
    private Tab tpMonthlyAppts;

    @FXML
    private TableView<?> tvMonthlyAppts;

    @FXML
    private TableColumn<?, ?> tcMonthlyCustName;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptTitle;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptDescription;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptLocation;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptContact;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptType;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptURL;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptStart;

    @FXML
    private TableColumn<?, ?> tcMonthlyApptEnd;

    @FXML
    void getWeeklyAppts(ActionEvent event) {

    }
    
    @FXML
    void getMonthlyAppts(ActionEvent event) {

    }
    
    @FXML
    void getNewAppt(ActionEvent event) {

    }
    
    @FXML
    void getModifyAppt(ActionEvent event) {

    }
    
    @FXML
    void getDeleteAppt(ActionEvent event) {

    }

    @FXML
    void getNewCust(ActionEvent event) {

    }
    
    @FXML
    void getModifyCust(ActionEvent event) {

    }
    
    @FXML
    void getDeleteCust(ActionEvent event) {

    }

    @FXML
    void getReports(ActionEvent event) {

    }

    @FXML
    void getUserLogs(ActionEvent event) {

    }
    
    @FXML
    void getExitAction(ActionEvent event) {

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
