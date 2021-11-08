package com.example.prm_bookingfield.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.ButtonToggle;

import java.util.ArrayList;
import java.util.List;

public class CustomGridViewBookingButton extends BaseAdapter {

    private List<ButtonToggle> listSchedule;
    private List<String> listChecked;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridViewBookingButton(Context aContext, List<ButtonToggle> listSchedule) {
        this.listSchedule = listSchedule;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
    }

    @Override
    public int getCount() {
        return listSchedule.size();
    }

    @Override
    public Object getItem(int position) {
        return listSchedule.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_button_booking, null);
            holder = new ViewHolder();
            holder.button =  convertView.findViewById(R.id.grid_item_button_booking_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String time = this.listSchedule.get(position).getTime();
        holder.button.setTextOn(time+"H-" + (Integer.parseInt(time)+1)+"H");
        holder.button.setTextOff(time+"H-" + (Integer.parseInt(time)+1)+"H");
        holder.button.setChecked(this.listSchedule.get(position).isStatus());

        holder.button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(listChecked == null){
                        listChecked = new ArrayList<>();
                        listChecked.add(time);
                    }else {
                        if(!checkIsExist(time)){
                            listChecked.add(time);
                        }
                    }
                }else {
                    if(listChecked != null){
                        if(checkIsExist(time)){
                            for (int i = 0; i < listChecked.size(); i++) {
                                if(listChecked.get(i).equals(time)){
                                    listChecked.remove(i);
                                    break;
                                };
                            }
                        }
                    }
                }
            }
        });

        return convertView;
    }

    public List<String> getListChecked(){
        return listChecked;
    }

    static class ViewHolder {
        ToggleButton button;
    }

    public boolean checkIsExist(String time){
        for (int i = 0; i < listChecked.size(); i++) {
            if(listChecked.get(i).toString().equalsIgnoreCase(time)){
                return true;
            }
        }
        return  false;
    }
}
