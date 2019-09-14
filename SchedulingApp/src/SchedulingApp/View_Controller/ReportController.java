/*
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>  * 
 */
package SchedulingApp.View_Controller;

import SchedulingApp.DAO.DBAppointment;
import SchedulingApp.DAO.DBCustomer;
import SchedulingApp.Model.Appointment;
import SchedulingApp.Model.Customer;
import SchedulingApp.Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Raymond
 */
public class ReportController implements Initializable {
    
    @FXML
    private ToggleGroup tgReports;
    
    @FXML
    private RadioButton tbApptTypes;

    @FXML
    private RadioButton tbSchedule;

    @FXML
    private RadioButton tbCustomer;
    
    @FXML
    private TextArea txtReportField;
    
    @FXML
    private Button btnExit;

    @FXML
    void getApptTypes(ActionEvent event) {
        if (tbApptTypes.isSelected()) {
            try {
                txtReportField.clear();
                ObservableList<Appointment> apptTypes = DBAppointment.getApptsByMonth();
                int apptTypeCount = 0;
                String apptType = "";
                String apptTypeOld = "";
                String apptTypeNew = "";
                for (Appointment aOld : apptTypes) {
                    apptTypeOld = aOld.getType();
                }
                for (Appointment aNew : apptTypes) {
                    apptTypeNew = aNew.getType();
                }
                for (Appointment a : apptTypes) {
                    if (apptTypeOld.equals(apptTypeNew)) {
                        apptTypeCount++;
                    }
                    txtReportField.appendText(apptTypeCount + " " + apptType);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void getCustomerList(ActionEvent event) {
        if (tbCustomer.isSelected()) {
            try {
                txtReportField.clear();
                ObservableList<Customer> allCustomers = DBCustomer.getAllCustomers();
                String customerName = "";
                Boolean active;
                String newline = "\n";
                for (Customer c : allCustomers) {
                    customerName = c.getCustomerName();
                    active = c.activeProperty().get();
                    txtReportField.appendText("Customer: " + customerName + " active status equals: " + active + " in the database." + newline);
                }
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void getSchedule(ActionEvent event) {
        if (tbSchedule.isSelected()) {
            try {
                txtReportField.clear();
                ObservableList schedule = DBAppointment.getApptsByUser();
                
                for (Object o : schedule) {
                    String appt = o.toString();
                    txtReportField.appendText(appt + "\n");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleExitAction(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
