/*
 * Defines the Appointment Class
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalTime start;
    private LocalTime end;
    private String userName;
    private String customerName;
    private LocalDate date;

    // Empty constructor for Appointment Class
    public Appointment() {
    }

    // Constructors for Appointment Class
    public Appointment(int appointmentId, int customerId, int userId, String type, LocalDate date, LocalTime start, LocalTime end, String userName, String customerName) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.type = type;
        this.date = date;
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

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getUserName() {
        return userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getDate() {
        return date;
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

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
