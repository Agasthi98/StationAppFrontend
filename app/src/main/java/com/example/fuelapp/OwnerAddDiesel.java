package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    }
}