package com.example.fuelapp;

import static com.example.fuelapp.LoginActivity.phoneNUMBER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.service.RetrofitClient;
import com.example.fuelapp.service.StationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShedOwnerActivity extends AppCompatActivity {

    Button removeFuel,logOut,addPetrol,addDiesel,addTime;
    TextView FstartTime,FendTime,dieselLiter,petrolLiter,dieselQueue,petrolQueue,Flocation,FlocationValue,petrolQueueValue,dieselQueueValue;
    TextView OpenTimeValue,CloseTimeValue,petrolLiterDisplayValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_owner);

        FstartTime = (TextView) findViewById(R.id.txtOpenTime);
        FendTime = (TextView) findViewById(R.id.txtCloseTime);
        dieselLiter = (TextView) findViewById(R.id.txtLitersDisplay);
        petrolLiter = (TextView) findViewById(R.id.txtPetrolLitersDisplay);
        dieselQueue = (TextView) findViewById(R.id.txtQueueDisplay);
        petrolQueue = (TextView) findViewById(R.id.txtPetrolQueueDisplay);
        Flocation = (TextView) findViewById(R.id.txtShedOwnerLocation);
        FlocationValue = (TextView) findViewById(R.id.txtShedOwnerLocationvalue);
        petrolQueueValue = (TextView) findViewById(R.id.txtPetrolQueueDisplayValue);
        OpenTimeValue = (TextView) findViewById(R.id.txtOpenTimeValue);
        CloseTimeValue = (TextView) findViewById(R.id.txtCloseTimeValue);
        removeFuel = (Button) findViewById(R.id.btnRemove);
        logOut = (Button) findViewById(R.id.btnStationLogOut);
        addPetrol = (Button) findViewById(R.id.btnAddPetrol);
        addDiesel = (Button) findViewById(R.id.btnAddDiesel);
        petrolLiterDisplayValue = (TextView) findViewById(R.id.txtPetrolLitersDisplayValue);
        logOut = (Button) findViewById(R.id.btnStationLogOut);
        addTime = (Button) findViewById(R.id.btnAddOpenTime);

        displayPetrol();
        displayDiesel();
        displayShedTime();



        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTime.class);
                startActivity(intent);
            }
        });

        addPetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OwnerAddPetrol.class);
                startActivity(intent);
            }
        });

        addDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OwnerAddDiesel.class);
                startActivity(intent);
            }
        });

    }

    public void displayPetrol(){

        TextView pQueue =  findViewById(R.id.txtPetrolQueueDisplayValue);
        TextView SLocation = findViewById(R.id.txtShedOwnerLocationvalue);
        TextView pdisplayValue =  findViewById(R.id.txtPetrolLitersDisplayValue);
        TextView dDisplayValue = findViewById(R.id.txtDieselLiterValue);
        TextView dQueueValue = findViewById(R.id.txtQueueDisplayValue);

        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getPetrol(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    FuelModel Fmodel = response.body();
                    pQueue.setText(Fmodel.getQueueLength());
                    SLocation.setText(Fmodel.getStationLocation());
                    pdisplayValue.setText(Fmodel.getLiters());
                    dDisplayValue.setText(Fmodel.getLiters());
                    dQueueValue.setText(Fmodel.getQueueLength());
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


    public void displayDiesel(){
        TextView dDisplayValue = findViewById(R.id.txtDieselLiterValue);
        TextView dQueueValue = findViewById(R.id.txtQueueDisplayValue);

        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getDiesel(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    FuelModel Fmodel = response.body();
                    dDisplayValue.setText(Fmodel.getLiters());
                    dQueueValue.setText(Fmodel.getQueueLength());
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    public void displayShedTime(){
        TextView shedOpenTime = findViewById(R.id.txtOpenTimeValue);
        TextView shedCloseTime = findViewById(R.id.txtCloseTimeValue);

        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getShedOpenTime(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if(response.isSuccessful()){
                    FuelModel Fmodel = response.body();
                    shedOpenTime.setText(Fmodel.getArrivalTime());
                    shedCloseTime.setText(Fmodel.getEndTime());
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}