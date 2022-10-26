package com.example.fuelapp.service;


import com.example.fuelapp.domain.Station;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StationService {

    @FormUrlEncoded
    @POST("api/station/")
    Call<Station> addStation(
            @Field("StationName") String StationName,
            @Field("StationPhoneNo") String StationPhoneNo,
            @Field("FuelStatus") String FuelStatus,
            @Field("FuelType") String FuelType,
            @Field("StationLocation") String StatusLocation
    );

}
