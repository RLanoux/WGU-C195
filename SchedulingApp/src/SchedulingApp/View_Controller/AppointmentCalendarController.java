/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import static SchedulingApp.DAO.DBAppointment.getApptsByWeek;
import SchedulingApp.Model.Appointment;
import SchedulingApp.Model.Customer;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableView<Appointment> tvWeeklyAppts;

    @FXML
    private TableColumn<Customer, String> tcWeeklyCustName;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptTitle;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptDescription;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptLocation;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptContact;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptType;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptURL;

    @FXML
    private TableColumn<Appointment, LocalDateTime> tcWeeklyApptStart;

    @FXML
    private TableColumn<Appointment, LocalDateTime> tcWeeklyApptEnd;

    @FXML
    private Tab tpMonthlyAppts;

    @FXML
    private TableView<Appointment> tvMonthlyAppts;

    @FXML
    private TableColumn<Customer, String> tcMonthlyCustName;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptTitle;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptDescription;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptLocation;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptContact;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptType;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptURL;

    @FXML
    private TableColumn<Appointment, LocalDateTime> tcMonthlyApptStart;

    @FXML
    private TableColumn<Appointment, LocalDateTime> tcMonthlyApptEnd;

    @FXML
    void getWeeklyAppts(ActionEvent event) {
        updateWeeklyApptTV();
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
    void getExitAction(ActionEvent eExitButton) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Scheduling App");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Press OK to exit the program. \nPress Cancel to stay on this screen.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Stage winMainScreen = (Stage)((Node)eExitButton.getSource()).getScene().getWindow();
            winMainScreen.close();
        }
        else {
            alert.close();
        }
    }
    
    @FXML
    public void updateWeeklyApptTV() {
        tvWeeklyAppts.setItems(getApptsByWeek());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcWeeklyCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        updateWeeklyApptTV();
    }    
    
}
