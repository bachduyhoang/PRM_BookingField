//package com.example.prm_bookingfield.service;
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.example.prm_bookingfield.dtos.MySingleton;
//import com.example.prm_bookingfield.dtos.User;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class UserService {
//    public static final String URL = "https://prmbookingfield.herokuapp.com/api/";
//
//    Context context;
//
//    public UserService(Context context){
//        this.context = context;
//    }
//
//    public interface VolleyResponseListener{
//        void onError(String msg);
//
//        void onResponse(User user);
//    }
//
//    public void getUserInformation(VolleyResponseListener volleyResponseListener){
//        String url = URL + "Users/GetUserInfor";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, null,
//                url, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                User u = new User(response);
//                volleyResponseListener.onResponse(response);
//            }
//        },new Response.ErrorListener(){
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                volleyResponseListener.onError(error.toString());
//            }
//        });
//        MySingleton.getInstance(context).addToRequestQueue(request);
//    }
//}
