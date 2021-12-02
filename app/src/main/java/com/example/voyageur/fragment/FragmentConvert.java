package com.example.voyageur.fragment;

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

import com.example.voyageur.R;
import com.example.voyageur.VoyageApi;
import com.example.voyageur.model.converter.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentConvert extends Fragment {
    private Button btnCalculate;
    private EditText amountToConvert;
    private TextView result;
    private TextView amountUsd;
    private Double sumConverted=0.0;
    private Button btnDisplayRate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_convert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amountToConvert=view.findViewById(R.id.raw_amountToConvert);
        amountUsd=view.findViewById(R.id.txt_usd);
        result =view.findViewById(R.id.txt_amountConverted);
        btnCalculate=view.findViewById(R.id.btnCalculate);
        btnDisplayRate=view.findViewById(R.id.btnDisplayRate);
        btnDisplayRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callService();

            }
        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    isEmpty();
                    calculate();
            }
        });

    }

    public void callService(){
        String key = "ff8dad8af5245be93fec758e62617d59";
        VoyageApi.VoyageService service = VoyageApi.getInstance().getClient().create(VoyageApi.VoyageService.class);
        Call<Root> call= service.getRates(key);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                updateView(response);
                Toast.makeText(FragmentConvert.this.getContext(), "Acces OK", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(FragmentConvert.this.getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                Log.d("FragmentConvert",t.getMessage());
            }
        });
    }

    private void updateView(Response<Root> response) {
        if(response.body()!=null){
            response.body().getRates().getUSD();
            //amountUsd.setText(Double.toString(response.body().getRates().getuSD()));
            amountUsd.setText(Double.toString(response.body().getRates().getUSD()));
            Log.d("Le montant est: ", String.valueOf(response.body().getRates().getUSD()));

        }
    }

    private void isEmpty(){
        if(amountToConvert.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(FragmentConvert.this.getContext(),"Veuillez saisir un montant a convertir",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void calculate(){

        sumConverted = (Double.parseDouble(amountToConvert.getText().toString()))*(Double.parseDouble(amountUsd.getText().toString()));
        Log.d("Le rest converti est:", String.valueOf(sumConverted));
        result.setText(String.valueOf(sumConverted));
    }


}
