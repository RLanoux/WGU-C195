package SchedulingApp.DAO;

import static Main.SchedulingApp.loggedUser;
import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
public class DBAppointment {
    
    public static ObservableList<Appointment> getApptsByWeek() {
        ObservableList<Appointment> apptsByWeek = FXCollections.observableArrayList();
        String getApptsByWeekSQL = "SELECT customer.customerName, appointment.* FROM customer"
                + "RIGHT JOIN appointment ON customer.customerId = appointment.customerId"
                + "WHERE start BETWEEN NOW() AND (SELECT ADDDATE(NOW(), INTERVAL 7 DAY))";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptsByWeekSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                apptsByWeek.add(new Appointment(
                        new SimpleStringProperty(rs.getString("customerName")), 
                        new SimpleIntegerProperty(rs.getInt("appointmentId")),
                        new SimpleIntegerProperty(rs.getInt("customerId")),
                        new SimpleIntegerProperty(rs.getInt("userId")),
                        new SimpleStringProperty(rs.getString("title")),
                        new SimpleStringProperty(rs.getString("description")),
                        new SimpleStringProperty(rs.getString("location")),
                        new SimpleStringProperty(rs.getString("contact")),
                        new SimpleStringProperty(rs.getString("type")),
                        new SimpleStringProperty(rs.getString("url")),
                        new SimpleObjectProperty(rs.getTimestamp("start").toLocalDateTime()),
                        new SimpleObjectProperty(rs.getTimestamp("end").toLocalDateTime())
                ));
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
        return apptsByWeek;
    }
    public static ObservableList<Appointment> getApptsByMonth() {
        ObservableList<Appointment> apptsByMonth = FXCollections.observableArrayList();
        String getApptsByMonthSQL = "SELECT customer.customerName, appointment.* FROM customer"
                + "RIGHT JOIN appointment ON customer.customerId = appointment.customerId"
                + "WHERE start BETWEEN NOW() AND (SELECT LAST_DAY(NOW()))";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptsByMonthSQL);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                apptsByMonth.add(new Appointment(
                        new SimpleStringProperty(rs.getString("customerName")), 
                        new SimpleIntegerProperty(rs.getInt("appointmentId")),
                        new SimpleIntegerProperty(rs.getInt("customerId")),
                        new SimpleIntegerProperty(rs.getInt("userId")),
                        new SimpleStringProperty(rs.getString("title")),
                        new SimpleStringProperty(rs.getString("description")),
                        new SimpleStringProperty(rs.getString("location")),
                        new SimpleStringProperty(rs.getString("contact")),
                        new SimpleStringProperty(rs.getString("type")),
                        new SimpleStringProperty(rs.getString("url")),
                        new SimpleObjectProperty(rs.getTimestamp("start").toLocalDateTime()),
                        new SimpleObjectProperty(rs.getTimestamp("end").toLocalDateTime())
                ));
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
        return apptsByMonth;
    }
    public Appointment getApptById(int appointmentId) {
        String getApptByIdSQL = "SELECT * FROM appointment WHERE appointmentId = ?";
        
        try {
            PreparedStatement stmt = DB_CONN.prepareStatement(getApptByIdSQL);
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Appointment getApptById = new Appointment(
                new SimpleStringProperty(rs.getString("customerName")), 
                new SimpleIntegerProperty(rs.getInt("appointmentId")),
                new SimpleIntegerProperty(rs.getInt("customerId")),
                new SimpleIntegerProperty(rs.getInt("userId")),
                new SimpleStringProperty(rs.getString("title")),
                new SimpleStringProperty(rs.getString("description")),
                new SimpleStringProperty(rs.getString("location")),
                new SimpleStringProperty(rs.getString("contact")),
                new SimpleStringProperty(rs.getString("type")),
                new SimpleStringProperty(rs.getString("url")),
                new SimpleObjectProperty(rs.getTimestamp("start").toLocalDateTime()),
                new SimpleObjectProperty(rs.getTimestamp("end").toLocalDateTime())
                );
            }
        }
        catch (SQLException e) {
        }
        return getApptById;
    }

    public int addAppointment(Appointment appointment) {
        String addAppointmentSQL = String.join(" ",
                "INSERT INTO appointment (customerId, userId, title,"
                            + "description, location, contact, type, url, start, end,"
                            + "createDate, createdBy, lastUpdate, lastUpdateBy)",
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)");
        
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
