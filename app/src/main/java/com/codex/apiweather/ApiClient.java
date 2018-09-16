package com.codex.apiweather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static String baseUrl = "https://api.openweathermap.org";

    public static Retrofit getClient(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;

    }
}
