package SchedulingApp.DAO;

import static Main.SchedulingApp.loggedUser;
import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Raymond Lanoux <rlanoux@wgu.edu>
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
            e.getMessage();
        }
        return activeCustomers;
    }
    
    /**
     * This method gets a Customer record from the MySQL database by customerId.
     * @param customerId
     * @return getCustomerQuery
     */
    public Customer getCustomerById(int customerId) {
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
        }
        return getCustomerQuery;
    }
    
    private int getMaxCustomerId() {
        int maxCustomerId = 0;
        String maxCustomerIdSQL = "SELECT MAX(customerId) FROM customer";
        
        try {
            Statement stmt = DB_CONN.createStatement();
            ResultSet rs = stmt.executeQuery(maxCustomerIdSQL);
            
            if (rs.next()) {
                maxCustomerId = rs.getInt(1);
            }
        }
        catch (SQLException e) {
        }
        return maxCustomerId + 1;
    }
    
    public int addCustomer(Customer customer) {
        String addCustomerSQL = String.join(" ", 
                "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)",
                "VALUES (?, ?, ?, 1, NOW(), ?, NOW(), ?)");
        
        int customerId = getMaxCustomerId();
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(addCustomerSQL);
            stmt.setInt(1, customerId);
            stmt.setString(2, customer.getCustomerName());
            stmt.setInt(3, customer.getAddressId());
            stmt.setString(4, loggedUser.getUserName());
            stmt.setString(5, loggedUser.getUserName());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
        return customerId;
    }
    
    public void updateCustomer(Customer customer) {
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
        }
    }
    
    public void deleteCustomer(Customer customer) {
        String deleteCustomerSQL = "DELETE FROM customer WHERE customerId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(deleteCustomerSQL);
            stmt.setInt(1, customer.getCustomerId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
}