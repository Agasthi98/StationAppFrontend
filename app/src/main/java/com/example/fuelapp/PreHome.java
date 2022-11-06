package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PreHome extends AppCompatActivity {

    Button toDiesel,toPetrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_home);

        /*
        declare two button for diesel and petrol
         */
        toDiesel = (Button) findViewById(R.id.btnDiesel);
        toPetrol = (Button) findViewById(R.id.btnPetrol);

        /*
        to diesel on click listner
         */
        toDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PreHome.this, "To Diesel!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        /*
        to petrol on click listner
         */
        toPetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PreHome.this, "To petrol!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }
}