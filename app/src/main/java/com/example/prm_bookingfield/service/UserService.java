package com.example.prm_bookingfield.service;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.prm_bookingfield.dtos.CartItemViewRequest;
import com.example.prm_bookingfield.dtos.MySingleton;
import com.example.prm_bookingfield.dtos.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";

    Context context;

    public UserService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String msg);

        void onResponse(User user);
    }

    public void getUserInformation(VolleyResponseListener userResponse){

        String url = URL + "Users/GetUserInfor";
        ManagePrefConfig mng = new ManagePrefConfig();
        User u = mng.getToken();

        StringRequest sr = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String fn = obj.getString("firstName");
                    String ln = obj.getString("lastName");
                    String phoneNumber = obj.getString("phoneNumber");
                    String idUser = obj.getString("id");
                    User user = new User();
                    user.setFirstName(fn);
                    user.setLastName(ln);
                    user.setPhone(phoneNumber);
                    user.setUserId(idUser);
                    userResponse.onResponse(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userResponse.onError(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", "Bearer " + u.getJwtToken());
                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(sr);
    }

}
