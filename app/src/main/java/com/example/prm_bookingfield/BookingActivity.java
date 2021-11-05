package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.prm_bookingfield.data.model.ItemInCart;
import com.example.prm_bookingfield.service.FieldService;
import com.example.prm_bookingfield.service.ManagePrefConfig;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private TextView txtSelectDate;
    private DatePickerDialog datePickerDialog;
    private int year, month, day;
    private Calendar calendar;
    String now;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = this.getIntent();
        String fieldID = intent.getStringExtra("fieldID");


        calendar = Calendar.getInstance();
        txtSelectDate = findViewById(R.id.actBooking_btnCalendar);
        toggleButton=findViewById(R.id.tgBtn);

        now = calendar.get(Calendar.DAY_OF_MONTH) +
                "/" + (calendar.get(Calendar.MONTH) + 1) +
                "/" + calendar.get(Calendar.YEAR);
        txtSelectDate.setText(now);
        System.out.println("Now:"+now);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    public void renderField(String fieldId){
        FieldService fieldService = new FieldService(this.getApplicationContext());
//        fieldService.getFieldById();
    }

    public void clickToChangeDate(View view) {
        String[] selectedDate = txtSelectDate.getText().toString().split("/");
        if(!txtSelectDate.getText().equals(now)){
            day = Integer.parseInt(selectedDate[0]);
            month = Integer.parseInt(selectedDate[1])-1;
            year = Integer.parseInt(selectedDate[2]);
        }
        System.out.println(day + "/" + month + "/" + year);
        datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                txtSelectDate.setText(d + "/" + (m + 1) + "/" + y);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

        toggleButton.setBackgroundColor(ContextCompat.getColor(BookingActivity.this, R.color.green));
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton.isChecked()){

                }else{

                }
            }
        });
    }

    public void clickToAddToCart(View view) {
        ManagePrefConfig managePrefConfig = new ManagePrefConfig();
        ItemInCart item = new ItemInCart();
        managePrefConfig.addItemToCart(this.getApplicationContext(), item);

        Intent intent=new Intent(BookingActivity.this,MainActivity.class);
        intent.putExtra("action","add to cart");
        startActivity(intent);
    }
}