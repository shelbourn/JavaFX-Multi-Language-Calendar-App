/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

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
    private Timestamp start;
    private Timestamp end;
    private String userName;
    private String customerName;

    // Empty constructor for Appointment Class
    public Appointment() {
    }

    // Constructors for Appointment Class
    public Appointment(int appointmentId, int customerId, int userId, String type, Timestamp start, Timestamp end, String userName, String customerName) {
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

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
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

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
