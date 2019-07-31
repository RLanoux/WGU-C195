/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.DAO;

import SchedulingApp.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author P00306944
 */
public class DBUser {
    
    private final static Connection DB_CONN = DBConnector.DB_CONN;
    
    public DBUser () {
    }
    
    public static ObservableList<User> getActiveUsers() {
        ObservableList<User> activeUsers = FXCollections.observableArrayList();
        String getActiveUsers = "SELECT * FROM user WHERE active = 1";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getActiveUsers);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                User activeUser = new User();
                activeUser.setUserId(rs.getInt("userId"));
                activeUser.setUserName(rs.getString("userName"));
                activeUser.setPassword(rs.getString("password"));
                activeUser.setActive(rs.getBoolean("active"));
                
                activeUsers.add(activeUser);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return activeUsers;
    }
}
