package com.example.voyageur.model.converter;

import java.io.Serializable;

public class Rates implements Serializable {
    public double USD;

    public Rates(double USD) {
        this.USD = USD;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }
}


