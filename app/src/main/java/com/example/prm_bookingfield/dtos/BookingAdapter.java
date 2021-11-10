package com.example.prm_bookingfield.dtos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.service.FieldService;
import com.example.prm_bookingfield.service.UserService;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
    private Activity activity;
    ArrayList<BookingHistory> bookings;

    public BookingAdapter(Activity activity, ArrayList<BookingHistory> bookings) {
        this.activity = activity;
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_fragment,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingHistory history = bookings.get(position);
        holder.bookingId.setText(history.getBookingId());
        holder.createAt.setText(history.getCreatedAt());
        holder.user.setText(history.getUserForeignKey());
        UserService userService = new UserService(activity);
        userService.getUserInformation(new UserService.VolleyResponseListener() {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onResponse(User user) {
                String name = user.getFirstName();
                holder.user.setText(name);
            }
        });
        holder.totalPrice.setText("$"+history.getTotalPrice());

        ItemDetailAdapter itemDetail = new ItemDetailAdapter(activity, (ArrayList<BookingDetail>) history.getBookingDetails());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL);

        holder.rv_ListDetail.setLayoutManager(linearLayoutManager);
        holder.rv_ListDetail.setAdapter(itemDetail);
        holder.rv_ListDetail.addItemDecoration(itemDecoration);

    }


    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookingId, createAt, user, totalPrice;
        RecyclerView rv_ListDetail;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            bookingId = itemView.findViewById(R.id.history_BookingId);
            createAt = itemView.findViewById(R.id.history_CreateAt);
            user = itemView.findViewById(R.id.history_User);
            totalPrice = itemView.findViewById(R.id.history_TotalPrice);
            rv_ListDetail = itemView.findViewById(R.id.history_rvBookingDetail);
        }
    }
}
