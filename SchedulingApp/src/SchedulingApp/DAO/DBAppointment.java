package SchedulingApp.DAO;

import static Main.SchedulingApp.loggedUser;
import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Raymond Lanoux <rlanoux@wgu.edu>
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
                getWeeklyAppts.setCustName(new SimpleStringProperty(rs.getString("customerName")));
                getWeeklyAppts.setAppointmentId(new SimpleIntegerProperty(rs.getInt("appointmentId")));
                getWeeklyAppts.setCustomerId(new SimpleIntegerProperty(rs.getInt("customerId")));
                getWeeklyAppts.setUserId(new SimpleIntegerProperty(rs.getInt("userId")));
                getWeeklyAppts.setTitle(new SimpleStringProperty(rs.getString("title")));
                getWeeklyAppts.setDescription(new SimpleStringProperty(rs.getString("description")));
                getWeeklyAppts.setLocation(new SimpleStringProperty(rs.getString("location")));
                getWeeklyAppts.setContact(new SimpleStringProperty(rs.getString("contact")));
                getWeeklyAppts.setType(new SimpleStringProperty(rs.getString("type")));
                getWeeklyAppts.setUrl(new SimpleStringProperty(rs.getString("url")));
                
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
                getMonthlyAppts.setCustName(new SimpleStringProperty(rs.getString("customerName")));
                getMonthlyAppts.setAppointmentId(new SimpleIntegerProperty(rs.getInt("appointmentId")));
                getMonthlyAppts.setCustomerId(new SimpleIntegerProperty(rs.getInt("customerId")));
                getMonthlyAppts.setUserId(new SimpleIntegerProperty(rs.getInt("userId")));
                getMonthlyAppts.setTitle(new SimpleStringProperty(rs.getString("title")));
                getMonthlyAppts.setDescription(new SimpleStringProperty(rs.getString("description")));
                getMonthlyAppts.setLocation(new SimpleStringProperty(rs.getString("location")));
                getMonthlyAppts.setContact(new SimpleStringProperty(rs.getString("contact")));
                getMonthlyAppts.setType(new SimpleStringProperty(rs.getString("type")));
                getMonthlyAppts.setUrl(new SimpleStringProperty(rs.getString("url")));
                
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
                getApptById.setCustName(new SimpleStringProperty(rs.getString("customerName")));
                getApptById.setCustomerId(new SimpleIntegerProperty(rs.getInt("customerId")));
                getApptById.setUserId(new SimpleIntegerProperty(rs.getInt("userId")));
                getApptById.setTitle(new SimpleStringProperty(rs.getString("title")));
                getApptById.setDescription(new SimpleStringProperty(rs.getString("description")));
                getApptById.setLocation(new SimpleStringProperty(rs.getString("location")));
                getApptById.setContact(new SimpleStringProperty(rs.getString("contact")));
                getApptById.setType(new SimpleStringProperty(rs.getString("type")));
                getApptById.setUrl(new SimpleStringProperty(rs.getString("url")));
                
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
            stmt.setInt(1,  (appointment.getCustomerId()));
            stmt.setInt(2, (appointment.getUserId()));
            stmt.setString(3, (appointment.getTitle()));
            stmt.setString(4, (appointment.getDescription()));
            stmt.setString(5, (appointment.getLocation()));
            stmt.setString(6, (appointment.getContact()));
            stmt.setString(7, (appointment.getType()));
            stmt.setString(8, (appointment.getUrl()));
            
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
    public void updateAppointment(Appointment appointment) {
        String updateApptSQL = String.join(" ",
                "UPDATE appointment",
                "SET customerId=?, userId=?, title=?, description=?, location=?," +
                "contact=?, type=?, url=?, start=?, end=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE appointmentId=?");
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(updateApptSQL);
            stmt.setInt(1, appointment.getCustomerId());
            stmt.setInt(2, appointment.getUserId());
            stmt.setString(3, appointment.getTitle());
            stmt.setString(4, appointment.getDescription());
            stmt.setString(5, appointment.getLocation());
            stmt.setString(6, appointment.getContact());
            stmt.setString(7, appointment.getType());
            stmt.setString(8, appointment.getUrl());
            stmt.setObject(9, appointment.getStart());
            stmt.setObject(10, appointment.getEnd());
            stmt.setString(11, loggedUser.getUserName());
            stmt.setInt(12, appointment.getAppointmentId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        }
    }
    public void deleteAppointment(Appointment appointment) {
        String deleteAppointmentSQL = "DELETE FROM appointment WHERE appointmentId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(deleteAppointmentSQL);
            stmt.setInt(1, (appointment.getAppointmentId()));
        }
        catch (SQLException e) {
        }
    }
}
