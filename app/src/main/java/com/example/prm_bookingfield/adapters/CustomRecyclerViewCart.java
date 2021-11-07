package com.example.prm_bookingfield.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldItemAdapter;
import com.example.prm_bookingfield.dtos.ItemInCart;
import java.util.List;

public class CustomRecyclerViewCart extends RecyclerView.Adapter<CustomRecyclerViewCart.ViewHolderCart> {

    private List<ItemInCart> listData;

    public CustomRecyclerViewCart(List<ItemInCart> listData) {
        this.listData = listData;
    }


    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart_layout,parent,false);
        return new ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewCart.ViewHolderCart holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolderCart extends RecyclerView.ViewHolder{
        TextView groupField;
        ImageView imageViewCancel;
        TextView field;
        TextView typeField;
        TextView address;
        TextView bookingDate;
        ImageView imageViewField;
        TableLayout timePicker;
        TextView total;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            groupField = itemView.findViewById(R.id.cart_list_txtGroupFieldName);
            field = itemView.findViewById(R.id.cart_list_txtFieldName);
            typeField = itemView.findViewById(R.id.cart_list_txtTypeField);
            address = itemView.findViewById(R.id.cart_list_txtLocation);
            bookingDate = itemView.findViewById(R.id.cart_list_txtBookingDate);
            timePicker = itemView.findViewById(R.id.cart_list_tableBook);
            total = itemView.findViewById(R.id.cart_list_txtTotal);
            imageViewField = itemView.findViewById(R.id.cart_list_imageView);
            imageViewCancel = itemView.findViewById(R.id.cart_list_btnCancel);
        }
    }
}
