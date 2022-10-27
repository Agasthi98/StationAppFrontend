package com.example.fuelapp.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Create Station model
public class FuelModel {


    private String id;

    private String stationName;

    private String stationNumber;

    private String liters;

    private String arrivalTime;

    private String endTime;

    private String queueLength;

    private String stationLocation;

    public FuelModel(String id, String stationName, String stationNumber, String liters, String arrivalTime, String endTime, String queueLength, String stationLocation) {
        this.id = id;
        this.stationName = stationName;
        this.stationNumber = stationNumber;
        this.liters = liters;
        this.arrivalTime = arrivalTime;
        this.endTime = endTime;
        this.queueLength = queueLength;
        this.stationLocation = stationLocation;
    }

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getLiters() {
        return liters;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getQueueLength() {
        return queueLength;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public void setLiters(String liters) {
        this.liters = liters;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setQueueLength(String queueLength) {
        this.queueLength = queueLength;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }
}
