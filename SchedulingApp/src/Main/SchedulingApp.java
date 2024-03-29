/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import SchedulingApp.DAO.DBConnector;
import SchedulingApp.Model.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import SchedulingApp.View_Controller.LoginScreenController;
import java.sql.SQLException;
import javafx.stage.StageStyle;

/**
 *
 * @author Raymond
 */
public class SchedulingApp extends Application {
    
    @Override
    public void start(Stage stage) throws ClassNotFoundException, SQLException, IOException, Exception {
        FXMLLoader loader = new FXMLLoader(LoginScreenController.class.getResource("LoginScreen.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setTitle("Scheduling App - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    
    /**
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        DBConnector.openConnection();
        launch(args);
        DBConnector.closeConnection();
    }
}
