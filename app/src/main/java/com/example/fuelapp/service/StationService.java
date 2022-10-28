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
                                  @Field("stationName") String StationName,
                                  @Field("stationNumber") String StationNumber,
                                  @Field("stationLocation") String StationLocation
    );

    @FormUrlEncoded
    @POST("api/diesel/display")
    Call<FuelModel> getDiesel(@Field("StationNumber") String StationNumber
    );

    @FormUrlEncoded
    @POST("api/petrol")
    Call<FuelModel> createPetrol(@Field("liters") String Liters,
                                  @Field("arrivalTime") String ArrivalTime,
                                  @Field("endTime") String EndTime,
                                  @Field("queueLength") String QueueLength,
                                  @Field("stationName") String StationName,
                                  @Field("stationNumber") String StationNumber,
                                  @Field("stationLocation") String StationLocation
    );

    @FormUrlEncoded
    @POST("api/petrol/display")
    Call<FuelModel> getPetrol(@Field("StationNumber") String StationNumber
    );

    @FormUrlEncoded
    @POST("api/time")
    Call<FuelModel> createShedAvailableTime(@Field("arrivalTime") String Liters,
                                 @Field("endTime") String ArrivalTime,
                                 @Field("stationName") String StationName,
                                 @Field("stationNumber") String StationNumber,
                                 @Field("stationLocation") String StationLocation
    );

}
