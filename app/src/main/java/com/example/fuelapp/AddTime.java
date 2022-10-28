package com.example.fuelapp;

import static com.example.fuelapp.LoginActivity.VTYPE;
import static com.example.fuelapp.LoginActivity.phoneNUMBER;
import static com.example.fuelapp.LoginActivity.userNAME;

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

public class AddTime extends AppCompatActivity {

    EditText startTime,endTime;
    Button addTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        startTime = (EditText) findViewById(R.id.txtAddStartTimeShed);
        endTime = (EditText) findViewById(R.id.txtAddEndTimeShed);
        addTime = (Button) findViewById(R.id.btnAddShedTime);

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTime = startTime.getText().toString();
                String eTime = endTime.getText().toString();

                String StationName = userNAME;
                String StationNumber = phoneNUMBER;
                String StationLocation = VTYPE;

                if(sTime.equals("") || eTime.equals("")){
                    Toast.makeText(AddTime.this, "Fill All the field", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("details:===="+StationName+StationNumber+StationLocation);
                    addShedTime(sTime,eTime,StationName,StationNumber,StationLocation);
                    Intent intent = new Intent(getApplicationContext(), ShedOwnerActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void addShedTime(String ArrivalTime,String EndTime, String StationName,String StationNumber,String StationLocation){
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.createShedAvailableTime(ArrivalTime,EndTime,StationName,StationNumber,StationLocation);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddTime.this, "Shed Time Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}