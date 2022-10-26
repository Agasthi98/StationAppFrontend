package com.example.fuelapp.service;

public class Api {

    private Api(){
    };
    public static final String API_URL = "http://10.0.2.2:8000/";

    public static StationService getStationService(){
        return RetrofitClient.getClient(API_URL).create(StationService.class);
    }
}
