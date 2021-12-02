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
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.voyageur.activities.MapWeatherActivity;
import com.example.voyageur.R;
import com.example.voyageur.SharedPreferencesManager;
import com.example.voyageur.VoyageApi;
import com.example.voyageur.modelweather.Root;
import com.example.voyageur.modelweather.Weather;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentWeather extends Fragment {
    private Button btnToSee;
    private ArrayList<Weather>listWeather = new ArrayList<>();
    private EditText editCity;
    private Weather weather;

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
        editCity=view.findViewById(R.id.raw_city);
        btnToSee=view.findViewById(R.id.raw_btnDone);
        btnToSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callService();


                Toast.makeText(FragmentWeather.this.getContext(), "Vers descriptif", Toast.LENGTH_SHORT).show();
            }
        });
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
                SharedPreferencesManager.getInstance(this.getContext()).saveWeather(listWeather,WEATHER_LIST_KEY);
                Intent intent =new Intent(FragmentWeather.this.getContext(),MapWeatherActivity.class);
                startActivity(intent);
            Log.d( "Reponse du serveur", String.valueOf(listWeather.size()));
        }
    }






}
