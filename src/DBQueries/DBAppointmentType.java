/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentType;
import model.User;
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

            while (rsGetTypes.next()) {
                String type = rsGetTypes.getString("type");

                AppointmentType a = new AppointmentType(type);
                appointmentTypesList.add(a);
            }
            System.out.println("Database Query Successful!\nAppointment Types list retrieved!");
        } catch (SQLException e) {
            System.out.println("Database Query Failed!");
            e.printStackTrace();
        }

        return appointmentTypesList;
    }

}
