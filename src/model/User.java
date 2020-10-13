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
public class User {

    // Properties for User Class
    private int userId;
    private String userName;
    private String password;

    // Empty Constructor for User Class
    public User() {
    }

    // Constructor for User Class
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    // Getters for User Class
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    // Setters for User Class
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
