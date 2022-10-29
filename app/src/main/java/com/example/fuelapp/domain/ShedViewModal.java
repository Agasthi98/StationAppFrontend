package com.example.fuelapp.domain;

import com.example.fuelapp.Database.UserInfo;

public class ShedViewModal {
        String userName;
        String phoneNo;
        String role;
        String vehicle;
        String password;
        int id;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getRole() {
        return role;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
