package com.codex.apiweather;

import com.codex.apiweather.weather_response.Weather;
import com.codex.apiweather.weather_response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("/data/2.5/weather")
    Call<WeatherResponse> getCurrentWeather(@Query("id") String id, @Query("APPID") String apiKey);
}
