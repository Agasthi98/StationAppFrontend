package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.service.RetrofitClient;
import com.example.fuelapp.service.StationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStationActivity extends AppCompatActivity {


    EditText shedName;
    EditText stationPhoneNo;
    EditText fuelStatus;
    EditText fuelType;
    EditText stationLocation;
    Button addStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);

        shedName = (EditText) findViewById(R.id.txtStationName);
        stationPhoneNo = (EditText) findViewById(R.id.txtFuelPhoneNo);
        fuelStatus = (EditText) findViewById(R.id.txtFuelStatus);
        fuelType = (EditText) findViewById(R.id.txtFuelType);
        stationLocation = (EditText) findViewById(R.id.txtShedLocation);
        addStation = (Button) findViewById(R.id.btnAddStation);


        //add station on click listner
        addStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shed_Name = shedName.getText().toString();
                String shed_Pno = stationPhoneNo.getText().toString();
                String fuel_status = fuelStatus.getText().toString();
                String fuel_type = fuelType.getText().toString();
                String station_loc = stationLocation.getText().toString();

                System.out.println(""+shed_Name+"\n"+shed_Pno+"\n"+fuel_status+"\n"+fuel_type+"\n"+station_loc);
                if(shed_Name.equals("") || shed_Pno.equals("") || fuel_status.equals("")|| fuel_type.equals("")|| station_loc.equals("")){
                    Toast.makeText(AddStationActivity.this, "Fill the blanks", Toast.LENGTH_SHORT).show();
                }else{
                    addStation(shed_Name,shed_Pno,fuel_status,fuel_type,station_loc);
                    System.out.println("run");
                    Intent intent = new Intent(getApplicationContext(),ShedOwnerActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    //Add station method
    public void addStation(String sName,String stationPhoneNo, String fStatus, String fType, String sLocation){
        System.out.println("Agasthi"+sName+stationPhoneNo+fStatus+fType+sLocation);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.createStation(sName, stationPhoneNo,fStatus,fType, sLocation);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddStationActivity.this, "Fuel Added successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ShedOwnerActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}