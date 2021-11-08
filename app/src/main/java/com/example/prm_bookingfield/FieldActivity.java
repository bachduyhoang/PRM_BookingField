package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.prm_bookingfield.dtos.CustomListFields;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldItemAdapter;
import com.example.prm_bookingfield.dtos.GroupField;
import com.example.prm_bookingfield.dtos.MyAdapter;
import com.example.prm_bookingfield.service.FieldService;
import com.example.prm_bookingfield.service.GroupFieldService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FieldActivity extends AppCompatActivity {

    ArrayList<Field> listFields = new ArrayList<>();
    private RecyclerView rv_listFieldItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        rv_listFieldItem = findViewById(R.id.rv_listFieldItem);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        GroupField groupField = (GroupField) bundle.get("GroupFieldSelected");

        GroupFieldService service = new GroupFieldService(FieldActivity.this);
        service.getGroupFieldById(1, new GroupFieldService.VolleyResponseListener() {
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }

            @Override
            public void onResponse(List<Field> listResponse) {
                ArrayList<Field> fields = (ArrayList<Field>) listResponse;

                FieldItemAdapter fieldItem = new FieldItemAdapter(FieldActivity.this, fields);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FieldActivity.this, RecyclerView.VERTICAL,false);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(FieldActivity.this, DividerItemDecoration.VERTICAL);

                rv_listFieldItem.setLayoutManager(linearLayoutManager);
                rv_listFieldItem.setAdapter(fieldItem);
                rv_listFieldItem.addItemDecoration(itemDecoration);
            }
        });
    }
}