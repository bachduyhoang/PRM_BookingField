package com.example.prm_bookingfield.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.se.omapi.Session;

import com.example.prm_bookingfield.LoginActivity;
import com.example.prm_bookingfield.data.model.ItemInCart;
import com.example.prm_bookingfield.dtos.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManagePrefConfig {
    private static final String SHARED_PREFERENCES = "MySharedPref";
    private static final String LIST_CART = "CART";
    private static final String JWT_TOKEN = "jwt_token";

    private static ManagePrefConfig mInstance;
    private static Context mCtx;

    public ManagePrefConfig(Context context){
        mCtx = context;
    }

    public ManagePrefConfig(){

    }

    public static synchronized ManagePrefConfig getInstance(Context context){
        if(mInstance == null){
            mInstance = new ManagePrefConfig(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences preferences = mCtx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = preferences.edit();
        myEdit.putString(JWT_TOKEN, user.getJwtToken());
        myEdit.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences preferences = mCtx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString(JWT_TOKEN,null) != null;
    }

    public User getToken(){
        SharedPreferences preferences = mCtx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return new User(preferences.getString(JWT_TOKEN, null));
    }

    public void userLogout(User user){
        SharedPreferences preferences = mCtx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = preferences.edit();
        myEdit.clear();
        myEdit.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }


    public void writeCartPref(Context context, List<ItemInCart> carts){
        Gson gson = new Gson();
        String jsonCart = gson.toJson(carts);

        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = preferences.edit();
        myEdit.putString(LIST_CART, jsonCart);
        myEdit.commit();
    }

    public List<ItemInCart> readListCartFromPref(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String jsonCart = preferences.getString(LIST_CART,"");

        if(!"".equals(jsonCart)){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ItemInCart>>() {}.getType();
            List<ItemInCart> cart = gson.fromJson(jsonCart,type);
            return cart;
        }
        return null;
    }

    public boolean addItemToCart(Context context, ItemInCart item){
        List<ItemInCart> cart = readListCartFromPref(context);
        if(cart != null){
            cart.add(item);
            writeCartPref(context, cart);
        }
        return  true;
    }
}
