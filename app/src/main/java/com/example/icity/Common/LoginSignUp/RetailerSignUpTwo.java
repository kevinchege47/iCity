package com.example.icity.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.icity.R;

import java.util.Calendar;

public class RetailerSignUpTwo extends AppCompatActivity {
    ImageView back_two;
    RadioGroup radiogroup;
    DatePicker datePicker;
    Button next_three;
    RadioButton selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up_two);
        next_three = findViewById(R.id.next_three);
        datePicker = findViewById(R.id.age_picker);
        radiogroup = findViewById(R.id.radio_group);
        back_two = findViewById(R.id.two_back);
        back_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetailerSignUpTwo.super.onBackPressed();
            }
        });
    }



    private boolean validateGender() {
        if (radiogroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = CurrentYear - userAge;
        if (isAgeValid < 18) {
            Toast.makeText(this, "You Are Not Eligible", Toast.LENGTH_SHORT).show();
            return false;

        } else
            return true;
    }
    public void callSignUpThree(View view){
        if (!validateAge() | !validateGender()) {
            return;
        }
        selectedGender = findViewById(radiogroup.getCheckedRadioButtonId());

        String _gender = selectedGender.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        String _date = day + "/" + month + "/" + year + "/";

        //Get values from previous activity

        String _FirstName = getIntent().getStringExtra("Firstname");
        String _Email = getIntent().getStringExtra("Email");
        String _Password = getIntent().getStringExtra("Password");
        String _UserName = getIntent().getStringExtra("UserName");

        Intent intent = new Intent(RetailerSignUpTwo.this, RetailerSignupThree.class);
        //Pass to next activity

        intent.putExtra(_FirstName,"Firstname");
        intent.putExtra(_Email,"Email");
        intent.putExtra(_Password,"Password");
        intent.putExtra(_UserName,"UserName");
        intent.putExtra(_date,"Date");
        intent.putExtra(_gender,"Gender");


        startActivity(intent);

    }
}