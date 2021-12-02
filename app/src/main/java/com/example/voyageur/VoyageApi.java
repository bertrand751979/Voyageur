package com.example.voyageur;

import com.example.voyageur.model.converter.Root;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class VoyageApi {
    public interface VoyageService{
        @GET("latest")
        Call<Root> getRates(@Query( "access_key" ) String q );


        @GET("weather")
        //J'ai mis deux query car il ya 2 parametre a envoyer a la requete le nom de la ville et la clef
        Call<com.example.voyageur.modelweather.Root> getWeather(@Query("q") String q ,@Query("appid") String appid);
    }

    private final static String BASE_URL="http://data.fixer.io/api/";
    private final static String BASE_URL_TWO="https://api.openweathermap.org/data/2.5/";

    private static VoyageApi INSTANCE = null;
    private VoyageApi(){}
    public static VoyageApi getInstance(){
        if(INSTANCE == null){
            INSTANCE = new VoyageApi();
        }
        return INSTANCE;
    }

    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
    public Retrofit getClientTwo(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_TWO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }



}












