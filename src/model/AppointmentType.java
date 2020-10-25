/*
 * Defines the AppointmentType Class
 */
package model;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class AppointmentType {

    // Properties for AppointmentType class
    private String type;
    private int typeCount;

    // Constructor for AppointmentType Class
    public AppointmentType(String type) {
        this.type = type;
    }

    public AppointmentType(String type, int typeCount) {
        this.type = type;
        this.typeCount = typeCount;
    }

    // Getters for AppointmentType class
    public String getType() {
        return type;
    }

    public int getTypeCount() {
        return typeCount;
    }

    // Setters for AppointmentType class
    public void setType(String type) {
        this.type = type;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    // Overrides the default toString() behavior
    @Override
    public String toString() {
        return (type);
    }

}
