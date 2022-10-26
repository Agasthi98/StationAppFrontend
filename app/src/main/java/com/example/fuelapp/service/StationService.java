package com.example.fuelapp.service;


import com.example.fuelapp.domain.Station;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StationService {
    @POST("/")
    Call<Station> addStation(@Body Station station);
}
