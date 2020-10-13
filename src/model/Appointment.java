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
public class Appointment {

    // Properties for Appointment Class
    private final IntegerProperty customerId;
    private final StringProperty customerName;
    private final IntegerProperty addressId;
    private final StringProperty address;
    private final IntegerProperty cityId;
    private final IntegerProperty countryId;
    private final StringProperty country;
    private final StringProperty phone;

    // Constructor to Instantiate the Appointment Class
    public Appointment() {
        this.customerId = new SimpleIntegerProperty();
        this.customerName = new SimpleStringProperty();
        this.addressId = new SimpleIntegerProperty();
        this.address = new SimpleStringProperty();
        this.cityId = new SimpleIntegerProperty();
        this.countryId = new SimpleIntegerProperty();
        this.country = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
    }

    // Getters for Appointment Property Values
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

    public int getCityId() {
        return cityId.get();
    }

    public int getCountryId() {
        return countryId.get();
    }

    public String getCountry() {
        return country.get();
    }

    public String getPhone() {
        return phone.get();
    }

    // Getters for Appointment Properties
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

    public IntegerProperty getCityIdProperty() {
        return cityId;
    }

    public IntegerProperty getCountryIdProperty() {
        return countryId;
    }

    public StringProperty getCountrPropertyy() {
        return country;
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    // Setters for Appointment Properties
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
