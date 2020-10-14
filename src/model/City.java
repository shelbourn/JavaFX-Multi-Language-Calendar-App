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
    private int countryId;
    private String country;

    //City Class Constructors
    public City() {
    }

    public City(int cityId, String city, String country) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
    }

    public City(int cityId, String city, int countryId, String country) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.country = country;
    }

    // Getters for City Class
    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    // Setters for City Class
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
