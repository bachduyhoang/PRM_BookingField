package com.example.prm_bookingfield.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prm_bookingfield.dtos.CartItemViewRequest;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOutService{
    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";

    Context context;

    public CheckOutService(Context context){
        this.context = context;
    }

    public interface CheckOutServiceListener{
        void onError(String msg);
        void onResponse(String success);
    }

    public void checkOut(Context context, List<CartItemViewRequest> cart, CheckOutService.CheckOutServiceListener check){

        String url = URL + "Booking";

        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                check.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                check.onError(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Gson gson = new Gson();
                String listCart = gson.toJson(cart);
                params.put("listCart",listCart);
                params.put("percentDiscount","0");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9tb2JpbGVwaG9uZSI6IjEyMzQ1Njc4OSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWVpZGVudGlmaWVyIjoiZWRhNGE3MmEtNGQ0OC00ZjI1LThjNWEtYjc5YTc1ZWU0Yzk0IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiIiwiZXhwIjoxNjM2MzgyNDM2LCJpc3MiOiJodHRwczovL2JhY2hkdXlob2FuZy5jb20iLCJhdWQiOiJodHRwczovL2JhY2hkdXlob2FuZy5jb20ifQ.tfipL5DUoxIX4GyA6OSVcKJX_J-f9N9RH5TpGmr016I");
                return params;
            }
        };
    }

    public interface CheckOutDetailServiceListener{
        void onError(String msg);
        void onResponse(String success);
    }

    public void checkOutDetail(String checkOutId ,Context context, List<CartItemViewRequest> cart, CheckOutService.CheckOutServiceListener check){
        String url = URL + "BookingDetail";

        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                check.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                check.onError(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Gson gson = new Gson();
                String listCart = gson.toJson(cart);
                params.put("listCart",listCart);
                params.put("idBooking",checkOutId);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9tb2JpbGVwaG9uZSI6IjEyMzQ1Njc4OSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWVpZGVudGlmaWVyIjoiZWRhNGE3MmEtNGQ0OC00ZjI1LThjNWEtYjc5YTc1ZWU0Yzk0IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiIiwiZXhwIjoxNjM2MzgyNDM2LCJpc3MiOiJodHRwczovL2JhY2hkdXlob2FuZy5jb20iLCJhdWQiOiJodHRwczovL2JhY2hkdXlob2FuZy5jb20ifQ.tfipL5DUoxIX4GyA6OSVcKJX_J-f9N9RH5TpGmr016I");
                return params;
            }
        };
    }


}


