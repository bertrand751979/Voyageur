package com.example.voyageur.fragment;

import static com.example.voyageur.activities.MainActivity.WEATHER_LIST_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyageur.R;
import com.example.voyageur.SharedPreferencesManager;
import com.example.voyageur.VoyageApi;
import com.example.voyageur.activities.MapWeatherActivity;
import com.example.voyageur.adapter.AdapterViewHolder;
import com.example.voyageur.modelweather.Main;
import com.example.voyageur.modelweather.Root;
import com.example.voyageur.modelweather.Weather;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentWeather extends Fragment {
    private Button btnToSee;
    private ArrayList<Weather>listWeather = new ArrayList<>();
    public static EditText editCity;
    private Weather weather;
    private Main main;
    public  double tempMin;
    public   double tempMax;
    private TextView tvalMin;
    private TextView tvalMax;
    //private ArrayList<Weather> listDisplay;
    private AdapterViewHolder adapterViewHolder;
    private RecyclerView recyclerView;
    private TextView txtTempMax;
    private TextView txtTempMin;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //listDisplay = new ArrayList<Weather>(SharedPreferencesManager.getInstance(this.getContext()).getWeather(WEATHER_LIST_KEY));
        recyclerView=view.findViewById(R.id.recyclerViewWeather);
        listWeather = new ArrayList<Weather>(SharedPreferencesManager.getInstance(this.getContext()).getWeather(WEATHER_LIST_KEY));

        //txtTempMin=view.findViewById(R.id.raw_temp_min);
        //txtTempMax=view.findViewById(R.id.raw_temp_max);

        tvalMin =view.findViewById(R.id.rawvalmin);
        tvalMax =view.findViewById(R.id.rawvalmax);
        editCity=view.findViewById(R.id.raw_city);
        btnToSee=view.findViewById(R.id.raw_btnDone);
        btnToSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callService();
                tvalMin.setText(String.valueOf(tempMin));
                tvalMax.setText(String.valueOf(tempMax));

                Toast.makeText(FragmentWeather.this.getContext(), "Vers descriptif", Toast.LENGTH_SHORT).show();
            }
        });
        setViewItem();
    }

    public void callService(){
        String key = "5b2327f8b0cd51950136ddb878f8a45d";
        VoyageApi.VoyageService service = VoyageApi.getInstance().getClientTwo().create(VoyageApi.VoyageService.class);
        Call<com.example.voyageur.modelweather.Root> call= service.getWeather(editCity.getText().toString(),key);
        call.enqueue(new Callback<com.example.voyageur.modelweather.Root>() {
            @Override
            public void onResponse(Call<com.example.voyageur.modelweather.Root> call, Response<com.example.voyageur.modelweather.Root> response) {
                updateView(response);
                Toast.makeText(FragmentWeather.this.getContext(), "Succes", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<com.example.voyageur.modelweather.Root> call, Throwable t) {
                Toast.makeText(FragmentWeather.this.getContext(), "Echec", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(Response<Root> response) {
            if(response.body()!=null){
                listWeather = (ArrayList<Weather>)response.body().getWeather();
                temperature(response);
                SharedPreferencesManager.getInstance(this.getContext()).saveWeather(listWeather,WEATHER_LIST_KEY);
                //temperature(response);
                //temp=response.body().getMain().getTemp_min();
                //tvalMax.setText(Double.toString(temp));
                //Intent intent =new Intent(FragmentWeather.this.getContext(),MapWeatherActivity.class);
                //startActivity(intent);
            Log.d( "Reponse du serveur", String.valueOf(listWeather.size()));
                //setViewItem();

            }
            setViewItem();
    }

    public void temperature(Response<Root> response){
        boolean resultat=false;
        if(editCity.getText().toString().equalsIgnoreCase(response.body().getName())) {
            resultat= true;
        }
        if(resultat==true){
            tempMin = response.body().getMain().getTemp_min();
            double celsiusMin=(tempMin-273.15);
            int roundDblMin = (int) Math.ceil((celsiusMin*100.0)/100.0);
            tvalMin.setText(String.valueOf(roundDblMin));

            tempMax = response.body().getMain().getTemp_max();
            double celsiusMax=(tempMax-273.15);
            int roundDblMax = (int) Math.ceil((celsiusMax*100.0)/100.0);
            tvalMax.setText(String.valueOf(roundDblMax));
        }
        Log.d("la ville: "+response.body().getName(),"la temperature min: "+ tempMin);
    }

    private void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterViewHolder=new AdapterViewHolder(listWeather);
        recyclerView.setAdapter(adapterViewHolder);
        //Toast.makeText(FragmentWeather.this.getContext(),"la liste contient"+listDisplay.size(),Toast.LENGTH_SHORT).show();
        //txtTempMin.setText();
    }





}
