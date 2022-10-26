package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fuelapp.Database.DBHandler;

public class SignupActivity extends AppCompatActivity {

    EditText userName, password, confirmPassword,phoneNo;
    Spinner role;
    Button signup,alreadyHave;
    DBHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = (EditText) findViewById(R.id.txtUsernameSignUp);
        role = (Spinner) findViewById(R.id.spinner_Signup);
        password = (EditText) findViewById(R.id.txtPasswordSignup);
        confirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        signup = (Button) findViewById(R.id.btnSignUp);
        phoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        alreadyHave = (Button) findViewById(R.id.btnAlreadySignup);

        //Retrieve dropdown strings
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.userType, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        role.setAdapter(adapter);

        myDB = new DBHandler(this);

        alreadyHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String rrole = role.getSelectedItem().toString();
                String user = userName.getText().toString();
                String phone = phoneNo.getText().toString();
                String pass = password.getText().toString();
                String cPass = confirmPassword.getText().toString();

                if(user.equals("") || rrole.equals("") || pass.equals("") || cPass.equals("")){
                    Toast.makeText(SignupActivity.this, "Fill All the field", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(cPass)){
                        Boolean userCkeckResult =  myDB.checkusername((phone));
                        if(userCkeckResult == false){
                           Boolean regResult =  myDB.addInfo(user,phone,rrole,pass);
                           if(regResult == true){
                                   Toast.makeText(SignupActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                                   System.out.println("username:"+user+ "\npassword:"+pass+ "\nrole:"+rrole);
                                   Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                   startActivity(intent);
                           }else{
                               Toast.makeText(SignupActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                           }
                        }else{
                            Toast.makeText(SignupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Password not matched!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}