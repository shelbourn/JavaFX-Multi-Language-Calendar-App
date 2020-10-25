/*
 * Defines the DBAppointType Class
 * Contains SQL operations related to AppointmentType Class
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentType;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBAppointmentType {

    public DBAppointmentType() {
    }

    // Retrieves all appointment type records from the database and assigns them to an Observable Array
    public static ObservableList<AppointmentType> getAllAppointmentTypes() {
        ObservableList<AppointmentType> appointmentTypesList = FXCollections.observableArrayList();

        try {

            String qGetTypes = "SELECT DISTINCT type from appointment";

            PreparedStatement psGetTypes = DBConn.startConnection().prepareStatement(qGetTypes);

            ResultSet rsGetTypes = psGetTypes.executeQuery();

            // Building out the Appointment Type Objects
            while (rsGetTypes.next()) {
                String type = rsGetTypes.getString("type");

                // Creating Appointment Type Object with data
                AppointmentType a = new AppointmentType(type);
                appointmentTypesList.add(a);
            }
            System.out.println("Database Query Successful!\nAppointment Types list retrieved!");
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }

        return appointmentTypesList;
    }

}
