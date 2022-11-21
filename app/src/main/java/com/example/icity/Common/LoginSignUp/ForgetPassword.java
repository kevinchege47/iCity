package com.example.icity.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.icity.R;
import com.example.icity.User.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgetPassword extends AppCompatActivity {
    ImageView forgotback;
    Button forgot_next;
    TextInputLayout signNumber;
    ProgressBar progressBar;
    CountryCodePicker codePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_password);
        forgotback = findViewById(R.id.forgot_back);
        forgot_next = findViewById(R.id.forgot_next);
        progressBar = findViewById(R.id.progressbar);
        codePicker = findViewById(R.id.countryCodePicker);
        signNumber = findViewById(R.id.sign_number);


        forgotback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPassword.super.onBackPressed();
            }
        });

    }

    public void callForgetPasswordTwo(View view) {
        if (!isConnected(this)) {
            Toast.makeText(this, "Please Connect to the internet", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);

        } else {
            progressBar.setVisibility(View.VISIBLE);
        }

        if (!validatePhone()) {
            return;
        }
        String _countryNumber = signNumber.getEditText().getText().toString().trim();
        if (_countryNumber.charAt(0) == '0') {
            _countryNumber = _countryNumber.substring(1);

        }
        final String _completePhoneNumber = "+" + codePicker.getFullNumber() + _countryNumber;

        //Database

        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("_countryNumber").equalTo(_completePhoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
//                    signNumber.setError(null);
//                    signNumber.setErrorEnabled(false);
                    Intent intent = new Intent(ForgetPassword.this, OtpVerify.class);
                    intent.putExtra("countrynumber", _completePhoneNumber);
                    intent.putExtra("WhatToDo", "UpdateData");
                    startActivity(intent);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ForgetPassword.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    private boolean validatePhone() {
        String UserPhone = signNumber.getEditText().getText().toString();
        if (UserPhone.isEmpty()) {
            signNumber.setError("Field Cannot Be Empty");
            signNumber.requestFocus();
            progressBar.setVisibility(View.GONE);
            return false;
        } else {
            signNumber.setError(null);
            signNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isConnected(ForgetPassword forgetPassword) {
        ConnectivityManager connectivityManager = (ConnectivityManager) forgetPassword.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wificonn != null && wificonn.isConnected()) || (mobileconn != null && mobileconn.isConnected())) {
            return true;

        } else {
            return false;
        }

    }
}