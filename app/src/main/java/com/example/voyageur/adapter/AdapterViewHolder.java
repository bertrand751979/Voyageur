package com.example.voyageur.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyageur.R;
import com.example.voyageur.modelweather.Weather;
import com.example.voyageur.viewHolder.ViewHolderWeather;

import java.util.ArrayList;

public class AdapterViewHolder extends RecyclerView.Adapter<ViewHolderWeather> {
    private ArrayList<Weather>listAdapter;

    public AdapterViewHolder(ArrayList<Weather> listAdapter) {
        this.listAdapter = listAdapter;
    }

    public void setListAdapter(ArrayList<Weather> listAdapter) {
        this.listAdapter = listAdapter;
    }

    @NonNull
    @Override
    public ViewHolderWeather onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_weather,parent,false);
        return new ViewHolderWeather(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderWeather holder, int position) {
    holder.bind(listAdapter.get(position));
    }

    @Override
    public int getItemCount() {
        return listAdapter.size();
    }
}
