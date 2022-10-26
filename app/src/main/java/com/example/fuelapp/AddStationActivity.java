package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelapp.domain.Station;
import com.example.fuelapp.service.StationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStationActivity extends AppCompatActivity {

    StationService stationService;
    EditText shedName, stationPhoneNo, fuelStatus, fuelType, stationLocation;
    Button addStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);

        shedName = (EditText) findViewById(R.id.txtShedName);
        stationPhoneNo = (EditText) findViewById(R.id.txtFuelPhoneNo);
        fuelStatus = (EditText) findViewById(R.id.txtFuelStatus);
        fuelType = (EditText) findViewById(R.id.txtFuelType);
        stationLocation = (EditText) findViewById(R.id.txtShedLocation);
        addStation = (Button) findViewById(R.id.btnAddStation);


        addStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Station s = new Station();

                s.setStationName(shedName.getText().toString());
                s.setStationPhoneNo(stationPhoneNo.getText().toString());
                s.setFuelStatus(fuelStatus.getText().toString());
                s.setFuelType(fuelType.getText().toString());
                s.setStationLocation(stationLocation.getText().toString());


                addStation(s);
                System.out.println(s);
                Intent intent = new Intent(getApplicationContext(),ShedOwnerActivity.class);
                startActivity(intent);
            }
        });
    }


    public void addStation(Station station){
        Call<Station> call = stationService.addStation(station);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddStationActivity.this, "Fuel Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}