package com.example.fuelapp.service;


import com.example.fuelapp.domain.FuelModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StationService {

    //Api route
    @FormUrlEncoded
    @POST("api/diesel")
    Call<FuelModel> createDiesel(@Field("liters") String Liters,
                                  @Field("arrivalTime") String ArrivalTime,
                                  @Field("endTime") String EndTime,
                                  @Field("queueLength") String QueueLength,
                                  @Field("stationNumber") String StationNumber,
                                  @Field("stationName") String StationName,
                                  @Field("stationLocation") String StationLocation
    );

    @FormUrlEncoded
    @POST("api/petrol")
    Call<FuelModel> createPetrol(@Field("liters") String Liters,
                                  @Field("arrivalTime") String ArrivalTime,
                                  @Field("endTime") String EndTime,
                                  @Field("queueLength") String QueueLength,
                                  @Field("stationNumber") String StationNumber,
                                  @Field("stationName") String StationName,
                                  @Field("stationLocation") String StationLocation
    );

    @FormUrlEncoded
    @POST("api/petrol/display")
    Call<FuelModel> getPetrol(@Field("StationNumber") String StationNumber
    );

}
