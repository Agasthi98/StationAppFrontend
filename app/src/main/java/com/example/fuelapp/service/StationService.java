package com.example.fuelapp.service;


import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.domain.QueueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface StationService {

    /*
    add diesel api route
     */
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

    /*
    get diesel route
     */
    @FormUrlEncoded
    @POST("api/diesel/display")
    Call<FuelModel> getDiesel(@Field("StationNumber") String StationNumber
    );

    /*
    add petrol route
     */
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

    /*
    get petrol details route
     */
    @FormUrlEncoded
    @POST("api/petrol/display")
    Call<FuelModel> getPetrol(@Field("StationNumber") String StationNumber
    );

    /*
    add shed available time route
     */
    @FormUrlEncoded
    @POST("api/time")
    Call<FuelModel> createShedAvailableTime(@Field("arrivalTime") String Liters,
                                 @Field("endTime") String ArrivalTime,
                                 @Field("stationName") String StationName,
                                 @Field("stationNumber") String StationNumber,
                                 @Field("stationLocation") String StationLocation
    );

    /*
    shed available time display route
     */
    @FormUrlEncoded
    @POST("api/time/display")
    Call<FuelModel> getShedOpenTime(@Field("StationNumber") String StationNumber
    );

    /*
    delete petrol details route
     */
    @FormUrlEncoded
    @POST("api/petrol/remove")
    Call<FuelModel> deletePetrol(@Field("StationNumber") String StationNumber
    );

    /*
    delete diesel details route
     */
    @FormUrlEncoded
    @POST("api/diesel/remove")
    Call<FuelModel> deleteDiesel(@Field("StationNumber") String StationNumber
    );

    /*
    search shed location route
     */
    @GET("api/petrol/search/{location}")
    Call<List<FuelModel>> searchLocation(@Path("location") String location);

    /*
    user add to queue route
     */
    @FormUrlEncoded
    @POST("api/queue")
    Call<QueueModel> addToQueue(@Field("type") String type,
                                @Field("queue") String queue,
                                @Field("shed") String shed
    );

    /*
    get user queue count route
     */
    @FormUrlEncoded
    @POST("api/queue/display")
    Call<QueueModel> getQueue(@Field("shed") String shed
    );

    /*
    edit the shed open and close time route
     */
    @FormUrlEncoded
    @POST("api/time/remove")
    Call<FuelModel> deleteTime(@Field("StationNumber") String StationNumber
    );

}
