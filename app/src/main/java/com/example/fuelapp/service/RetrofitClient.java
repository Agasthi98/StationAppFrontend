package com.example.fuelapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    //initialize a connectivity url backend and frontend
    public static final String API_URL = "http://10.0.2.2:8000/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
