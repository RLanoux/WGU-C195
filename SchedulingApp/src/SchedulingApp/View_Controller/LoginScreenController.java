/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import SchedulingApp.DAO.DBUser;
import SchedulingApp.Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Raymond
 */
public class LoginScreenController implements Initializable {
    
    @FXML
    private Label lblAlert;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnExit;

    @FXML
    void getExitAction(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void getLoginAction(ActionEvent event) throws IOException, Exception {
        Integer userId;
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Boolean active;
        
        User userLogin = new User();
        userLogin.setUserName(username);
        userLogin.setPassword(password);
        
        FXMLLoader ApptCalLoader = new FXMLLoader(AppointmentCalendarController.class.getResource("AppointmentCalendar.fxml"));
        Parent ApptCalRoot = ApptCalLoader.load();
        Scene ApptCalScene = new Scene(ApptCalRoot);
        Stage ApptCalStage = new Stage();
        ApptCalStage.setScene(ApptCalScene);
        
        try {
            ObservableList<User> userLoginInfo = DBUser.getUserLoginInfo(username, password);
            boolean found = false;
            for (User u: userLoginInfo) {
                if (userLogin.getUserName().equals(u.getUserName())) {
                    if (userLogin.getPassword().equals(u.getPassword()))
                    found = true;
                        ApptCalStage.setTitle("Appointment Calendar");
                        ApptCalStage.show();
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                } 
            }
            if (!found) {
                System.out.print("Wrong");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
