package com.example.prm_bookingfield.dtos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.prm_bookingfield.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Activity activity;
    ArrayList<GroupField> groupFieldArrayList;

    public MyAdapter(Activity activity, ArrayList<GroupField> groupFieldArrayList) {
        this.activity = activity;
        this.groupFieldArrayList = groupFieldArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_group_fields,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupField groupField = groupFieldArrayList.get(position);
        holder.groupField.setText(groupField.getName());
        holder.address.setText(groupField.getAddress());

        if(groupField.getImagePath() != null){
            Uri uri = Uri.parse(groupField.getImagePath());
            Glide.with(holder.imageGroupField.getContext())
                    .load(uri)
                    .apply(new RequestOptions().override(500, 400))
                    .into(holder.imageGroupField);
        }
        FieldItemAdapter fieldItem = new FieldItemAdapter(activity, (ArrayList<Field>) groupField.getFields());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL);

        holder.rv_listField.setLayoutManager(linearLayoutManager);
        holder.rv_listField.setAdapter(fieldItem);
        holder.rv_listField.addItemDecoration(itemDecoration);

    }


    @Override
    public int getItemCount() {
        return groupFieldArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView groupField, address;
        RecyclerView rv_listField;
        ImageView imageGroupField;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            groupField = itemView.findViewById(R.id.tvGroupFieldName);
            address = itemView.findViewById(R.id.tvLocation);
            rv_listField = itemView.findViewById(R.id.rv_listField);
            imageGroupField = itemView.findViewById(R.id.ivGroupField);
        }
    }
}
