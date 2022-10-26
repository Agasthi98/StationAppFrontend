package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fuelapp.Database.DBHandler;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    EditText password, phoneNo;
    Spinner role;
    Button signIn;
    DBHandler myDB;

    //declare static variables
    public static String userNAME;
    public static String phoneNUMBER;
    public static String userROLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNo = (EditText) findViewById(R.id.txtuserPhoneNo);
        password = (EditText) findViewById(R.id.txtPasswordLogin);
        signIn = (Button) findViewById(R.id.btnSignIn);
        myDB = new DBHandler(this);



        //SignIn on click
        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String phone = phoneNo.getText().toString();
                String pass = password.getText().toString();

                    //array list for get registred userdetails
                    ArrayList checkuserpass = myDB.getUserInfo(phone,pass);
                        if(!checkuserpass.isEmpty()){

                            String Id = checkuserpass.get(0).toString();
                            String username = checkuserpass.get(1).toString();
                            String phoneNo = checkuserpass.get(2).toString();
                            String role = checkuserpass.get(3).toString();
                            String password = checkuserpass.get(4).toString();

                            userNAME = username;
                            phoneNUMBER = phoneNo;
                            userROLE = role;

                            //check the password and phone
                            if(password.equals(pass) && phoneNo.equalsIgnoreCase(phone))
                                if(role.equalsIgnoreCase("User")){
                                    Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LoginActivity.this, "Login failed..", Toast.LENGTH_SHORT).show();
                                }
                        }else {
                            Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ShedOwnerActivity.class);
                            startActivity(intent);

                        }
                    }
        });

    }
}