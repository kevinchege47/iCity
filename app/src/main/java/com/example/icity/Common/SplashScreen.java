package com.example.icity.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icity.Common.LoginSignUp.Retailer_login;
import com.example.icity.R;
import com.example.icity.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 4000;

    ImageView splash_image;
    TextView splash_text,splash_power;
    Animation top_anim,bottom_anim;
    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        splash_image = findViewById(R.id.splash_image);
        splash_text = findViewById(R.id.splash_text);
        splash_power = findViewById(R.id.splash_power);
        splash_image.setAnimation(top_anim);
        splash_text.setAnimation(bottom_anim);
        splash_power.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if (isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
                    startActivity(intent);
                    finish();


                }else{
                    Intent intent = new Intent(SplashScreen.this, UserDashboard.class);
                    startActivity(intent);
                    finish();


                }

                Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
                startActivity(intent);
                finish();
                //Log.i("Splash","Splashscreen");
            }
        },SPLASH_SCREEN);



}}