package com.example.icity.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.icity.Common.LoginSignUp.LoginSignUp;
import com.example.icity.Common.LoginSignUp.Retailer_login;
import com.example.icity.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.icity.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.icity.HelperClasses.HomeAdapter.ViewAdapter;
import com.example.icity.HelperClasses.HomeAdapter.ViewedHelperClass;
import com.example.icity.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    LinearLayout contentView;
    RecyclerView featuredRecycler, mostViewed;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter viewAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    ImageView plus;
    TextView name,hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        contentView = findViewById(R.id.content);
        mostViewed = findViewById(R.id.mostViewed_recycler);
        featuredRecycler = findViewById(R.id.features_recycler);
        plus = findViewById(R.id.plus);
        name = findViewById(R.id.name);
        hello = findViewById(R.id.hello);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        String systemusername = getIntent().getStringExtra("systemusername") ;
        name.setText(systemusername);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Retailer_login.class);
                startActivity(intent);
            }
        });

        // Navigation drawer function call

        navigationDrawer();

        //Recycler Function Calls.
        featuredRecycler();
        viewedRecycler();


    }


    // Navigation drawer functions
    private void navigationDrawer() {
        //Navigation View
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);


            }
        });
        animatenavigationdrawer();
    }

    private void animatenavigationdrawer() {


        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //Scale the view
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate v1iew,accounting for scaled width

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTransition = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTransition);

            }


            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }


            @Override
            public void onDrawerStateChanged(int newState) {

            }


        });
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_allcategories:
                Intent intent = new Intent(getApplicationContext(), AllCategories.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_login:
                Intent intent = new Intent(getApplicationContext(), LoginSignUp.class);
                startActivity(intent);
                break;
        }

        return true;
    }


    //Recycler view functions (Featured locations)

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonalds, "McDonald's", "fehc jdjhcvc jhdgcj hsfgefguef gywtyu dyutwd qdgywydw dwgydywd"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.naivas, "naivas", "fehc jdjhcvc jhdgcj hsfgefguef gywtyu dyutwd qdgywydw dwgydywd"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.cleanshelf, "cleanshelf", "fehc jdjhcvc jhdgcj hsfgefguef gywtyu dyutwd qdgywydw dwgydywd"));
        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    private void viewedRecycler() {
        mostViewed.setHasFixedSize(true);
        mostViewed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<ViewedHelperClass> mostViewedA = new ArrayList<>();
        mostViewedA.add(new ViewedHelperClass(R.drawable.woolworths, "WoolWorths"));
        mostViewedA.add(new ViewedHelperClass(R.drawable.jkia, "JKIA"));
        mostViewedA.add(new ViewedHelperClass(R.drawable.naivas, "Naivas"));
        mostViewedA.add(new ViewedHelperClass(R.drawable.cleanshelf, "Cleanshelf"));
        mostViewedA.add(new ViewedHelperClass(R.drawable.mcdonalds, "McDonald's"));
        viewAdapter = new ViewAdapter(mostViewedA);
        mostViewed.setAdapter(viewAdapter);


    }

}

