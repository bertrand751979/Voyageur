package com.example.voyageur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.voyageur.fragment.FragmentConvert;
import com.example.voyageur.fragment.FragmentTranslate;
import com.example.voyageur.fragment.FragmentWeather;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    public static String EXTRA_KEY ="ExtraKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_convert);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_convert:
                    selectedFragment = new FragmentConvert();
                    break;
            }
            switch (item.getItemId()) {
                case R.id.nav_weather:
                    selectedFragment = new FragmentWeather();
                    break;
            }

            switch (item.getItemId()) {
                case R.id.nav_traslate:
                    selectedFragment = new FragmentTranslate();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };




}