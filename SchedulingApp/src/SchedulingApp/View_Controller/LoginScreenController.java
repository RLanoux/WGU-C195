/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.View_Controller;

import Main.SchedulingApp;
import SchedulingApp.DAO.DBUser;
import SchedulingApp.Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    void getLoginAction(ActionEvent eLogin) throws IOException, Exception {
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
                        try {
                            FXMLLoader apptCalLoader = new FXMLLoader(AppointmentCalendarController.class.getResource("AppointmentCalendar.fxml"));
                            Parent apptCalScreen = apptCalLoader.load();
                            Scene apptCalScene = new Scene(apptCalScreen);
                            Stage apptCalStage = new Stage();
                            apptCalStage.setTitle("Appointment Calendar");
                            apptCalStage.setScene(apptCalScene);
                            apptCalStage.show();
                            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
                            loginStage.close();
                        }
                        catch (IOException e) {
                        }
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
}
