package com.example.icity.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.icity.R;
import com.google.android.material.textfield.TextInputLayout;

public class ForgetPasswordTwo extends AppCompatActivity {
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_two);
        login = findViewById(R.id.loginotp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Retailer_login.class);
                startActivity(intent);
            }
        });
    }





}