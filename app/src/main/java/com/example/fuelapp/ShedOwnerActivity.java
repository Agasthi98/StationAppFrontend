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

    Button removepetrol, removeDiesel, logOut, addPetrol, addDiesel, addTime, removeTime;
    TextView FstartTime, FendTime, dieselLiter, petrolLiter, dieselQueue, petrolQueue, Flocation, FlocationValue, petrolQueueValue, dieselQueueValue;
    TextView OpenTimeValue, CloseTimeValue, petrolLiterDisplayValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_owner);

        /*
        combine java class and related layout components
         */
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
        removepetrol = (Button) findViewById(R.id.btnRemovePetrol);
        removeDiesel = (Button) findViewById(R.id.btnRemoveDiesel);
        logOut = (Button) findViewById(R.id.btnStationLogOut);
        addPetrol = (Button) findViewById(R.id.btnAddPetrol);
        addDiesel = (Button) findViewById(R.id.btnAddDiesel);
        petrolLiterDisplayValue = (TextView) findViewById(R.id.txtPetrolLitersDisplayValue);
        logOut = (Button) findViewById(R.id.btnStationLogOut);
        addTime = (Button) findViewById(R.id.btnAddOpenTime);
        removeTime = (Button) findViewById(R.id.btnRemoveOpenTime);

        /*
        display petrol
         */
        displayPetrol();

        /*
        display diesel
         */
        displayDiesel();

        /*
        display shed time
         */
        displayShedTime();

            /*
        shed owner log out method
         */
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        /*
        remove time on click listenr
         */
        removeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTime();
                System.out.println("Time deleted");
            }
        });

        /*
        remove petrol on click listner
         */
        removepetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePetrol();
                System.out.println("Petrol deleted");
            }
        });

        /*
        remove diesel o click listner
         */
        removeDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDiesel();
                System.out.println("Diesel deleted");
            }
        });

        /*
        add time on click listenr
         */
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTime.class);
                startActivity(intent);
            }
        });

        /*
        add petrol on click listenr
         */
        addPetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OwnerAddPetrol.class);
                startActivity(intent);
            }
        });

        /*
        add diesel on click listenr
         */
        addDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OwnerAddDiesel.class);
                startActivity(intent);
            }
        });

    }

    /*
    display petrol method
     */
    public void displayPetrol() {

        TextView pQueue = findViewById(R.id.txtPetrolQueueDisplayValue);
        TextView SLocation = findViewById(R.id.txtShedOwnerLocationvalue);
        TextView pdisplayValue = findViewById(R.id.txtPetrolLitersDisplayValue);

        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getPetrol(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if (response.isSuccessful()) {
                    FuelModel Fmodel = response.body();
                    if (Fmodel.getLiters() == null) {
                        System.out.println("unavailable");
                        pdisplayValue.setText("Unavailable");
                        pQueue.setText("Unavailable");
                    } else {
                        pQueue.setText(Fmodel.getQueueLength());
                        SLocation.setText(Fmodel.getStationLocation());
                        pdisplayValue.setText(Fmodel.getLiters());
                    }

                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


    /*
    display diesel method
     */
    public void displayDiesel() {
        TextView dDisplayValue = findViewById(R.id.txtDieselLiterValue);
        TextView dQueueValue = findViewById(R.id.txtQueueDisplayValue);
        TextView SLocation = findViewById(R.id.txtShedOwnerLocationvalue);

        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getDiesel(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if (response.isSuccessful()) {
                    FuelModel Fmodel = response.body();
                    if (Fmodel.getLiters() == null) {
                        System.out.println("unavailable");
                        dDisplayValue.setText("Unavailable");
                        dQueueValue.setText("Unavailable");
                    } else {
                        dDisplayValue.setText(Fmodel.getLiters());
                        dQueueValue.setText(Fmodel.getQueueLength());
                        SLocation.setText(Fmodel.getStationLocation());
                    }
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }


    /*
    display shed time method
     */
    public void displayShedTime() {
        TextView shedOpenTime = findViewById(R.id.txtOpenTimeValue);
        TextView shedCloseTime = findViewById(R.id.txtCloseTimeValue);

        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.getShedOpenTime(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if (response.isSuccessful()) {
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

    }

    /*
    remove petrol method
     */
    public void removePetrol() {
        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.deletePetrol(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ShedOwnerActivity.this, "Petrol Delete successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    /*
    remove diesel method
     */
    public void removeDiesel() {
        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.deleteDiesel(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ShedOwnerActivity.this, "Diesel Delete successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    /*
    remove time method
     */
    public void removeTime() {
        System.out.println(phoneNUMBER);
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<FuelModel> call = stationService.deleteTime(phoneNUMBER);
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ShedOwnerActivity.this, "Time Delete successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}