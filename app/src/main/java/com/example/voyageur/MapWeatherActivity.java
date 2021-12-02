package com.example.voyageur;

import static com.example.voyageur.MainActivity.EXTRA_KEY;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyageur.modelweather.AdapterViewHolder;
import com.example.voyageur.modelweather.Root;

import java.util.ArrayList;

public class MapWeatherActivity extends AppCompatActivity {
    private ArrayList<Root> listDisplay;
    private AdapterViewHolder adapterViewHolder;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_weather);
        listDisplay = new ArrayList<Root>(SharedPreferencesManager.getInstance(this).getWeather(EXTRA_KEY));
        recyclerView=findViewById(R.id.recyclerViewWeather);
        setViewItem();

    }

    private void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterViewHolder=new AdapterViewHolder(listDisplay);
        recyclerView.setAdapter(adapterViewHolder);
    }
}
