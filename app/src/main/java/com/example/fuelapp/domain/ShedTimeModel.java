package com.example.fuelapp.domain;

public class ShedTimeModel {

    private String arrivalTime;
    private String endTime;
    private String stationNumber;
    private String stationName;
    private String stationLocation;


    public ShedTimeModel(String arrivalTime, String endTime, String stationNumber, String stationName, String stationLocation) {
        this.arrivalTime = arrivalTime;
        this.endTime = endTime;
        this.stationNumber = stationNumber;
        this.stationName = stationName;
        this.stationLocation = stationLocation;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationLocation() {
        return stationLocation;
    }
}
