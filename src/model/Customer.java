/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public final class Customer {

    // Customer Class Properties
    private final IntegerProperty customerId = new SimpleIntegerProperty();
    private final StringProperty customerName = new SimpleStringProperty();
    private final IntegerProperty addressId = new SimpleIntegerProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final IntegerProperty cityId = new SimpleIntegerProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final IntegerProperty countryId = new SimpleIntegerProperty();
    private final StringProperty country = new SimpleStringProperty();

    // Customer Class Constructors
    public Customer() {
    }

    public Customer(int customerId, String customerName, int addressId, String address, String phone, int cityId, String city, int countryId, String country) {
        setCustomerId(customerId);
        setCustomerName(customerName);
        setAddressId(addressId);
        setAddress(address);
        setPhone(phone);
        setCityId(cityId);
        setCity(city);
        setCountryId(countryId);
        setCountry(country);
    }

    // Getters for Customer Class Property Values
    public int getCustomerId() {
        return customerId.get();
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public int getAddressId() {
        return addressId.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public int getCityId() {
        return cityId.get();
    }

    public String getCity() {
        return city.get();
    }

    public int getCountryId() {
        return countryId.get();
    }

    public String getCountry() {
        return country.get();
    }

    //Getters for Customer Class Properties
    public IntegerProperty getCustomerIdProperty() {
        return customerId;
    }

    public StringProperty getCustomerNameProperty() {
        return customerName;
    }

    public IntegerProperty getAddressIdProperty() {
        return addressId;
    }

    public StringProperty getAddressProperty() {
        return address;
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    public IntegerProperty getCityIdProperty() {
        return cityId;
    }

    public StringProperty getCityProperty() {
        return city;
    }

    public IntegerProperty getCountryIdProperty() {
        return countryId;
    }

    public StringProperty getCountryProperty() {
        return country;
    }

    // Setters for Customer Class
    public void setCustomerId(int value) {
        customerId.set(value);
    }

    public void setCustomerName(String value) {
        customerName.set(value);
    }

    public void setAddressId(int value) {
        addressId.set(value);
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public void setCityId(int value) {
        cityId.set(value);
    }

    public void setCity(String value) {
        city.set(value);
    }

    public void setCountryId(int value) {
        countryId.set(value);
    }

    public void setCountry(String value) {
        country.set(value);
    }

    public void setPhone(String value) {
        phone.set(value);
    }

}
