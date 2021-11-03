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

    public interface VolleyResponseListener{
        void onError(String msg);

        void onResponse(JSONObject objectResponse);
    }

    public void getFieldById(int id, VolleyResponseListener volleyResponseListener){
        String url = URL + "field/"+id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,  null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface ListFieldResponse{
        void onError(String msg);

        void onResponse(List<Field> listResponse);
    }

    public void getListField(ListFieldResponse listResponse){
        List<Field> fields = new ArrayList<>();
        String url = URL + "field/";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,  null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
//                    JSONArray listField = response.getJSONObject("list")
                    for(int i = 0; i < response.length(); i++){
                        Field field = new Field();
                        JSONObject item = (JSONObject) response.getJSONObject(i);
                        field.setFieldID(item.getInt("fieldId"));
                        field.setFieldName(item.getString("name"));
                        field.setAddress(item.getString("address"));
//                        field.setTypeField(item.getInt("typeField"));
                        field.setImagePath(item.getString("imagePath"));
                        field.setImageName(item.getString("imageName"));
                        field.setCreateAt(item.getString("createdAt"));
                        field.setStatus(item.getBoolean("status"));
                        field.setDeleteAt(item.getString("deletedAt"));
                        field.setHot(item.getBoolean("isHot"));
                        field.setTopHot(item.getInt("topHot"));
                        field.setGroupFieldID(item.getInt("groupFieldForeinKey"));
                        field.setUserID(item.getString("userForeignKey"));

                        fields.add(field);
                    }
                    listResponse.onResponse(fields);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                listResponse.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
