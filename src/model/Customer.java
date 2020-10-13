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
public class Customer {

    // Customer Class Properties
    private int customerId;
    private String customerName;
    private int addressId;
    private String address;
    private int cityId;
    private String city;
    private int countryId;
    private String country;
    private int phone;

    // Empty Constructor for Customer Class
    public Customer() {
    }

    // Customer Class Constructor
    public Customer(int customerId, String customerName, int addressId, String address, int cityId, String city, int countryId, String country, int phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.address = address;
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.country = country;
        this.phone = phone;
    }

    //Getters for Customer Class
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

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

    public int getPhone() {
        return phone;
    }

    // Setters for Customer Class
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
