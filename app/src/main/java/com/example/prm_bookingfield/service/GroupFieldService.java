package com.example.prm_bookingfield.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldSchedule;
import com.example.prm_bookingfield.dtos.GroupField;
import com.example.prm_bookingfield.dtos.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupFieldService {

    Context context;

    public static final String COMMON_URL = "https://prmbookingfield.herokuapp.com/api/";

    public GroupFieldService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String msg);

        void onResponse(List<Field> listResponse);
    }

    public void getGroupFieldById(int id, GroupFieldService.VolleyResponseListener volleyResponseListener){
        String url = COMMON_URL + "GroupField/GroupFieldWithField/"+id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,  null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Field> fields = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++){
                                JSONObject childField = (JSONObject) response.getJSONObject(i); // field
                                Field field = new Field();
                                field.setFieldName(childField.getString("name"));
                                field.setTypeField(childField.getInt("typeField"));
                                field.setImagePath(childField.getString("imagePath"));

                                JSONArray schedules = (JSONArray) childField.getJSONArray("fieldSchedules");
                                for(int a = 0; a < schedules.length(); a++) {
                                    JSONObject s = (JSONObject) schedules.getJSONObject(a); // fieldschedule
                                    FieldSchedule schedule = new FieldSchedule();

                                    String timeStart = s.getString("timeStart");
                                    String timeEnd = s.getString("timeEnd");

                                    String[] start = timeStart.split("T");
                                    String demo = start[1];
                                    String[] end = timeEnd.split("T");
                                    String demo2 = end[1];

                                    String startSplit[] = demo.split(":");
                                    String endSplit[] = demo2.split(":");

                                    schedule.setTimeStart(startSplit[0]);
                                    schedule.setTimeEnd(endSplit[0]);

                                    schedule.setOriginPrice(s.getString("originPrice"));
                                    field.setSchedule(schedule);
                                }
                                fields.add(field);
                            }
                            volleyResponseListener.onResponse(fields);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        String url = COMMON_URL + "GroupField/GroupFieldWithField";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<GroupField> groupFields = new ArrayList<>();
                    for(int i = 0; i < response.length(); i++){
                        GroupField parent = new GroupField();
                        List<Field> fields = new ArrayList<>();
                        JSONObject item = (JSONObject) response.getJSONObject(i); //group field
                        parent.setName(item.getString("name"));
                        parent.setAddress(item.getString("address"));
                        parent.setImagePath(item.getString("imagePath"));

                        JSONArray listFields = (JSONArray) item.getJSONArray("fields");
                        for(int j = 0; j < listFields.length(); j++){
                            JSONObject childField = (JSONObject) listFields.getJSONObject(j); // field
                            Field child = new Field();
                            child.setFieldName(childField.getString("name"));
                            child.setTypeField(childField.getInt("typeField"));
                            child.setImagePath(childField.getString("imagePath"));

                            JSONArray schedules = (JSONArray) childField.getJSONArray("fieldSchedules");
                            for(int a = 0; a < schedules.length(); a++) {
                                JSONObject s = (JSONObject) schedules.getJSONObject(a); // fieldschedule
                                FieldSchedule schedule = new FieldSchedule();

                                String timeStart = s.getString("timeStart");
                                String timeEnd = s.getString("timeEnd");

                                String[] start = timeStart.split("T");
                                String[] end = timeEnd.split("T");

                                schedule.setTimeStart(start[1]);
                                schedule.setTimeEnd(end[1]);
                                schedule.setOriginPrice(s.getString("originPrice"));
                                child.setSchedule(schedule);
                            }
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

    public void getGroupFieldByTypeField(int type, GroupFieldService.ListGroupFieldResponse listResponse){
        String url = COMMON_URL + "GroupField/Search/TypeField/"+type;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,  null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<GroupField> groupFields = new ArrayList<>();

                    for(int i = 0; i < response.length(); i++){
                        GroupField parent = new GroupField();
                        List<Field> fields = new ArrayList<>();
                        JSONObject item = (JSONObject) response.getJSONObject(i); //group field
                        parent.setName(item.getString("name"));
                        parent.setAddress(item.getString("address"));
                        parent.setImagePath(item.getString("imagePath"));

                        JSONArray listFields = (JSONArray) item.getJSONArray("fields");
                        for(int j = 0; j < listFields.length(); j++){
                            JSONObject childField = (JSONObject) listFields.getJSONObject(j); // field
                            Field child = new Field();
                            child.setFieldName(childField.getString("name"));
                            child.setTypeField(childField.getInt("typeField"));
                            child.setImagePath(childField.getString("imagePath"));

                            JSONArray schedules = (JSONArray) childField.getJSONArray("fieldSchedules");
                            for(int a = 0; a < schedules.length(); a++) {
                                JSONObject s = (JSONObject) schedules.getJSONObject(a); // fieldschedule
                                FieldSchedule schedule = new FieldSchedule();

                                String timeStart = s.getString("timeStart");
                                String timeEnd = s.getString("timeEnd");

                                String[] start = timeStart.split("T");
                                String[] end = timeEnd.split("T");

                                schedule.setTimeStart(start[1]);
                                schedule.setTimeEnd(end[1]);
                                schedule.setOriginPrice(s.getString("originPrice"));
                                child.setSchedule(schedule);
                            }
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

    public void getGroupFieldByName(String name, GroupFieldService.ListGroupFieldResponse listResponse){
        String url = COMMON_URL + "GroupField/Search/Name/"+name;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,  null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            List<GroupField> groupFields = new ArrayList<>();
                            for(int i = 0; i < array.length(); i++){
                                GroupField parent = new GroupField();
                                List<Field> fields = new ArrayList<>();
                                JSONObject item = (JSONObject) array.getJSONObject(i); //group field
                                parent.setName(item.getString("name"));
                                parent.setAddress(item.getString("address"));
                                parent.setImagePath(item.getString("imagePath"));

                                JSONArray listFields = (JSONArray) item.getJSONArray("fields");
                                for(int j = 0; j < listFields.length(); j++){
                                    JSONObject childField = (JSONObject) listFields.getJSONObject(j); // field
                                    Field child = new Field();
                                    child.setFieldName(childField.getString("name"));
                                    child.setTypeField(childField.getInt("typeField"));
                                    child.setImagePath(childField.getString("imagePath"));

                                    JSONArray schedules = (JSONArray) childField.getJSONArray("fieldSchedules");
                                    for(int a = 0; a < schedules.length(); a++) {
                                        JSONObject s = (JSONObject) schedules.getJSONObject(a); // fieldschedule
                                        FieldSchedule schedule = new FieldSchedule();

                                        String timeStart = s.getString("timeStart");
                                        String timeEnd = s.getString("timeEnd");

                                        String[] start = timeStart.split("T");
                                        String[] end = timeEnd.split("T");

                                        schedule.setTimeStart(start[1]);
                                        schedule.setTimeEnd(end[1]);
                                        schedule.setOriginPrice(s.getString("originPrice"));
                                        child.setSchedule(schedule);
                                    }

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
