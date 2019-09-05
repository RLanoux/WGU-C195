/**
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
package SchedulingApp.DAO;

import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.City;
import static SchedulingApp.View_Controller.LoginScreenController.loggedUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains the data access objects (DAOs) 
 * for the city table in the MySQL database.
 */
public class DBCity {
    
    public City getCityById(int cityId) {
        String getCityByIdSQL = "SELECT * FROM city WHERE cityId = ?";
        City getCityById = new City();
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getCityByIdSQL);
            stmt.setInt(1, cityId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                getCityById.setCityId(rs.getInt("cityId"));
                getCityById.setCity(rs.getString("city"));
                getCityById.setCountryId(rs.getInt("countryId"));
            }
        }
        catch (SQLException e) {
        }
        return getCityById;
    }

    public City addCity(City city) {
        String addCitySQL = String.join(" ",
                "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)",
                "VALUES (?, ?, NOW(), ?, NOW(), ?)");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(addCitySQL);
            stmt.setString(1, city.getCity());
            stmt.setInt(2, city.getCountryId());
            stmt.setString(3, loggedUser.getUserName());
            stmt.setString(4, loggedUser.getUserName());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
        return city;
    }
    public void updateCity(City city) {
        String updateCitySQL = String.join(" ",
                "UPDATE city",
                "SET city=?, countryId=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE cityId=?");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(updateCitySQL);
            stmt.setString(1, city.getCity());
            stmt.setInt(2, city.getCountryId());
            stmt.setString(3, loggedUser.getUserName());
            stmt.setInt(4, city.getCityId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
    public void deleteCity(City city) {
        String deleteCitySQL = "DELETE FROM city WHERE cityId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(deleteCitySQL);
            stmt.setInt(1, city.getCityId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
}
