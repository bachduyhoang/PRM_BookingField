package com.example.prm_bookingfield.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prm_bookingfield.dtos.CartItemViewRequest;
import com.example.prm_bookingfield.dtos.CheckOutRequest;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.example.prm_bookingfield.dtos.MySingleton;
import com.example.prm_bookingfield.dtos.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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


    public void checkOut(Context context, List<CartItemViewRequest> cart, CheckOutService.CheckOutServiceListener check) {
        String url = URL + "Booking/checkout/booking";
        ManagePrefConfig mng = new ManagePrefConfig();
        User u = mng.getToken();
        if(u == null){
            Toast.makeText(context, "Please login first!", Toast.LENGTH_SHORT).show();
        }else {

            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    check.onError(error.toString());
                    if (error == null || error.networkResponse == null) {
                        return;
                    }

                    String body;
                    //get status code here
                    final String statusCode = String.valueOf(error.networkResponse.statusCode);
                    //get response body and parse with appropriate encoding
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                        Log.e("body", body);
                    } catch (UnsupportedEncodingException e) {
                        Log.e("error", e.toString());
                    }
                    Log.e("OK",error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Gson gson = new Gson();
                    String listCart = gson.toJson(cart);
                    params.put("listCart", listCart);
                    return params;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    Gson gson = new Gson();
                    String listCart = gson.toJson(cart);
                    params.put("listCart", listCart);
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("listCart", cart);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final String requestBody = jsonBody.toString();
                    String a = new JSONObject(params).toString().replace("\\","");
                    int b = a.indexOf("[");
                    int c = a.indexOf("]");

                    char[] myNameChars = a.toCharArray();
                    myNameChars[b-1] = ' ';
                    myNameChars[c+1] = ' ';
                    String myName = String.valueOf(myNameChars);
                    return  myName.getBytes();
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "Bearer " + u.getJwtToken());
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(sr);
    }}

    public interface CheckOutDetailServiceListener{
        void onError(String msg);
        void onResponse(String success);
    }

    public void checkOutDetail(String checkOutId, Context context, List<CartItemViewRequest> cart, CheckOutService.CheckOutDetailServiceListener check){
        String url = URL + "BookingDetail/authenticate";
        ManagePrefConfig mng = new ManagePrefConfig();
        User u = mng.getToken();

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
                params.put("bookingId", checkOutId);
                params.put("listCart", listCart);
                return params;
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Gson gson = new Gson();
                String listCart = gson.toJson(cart);
                params.put("bookingId",checkOutId);
                params.put("listCart", listCart);
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("listCart", cart);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String requestBody = jsonBody.toString();
                String a = new JSONObject(params).toString().replace("\\","");
                int b = a.indexOf("[");
                int c = a.indexOf("]");

                char[] myNameChars = a.toCharArray();
                myNameChars[b-1] = ' ';
                myNameChars[c+1] = ' ';
                String myName = String.valueOf(myNameChars);
                return  myName.getBytes();
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + u.getJwtToken());
                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(sr);
    }


}


