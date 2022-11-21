package com.example.icity.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.icity.Database.UserHelperClass;
import com.example.icity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OtpVerify extends AppCompatActivity {
    PinView pinView;
    String codeBySystem;
    Button button, loginotp;
    String firstname, email, password, username, gender, date, _countryNumber, WhatToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp_verify);
        pinView = findViewById(R.id.pinView);
        button = findViewById(R.id.nextFromOtp);
        loginotp = findViewById(R.id.loginotp);

        _countryNumber = getIntent().getStringExtra("countrynumber");
        firstname = getIntent().getStringExtra("Firstname");
        email = getIntent().getStringExtra("Email");
        password = getIntent().getStringExtra("Password");
        username = getIntent().getStringExtra("UserName");
        gender = getIntent().getStringExtra("Gender");
        date = getIntent().getStringExtra("Date");
        WhatToDo = getIntent().getStringExtra("WhatToDo");

        loginotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Retailer_login.class);
                startActivity(intent);
            }
        });


        sendVerificationCodeToUser(_countryNumber);

    }

    private void sendVerificationCodeToUser(String _countryNumber) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(_countryNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        pinView.setText(code);
                        verifyCode(code);
                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OtpVerify.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInUsingCredential(credential);
    }


    private void signInUsingCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (WhatToDo.equals("UpdateData")) {
                                storeUpdatedData();
//                                Log.i("update", "Updated");
                            } else {
                                storeNewUserData();
                                Toast.makeText(OtpVerify.this, "VERIFICATION SUCCESSFULL!", Toast.LENGTH_LONG).show();
                                loginotp.setVisibility(View.VISIBLE);
                            }

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OtpVerify.this, "ERROR!VERIFICATION FAILED", Toast.LENGTH_SHORT).show();
                                loginotp.setVisibility(View.GONE);

                            }
                        }
                    }
                });
    }

    private void storeUpdatedData() {
        Intent intent = new Intent(OtpVerify.this, SetNewPassword.class);
        intent.putExtra("countrynumber",_countryNumber);
        startActivity(intent);
        finish();

    }

    private void storeNewUserData() {

        //Get values from previous activity
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");
        UserHelperClass addNewUser = new UserHelperClass(firstname, email, password, username, gender, date, _countryNumber);
        reference.child(_countryNumber).setValue(addNewUser);
    }

    public void callNextFromOtp(View view) {
        String code = pinView.getText().toString();
        verifyCode(code);

    }
}