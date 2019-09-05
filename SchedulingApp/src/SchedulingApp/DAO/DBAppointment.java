/**
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
package SchedulingApp.DAO;

import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Appointment;
import static SchedulingApp.View_Controller.LoginScreenController.loggedUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains the data access objects (DAOs) 
 * for the appointment table in the MySQL database.
 */
public class DBAppointment {
    private static ZoneId zId = ZoneId.systemDefault();
    
    public static ObservableList<Appointment> getApptsByWeek() {
        ObservableList<Appointment> apptsByWeek = FXCollections.observableArrayList();
        String getApptsByWeekSQL = "SELECT customer.customerName, appointment.* FROM customer "
                + "RIGHT JOIN appointment ON customer.customerId = appointment.customerId "
                + "WHERE start BETWEEN NOW() AND (SELECT ADDDATE(NOW(), INTERVAL 7 DAY))";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptsByWeekSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Appointment getWeeklyAppts = new Appointment();
                getWeeklyAppts.getCustomer().setCustomerName(rs.getString("customerName"));
                getWeeklyAppts.setAppointmentId(rs.getInt("appointmentId"));
                getWeeklyAppts.setCustomerId(rs.getInt("customerId"));
                getWeeklyAppts.setUserId(rs.getInt("userId"));
                getWeeklyAppts.setTitle(rs.getString("title"));
                getWeeklyAppts.setDescription(rs.getString("description"));
                getWeeklyAppts.setLocation(rs.getString("location"));
                getWeeklyAppts.setContact(rs.getString("contact"));
                getWeeklyAppts.setType(rs.getString("type"));
                getWeeklyAppts.setUrl(rs.getString("url"));
                
                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), zId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), zId);
                
                getWeeklyAppts.setStart(startLocal);
                getWeeklyAppts.setEnd(endLocal);
                apptsByWeek.add(getWeeklyAppts);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return apptsByWeek;
    }
    public static ObservableList<Appointment> getApptsByMonth() {
        ObservableList<Appointment> apptsByMonth = FXCollections.observableArrayList();
        String getApptsByMonthSQL = "SELECT customer.customerName, appointment.* FROM customer "
                + "RIGHT JOIN appointment ON customer.customerId = appointment.customerId "
                + "WHERE start BETWEEN NOW() AND (SELECT LAST_DAY(NOW()))";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptsByMonthSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Appointment getMonthlyAppts = new Appointment();
                getMonthlyAppts.getCustomer().setCustomerName(rs.getString("customerName"));
                getMonthlyAppts.setAppointmentId(rs.getInt("appointmentId"));
                getMonthlyAppts.setCustomerId(rs.getInt("customerId"));
                getMonthlyAppts.setUserId(rs.getInt("userId"));
                getMonthlyAppts.setTitle(rs.getString("title"));
                getMonthlyAppts.setDescription(rs.getString("description"));
                getMonthlyAppts.setLocation(rs.getString("location"));
                getMonthlyAppts.setContact(rs.getString("contact"));
                getMonthlyAppts.setType(rs.getString("type"));
                getMonthlyAppts.setUrl(rs.getString("url"));
                
                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), zId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), zId);
                
                getMonthlyAppts.setStart(startLocal);
                getMonthlyAppts.setEnd(endLocal);
                apptsByMonth.add(getMonthlyAppts);
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
        return apptsByMonth;
    }
    public Appointment getApptById(int appointmentId) {
        String getApptByIdSQL = "SELECT customer.customerName, appointment.* FROM customer "
                + "RIGHT JOIN appointment ON customer.customerId = appointment.customerId " 
                +"WHERE appointmentId = ?";
        Appointment getApptById = new Appointment();
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptByIdSQL);
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                getApptById.getCustomer().setCustomerName(rs.getString("customerName"));
                getApptById.setCustomerId(rs.getInt("customerId"));
                getApptById.setUserId(rs.getInt("userId"));
                getApptById.setTitle(rs.getString("title"));
                getApptById.setDescription(rs.getString("description"));
                getApptById.setLocation(rs.getString("location"));
                getApptById.setContact(rs.getString("contact"));
                getApptById.setType(rs.getString("type"));
                getApptById.setUrl(rs.getString("url"));
                
                LocalDateTime startUTC = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = rs.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), zId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), zId);
                
                getApptById.setStart(startLocal);
                getApptById.setEnd(endLocal);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return getApptById;
    }

    public Appointment addAppointment(Appointment appointment) {
        String addAppointmentSQL = String.join(" ",
                "INSERT INTO appointment (customerId, userId, title, "
                            + "description, location, contact, type, url, start, end, "
                            + "createDate, createdBy, lastUpdate, lastUpdateBy) ",
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(addAppointmentSQL);
            stmt.setObject(1, appointment.getCustomerId());
            stmt.setObject(2, appointment.getUserId());
            stmt.setObject(3, appointment.getTitle());
            stmt.setObject(4, appointment.getDescription());
            stmt.setObject(5, appointment.getLocation());
            stmt.setObject(6, appointment.getContact());
            stmt.setObject(7, appointment.getType());
            stmt.setObject(8, appointment.getUrl());
            
            LocalDateTime startUTC = appointment.getStart().toLocalDateTime();
            LocalDateTime endUTC = appointment.getEnd().toLocalDateTime();
            stmt.setTimestamp(9, Timestamp.valueOf(startUTC));
            stmt.setTimestamp(10, Timestamp.valueOf(endUTC));
            
            stmt.setString(11, loggedUser.getUserName());
            stmt.setString(12, loggedUser.getUserName());
            stmt.executeUpdate();
        }
        catch (SQLException e){
        }
        return appointment;
    }
    public static void updateAppointment(Appointment appointment) {
        String updateApptSQL = String.join(" ",
                "UPDATE appointment",
                "SET customerId=?, userId=?, title=?, description=?, location=?," +
                "contact=?, type=?, url=?, start=?, end=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE appointmentId=?");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(updateApptSQL);
            stmt.setObject(1, appointment.getCustomerId());
            stmt.setObject(2, appointment.getUserId());
            stmt.setObject(3, appointment.getTitle());
            stmt.setObject(4, appointment.getDescription());
            stmt.setObject(5, appointment.getLocation());
            stmt.setObject(6, appointment.getContact());
            stmt.setObject(7, appointment.getType());
            stmt.setObject(8, appointment.getUrl());
            LocalDateTime startUTC = appointment.getStart().toLocalDateTime();
            LocalDateTime endUTC = appointment.getEnd().toLocalDateTime();
            stmt.setTimestamp(9, Timestamp.valueOf(startUTC));
            stmt.setTimestamp(10, Timestamp.valueOf(endUTC));
            
            stmt.setString(11, loggedUser.getUserName());
            stmt.setObject(12, appointment.getAppointmentId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
    public static void deleteAppointment(Appointment appointment) {
        String deleteAppointmentSQL = "DELETE FROM appointment WHERE appointmentId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(deleteAppointmentSQL);
            stmt.setObject(1, appointment.getAppointmentId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
}
