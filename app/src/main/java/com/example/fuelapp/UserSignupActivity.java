package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Database.DBHandler;

public class UserSignupActivity extends AppCompatActivity {

    EditText userName, password, confirmPassword,phoneNo,vehicle;
    Button signup,alreadyHave;
    DBHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = (EditText) findViewById(R.id.txtUsernameSignUp);
        vehicle = (EditText) findViewById(R.id.txtVehicle);
        password = (EditText) findViewById(R.id.txtPasswordSignup);
        confirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        signup = (Button) findViewById(R.id.btnSignUp);
        phoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        alreadyHave = (Button) findViewById(R.id.btnAlreadySignup);


        /*
        call local database
         */
        myDB = new DBHandler(this);

        /*
        already have button on click listner
         */
        alreadyHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        /*
        textview for navigate shed owner sign up
         */
        TextView stationSignUp = findViewById(R.id.txtNavigateStationOwnerRegistration);
        stationSignUp.setOnClickListener(v -> {

            Intent intent = new Intent(this, StationOwnerSignUp.class);
            startActivity(intent);
        });


        /*
        sign up on click listner
         */
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String phone = phoneNo.getText().toString();
                String pass = password.getText().toString();
                String vehi = vehicle.getText().toString();
                String cPass = confirmPassword.getText().toString();
                String role = "User";

                /*
                check the fields are empty
                 */
                if(user.equals("") || vehi.equals("") || pass.equals("") || cPass.equals("")){
                    Toast.makeText(UserSignupActivity.this, "Fill All the field", Toast.LENGTH_SHORT).show();
                }
                else{
                    /*
                    check the password and confirm password
                     */
                    if(pass.equals(cPass)){
                        Boolean userCkeckResult =  myDB.checkusername((phone));
                        if(userCkeckResult == false){
                           Boolean regResult =  myDB.addInfo(user,phone,role,vehi,pass);
                           if(regResult == true){
                                   Toast.makeText(UserSignupActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                                   System.out.println("username:"+user+ "\npassword:"+pass+ "\nrole:"+role);
                                   Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                   startActivity(intent);
                           }else{
                               Toast.makeText(UserSignupActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                           }
                        }else{
                            Toast.makeText(UserSignupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(UserSignupActivity.this, "Password not matched!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}