package com.example.prm_bookingfield.dtos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.prm_bookingfield.BookingActivity;
import com.example.prm_bookingfield.MainActivity;
import com.example.prm_bookingfield.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FieldItemAdapter extends RecyclerView.Adapter<FieldItemAdapter.ViewHolder> {

    ArrayList<Field> fieldArrayList;
    Activity activity;

    public FieldItemAdapter(Activity activity, ArrayList<Field> fieldArrayList) {
        this.activity = activity;
        this.fieldArrayList = fieldArrayList;
    }

    @NonNull
    @Override
    public FieldItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_field_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldItemAdapter.ViewHolder holder, int position) {

        Field fieldItem = fieldArrayList.get(position);
        holder.tvFieldName.setText("Field: " +fieldItem.getFieldName());
        holder.tvTypeField.setText(String.valueOf(fieldItem.getTypeField()));
        holder.tvStartTime.setText(fieldItem.getSchedule().getTimeStart()+"h");
        holder.tvEndTime.setText(" - " + fieldItem.getSchedule().getTimeEnd()+"h");
        holder.tvOriginalPrice.setText("Price: " + fieldItem.getSchedule().getOriginPrice()+"$");
        if(fieldItem.getImagePath() != null){
            Uri uri = Uri.parse(fieldItem.getImagePath());
            Glide.with(holder.ivItemField.getContext())
                    .load(uri)
                    .apply(new RequestOptions().override(400, 300))
                    .into(holder.ivItemField);
        }

        holder.ivItemField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, BookingActivity.class);
                intent.putExtra("price", String.valueOf(fieldItem.getSchedule().getOriginPrice()));
                intent.putExtra("fieldId", String.valueOf(fieldItem.getFieldID()));
                intent.putExtra("groupFieldId", String.valueOf(fieldItem.getGroupFieldID()));
                activity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        return fieldArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivItemField;

        TextView tvFieldName;
        ImageView ivIconAmount;
        TextView tvTypeField;
        TextView tvStartTime;
        TextView tvEndTime;
        TextView tvOriginalPrice;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivItemField = itemView.findViewById(R.id.ivItemField);
            ivIconAmount = itemView.findViewById(R.id.ivIconAmount);
            tvFieldName = itemView.findViewById(R.id.tvFieldName);
            tvTypeField = itemView.findViewById(R.id.tvTypeField);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            tvEndTime = itemView.findViewById(R.id.tvEndTime);
            tvOriginalPrice = itemView.findViewById(R.id.tvOriginalPrice);
        }
    }
}
