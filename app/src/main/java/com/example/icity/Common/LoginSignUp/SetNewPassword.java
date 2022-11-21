package com.example.icity.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.icity.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {
    Button credentials;
    TextInputLayout first, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        credentials = findViewById(R.id.credentials);
        first = findViewById(R.id.firstPassword);
        second = findViewById(R.id.secondPassword);
    }

    public void updateData(View view) {
        if (!validatePhoneNumber()) {
            return;
        }
        String newPassword = second.getEditText().getText().toString().trim();
        String _countrynumber = getIntent().getStringExtra("countrynumber");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(_countrynumber).child("password").setValue(newPassword);
        startActivity(new Intent(getApplicationContext(), ForgetPasswordTwo.class));
        finish();

    }

    private boolean validatePhoneNumber() {
        String one = first.getEditText().getText().toString().trim();
        String two = second.getEditText().getText().toString().trim();
        if (one.isEmpty() || two.isEmpty()) {
            first.setError("Empty");
            second.setError("Empty");
            return false;


//        } else if (one.equals(two)) {
//            second.setError(null);
//            second.setErrorEnabled(false);
//            return true;
//
//
        }
       else if (one.equals(two)) {
           first.setError(null);
            first.setErrorEnabled(false);
            second.setError(null);
            second.setErrorEnabled(false);
            return true;

        } else {
//            second.setError(null);
//            second.setErrorEnabled(false);
            second.setError("Password Dont Match");
            return false;
        }

    }

}