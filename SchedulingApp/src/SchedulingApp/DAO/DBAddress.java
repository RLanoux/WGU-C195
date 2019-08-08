/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.DAO;

import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Address;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Raymond
 */
public class DBAddress {
    
    public Address getAddressById(int addressId) {
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
}
