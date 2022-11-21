
package com.example.icity.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.icity.R;

public class LoginSignUp extends AppCompatActivity {
ImageView relativeLayout;
Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_sign_up);
        login = findViewById(R.id.startpage_Login);
        signup = findViewById(R.id.startpage_signup);



    }

    public void callLoginScreen(View view) {
        Intent intent = new Intent(LoginSignUp.this, Retailer_login.class);
        Pair[] pairs = new Pair[1];
        pairs [0] = new Pair <View,String>(login,"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginSignUp.this,pairs);
            startActivity(intent,activityOptions.toBundle());
        }else{
            startActivity(intent);
        }


    }
    public void callSignUpScreen(View view){
        Intent intent = new Intent(LoginSignUp.this,RetailerSignUp.class);
        Pair[] pairs = new Pair[1];
        pairs [0] = new Pair <View,String>(signup,"trans_signup");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginSignUp.this,pairs);
            startActivity(intent,activityOptions.toBundle());
        }else{
            startActivity(intent);
        }

    }
}