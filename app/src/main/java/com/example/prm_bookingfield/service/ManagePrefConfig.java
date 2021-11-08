package com.example.prm_bookingfield.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.prm_bookingfield.dtos.ItemInCart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManagePrefConfig {
    private static final String SHARED_PREFERENCES = "MySharedPref";
    private static final String LIST_CART = "CART";

    public void writeCartPref(Context context, List<ItemInCart> carts){
        Gson gson = new Gson();
        String jsonCart = gson.toJson(carts);

        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = preferences.edit();
        myEdit.putString(LIST_CART, jsonCart);
        myEdit.apply();
    }

    public void removeCart(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = preferences.edit();
        myEdit.putString(LIST_CART, null);
        myEdit.apply();
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

    public void addItemToCart(Context context, ItemInCart item){
        List<ItemInCart> cart = readListCartFromPref(context);
        if(cart == null){
            cart = new ArrayList<>();
        }
        if(cart != null){
            cart.add(item);
            writeCartPref(context, cart);
        }
    }

    public ItemInCart checkItemExist(Context context ,String fieldId, String date){
        List<ItemInCart> listCart = readListCartFromPref(context);
        if(listCart == null){
            return null;
        }else {
            for (int i = 0; i < listCart.size(); i++) {
                ItemInCart item = listCart.get(i);
                if(item.getFieldID().equalsIgnoreCase(fieldId) && item.getBookDate().equals(date)){
                    return item;
                }
            }
        }
        return null;
    }

    public void replaceItemInCart(Context context, ItemInCart newItem, String fieldId, String date){
        List<ItemInCart> listCart = readListCartFromPref(context);
        if(listCart != null){
            for (int i = 0; i < listCart.size(); i++) {
                ItemInCart item = listCart.get(i);
                if(item.getFieldID().equalsIgnoreCase(fieldId) && item.getBookDate().equals(date)){
                    item.setTimePicker(newItem.getTimePicker());
                    break;
                }
            }
            writeCartPref(context, listCart);
        }
    }

    public void removeItemInCart(Context context, String fieldId, String date){
        List<ItemInCart> listCart = readListCartFromPref(context);
        if(listCart != null){
            for (int i = 0; i < listCart.size(); i++) {
                ItemInCart item = listCart.get(i);
                if(item.getFieldID().equalsIgnoreCase(fieldId) && item.getBookDate().equals(date)){
                    listCart.remove(i);
                    break;
                }
            }
            writeCartPref(context, listCart);
        }
    }


}
