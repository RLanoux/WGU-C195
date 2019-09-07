/*
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>  * 
 */
package SchedulingApp.View_Controller;

import SchedulingApp.DAO.DBAppointment;
import SchedulingApp.DAO.DBCustomer;
import SchedulingApp.Model.Appointment;
import SchedulingApp.Model.Customer;
import static SchedulingApp.View_Controller.AppointmentCalendarController.selectedAppt;
import static SchedulingApp.View_Controller.LoginScreenController.loggedUser;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Raymond
 */
public class AddAppointmentController implements Initializable {
    
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
    private ComboBox<Customer> cbCustomer;
    
    @FXML
    private final DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a z");

    @FXML
    void getExitAction(ActionEvent eExitAction) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit Scheduling App");
        exitAlert.setHeaderText("Are you sure you want to exit?");
        exitAlert.setContentText("Press OK to exit the program. \nPress Cancel to stay on this screen.");
        exitAlert.showAndWait();
        if (exitAlert.getResult() == ButtonType.OK) {
            Stage winMainScreen = (Stage)((Node)eExitAction.getSource()).getScene().getWindow();
            winMainScreen.close();
        }
        else {
            exitAlert.close();
        }
    }

    @FXML
    void getSaveAction(ActionEvent eSaveAction) {
        if (isInputEmpty() == true || isInputValid() == false) {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setTitle("Invalid Appointment Addition");
            emptyAlert.setHeaderText("The appointment is not able to be added!");
            emptyAlert.setContentText(emptyInput);
            emptyAlert.showAndWait();
            emptyInput = "";
            
            Alert invalidAlert = new Alert(Alert.AlertType.ERROR);
            invalidAlert.setTitle("Invalid Appointment Addition");
            invalidAlert.setHeaderText("The appointment is not able to be added!");
            invalidAlert.setContentText(invalidInput);
            invalidAlert.showAndWait();
            invalidInput = "";
        }
        if (isInputValid()) {
            Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
            saveAlert.setTitle("Save Appointment Modifications");
            saveAlert.setHeaderText("Are you sure you want to save?");
            saveAlert.setContentText("Press OK to save the addition. \nPress Cancel to stay on this screen.");
            saveAlert.showAndWait();
            if (saveAlert.getResult() == ButtonType.OK) {
                Stage winExitScreen = (Stage)((Node)eSaveAction.getSource()).getScene().getWindow();
                winExitScreen.close();
            }
            else {
                saveAlert.close();
            }
        }
    }
    
    public void getApptInfo() {
        Appointment appt = new Appointment();
        appt.setCustomerId(cbCustomer.getValue().getCustomerId());
        appt.setUserId(loggedUser.getUserId());
        appt.setTitle(txtTitle.getText());
        appt.setDescription(txtDescription.getText());
        appt.setLocation(txtLocation.getText());
        appt.setContact(txtContact.getText());
        appt.setType(txtType.getText());
        appt.setUrl(txtUrl.getText());
        appt.setStart(ZonedDateTime.parse(txtStart.getText(), formatDT));
        appt.setEnd(ZonedDateTime.parse(txtEnd.getText(), formatDT));
        appt.setAppointmentId(selectedAppt.getAppointmentId());
        DBAppointment.addAppointment(appt);
    }
    
    public Customer getActiveCustomers() {
        ObservableList<Customer> activeCusts = DBCustomer.getActiveCustomers();
        cbCustomer.setItems(activeCusts);
        cbCustomer.setPromptText("Select a customer:");
        return null;
    }
    
    public void convertCustomerString() {
        cbCustomer.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer cust) {
                return cust.getCustomerName();
            }

            @Override
            public Customer fromString(String string) {
                return cbCustomer.getValue();
            }
        });
    }
    
    private String emptyInput = new String();
    
    private boolean isInputEmpty() {
        boolean bEmpty = true;
        if (cbCustomer.getValue() == null) {
            bEmpty=true;
            emptyInput += "There was no customer selected!";
        }
        if (txtTitle.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe title cannot be empty!";
        }
        if (txtDescription.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe description cannot be empty!";
        }
        if (txtLocation.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe location cannot be empty!";
        }
        if (txtContact.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe contact cannot be empty!";
        }
        if (txtType.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe type cannot be empty!";
        }
        if (txtUrl.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe url cannot be empty!";
        }
        if (txtStart.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe start cannot be empty!";
        }
        if (txtEnd.getText().isEmpty()) {
            bEmpty=true;
            emptyInput += "\nThe end cannot be empty!";
        }
        return bEmpty;
    }
    
    private String invalidInput = new String();
    
    private boolean isInputValid() {
        boolean bValid = false;
        if (cbCustomer.getValue() != null) {
            bValid=true;
        }
        if (!(txtTitle.getText().isEmpty())) {
           if (!(txtTitle.getText().matches("[a-zA-Z]"))) {
                bValid=false;
                invalidInput += "\nThe title can only contain letters!";
            }
           else {
               bValid=true;
           }
        }
        return bValid;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getActiveCustomers();
        convertCustomerString();
    }    
    
}
