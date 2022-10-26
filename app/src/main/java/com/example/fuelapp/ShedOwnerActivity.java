package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShedOwnerActivity extends AppCompatActivity {

    Button addAStation, updateAStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_owner);

        addAStation = (Button) findViewById(R.id.navigateToAddStation);
        updateAStation = (Button) findViewById(R.id.navigateToUpdateStation);

        //add station onclick listner
        addAStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStationActivity.class);
                startActivity(intent);
            }
        });

        //update station
        updateAStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStationActivity.class);
                startActivity(intent);
            }
        });
    }
}