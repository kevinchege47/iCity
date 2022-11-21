package com.example.icity.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.icity.R;
import com.google.android.material.textfield.TextInputLayout;

public class RetailerSignUp extends AppCompatActivity {
    TextInputLayout sign_firstname, sign_email, sign_username, sign_password;
    Button next_two, logintwo;
    ImageView one_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up);
        sign_firstname = findViewById(R.id.sign_firstname);
        sign_email = findViewById(R.id.sign_email);
        sign_username = findViewById(R.id.sign_username);
        sign_password = findViewById(R.id.sign_password);
        logintwo = findViewById(R.id.logintwo);
        logintwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerSignUp.this, Retailer_login.class);
                startActivity(intent);
            }
        });


        next_two = findViewById(R.id.next_two);
        one_back = findViewById(R.id.signUpBack);
        one_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetailerSignUp.super.onBackPressed();
            }
        });

        next_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFirstName() | !validateUserName() | !validateEmail() | !validatePassword()) {
                    return;
                }

                //Get All Values From Text Fields
                Intent intent = new Intent(RetailerSignUp.this, RetailerSignupThree.class);
                String _FirstName = sign_firstname.getEditText().getText().toString();
                String _UserName = sign_username.getEditText().getText().toString();
                String _Email = sign_email.getEditText().getText().toString();
                String _Password = sign_password.getEditText().getText().toString();

                //Pass to the next activity

                intent.putExtra("Firstname", _FirstName);
                intent.putExtra("UserName", _UserName);
                intent.putExtra("Email", _Email);
                intent.putExtra("Password", _Password);

                startActivity(intent);
            }
        });

    }

    private boolean validateFirstName() {
        String val = sign_firstname.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            sign_firstname.setError("Field Cannot Be Empty");
            return false;

        } else {
            sign_firstname.setError(null);
            sign_firstname.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUserName() {
        String val = sign_username.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            sign_username.setError("Field Cannot Be Empty");
            return false;

        } else if (val.length() > 20) {
            sign_username.setError("UserName Too Long");
            return false;


        } else if (!val.matches(checkSpaces)) {
            sign_username.setError("WhiteSpaces Not Allowed");
            return false;

        } else {
            sign_username.setError(null);
            sign_username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String val = sign_email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            sign_email.setError("Field Cannot Be Empty");
            return false;

        } else if (!val.matches(checkEmail)) {
            sign_email.setError("Invalid Email");
            return false;

        } else {
            sign_email.setError(null);
            sign_email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = sign_password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                //  "(?=.*[A-Z])" +         //at least 1 upper case letter
                // "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=/.,''?{}()])" + //at least 1 special character
                //"(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            sign_password.setError("Field Cannot Be Empty");
            return false;

        } else if (!val.matches(checkPassword)) {
            sign_password.setError("Must Contain atleast 1 digit,1 lowercase, 1 special Character,atleast 4 characters");
            return false;

        } else {
            sign_password.setError(null);
            sign_password.setErrorEnabled(false);
            return true;
        }

    }


}