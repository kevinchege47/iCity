package com.example.icity.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icity.Common.LoginSignUp.LoginSignUp;
import com.example.icity.Common.LoginSignUp.RetailerSignUp;
import com.example.icity.HelperClasses.SliderAdapter;
import com.example.icity.R;
import com.example.icity.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dots_layout;
    SliderAdapter sliderAdapter;
    TextView [] dotts;
    Button button,nexti;
    Animation bottom_anim;
    int currentPos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        button = findViewById(R.id.getstarted_button);
        viewPager = findViewById(R.id.slider);
        dots_layout = findViewById(R.id.dots);
        nexti = findViewById(R.id.next_button);


        sliderAdapter = new SliderAdapter(this);
        addDots(0);

        viewPager.setAdapter(sliderAdapter);
        viewPager.addOnPageChangeListener(changeListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(OnBoarding.this, LoginSignUp.class);
                startActivity(intent);
                finish();

            }
        });



    }

    public void skip(View view)
    {
        Intent intent;
        intent = new Intent(this,LoginSignUp.class);
        startActivity(intent);
        finish();

    }
    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);

    }

    private void addDots(int position){
        dotts = new TextView[4];
        dots_layout.removeAllViews();
        for (int i = 0;i<dotts.length; i++){
            dotts[i] = new TextView(this);
            dotts[i].setText(Html.fromHtml("&#8226;"));

            dots_layout.addView(dotts[i]);

        }

        if(dotts.length > 0){

            dotts[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }


    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

         if (position == 0){
             button.setVisibility(View.INVISIBLE);
         }

         else if(position ==1){
             button.setVisibility(View.INVISIBLE);

         }
         else if(position ==2){
             button.setVisibility(View.INVISIBLE);
         }
         else{
             bottom_anim = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.bottom_anim);
             button.setAnimation(bottom_anim);

             nexti.setVisibility(View.INVISIBLE);
             button.setVisibility(View.VISIBLE);

         }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




}