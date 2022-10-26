package com.example.fuelapp;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/")
    Call<AddStationActivity> addStation(@Body HashMap<String, String>map);


}
