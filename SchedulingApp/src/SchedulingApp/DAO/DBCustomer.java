/**
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
package SchedulingApp.DAO;

import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Customer;
import static SchedulingApp.View_Controller.LoginScreenController.loggedUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains the data access objects (DAOs) 
 * for the customer table in the MySQL database.
 */
public class DBCustomer {
    
    /**
     * This method creates an ObservableList and populates it with 
     * all currently active Customer records in the MySQL database.
     * @return activeCustomers
     */
    public static ObservableList<Customer> getActiveCustomers() {
        ObservableList<Customer> activeCustomers = FXCollections.observableArrayList();
        String getActiveCustomersSQL = "SELECT * FROM customer WHERE active = 1";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getActiveCustomersSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Customer activeCustomer = new Customer();
                activeCustomer.setCustomerId(rs.getInt("customerId"));
                activeCustomer.setCustomerName(rs.getString("customerName"));
                activeCustomer.setAddressId(rs.getInt("addressId"));
                activeCustomer.setActive(rs.getBoolean("active"));
                activeCustomers.add(activeCustomer);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return activeCustomers;
    }
    
    /**
     * This method gets a Customer record from the MySQL database by customerId.
     * @param customerId
     * @return getCustomerQuery
     */
    public static Customer getCustomerById(int customerId) {
        String getCustomerByIdSQL = "SELECT * FROM customer WHERE customerId = ?";
        Customer getCustomerQuery = new Customer();
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getCustomerByIdSQL);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                getCustomerQuery.setCustomerId(rs.getInt("customerId"));
                getCustomerQuery.setCustomerName(rs.getString("customerName"));
                getCustomerQuery.setAddressId(rs.getInt("addressId"));
                getCustomerQuery.setActive(rs.getBoolean("active"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return getCustomerQuery;
    }
    
    /**
     * This method adds a new Customer to the MySQL database.
     * @param customer
     * @return customer
     */
    public static Customer addCustomer(Customer customer) {
        String addCustomerSQL = String.join(" ", 
                "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)",
                "VALUES (?, ?, 1, NOW(), ?, NOW(), ?)");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(addCustomerSQL);
            stmt.setString(1, customer.getCustomerName());
            stmt.setInt(2, customer.getAddressId());
            stmt.setString(3, loggedUser.getUserName());
            stmt.setString(4, loggedUser.getUserName());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
    /**
     * This method updates an existing Customer record in the MySQL database.
     * @param customer
     */
    public static void updateCustomer(Customer customer) {
        String updateCustomerSQL = String.join(" ", 
                "UPDATE customer",
                "SET customerName=?, addressId=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE customerId = ?");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(updateCustomerSQL);
            stmt.setString(1, customer.getCustomerName());
            stmt.setInt(2, customer.getAddressId());
            stmt.setString(3, loggedUser.getUserName());
            stmt.setInt(4, customer.getCustomerId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method deletes an existing Customer from the MySQL database.
     * @param customer
     */
    public static void deleteCustomer(Customer customer) {
        String deleteCustomerSQL = "DELETE FROM customer WHERE customerId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(deleteCustomerSQL);
            stmt.setInt(1, customer.getCustomerId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}