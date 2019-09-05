/**
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
package SchedulingApp.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * This class contains the code that allows customer records
 * in the MySQL database to be modified.
 */
public class ModifyCustomerController implements Initializable {

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblCustAddress;

    @FXML
    private TextField txtCustAddress2;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCustPostalCode;

    @FXML
    private Label lblCustPhone;

    @FXML
    private TextField txtCustAddress;

    @FXML
    private TextField txtCustCity;

    @FXML
    private TextField txtCustPostalCode;

    @FXML
    private TextField txtCustPhone;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnExit;

    @FXML
    private TextField txtCustName;

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
    void getSaveAction(ActionEvent event) {

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
