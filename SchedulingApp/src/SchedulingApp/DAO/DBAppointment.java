package SchedulingApp.DAO;

import static SchedulingApp.DAO.DBConnector.DB_CONN;
import SchedulingApp.Model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Raymond Lanoux <rlanoux@wgu.edu>
 */
public class DBAppointment {
    
    public static ObservableList<Appointment> getApptsByWeek() {
        ObservableList<Appointment> apptsByWeek = FXCollections.observableArrayList();
        String getApptsByWeekSQL = "SELECT * FROM appointment WHERE start = ?";
        
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
                apptByWeek.setStart(rs.getTimestamp("start"));
                apptByWeek.setEnd(rs.getTimestamp("end"));
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }
    
}
