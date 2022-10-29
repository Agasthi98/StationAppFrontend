package com.example.fuelapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelapp.Database.DBHandler;
import com.example.fuelapp.Database.ShedAdapter;
import com.example.fuelapp.domain.FuelModel;
import com.example.fuelapp.service.RetrofitClient;
import com.example.fuelapp.service.StationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends AppCompatActivity {
    DBHandler db;

    ListView shedList;
    EditText location;
    Button searchloc;
    List<FuelModel> list = new ArrayList<FuelModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shedList = findViewById(R.id.ShedListView);
        location = findViewById(R.id.textLocation);
        searchloc =findViewById(R.id.btnSearchLocation);

        searchloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shedLocation = location.getText().toString();
                searchLoc(shedLocation);
                System.out.println("Search Clicked");
            }
        });


    }

    public void searchLoc (String location){
        StationService stationService = RetrofitClient.getRetrofitInstance().create(StationService.class);
        Call<List<FuelModel>> call = stationService.searchLocation(location);
        call.enqueue(new Callback<List<FuelModel>>(){

            @Override
            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    shedList.setAdapter(new ShedAdapter(Home.this, R.layout.shed_list_view, list));
                    Toast.makeText(Home.this, "sucessfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }



}
