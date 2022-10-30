package com.example.fuelapp;

import static com.example.fuelapp.Database.ShedAdapter.SHED_PHONE;
import static com.example.fuelapp.LoginActivity.phoneNUMBER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.service.RetrofitClient;
import com.example.fuelapp.service.StationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShedView extends AppCompatActivity {

    TextView shedName,shedPhoneNo,fuelQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_view);

        shedName = (TextView) findViewById(R.id.txtShedName);
        shedPhoneNo = (TextView) findViewById(R.id.txtShedPhoneNo);
        fuelQueue = (TextView) findViewById(R.id.txtShedQueue);

        System.out.println(SHED_PHONE);
        displayPetrol();
    }

    public void displayPetrol(){

        TextView pQueue =  findViewById(R.id.txtShedName);
        TextView SLocation = findViewById(R.id.txtShedPhoneNo);
        TextView pdisplayValue =  findViewById(R.id.txtShedQueue);

        System.out.println(SHED_PHONE);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getPetrol(SHED_PHONE);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    FuelModel Fmodel = response.body();
                    pQueue.setText((String.format("Shed Name: %s", Fmodel.getStationName())));
                    SLocation.setText((String.format("Shed No: %s", Fmodel.getStationNumber())));
                    pdisplayValue.setText((String.format("Queue length: %s", Fmodel.getQueueLength())));
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}