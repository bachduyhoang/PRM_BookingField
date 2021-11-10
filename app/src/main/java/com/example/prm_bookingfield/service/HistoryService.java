package com.example.prm_bookingfield.service;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.prm_bookingfield.dtos.BookingDetail;
import com.example.prm_bookingfield.dtos.BookingHistory;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldSchedule;
import com.example.prm_bookingfield.dtos.MySingleton;
import com.example.prm_bookingfield.dtos.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void getHistory(String idUser, HistoryService.VolleyResponseListener volleyResponseListener){
        String url = COMMON_URL + "Booking/"+idUser;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,  null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<BookingHistory> bookings = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++){
                                JSONObject bookingJson = response.getJSONObject(i); // booking
                                BookingHistory booking = new BookingHistory();
                                List<BookingDetail> details = new ArrayList<>();

                                booking.setBookingId(String.valueOf(bookingJson.getInt("bookingId")));

                                String bDate = bookingJson.getString("createdAt");
                                String bDateSplit[] = bDate.split("T");

                                booking.setCreatedAt(bDateSplit[0] +" "+ bDateSplit[1]);

                                booking.setTotalPrice(String.valueOf(bookingJson.getInt("totalPrice")));
                                booking.setUserForeignKey(bookingJson.getString("userForeignKey"));

                                JSONArray detailArray = bookingJson.getJSONArray("bookingDetails");
                                for(int j = 0; j < detailArray.length(); j++) {
                                    JSONObject detailJson = detailArray.getJSONObject(j); // fieldschedule
                                    BookingDetail detail = new BookingDetail();

                                    String timeStart = detailJson.getString("timeStart");
                                    String timeEnd = detailJson.getString("timeEnd");

                                    String[] start = timeStart.split("T");
                                    String[] end = timeEnd.split("T");

                                    detail.setTimeStart(start[0]+" ("+start[1]);
                                    detail.setTimeEnd(end[1]+")");

                                    detail.setBookingForeignKey(detailJson.getString("bookingForeignKey"));
                                    detail.setFieldForeignKey(detailJson.getString("fieldForeignKey"));
                                    detail.setPrice(detailJson.getString("price"));
                                    details.add(detail);
                                }
                                booking.setBookingDetails(details);
                                bookings.add(booking);
                            }
                            volleyResponseListener.onResponse(bookings);
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
