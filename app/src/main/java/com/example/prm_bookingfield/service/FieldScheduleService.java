package com.example.prm_bookingfield.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FieldScheduleService {
    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";

    Context context;

    public FieldScheduleService(Context context){
        this.context = context;
    }

    public interface FieldScheduleResponse{
        void onError(String msg);
        void onResponse(List<String> schedule);
    }

    public void getScheduleByIdAndTime(int id, String date,FieldScheduleService.FieldScheduleResponse fieldScheduleResponse){
        String url = URL + "FieldSchedule/ScheduleAvailable/"+id +"?bookDate="+date;
        List<String> schedule = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++)
                    try {
                        schedule.add(response.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                fieldScheduleResponse.onResponse(schedule);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                fieldScheduleResponse.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
