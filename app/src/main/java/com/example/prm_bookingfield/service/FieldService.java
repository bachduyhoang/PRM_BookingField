package com.example.prm_bookingfield.service;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.appcompat.widget.VectorEnabledTintResources;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FieldService {
    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";

    Context context;

    public FieldService(Context context){
        this.context = context;
    }

    public interface FieldResponse{
        void onError(String msg);
        void onResponse(List<Field> field);
    }

    public void getFieldById(int id, FieldResponse volleyResponseListener){
        String url = URL + "field/"+id;
        Field field = new Field();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,  null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    field.setFieldID(id);
                    field.setFieldName(response.getString("name"));
                    field.setTypeField(response.getInt("typeField"));
                    field.setAddress(response.getString("address"));
                    field.setImagePath(response.getString("imagePath"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(field);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
