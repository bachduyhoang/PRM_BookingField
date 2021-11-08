package com.example.prm_bookingfield.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.prm_bookingfield.dtos.BookingHistory;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldSchedule;
import com.example.prm_bookingfield.dtos.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    Context context;

    public static final String COMMON_URL = "https://prmbookingfield.herokuapp.com/api/";

    public HistoryService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String msg);

        void onResponse(List<BookingHistory> listResponse);
    }

    public void getGroupFieldById(String idUser, GroupFieldService.VolleyResponseListener volleyResponseListener){
        String url = COMMON_URL + "Booking/"+idUser;

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


}
