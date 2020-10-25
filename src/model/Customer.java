/*
 * Defines the Customer Class
 */
package model;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public final class Customer {

    // Customer Class Properties
    private int customerId;
    private String customerName;
    private int addressId;
    private String address;
    private String phone;
    private int cityId;
    private String city;
    private int countryId;
    private String country;

    // Customer Class Constructors
    public Customer() {
    }

    public Customer(int customerId, String customerName, int addressId, String address, String phone, int cityId, String city, int countryId, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.address = address;
        this.phone = phone;
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.country = country;
    }

    // Getters for Customer Class
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

    public String getPhone() {
        return phone;
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

    public void setPhone(String phone) {
        this.phone = phone;
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

    // Overrides the default toString() behavior
    @Override
    public String toString() {
        return (customerName);
    }
}
