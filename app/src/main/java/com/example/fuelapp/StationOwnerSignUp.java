package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelapp.Database.DBHandler;

public class StationOwnerSignUp extends AppCompatActivity {

    EditText StationName,StationPhoneNo,StationLocation,StationPassowrd,StationConfirmPassword;
    Button stationSignUp;
    DBHandler myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_sign_up);


        StationName = (EditText) findViewById(R.id.txtStationName);
        StationPhoneNo = (EditText) findViewById(R.id.txtStationPhoneNo);
        StationLocation = (EditText) findViewById(R.id.txtStationLocation);
        StationPassowrd = (EditText) findViewById(R.id.txtStationPassword);
        StationConfirmPassword = (EditText) findViewById(R.id.txtStationConfirmPassword);
        stationSignUp = (Button) findViewById(R.id.btnStationOwnerSignUp);

        myDB = new DBHandler(this);

        stationSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = StationName.getText().toString();
                String sPhone = StationPhoneNo.getText().toString();
                String sLocation = StationLocation.getText().toString();
                String sPassowrd = StationPassowrd.getText().toString();
                String sCPassword = StationConfirmPassword.getText().toString();
                String role = "ShedOwner";

                if(sName.equals("") || sPhone.equals("") || sLocation.equals("") || sPassowrd.equals("")){
                    Toast.makeText(StationOwnerSignUp.this, "Fill All the field", Toast.LENGTH_SHORT).show();
                } else{
                    //check the password and confirm password
                    if(sPassowrd.equals(sCPassword)){
                        Boolean userCkeckResult =  myDB.checkusername((sPhone));
                        if(userCkeckResult == false){
                            Boolean regResult =  myDB.addInfo(sName,sPhone,role,sLocation,sPassowrd);
                            if(regResult == true){
                                Toast.makeText(StationOwnerSignUp.this, "Registration success", Toast.LENGTH_SHORT).show();
                                System.out.println("username:"+sName+ "\npassword:"+sPassowrd+ "\nrole:"+role);
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(StationOwnerSignUp.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(StationOwnerSignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(StationOwnerSignUp.this, "Password not matched!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}