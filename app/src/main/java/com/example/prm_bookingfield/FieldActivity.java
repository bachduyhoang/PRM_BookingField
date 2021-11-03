package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.prm_bookingfield.dtos.CustomListFields;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.service.FieldService;

import java.util.List;

public class FieldActivity extends AppCompatActivity {

    private CustomListFields _listViewAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        listView = findViewById(R.id.listViewFields);

        FieldService service = new FieldService(FieldActivity.this);

        service.getListField(new FieldService.ListFieldResponse(){
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }

            @Override
            public void onResponse(List<Field> listResponse) {
                _listViewAdapter = new CustomListFields(FieldActivity.this, listResponse);
                listView.setAdapter(_listViewAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Field selectItem = (Field) parent.getItemAtPosition(position);
                        Log.e("Select Item: ", selectItem.getFieldName());
//                        Intent intent = new Intent(MainActivity.this, UpdateCreditActivity.class);
//                        intent.putExtra("credit", selectIteam);
//                        MainActivity.this.startActivityForResult(intent,REQUEST_CODE);
                    }

                });

            }
        });
    }
}