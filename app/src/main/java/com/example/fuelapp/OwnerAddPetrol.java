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

public class OwnerAddPetrol extends AppCompatActivity {

    EditText petrolLiters,petrolStartTime,petrolEndTime,petrolQueueLength;
    Button addPetrol;
    private static final String Logger = "OwnerAddPetrol";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_petrol);



        petrolLiters = (EditText) findViewById(R.id.txtAddPetrolLiters);
        petrolStartTime = (EditText) findViewById(R.id.txtPetrolStartTime);
        petrolEndTime = (EditText) findViewById(R.id.txtPetrolEndTime);
        petrolQueueLength = (EditText) findViewById(R.id.txtPetrolQueueLength);
        addPetrol = (Button) findViewById(R.id.btnAddPetrol);



        addPetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pLiters = petrolLiters.getText().toString();
                String pStartTime = petrolStartTime.getText().toString();
                String pEndTime = petrolEndTime.getText().toString();
                String pQueueLength = petrolQueueLength.getText().toString();

                String StationName = userNAME;
                String StationNumber = phoneNUMBER;
                String StationLocation = VTYPE;

                if(pLiters.equals("") || pStartTime.equals("") || pEndTime.equals("") || pQueueLength.equals("")){
                    Toast.makeText(OwnerAddPetrol.this, "Fill All the field", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("details:===="+StationName+StationNumber+StationLocation);
                    addPetrol(pLiters,pStartTime,pEndTime,pQueueLength,StationName,StationNumber,StationLocation);
                    Intent intent = new Intent(getApplicationContext(), ShedOwnerActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void addPetrol(String Liters,String ArrivalTime,String EndTime, String QueueLength, String StationName,String StationNumber,String StationLocation){
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.createPetrol(Liters,ArrivalTime,EndTime,QueueLength,StationName,StationNumber,StationLocation);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(OwnerAddPetrol.this, "Fuel Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


}

