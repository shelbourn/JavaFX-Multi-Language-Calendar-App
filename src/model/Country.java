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
public class Country {

    // Properties for Country Class
    private int countryId;
    private String country;

    // Empty Constructor for Country Class
    public Country() {
    }

    // Constructor for Country Class
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    // Getters for Country Class
    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    // Setters for Country Class
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
