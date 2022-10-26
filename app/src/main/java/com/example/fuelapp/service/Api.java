package com.example.fuelapp.service;

public class Api {

    private Api(){
    };
    public static final String API_URL = "http://192.168.1.7:8000/api/station";

    public static StationService getStationService(){
        return RetrofitClient.getClient(API_URL).create(StationService.class);
    }
}
