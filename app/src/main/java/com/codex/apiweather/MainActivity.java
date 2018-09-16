package com.codex.apiweather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codex.apiweather.weather_response.Weather;
import com.codex.apiweather.weather_response.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button showResultButton;
    EditText cityET;
    TextView cityName,cityTemperature;

    String apiKey ="d31ac27d3e383a7baf33b5038b945844";
    ProgressDialog progressDialog;
    //ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showResultButton = findViewById(R.id.show_weather_button);
        cityET = findViewById(R.id.city_code_ET);
        cityName = findViewById(R.id.city_name_view);
        cityTemperature = findViewById(R.id.temp_view);
        //progressBar = new ProgressBar(this);
        progressDialog = new ProgressDialog(this);
        showResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cityET.getText().toString().isEmpty()){
                   // progressBar.setVisibility(View.VISIBLE);
                    progressDialog.show();
                    ApiEndpoint apiEndpoint = ApiClient.getClient().create(ApiEndpoint.class);
                    Call<WeatherResponse> call = apiEndpoint.getCurrentWeather(cityET.getText().toString(),apiKey);
                    call.enqueue(new Callback<WeatherResponse>() {
                        @Override
                        public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                            //progressBar.setVisibility(View.INVISIBLE);
                            progressDialog.dismiss();
                            WeatherResponse weatherResponse = response.body();
                            if (weatherResponse!=null){
                                cityName.setText(weatherResponse.getName());
                                String temperature = String.format("%.0f",weatherResponse.getMain().getTemp());
                                cityTemperature.setText(temperature);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<WeatherResponse> call, Throwable t) {
                           // progressBar.setVisibility(View.INVISIBLE);
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }



}
