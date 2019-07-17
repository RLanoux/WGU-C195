/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Raymond
 */
public class DBConnector {
    private static final String DB_NAME = "U04PjR";
    private static final String DB_URL = "jdbc:mysql://52.206.157.109/"+DB_NAME;
    private static final String USERNAME = "U04PjR";
    private static final String PASSWORD = "53688305516";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    /*private static final String DB_NAME = "c195test";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/"+DB_NAME;
    private static final String USERNAME = "rlanoux";
    private static final String PASSWORD = "P@ss2019!";*/
            
    static Connection DB_CONN;
    
    public static void openConnection() throws ClassNotFoundException, SQLException, Exception
    {
        Class.forName(DRIVER);
        DB_CONN = (Connection) DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        System.out.println("Connection Opened Successfully");
    }
    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception
    {
        DB_CONN.close();
        System.out.println("Connection Closed Successfully");
    }
}
