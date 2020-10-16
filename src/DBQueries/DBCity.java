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

            while (rs.next()) {
                int cityId = rs.getInt("cityId");
                String city = rs.getString("city");
                String country = rs.getString("country");
                City c = new City(cityId, city, country);
                allCities.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCities;
    }

}
