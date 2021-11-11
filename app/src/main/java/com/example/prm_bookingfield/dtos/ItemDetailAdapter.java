package com.example.prm_bookingfield.dtos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.service.FieldService;

import java.util.ArrayList;

public class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.ViewHolder>{

    ArrayList<BookingDetail> detais;
    Activity activity;

    public ItemDetailAdapter(Activity activity, ArrayList<BookingDetail> detais) {
        this.activity = activity;
        this.detais = detais;
    }

    @NonNull
    @Override
    public ItemDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailAdapter.ViewHolder holder, int position) {

        BookingDetail detail = detais.get(position);
        holder.bookingId.setText("ID Booking: " +detail.getBookingForeignKey());

        FieldService fieldService = new FieldService(activity);
        fieldService.getFieldById(Integer.parseInt(detail.getFieldForeignKey()), new FieldService.FieldResponse() {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onResponse(Field field) {
                String name = field.getFieldName();
                holder.fieldId.setText("Field: "+name);

            }
        });

        holder.tvStartTime.setText(detail.getTimeStart());
        holder.tvEndTime.setText(" - " + detail.getTimeEnd());
        holder.price.setText("Price: " + detail.getPrice()+"$");
    }


    @Override
    public int getItemCount() {

        return detais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView bookingId;
        TextView fieldId;
        TextView tvStartTime;
        TextView tvEndTime;
        TextView price;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            bookingId = itemView.findViewById(R.id.detail_BookingForeignKey);
            fieldId = itemView.findViewById(R.id.detail_fieldForeignKey);
            tvStartTime = itemView.findViewById(R.id.detail_timeStart);
            tvEndTime = itemView.findViewById(R.id.detail_timeEnd);
            price = itemView.findViewById(R.id.detail_price);
        }
    }
}
