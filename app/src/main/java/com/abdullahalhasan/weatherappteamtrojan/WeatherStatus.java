package com.abdullahalhasan.weatherappteamtrojan;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Abdullah Al Hasan on 8/2/2016.
 */
public class WeatherStatus {
    String url;


    public WeatherStatus(String url) {
        this.url = url;
    }

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray weatherArray = response.getJSONArray("weather");
                JSONObject jsonObject = weatherArray.getJSONObject(1);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }


    });

}
