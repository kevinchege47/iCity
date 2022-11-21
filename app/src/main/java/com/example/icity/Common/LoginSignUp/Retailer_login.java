package com.example.icity.Common.LoginSignUp;

import android.app.DownloadManager;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icity.R;
import com.example.icity.User.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Retailer_login extends AppCompatActivity {
    TextInputLayout PhoneInputLayout, PasswordInputLayout;
    Button create, login, forgotPassword;
    ImageView back_button;
    ProgressBar progressBar;
    CountryCodePicker picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);
        back_button = findViewById(R.id.back_button);
        PhoneInputLayout = findViewById(R.id.RetailerLogin_username);
        forgotPassword = findViewById(R.id.forgotpassword);
        picker = findViewById(R.id.codePicker);
        PasswordInputLayout = findViewById(R.id.RetailerLogin_password);
        login = findViewById(R.id.retailer_login);
        progressBar = findViewById(R.id.progressbar);
        create = findViewById(R.id.create);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retailer_login.super.onBackPressed();
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetailerSignUp.class);
                startActivity(intent);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    public void letUserLogIn(View view) {
        if (!isConnected(this)) {
            Toast.makeText(this, "Please Connect to the internet", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);

        } else {
            progressBar.setVisibility(View.VISIBLE);
        }

        if (!validateFields()) {
            return;
        }

        String _countryNumber = PhoneInputLayout.getEditText().getText().toString().trim();
        final String _password = PasswordInputLayout.getEditText().getText().toString().trim();

        if (_countryNumber.charAt(0) == '0') {
            _countryNumber = _countryNumber.substring(1);

        }
        final String _completePhoneNumber = "+" + picker.getFullNumber() + _countryNumber;

        //Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("_countryNumber").equalTo(_completePhoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    PhoneInputLayout.setError(null);
                    PhoneInputLayout.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_completePhoneNumber).child("_countryNumber").getValue(String.class);

                    if (systemPassword.equals(_password)) {
                        PasswordInputLayout.setError(null);
                        PasswordInputLayout.setErrorEnabled(false);

                        String systemusername = snapshot.child(_completePhoneNumber).child("username").getValue(String.class);
//                        Toast.makeText(Retailer_login.this,systemusername, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Retailer_login.this, UserDashboard.class);
                        intent.putExtra("systemusername", systemusername);
                        startActivity(intent);


                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Retailer_login.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Retailer_login.this, "User does not exist", Toast.LENGTH_SHORT).show();


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Retailer_login.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean validateFields() {
        String UserPhone = PhoneInputLayout.getEditText().getText().toString();
        String UserPassword = PasswordInputLayout.getEditText().getText().toString();

        if (UserPhone.isEmpty()) {
            PhoneInputLayout.setError("Field Cannot Be Empty");
            PhoneInputLayout.requestFocus();
            return false;
        } else if (UserPassword.isEmpty()) {
            PasswordInputLayout.setError("Field Cannot Be Empty");
            PasswordInputLayout.requestFocus();
            return false;
        } else {
            PasswordInputLayout.setError(null);
            PasswordInputLayout.setErrorEnabled(false);
            PhoneInputLayout.setError(null);
            PhoneInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isConnected(Retailer_login mainActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wificonn != null && wificonn.isConnected()) || (mobileconn != null && mobileconn.isConnected())) {
            return true;

        } else {
            return false;
        }

    }


}




