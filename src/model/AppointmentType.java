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
public class AppointmentType {

    // Properties for AppointmentType class
    private String type;

    // Constructor for AppointmentType Class
    public AppointmentType(String type) {
        this.type = type;
    }

    // Getters for AppointmentType class
    public String getType() {
        return type;
    }

    // Setters for AppointmentType class
    public void setType(String type) {
        this.type = type;
    }

    // Overriding the default toString() behavior
    @Override
    public String toString() {
        return (type);
    }

}
