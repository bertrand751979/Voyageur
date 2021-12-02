package com.example.voyageur;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.voyageur.modelweather.Root;
import com.example.voyageur.modelweather.Weather;

public class ViewHolderWeather extends RecyclerView.ViewHolder {
    private ImageView txtIcone;
    private TextView txtId;
    private TextView txtMain;
    private TextView txtDescription;

    public ViewHolderWeather(@NonNull View view) {
        super(view);
        txtIcone=view.findViewById(R.id.raw_icon);
        txtId=view.findViewById(R.id.raw_id);
        txtMain=view.findViewById(R.id.raw_main);
        txtDescription=view.findViewById(R.id.raw_description);
    }

    public ImageView getTxtIcone() {
        return txtIcone;
    }

    public void setTxtIcone(ImageView txtIcone) {
        this.txtIcone = txtIcone;
    }

    public TextView getTxtId() {
        return txtId;
    }

    public void setTxtId(TextView txtId) {
        this.txtId = txtId;
    }

    public TextView getTxtMain() {
        return txtMain;
    }

    public void setTxtMain(TextView txtMain) {
        this.txtMain = txtMain;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextView txtDescription) {
        this.txtDescription = txtDescription;
    }

    public void bind(Root root ){
     txtId.setText(root.getWeather().get(0).getId());
     txtMain.setText(root.getWeather().get(0).getMain());
     txtDescription.setText(root.getWeather().get(0).getDescription());
            Glide.with(txtIcone.getContext())
                    .load(root.getWeather().get(0).getIcon())
                    .into(txtIcone);
    }
}
