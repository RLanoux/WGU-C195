/**
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
package SchedulingApp.DAO;

import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Address;
import static SchedulingApp.View_Controller.LoginScreenController.loggedUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class contains the data access objects (DAOs) 
 * for the address table in the MySQL database.
 */
public class DBAddress {
    
    public static Address getAddressById(int addressId) {
        String getAddressByIdSQL = "SELECT * FROM address WHERE addressId = ?";
        Address getAddById = new Address();
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getAddressByIdSQL);
            stmt.setInt(1, addressId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                getAddById.setAddressId(rs.getInt("addressId"));
                getAddById.setAddress(rs.getString("address"));
                getAddById.setAddress2(rs.getString("address2"));
                getAddById.setCityId(rs.getInt("cityId"));
                getAddById.setPostalCode(rs.getString("postalCode"));
                getAddById.setPhone(rs.getString("phone"));
            }
        }
        catch (SQLException e) {
        }
        return getAddById;
    }
    private static int getMaxAddressId() {
        int maxAddressId = 0;
        String maxAddressIdSQL = "SELECT MAX(addressId) FROM address";
        
        try {
            Statement stmt = DB_CONN.createStatement();
            ResultSet rs = stmt.executeQuery(maxAddressIdSQL);
            
            if (rs.next()) {
                maxAddressId = rs.getInt(1);
            }
        }
        catch (SQLException e) {
        }
        return maxAddressId + 1;
    }
    public static int addAddress(Address address) {
        String addAddressSQL = String.join(" ",
                "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)",
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)");
        
        int addressId = getMaxAddressId();
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(addAddressSQL);
            stmt.setInt(1, addressId);
            stmt.setString(2, address.getAddress());
            stmt.setString(3, address.getAddress2());
            stmt.setInt(4, address.getCityId());
            stmt.setString(5, address.getPostalCode());
            stmt.setString(6, address.getPhone());
            stmt.setString(7, loggedUser.getUserName());
            stmt.setString(8, loggedUser.getUserName());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
        return addressId;
    }
    public static void updateAddress(Address address) {
        String updateAddressSQL = String.join(" ",
                "UPDATE address",
                "SET address=?, address2=?, cityId=?, postalCode=?, phone=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE addressId=?");
        
        try{
            PreparedStatement stmt = DB_CONN.prepareStatement(updateAddressSQL);
            stmt.setString(1, address.getAddress());
            stmt.setString(2, address.getAddress2());
            stmt.setInt(3, address.getCityId());
            stmt.setString(4, address.getPostalCode());
            stmt.setString(5, address.getPhone());
            stmt.setString(6, loggedUser.getUserName());
            stmt.setInt(7, address.getAddressId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
    public void deleteAddress(Address address) {
        String deleteAddressSQL = "DELETE FROM address WHERE addressId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(deleteAddressSQL);
            stmt.setInt(1, address.getAddressId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
}
