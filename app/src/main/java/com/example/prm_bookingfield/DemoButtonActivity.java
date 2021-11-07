package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.prm_bookingfield.adapters.CustomGridViewBookingButton;
import com.example.prm_bookingfield.adapters.CustomListViewCart;
import com.example.prm_bookingfield.dtos.ButtonToggle;
import com.example.prm_bookingfield.dtos.CartTimePicker;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.example.prm_bookingfield.service.ManagePrefConfig;

import java.util.ArrayList;
import java.util.List;

public class DemoButtonActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_button);

        listView = (ListView) findViewById(R.id.demoListView);
        listView.setAdapter(new CustomListViewCart(this, getList()));
    }

    public List<ItemInCart> getList(){
        List<ItemInCart> list = new ArrayList<>();
        ItemInCart item = new ItemInCart();
        item.setFieldID("1");
        List<CartTimePicker> cartTimePickers = new ArrayList<>();
        cartTimePickers.add(new CartTimePicker("2",4));
        cartTimePickers.add(new CartTimePicker("3",4));
        cartTimePickers.add(new CartTimePicker("4",4));
        item.setTimePicker(cartTimePickers);
        item.setAddress("HN");
        item.setFiledName("A");
        item.setGroupFiledName("Hoang");
        item.setTotal(100);
        item.setTypeField(5);
        return list;
    }
}