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

public class LoginActivity extends AppCompatActivity {


    EditText userName, password;
    Spinner role;
    Button signIn;
    DBHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.txtUserNameLogin);
        password = (EditText) findViewById(R.id.txtPasswordLogin);
        signIn = (Button) findViewById(R.id.btnSignIn);
        myDB = new DBHandler(this);


        //SignIn
        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();


                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please input user details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = myDB.checkusernamepassword(user,pass);
                    Boolean checkuserpass2 = myDB.checkusernamepassword2(user,pass);
                        if(checkuserpass == true){
                            Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            startActivity(intent);
                        }else if(checkuserpass2 == true){
                            Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ShedOwnerActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Login failed..", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });

    }
}