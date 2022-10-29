package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShedView extends AppCompatActivity {

    TextView shedName,shedPhoneNo,fuelStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_view);

        shedName = (TextView) findViewById(R.id.txtShedNameValue);
        shedPhoneNo = (TextView) findViewById(R.id.txtShedPhoneNoValue);
        fuelStatus = (TextView) findViewById(R.id.txtShedStatusValue);
    }
}