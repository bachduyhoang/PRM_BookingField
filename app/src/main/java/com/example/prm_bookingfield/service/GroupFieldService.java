package com.example.prm_bookingfield.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.GroupField;
import com.example.prm_bookingfield.dtos.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupFieldService {

    Context context;

    public GroupFieldService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String msg);

        void onResponse(JSONObject objectResponse);
    }

    public void getGroupFieldById(int id, GroupFieldService.VolleyResponseListener volleyResponseListener){
        String url = "https://prmbookingfield.herokuapp.com/api/GroupField/"+id;

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

    public interface ListGroupFieldResponse{
        void onError(String msg);

        void onResponse(List<GroupField> listResponse);
    }

    public void getListGroupField(GroupFieldService.ListGroupFieldResponse listResponse){
        String url = "https://prmbookingfield.herokuapp.com/api/GroupField/GroupFieldWithField";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<GroupField> groupFields = new ArrayList<>();
                    for(int i = 0; i < response.length(); i++){
                        GroupField parent = new GroupField();
                        List<Field> fields = new ArrayList<>();
                        JSONObject item = (JSONObject) response.getJSONObject(i);
                        parent.setName(item.getString("name"));
                        parent.setAddress(item.getString("address"));
                        parent.setImagePath(item.getString("imagePath"));

                        JSONArray listFields = (JSONArray) item.getJSONArray("fields");
                        for(int j = 0; j < listFields.length(); j++){
                            JSONObject childField = (JSONObject) listFields.getJSONObject(j);
                            Field child = new Field();
                            child.setFieldName(childField.getString("name"));
                            child.setTypeField(childField.getInt("typeField"));
                            fields.add(child);

                        }
                        parent.setFields(fields);
                        groupFields.add(parent);
                    }
                    listResponse.onResponse(groupFields);
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
