package com.example.voyageur.activities;

import static com.example.voyageur.activities.MainActivity.WEATHER_LIST_KEY;
import static com.example.voyageur.fragment.FragmentWeather.editCity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyageur.R;
import com.example.voyageur.SharedPreferencesManager;
import com.example.voyageur.adapter.AdapterViewHolder;
import com.example.voyageur.fragment.FragmentWeather;
import com.example.voyageur.modelweather.Root;
import com.example.voyageur.modelweather.Weather;

import java.util.ArrayList;

import retrofit2.Response;

public class MapWeatherActivity extends AppCompatActivity {
    private ArrayList<Weather> listDisplay;
    private AdapterViewHolder adapterViewHolder;
    private RecyclerView recyclerView;
    private Weather weather;
    //private TextView txtTempMax;
    //private TextView txtTempMin;

    private TextView tvalMin;
    private TextView tvalMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_weather);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvalMin =findViewById(R.id.rawvalmin);
        tvalMax =findViewById(R.id.rawvalmax);
        //txtTempMin=findViewById(R.id.raw_temp_min);
        //txtTempMax=findViewById(R.id.raw_temp_max);
        listDisplay = new ArrayList<Weather>(SharedPreferencesManager.getInstance(this).getWeather(WEATHER_LIST_KEY));
        recyclerView=findViewById(R.id.recyclerViewWeather);
        //tvalMax.setText();
        setViewItem();



    }

    private void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterViewHolder=new AdapterViewHolder(listDisplay);
        recyclerView.setAdapter(adapterViewHolder);

        Toast.makeText(MapWeatherActivity.this,"la liste contient"+listDisplay.size(),Toast.LENGTH_SHORT).show();
        //txtTempMin.setText();
    }










    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();}
        return super.onOptionsItemSelected(item);
    }




}
