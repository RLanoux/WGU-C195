/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import SchedulingApp.DAO.DBUser;
import SchedulingApp.Model.User;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author Raymond
 */
public class LoginScreenController implements Initializable {
    ResourceBundle rb;
    Locale userLocale;
    
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
        
        try {
            ObservableList<User> userLoginInfo = DBUser.getActiveUsers();
            boolean found = false;
            for (User u: userLoginInfo) {
                if (userLogin.getUserName().equals(u.getUserName()) && userLogin.getPassword().equals(u.getPassword())) {
                    found = true;
                        openApptCal();
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                } 
            }
            if (!found) {
                this.lblAlert.setText(this.rb.getString("lblErrorAlert") + ".");
                this.lblAlert.setTextFill(Paint.valueOf("RED"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("LocaleLanguageFiles/rb", this.userLocale);
        this.lblUsername.setText(this.rb.getString("username") + ":");
        this.lblPassword.setText(this.rb.getString("password") + ":");
        this.txtUsername.setPromptText(this.rb.getString("usernamePrompt"));
        this.txtPassword.setPromptText(this.rb.getString("passwordPrompt"));
        this.btnLogin.setText(this.rb.getString("btnLoginText"));
        this.btnExit.setText(this.rb.getString("btnExitText"));
    }    
    
    public void openApptCal() throws IOException {
        try {
            FXMLLoader ApptCalLoader = new FXMLLoader(AppointmentCalendarController.class.getResource("AppointmentCalendar.fxml"));
            Parent ApptCalRoot = ApptCalLoader.load();
            AppointmentCalendarController ApptCalController = ApptCalLoader.getController();
            Scene ApptCalScene = new Scene(ApptCalRoot);
            Stage ApptCalStage = new Stage();
            ApptCalStage.setScene(ApptCalScene);
            ApptCalStage.setTitle("Appointment Calendar");
            ApptCalStage.show();
        }
        catch (IOException e) {
        }
    }
}
