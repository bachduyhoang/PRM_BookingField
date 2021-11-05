package com.example.prm_bookingfield.dtos;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm_bookingfield.R;

import java.util.ArrayList;

public class FieldItemAdapter extends RecyclerView.Adapter<FieldItemAdapter.ViewHolder> {

    ArrayList<Field> fieldArrayList;

    public FieldItemAdapter(ArrayList<Field> fieldArrayList) {
        this.fieldArrayList = fieldArrayList;
    }

    @NonNull
    @Override
    public FieldItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_field_items,parent,false);
        return new ViewHolder(view);
    }

    @BindingAdapter({"bind:image_url"})
    @Override
    public void onBindViewHolder(@NonNull FieldItemAdapter.ViewHolder holder, int position) {
        Field fieldItem = fieldArrayList.get(position);
        holder.tvFieldName.setText(fieldItem.getFieldName());
        holder.tvTypeField.setText(String.valueOf(fieldItem.getTypeField()));
        holder.tvSlotsAvailable.setText("Slot available: 3");
        Glide.with(holder.ivItemField.getContext())
                .load(fieldItem.getImagePath())
                .into(holder.ivItemField);

    }

    @Override
    public int getItemCount() {
        return fieldArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivItemField;
        ImageView ivIconAmount;
        TextView tvFieldName;
        TextView tvTypeField;
        TextView tvStartTime;
        TextView tvEndTime;
        TextView tvOriginalPrice;
        TextView tvSlotsAvailable;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            ivItemField = itemView.findViewById(R.id.ivItemField);
            ivIconAmount = itemView.findViewById(R.id.ivIconAmount);
            tvFieldName = itemView.findViewById(R.id.tvFieldName);
            tvTypeField = itemView.findViewById(R.id.tvTypeField);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            tvEndTime = itemView.findViewById(R.id.tvEndTime);
            tvOriginalPrice = itemView.findViewById(R.id.tvOriginalPrice);
            tvSlotsAvailable = itemView.findViewById(R.id.tvSlotsAvailable);

        }
    }
}
