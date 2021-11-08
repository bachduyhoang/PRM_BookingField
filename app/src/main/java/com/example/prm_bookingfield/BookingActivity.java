package com.example.prm_bookingfield;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.prm_bookingfield.adapters.CustomGridViewBookingButton;
import com.example.prm_bookingfield.dtos.ButtonToggle;
import com.example.prm_bookingfield.dtos.CartTimePicker;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldAndSchedule;
import com.example.prm_bookingfield.service.FieldScheduleService;
import com.example.prm_bookingfield.service.FieldService;
import com.example.prm_bookingfield.service.ManagePrefConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView txtGroupFieldNameAndFieldName;
    private TextView txtAddress;
    private FloatingActionButton btnBack;
    private TextView txtTypeField;
    private TextView txtDate;
    private TextView txtSelectDate;
    private TextView txtPrice;
    private Button btnBook;
    private int year, month, day;
    private String date;
    private String fieldId;
    private String groupField;
    private boolean isExist = false;
    private List<ButtonToggle> listCheck = new ArrayList<>();
    private CustomGridViewBookingButton customGridViewBookingButton ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = this.getIntent();
        fieldId = intent.getStringExtra("fieldID");
        fieldId = "1";
        date = intent.getStringExtra("date");
        groupField = intent.getStringExtra("groupField");
        String price = intent.getStringExtra("price");
        price = "5";

        if(date == null){
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            month = Calendar.getInstance().get(Calendar.MONTH);
            year = Calendar.getInstance().get(Calendar.YEAR);
            date = day+"/"+(month+1)+"/"+year;
        }else{
            day = Integer.parseInt(date.split("/")[0]);
            month = Integer.parseInt(date.split("/")[1]);
            year = Integer.parseInt(date.split("/")[2]);
            date = day+"/"+month+"/"+year;
        }

        btnBack = findViewById(R.id.actBooking_btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        txtPrice = findViewById(R.id.txtPriceOfDetail);
        txtDate = findViewById(R.id.actBooking_txtDate);
        txtTypeField = findViewById(R.id.actBooking_Type);
        txtDate.setText(day +"/"+(month +1) + "/"+ year);
        txtGroupFieldNameAndFieldName = findViewById(R.id.actBooking_txtFieldName);
        txtAddress = findViewById(R.id.actBooking_txtAddress);
        txtSelectDate = findViewById(R.id.actBooking_btnCalendar);
        txtSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        btnBook = findViewById(R.id.actBooking_btnAddToCart);

        //Get field and schedule of it and set to GridView
        FieldAndSchedule fieldAndSchedule = new FieldAndSchedule();
        FieldService fieldService = new FieldService(BookingActivity.this);
        fieldService.getFieldById(Integer.parseInt(fieldId), new FieldService.FieldResponse(){
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }

            @Override
            public void onResponse(Field fieldResponse) {
                Field field = new Field();
                field.setFieldID(fieldResponse.getFieldID());
                field.setFieldName(fieldResponse.getFieldName());
                field.setTypeField(fieldResponse.getTypeField());
                field.setAddress(fieldResponse.getAddress());
                field.setImagePath(fieldResponse.getImagePath());
                fieldAndSchedule.setField(field);
                txtGroupFieldNameAndFieldName.setText(field.getFieldName());
                txtTypeField.setText("Type Field:" + field.getTypeField());
                txtPrice.setText("5");
                txtAddress.setText(field.getAddress());

                btnBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ItemInCart item = new ItemInCart();
                        List<String> listChecked = customGridViewBookingButton.getListChecked();
                        item.setFieldID(String.valueOf(field.getFieldID()));
                        item.setImageUrl(field.getImagePath());
                        item.setAddress(field.getAddress());
                        item.setBookDate(date);
                        item.setTypeField(field.getTypeField());
                        item.setFiledName(field.getFieldName());
                        item.setGroupFiledName(String.valueOf(field.getGroupFieldID()));

                        List<CartTimePicker> timePickerList = new ArrayList<>();
                        for (int i = 0; i < listChecked.size(); i++) {
                            CartTimePicker itemCartTimePicker = new CartTimePicker(listChecked.get(i).toString(), Float.parseFloat(txtPrice.getText().toString()));
                            timePickerList.add(itemCartTimePicker);
                        }
                        item.setTimePicker(timePickerList);
                        clickToAddToCart(view, item);
                    }
                });
            }
        });
        FieldScheduleService fieldScheduleService = new FieldScheduleService(BookingActivity.this);

        fieldScheduleService.getScheduleByIdAndTime(Integer.parseInt(fieldId), date, new FieldScheduleService.FieldScheduleResponse() {
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }
            @Override
            public void onResponse(List<String> scheduleResponse) {
                fieldAndSchedule.setListFieldSchedule(scheduleResponse);
                isExist = checkItemIsExist(fieldId,date) == null ? false: true;
                List<ButtonToggle> listButtonRender = new ArrayList<>();
                if(isExist){
                    List<String> listButtonCheck = checkItemIsExist(fieldId,date);
                    listButtonRender = transferListStringToListButtonToggleChecked(fieldAndSchedule.getListFieldSchedule(), listButtonCheck);
                }else {
                    listButtonRender = transferListStringToListButtonToggle(fieldAndSchedule.getListFieldSchedule());
                }
                final GridView gridView = (GridView) findViewById(R.id.actBooking_gridViewButton);
                if(fieldAndSchedule != null ){
                    if(listButtonRender != null){
                        customGridViewBookingButton =new CustomGridViewBookingButton(BookingActivity.this,listButtonRender );
                        gridView.setAdapter(customGridViewBookingButton);
                    }
                }
            };
        });


    }

    public List<ButtonToggle> transferListStringToListButtonToggleChecked(List<String> listSchedule, List<String> listChecked){
        List<ButtonToggle> result = null;
        if(listSchedule != null){
            result = new ArrayList<>();
            for (int i = 0; i < listSchedule.size(); i++) {
                String schedule = listSchedule.get(i);
                ButtonToggle b = new ButtonToggle();
                b.setTime(listSchedule.get(i));
                b.setStatus(false);
                for (int j = 0; j < listChecked.size(); j++) {
                    String checked = listChecked.get(j);
                    if(schedule.equalsIgnoreCase(checked)){
                        b.setStatus(true);
                    }
                }
                result.add(b);
            }
        }
        return result;
    }

    public List<ButtonToggle> transferListStringToListButtonToggle(List<String> listSchedule){
        List<ButtonToggle> result = null;
        if(listSchedule != null){
            result = new ArrayList<>();
            for (int i = 0; i < listSchedule.size(); i++) {
                ButtonToggle b = new ButtonToggle();
                b.setTime(listSchedule.get(i));
                b.setStatus(false);
                result.add(b);
            }
        }
        return result;
    }

    public List<String> checkItemIsExist(String fieldId, String date){
        ManagePrefConfig managePrefConfig = new ManagePrefConfig();
        ItemInCart item = managePrefConfig.checkItemExist(BookingActivity.this,fieldId,date);
        if(item != null){
            List<String> listTime = new ArrayList<>();
            for (int i = 0; i < item.getTimePicker().size(); i++) {
                listTime.add(item.getTimePicker().get(i).getTimePick());
            }
        }
        return  null;
    }

    public FieldAndSchedule getFieldAndFieldScheduleFromApi(int fieldId, String date){
        FieldAndSchedule fieldAndSchedule = new FieldAndSchedule();
        FieldService fieldService = new FieldService(BookingActivity.this);


        fieldService.getFieldById(fieldId, new FieldService.FieldResponse(){
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }

            @Override
            public void onResponse(Field fieldResponse) {
                Field field = new Field();
                Log.e("Error: ", "VAO " );
                field.setFieldID(fieldResponse.getFieldID());
                field.setFieldName(fieldResponse.getFieldName());
                field.setTypeField(fieldResponse.getTypeField());
                field.setAddress(fieldResponse.getAddress());
                field.setImagePath(fieldResponse.getImagePath());
                fieldAndSchedule.setField(field);
            }
        });

        FieldScheduleService fieldScheduleService = new FieldScheduleService(BookingActivity.this);

        fieldScheduleService.getScheduleByIdAndTime(fieldId, date, new FieldScheduleService.FieldScheduleResponse() {
            @Override
            public void onError(String msg) {
                Log.e("Error: ", msg);
            }
            @Override
            public void onResponse(List<String> scheduleResponse) {
                Log.e("Error: ", "VAO " );
//                for (int i = 0; i < scheduleResponse.size(); i++) {
//                    schedule.add(scheduleResponse.get(i));
//                }
                fieldAndSchedule.setListFieldSchedule(scheduleResponse);
            };
        });


        return fieldAndSchedule;
    }

    public void clickToAddToCart(View view, ItemInCart itemInCart) {
        ManagePrefConfig managePrefConfig = new ManagePrefConfig();
        ItemInCart item = managePrefConfig.checkItemExist(this.getApplicationContext(), itemInCart.getFieldID(), String.valueOf(itemInCart.getBookDate()));
        if(item != null){
            managePrefConfig.replaceItemInCart(this.getApplicationContext(), itemInCart,itemInCart.getFieldID(), String.valueOf(itemInCart.getBookDate()));
        }else {
            managePrefConfig.addItemToCart(this.getApplicationContext(), itemInCart);
        }
        if(itemInCart.getTimePicker().size() ==0){
            Toast.makeText(this,"Please choose a time to book", Toast.LENGTH_LONG).show();
        }else {
//            Intent intent=new Intent(BookingActivity.this,MainActivity.class);
//            intent.putExtra("action","add to cart");
//            startActivity(intent);
        }
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i2 + "/" +(i1 +1)  +"/"+i;
        txtDate.setText(date);
    }
}