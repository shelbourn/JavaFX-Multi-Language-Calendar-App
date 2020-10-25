/*
 * Defines the DBCity Class
 * Contains SQL operations related to City Class
 */
package DBQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.City;
import utils.DBConn;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class DBCity {

    public static ObservableList<City> getAllCities() {
        ObservableList<City> allCities = FXCollections.observableArrayList();

        try {

            String q = "SELECT cityId, city, country from city, country where city.countryId = country.countryId";

            PreparedStatement ps = DBConn.startConnection().prepareStatement(q);

            ResultSet rs = ps.executeQuery();

            // Building out the City Objects
            while (rs.next()) {
                int cityId = rs.getInt("cityId");
                String city = rs.getString("city");
                String country = rs.getString("country");

                // Creating City Object with data
                City c = new City(cityId, city, country);
                allCities.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Database Query Failed!");
            e.printStackTrace();
        }
        return allCities;
    }

}
