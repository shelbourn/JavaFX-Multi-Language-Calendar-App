/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class City {

    // Properties for City Class
    private int cityId;
    private String city;

    // Empty Constructor for City Class
    public City() {
    }

    // City Class Constructor
    public City(int cityId, String city) {
        this.cityId = cityId;
        this.city = city;
    }

    // Getters for City Class
    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    // Setters for City Class
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
