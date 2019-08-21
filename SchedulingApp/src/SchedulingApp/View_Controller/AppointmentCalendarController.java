/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import SchedulingApp.DAO.DBAppointment;
import static SchedulingApp.DAO.DBAppointment.getApptsByMonth;
import static SchedulingApp.DAO.DBAppointment.getApptsByWeek;
import SchedulingApp.Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<Appointment, String> tcWeeklyCustName;

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
    private TableColumn<Appointment, String> tcWeeklyApptStart;

    @FXML
    private TableColumn<Appointment, String> tcWeeklyApptEnd;

    @FXML
    private Tab tpMonthlyAppts;

    @FXML
    private TableView<Appointment> tvMonthlyAppts;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyCustName;

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
    private TableColumn<Appointment, String> tcMonthlyApptStart;

    @FXML
    private TableColumn<Appointment, String> tcMonthlyApptEnd;
    
    @FXML
    private final DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a z");
    
    private static Appointment selectedAppt;

    @FXML
    void getWeeklyAppts(Event event) {
        
    }
    
    @FXML
    void getMonthlyAppts(Event event) {

    }
    
    @FXML
    void getNewAppt(ActionEvent event) {
        
    }
    
    @FXML
    void getModifyAppt(ActionEvent event) {
        if (tpWeeklyAppts.isSelected()) {
            selectedAppt = tvWeeklyAppts.getSelectionModel().getSelectedItem();
            if (selectedAppt == null) {
                Alert nullAlert = new Alert(AlertType.ERROR);
                nullAlert.setTitle("Appointment Modification Error");
                nullAlert.setHeaderText("The appointment is not able to be modified!");
                nullAlert.setContentText("There was no appointment selected!");
                nullAlert.showAndWait();
            }
            else {
                    Alert modAlert = new Alert(AlertType.CONFIRMATION);
                    modAlert.setTitle("Modify Appointment");
                    modAlert.setHeaderText("Are you sure you want to modify this appointment?");
                    modAlert.setContentText("Press OK to modify the appointment. \nPress Cancel to cancel the modification.");
                    modAlert.showAndWait();
                    if (modAlert.getResult() == ButtonType.OK) {
                        try {
                            Appointment appt = tvWeeklyAppts.getSelectionModel().getSelectedItem();
                            FXMLLoader modApptLoader = new FXMLLoader(ModifyAppointmentController.class.getResource("ModifyAppointment.fxml"));
                            Parent modApptScreen = modApptLoader.load();
                            Scene modApptScene = new Scene(modApptScreen);
                            Stage modApptStage = new Stage();
                            modApptStage.setTitle("Modify Appointment");
                            modApptStage.setScene(modApptScene);
                            modApptStage.show();
                        }
                        catch (IOException e) {
                        }
                    }
                }
            }
        else if (tpMonthlyAppts.isSelected()) {
            selectedAppt = tvMonthlyAppts.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    void getDeleteAppt(ActionEvent event) {
        if (tpWeeklyAppts.isSelected()) {
            Alert delAlert = new Alert(AlertType.CONFIRMATION);
            delAlert.setTitle("Delete Appointment");
            delAlert.setHeaderText("Are you sure you want to delete this appointment?");
            delAlert.setContentText("Press OK to delete the appointment. \nPress Cancel to cancel the deletion.");
            delAlert.showAndWait();
            if (delAlert.getResult() == ButtonType.OK) {
                try {
                    Appointment appt = tvWeeklyAppts.getSelectionModel().getSelectedItem();
                    DBAppointment.deleteAppointment(appt);
                }
                catch (NullPointerException e) {
                    Alert nullAlert = new Alert(AlertType.ERROR);
                    nullAlert.setTitle("Appointment Modification Error");
                    nullAlert.setHeaderText("The appointment is not able to be deleted!");
                    nullAlert.setContentText("There was no appointment selected!");
                    nullAlert.showAndWait();
                }
            }
            else {
                delAlert.close();
            }
        }
        else if (tpMonthlyAppts.isSelected()) {
            Alert delAlert = new Alert(AlertType.CONFIRMATION);
            delAlert.setTitle("Delete Appointment");
            delAlert.setHeaderText("Are you sure you want to delete this appointment?");
            delAlert.setContentText("Press OK to delete the appointment. \nPress Cancel to cancel the deletion.");
            delAlert.showAndWait();
            if (delAlert.getResult() == ButtonType.OK) {
                selectedAppt = tvMonthlyAppts.getSelectionModel().getSelectedItem();
            }
            else {
                delAlert.close();
            }
        }
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcWeeklyCustName.setCellValueFactory(cellData -> { return cellData.getValue().getCustomer().customerNameProperty(); });
        tcWeeklyApptTitle.setCellValueFactory(cellData -> { return cellData.getValue().titleProperty(); });
        tcWeeklyApptDescription.setCellValueFactory(cellData -> { return cellData.getValue().descriptionProperty(); });
        tcWeeklyApptLocation.setCellValueFactory(cellData -> { return cellData.getValue().locationProperty(); });
        tcWeeklyApptContact.setCellValueFactory(cellData -> { return cellData.getValue().contactProperty(); });
        tcWeeklyApptType.setCellValueFactory(cellData -> { return cellData.getValue().typeProperty(); });
        tcWeeklyApptURL.setCellValueFactory(cellData -> { return cellData.getValue().urlProperty(); });
        tcWeeklyApptStart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().format(formatDT)));
        tcWeeklyApptEnd.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getEnd().format(formatDT)));
        tvWeeklyAppts.getItems().addAll(getApptsByWeek());
        tcMonthlyCustName.setCellValueFactory(cellData -> { return cellData.getValue().getCustomer().customerNameProperty(); });
        tcMonthlyApptTitle.setCellValueFactory(cellData -> { return cellData.getValue().titleProperty(); });
        tcMonthlyApptDescription.setCellValueFactory(cellData -> { return cellData.getValue().descriptionProperty(); });
        tcMonthlyApptLocation.setCellValueFactory(cellData -> { return cellData.getValue().locationProperty(); });
        tcMonthlyApptContact.setCellValueFactory(cellData -> { return cellData.getValue().contactProperty(); });
        tcMonthlyApptType.setCellValueFactory(cellData -> { return cellData.getValue().typeProperty(); });
        tcMonthlyApptURL.setCellValueFactory(cellData -> { return cellData.getValue().urlProperty(); });
        tcMonthlyApptStart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().format(formatDT)));
        tcMonthlyApptEnd.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getEnd().format(formatDT)));
        tvMonthlyAppts.getItems().addAll(getApptsByMonth());
    }    
    
}
