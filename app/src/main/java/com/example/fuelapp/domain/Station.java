package com.example.fuelapp.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("stationName")
    @Expose
    private String stationName;

    @SerializedName("stationPhoneNo")
    @Expose
    private String stationPhoneNo;

    @SerializedName("fuelStatus")
    @Expose
    private String fuelStatus;

    @SerializedName("fuelType")
    @Expose
    private String fuelType;

    @SerializedName("stationLocation")
    @Expose
    private String stationLocation;

    public Station(){

    }

    public Station(String id, String stationName, String stationPhoneNo, String fuelStatus, String fuelType, String stationLocation) {
        this.id = id;
        this.stationName = stationName;
        this.stationPhoneNo = stationPhoneNo;
        this.fuelStatus = fuelStatus;
        this.fuelType = fuelType;
        this.stationLocation = stationLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationPhoneNo() {
        return stationPhoneNo;
    }

    public String getFuelStatus() {
        return fuelStatus;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationPhoneNo(String stationPhoneNo) {
        this.stationPhoneNo = stationPhoneNo;
    }

    public void setFuelStatus(String fuelStatus) {
        this.fuelStatus = fuelStatus;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }
}
