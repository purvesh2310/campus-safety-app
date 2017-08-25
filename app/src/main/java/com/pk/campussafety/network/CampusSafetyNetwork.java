package com.pk.campussafety.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Purvesh on 4/11/2017.
 */

public class CampusSafetyNetwork {

    public void makeRequestWithVolley(Context context, String url, final double latitude, final double longitude, final String currentSection) {

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Handle response as per the requirement and sent by the server

                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Network Error","Network Error Occurred..");
                    }
                 }){
                    @Override
                    // Passing current location as latitude, longitude and room name to the network
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();

                        params.put("latitude",String.valueOf(latitude));
                        params.put("longitude",String.valueOf(longitude));
                        params.put("section",currentSection);

                        return params;
                    }};

        queue.add(stringRequest);
    }
}
