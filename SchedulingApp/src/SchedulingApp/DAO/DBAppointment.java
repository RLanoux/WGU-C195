package SchedulingApp.DAO;

import static Main.SchedulingApp.loggedUser;
import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Appointment;
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
public class DBAppointment {
    
    public static ObservableList<Appointment> getApptsByWeek() {
        ObservableList<Appointment> apptsByWeek = FXCollections.observableArrayList();
        String getApptsByWeekSQL = "SELECT * FROM appointment WHERE start = (SELECT DATE_ADD(NOW(), INTERVAL 7 DAY))";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptsByWeekSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Appointment apptByWeek = new Appointment();
                apptByWeek.setAppointmentId(rs.getInt("appointmentId"));
                apptByWeek.setCustomerId(rs.getInt("customerId"));
                apptByWeek.setUserId(rs.getInt("userId"));
                apptByWeek.setTitle(rs.getString("title"));
                apptByWeek.setDescription(rs.getString("description"));
                apptByWeek.setLocation(rs.getString("location"));
                apptByWeek.setContact(rs.getString("contact"));
                apptByWeek.setType(rs.getString("type"));
                apptByWeek.setUrl(rs.getString("url"));
                apptByWeek.setStart(rs.getTimestamp("start").toLocalDateTime());
                apptByWeek.setEnd(rs.getTimestamp("end").toLocalDateTime());
                apptsByWeek.add(apptByWeek);
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
        return apptsByWeek;
    }
    public static ObservableList<Appointment> getApptsByMonth() {
        ObservableList<Appointment> apptsByMonth = FXCollections.observableArrayList();
        String getApptsByMonthSQL = "SELECT * FROM appointment WHERE start = (SELECT LAST_DAY(NOW()))";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptsByMonthSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Appointment apptByMonth = new Appointment();
                apptByMonth.setAppointmentId(rs.getInt("appointmentId"));
                apptByMonth.setCustomerId(rs.getInt("customerId"));
                apptByMonth.setUserId(rs.getInt("userId"));
                apptByMonth.setTitle(rs.getString("title"));
                apptByMonth.setDescription(rs.getString("description"));
                apptByMonth.setLocation(rs.getString("location"));
                apptByMonth.setContact(rs.getString("contact"));
                apptByMonth.setType(rs.getString("type"));
                apptByMonth.setUrl(rs.getString("url"));
                apptByMonth.setStart(rs.getTimestamp("start").toLocalDateTime());
                apptByMonth.setEnd(rs.getTimestamp("end").toLocalDateTime());
                apptsByMonth.add(apptByMonth);
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
        return apptsByMonth;
    }
    public Appointment getApptById(int appointmentId) {
        String getApptByIdSQL = "SELECT * FROM appointment WHERE appointmentId = ?";
        Appointment getApptById = new Appointment();
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptByIdSQL);
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                getApptById.setAppointmentId(rs.getInt("appointmentId"));
                getApptById.setCustomerId(rs.getInt("customerId"));
                getApptById.setUserId(rs.getInt("userId"));
                getApptById.setTitle(rs.getString("title"));
                getApptById.setDescription(rs.getString("description"));
                getApptById.setLocation(rs.getString("location"));
                getApptById.setContact(rs.getString("contact"));
                getApptById.setType(rs.getString("type"));
                getApptById.setUrl(rs.getString("url"));
                getApptById.setStart(rs.getTimestamp("start").toLocalDateTime());
                getApptById.setEnd(rs.getTimestamp("end").toLocalDateTime());
            }
        }
        catch (SQLException e) {
        }
        return getApptById;
    }
    private int getMaxAppointmentId() {
        int maxApptId = 0;
        String maxApptIdSQL = "SELECT MAX(appointmentId) FROM appointment";
        
        try {
            Statement stmt = DB_CONN.createStatement();
            ResultSet rs = stmt.executeQuery(maxApptIdSQL);
            
            if (rs.next()) {
                maxApptId = rs.getInt(1);
            }
        }
        catch (SQLException e) {
        }
        return maxApptId + 1;
    }
    public int addAppointment(Appointment appointment) {
        String addAppointmentSQL = String.join(" ",
                "INSERT INTO appointment (appointmentId, customerId, userId, title,"
                            + "description, location, contact, type, url, start, end,"
                            + "createDate, createdBy, lastUpdate, lastUpdateBy)",
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)");
        
        int appointmentId = getMaxAppointmentId();
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(addAppointmentSQL);
            stmt.setInt(1, appointmentId);
            stmt.setInt(2, appointment.getCustomerId());
            stmt.setInt(3, appointment.getUserId());
            stmt.setString(4, appointment.getTitle());
            stmt.setString(5, appointment.getDescription());
            stmt.setString(6, appointment.getLocation());
            stmt.setString(7, appointment.getContact());
            stmt.setString(8, appointment.getType());
            stmt.setString(9, appointment.getUrl());
            stmt.setObject(10, appointment.getStart());
            stmt.setObject(11, appointment.getEnd());
            stmt.setString(12, loggedUser.getUserName());
            stmt.setString(13, loggedUser.getUserName());
            stmt.executeUpdate();
        }
        catch (SQLException e){
        }
        return appointmentId;
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
            stmt.setInt(1, appointment.getAppointmentId());
        }
        catch (SQLException e) {
        }
    }
}
