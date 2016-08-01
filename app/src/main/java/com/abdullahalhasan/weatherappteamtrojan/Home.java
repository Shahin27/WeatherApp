package com.abdullahalhasan.weatherappteamtrojan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    public static TextView weatherStatTV;
    public static TextView placeNameTV;
    public static TextView weatherDescriptionTV;

    private String url = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=abe177becc14b8ba94a7f11e0bd2e1bb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        weatherStatTV = (TextView) findViewById(R.id.weatherStatusText);
        placeNameTV = (TextView) findViewById(R.id.placeNameText);
        weatherDescriptionTV = (TextView) findViewById(R.id.descriptionTV);
        getWeaterStatus();


    }

    private void getWeaterStatus() {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray weatherArray = response.getJSONArray("weather");
                    for (int i = 0; i < weatherArray.length(); i++) {
                        JSONObject weatherObject = weatherArray.getJSONObject(i);
                        String weatherStatus = weatherObject.getString("main");
                        String weatherDescription = weatherObject.getString("description");
                        weatherStatTV.setText(weatherStatus);
                        weatherDescriptionTV.setText(weatherDescription);
                        //Toast.makeText(Home.this, name, Toast.LENGTH_SHORT).show();
                    }
                    JSONArray jsonArray = response.getJSONArray("main");

                    Double temp = jsonArray.getDouble(0);



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
