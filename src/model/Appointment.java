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
public class Appointment {

    // Properties for Appointment Class
    private int appointmentId;
    private int customerId;
    private int userId;
    private String type;
    private String start;
    private String end;
    private String userName;
    private String customerName;

    // Empty constructor for Appointment Class
    public Appointment() {
    }

    // Constructor for Appointment Class
    public Appointment(int appointmentId, int customerId, int userId, String type, String start, String end, String userName, String customerName) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.type = type;
        this.start = start;
        this.end = end;
        this.userName = userName;
        this.customerName = customerName;
    }

    // Getters for Appointment Class
    public int getAppointmentId() {
        return appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getUserName() {
        return userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    // Setters for Appointment Class
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
