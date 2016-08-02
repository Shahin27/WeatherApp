package com.abdullahalhasan.weatherappteamtrojan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Home extends AppCompatActivity {

    public static TextView weatherStatTV;
    public static TextView temperatureTV;
    public static TextView weatherDescriptionTV;
    public static TextView pressureTV;
    public static TextView humidityTV;
    public static TextView temperatureMinTV;
    public static TextView temperatureMaxTV;
    public static TextView windSpeedTV;
    public static TextView windAngleTV;
    public static TextView cityNameTV;
    public static TextView countryNameTV;

    private String url = "http://api.openweathermap.org/data/2.5/weather?lat=23.7104&lon=90.40744&appid=abe177becc14b8ba94a7f11e0bd2e1bb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        weatherStatTV = (TextView) findViewById(R.id.weatherStatusText);
        temperatureTV = (TextView) findViewById(R.id.temperatureText);
        weatherDescriptionTV = (TextView) findViewById(R.id.descriptionTV);
        pressureTV = (TextView) findViewById(R.id.pressureText);
        humidityTV = (TextView) findViewById(R.id.humidityText);
        temperatureMinTV = (TextView) findViewById(R.id.tempMinText);
        temperatureMaxTV = (TextView) findViewById(R.id.tempMaxText);
        windSpeedTV = (TextView) findViewById(R.id.windSpeedText);
        windAngleTV = (TextView) findViewById(R.id.windAngleText);
        cityNameTV = (TextView) findViewById(R.id.cityNameText);
        countryNameTV = (TextView) findViewById(R.id.countryText);



        getWeaterStatus();


    }

    private void getWeaterStatus() {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray weatherArray = response.getJSONArray("weather");

                        JSONObject weatherObject = weatherArray.getJSONObject(0);
                        String weatherStatus = weatherObject.getString("main");
                        String weatherDescription = weatherObject.getString("description");
                        weatherStatTV.setText(weatherStatus);
                        weatherDescriptionTV.setText(weatherDescription);



                    JSONObject mainObject = response.getJSONObject("main");

                    int temp = mainObject.getInt("temp");
                    temp = temp - 273;
                    temperatureTV.setText(String.valueOf(temp)+"ºC");

                    int pressure = mainObject.getInt("pressure");
                    pressureTV.setText(String.valueOf(pressure)+"mBar");

                    int humidity = mainObject.getInt("humidity");
                    humidityTV.setText(String.valueOf(humidity)+"%");

                    int tempMin = mainObject.getInt("temp_min");
                    tempMin = tempMin - 273;
                    temperatureMinTV.setText(String.valueOf(tempMin)+"ºC");

                    int tempMax = mainObject.getInt("temp_max");
                    tempMax = tempMax - 273;
                    temperatureMaxTV.setText(String.valueOf(tempMax)+"ºC");

                    JSONObject windObject = response.getJSONObject("wind");
                    Double windSpeed = windObject.getDouble("speed");
                    int speed = (int) (windSpeed*2.2369);
                    windSpeedTV.setText(String.valueOf(speed)+"MPH");

                    int windAngle = windObject.getInt("deg");
                    windAngleTV.setText(String.valueOf(windAngle)+"º");

                    JSONObject sysObj = response.getJSONObject("sys");
                    String countryName = sysObj.getString("country");
                    countryNameTV.setText(countryName);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {

                }

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

}
