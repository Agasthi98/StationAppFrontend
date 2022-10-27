package com.example.fuelapp.service;


import com.example.fuelapp.domain.FuelModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StationService {

    //Api route
    @FormUrlEncoded
    @POST("api/station")
    Call<FuelModel> createStation(@Field("stationName") String stationName,
                                  @Field("stationPhoneNo") String stationPhoneNo,
                                  @Field("fuelStatus") String fuelStatus,
                                  @Field("fuelType") String fuelType,
                                  @Field("stationLocation") String stationLocation
    );

}
