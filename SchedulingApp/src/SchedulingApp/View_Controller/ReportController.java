/*
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>  * 
 */
package SchedulingApp.View_Controller;

import SchedulingApp.DAO.DBAppointment;
import SchedulingApp.DAO.DBCustomer;
import SchedulingApp.DAO.DBUser;
import SchedulingApp.Model.Appointment;
import SchedulingApp.Model.Customer;
import SchedulingApp.Model.User;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.R;

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
    private final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    @FXML
    private final DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("hh:mm a z");

    @FXML
    void getApptTypes(ActionEvent event) {
        if (tbApptTypes.isSelected()) {
            try {
                txtReportField.clear();
                ObservableList<Appointment> apptTypes = DBAppointment.getApptsByMonth();
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
                ObservableList<Appointment> userSchedule = DBAppointment.getApptsByUser();
                String userName = "";
                String custName = "";
                ZonedDateTime startZDT;
                
                for (Appointment a : userSchedule) {
                    int u = a.getUserId();
                    int c = a.getCustomerId();
                    startZDT = a.getStart();
                    User userById = DBUser.getUserById(u);
                    userName = userById.getUserName();
                    Customer activeCustomerById = DBCustomer.getActiveCustomerById(c);
                    custName = activeCustomerById.getCustomerName();
                    txtReportField.appendText("User: " + userName 
                            + " has an appointment with " + custName 
                            + " on " + startZDT.format(formatDate) 
                            + " at " + startZDT.format(formatTime) + ".\n");
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
