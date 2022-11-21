package com.example.icity.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.icity.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class RetailerSignupThree extends AppCompatActivity {
    TextInputLayout number_three;
    Button nextThree, loginthree;
    CountryCodePicker codePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_signup_three);
        codePicker = findViewById(R.id.countryCodePicker);
        nextThree = findViewById(R.id.next_three);
        loginthree = findViewById(R.id.loginthree);
        loginthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Retailer_login.class);
                startActivity(intent);
            }
        });
        number_three = findViewById(R.id.sign_number);
    }

    public void callVerifyOtp(View view) {
        if (!validatePhoneNumber()) {
            return;
        }

        final String phone = number_three.getEditText().getText().toString().trim();
        final String Completephone = "+" + codePicker.getFullNumber() + phone;

        Query checkUserExistance = FirebaseDatabase.getInstance().getReference("Users").orderByChild("_countryNumber").equalTo(Completephone);
        checkUserExistance.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                   number_three.setError("User Exists");

                } else {
                    number_three.setError(null);
                    number_three.setErrorEnabled(false);
                    String _FirstName = getIntent().getStringExtra("Firstname");
                    String _Email = getIntent().getStringExtra("Email");
                    String _Password = getIntent().getStringExtra("Password");
                    String _UserName = getIntent().getStringExtra("UserName");
//        String _gender = getIntent().getStringExtra("Gender");
//        String _date = getIntent().getStringExtra("Date");
                    String userEnteredPhone = number_three.getEditText().getText().toString();
                    String _countryNumber = "+" + codePicker.getFullNumber() + userEnteredPhone;

                    Intent intent = new Intent(RetailerSignupThree.this, OtpVerify.class);

                    intent.putExtra("Firstname", _FirstName);
                    intent.putExtra("Email", _Email);
                    intent.putExtra("Password", _Password);
                    intent.putExtra("UserName", _UserName);
//        intent.putExtra("Gender",_gender);
//        intent.putExtra("Date",_date);
                    intent.putExtra("countrynumber", _countryNumber);

                    startActivity(intent);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RetailerSignupThree.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }


    private boolean validatePhoneNumber() {
        String phone = number_three.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            number_three.setError("Field cannot be empty");
            return false;
        } else {
            number_three.setError(null);
            number_three.setErrorEnabled(false);
            return true;
        }
    }
//
//    private boolean checkIfUserExists() {
//        final String phone = number_three.getEditText().getText().toString().trim();
//        Query checkUserExistance = FirebaseDatabase.getInstance().getReference("Users").child("_countryNumber");
//        checkUserExistance.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String systemNumber = snapshot.child("_countryNumber").getValue(String.class);
//                if (systemNumber.equals(phone)) {
//                    Toast.makeText(RetailerSignupThree.this, "User Exists", Toast.LENGTH_SHORT).show();
//                }
//
//                else {
//                    Toast.makeText(RetailerSignupThree.this, "GOOOD", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(RetailerSignupThree.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//
//        return true;
//    }

}
