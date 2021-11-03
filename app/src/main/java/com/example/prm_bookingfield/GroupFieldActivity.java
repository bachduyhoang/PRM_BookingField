package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.GroupField;
import com.example.prm_bookingfield.dtos.MyAdapter;
import com.example.prm_bookingfield.service.GroupFieldService;

import java.util.ArrayList;
import java.util.List;

public class GroupFieldActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    ArrayList<GroupField> groupFieldArrayList;
    ArrayList<Field> fieldArrayList;
    RecyclerView rvGroupField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_field);
        rvGroupField = findViewById(R.id.rvGroupField);

        groupFieldArrayList = new ArrayList<>();
        fieldArrayList = new ArrayList<>();
        GroupFieldService service = new GroupFieldService(GroupFieldActivity.this);

        service.getListGroupField(new GroupFieldService.ListGroupFieldResponse(){
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }

            @Override
            public void onResponse(List<GroupField> listResponse) {
                groupFieldArrayList = new ArrayList<>();
                fieldArrayList = new ArrayList<>();

                myAdapter = new MyAdapter(GroupFieldActivity.this, (ArrayList<GroupField>) listResponse);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupFieldActivity.this);
                rvGroupField.setLayoutManager(linearLayoutManager);
                rvGroupField.setAdapter(myAdapter);

            }
        });
    }


}