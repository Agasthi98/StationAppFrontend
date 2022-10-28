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

public class OwnerAddDiesel extends AppCompatActivity {

    EditText dieselLiters,dieselStartTime,dieselEndTime,dieselQueueLength;
    Button addDiesel;
    private static final String Logger = "OwnerAddDiesel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_diesel);

        dieselLiters = (EditText) findViewById(R.id.txtAddDieselLiters);
        dieselStartTime = (EditText) findViewById(R.id.txtDieselStartTime);
        dieselEndTime = (EditText) findViewById(R.id.txtDieselEndTime);
        dieselQueueLength = (EditText) findViewById(R.id.txtDieselQueueLength);
        addDiesel = (Button) findViewById(R.id.btnAddDiesel);

        addDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dLiters = dieselLiters.getText().toString();
                String dStartTime = dieselStartTime.getText().toString();
                String dEndTime = dieselEndTime.getText().toString();
                String dQueueLength = dieselQueueLength.getText().toString();

                String StationName = userNAME;
                String StationNumber = phoneNUMBER;
                String StationLocation = VTYPE;

                if(dLiters.equals("") || dStartTime.equals("") || dEndTime.equals("") || dQueueLength.equals("")){
                    Toast.makeText(OwnerAddDiesel.this, "Fill All the field", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("details:===="+StationName+StationNumber+StationLocation);
                    addDiesel(dLiters,dStartTime,dEndTime,dQueueLength,StationName,StationNumber,StationLocation);
                    Intent intent = new Intent(getApplicationContext(), ShedOwnerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addDiesel(String Liters,String ArrivalTime,String EndTime, String QueueLength, String StationName,String StationNumber,String StationLocation){
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.createDiesel(Liters,ArrivalTime,EndTime,QueueLength,StationName,StationNumber,StationLocation);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(OwnerAddDiesel.this, "Fuel Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}