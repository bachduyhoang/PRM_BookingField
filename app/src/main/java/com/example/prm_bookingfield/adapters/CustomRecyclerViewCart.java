package com.example.prm_bookingfield.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.Field;
import com.example.prm_bookingfield.dtos.FieldItemAdapter;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.example.prm_bookingfield.service.ManagePrefConfig;

import java.util.Calendar;
import java.util.List;

public class CustomRecyclerViewCart extends RecyclerView.Adapter<CustomRecyclerViewCart.ViewHolderCart> {

    private List<ItemInCart> listData;
    private Activity context;

    public CustomRecyclerViewCart(Activity context, List<ItemInCart> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart_layout,parent,false);
        return new ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewCart.ViewHolderCart holder, int position) {
        ItemInCart items = this.listData.get(position);
        Glide.with(holder.imageViewField.getContext())
                .load(items.getImageUrl())
                .into(holder.imageViewField);
        holder.groupField.setText(items.getGroupFiledName());
        holder.field.setText(items.getFiledName());
        holder.typeField.setText(String.valueOf(items.getTypeField()));
        holder.address.setText(items.getAddress());
        holder.txtTimePicker.setText((CharSequence) items.getBookDate());
        holder.bookingDate.setText(
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+
                (Calendar.getInstance().get(Calendar.MONTH)+1) +"/"+
                Calendar.getInstance().get(Calendar.YEAR));
        holder.total.setText((String.valueOf(items.getTotalList())));
        holder.imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        for (int i = 0; i < items.getTimePicker().size(); i++) {
            TableRow tbr = new TableRow(holder.timePicker.getContext());
            TextView tv1 = new TextView(holder.timePicker.getContext());
            tv1.setText(items.getTimePicker().get(i).getTimePick()+"H-"+(Integer.parseInt(items.getTimePicker().get(i).getTimePick())+1)+"H");
            tv1.setGravity(Gravity.LEFT);
            tv1.setMinWidth(450);
            tbr.addView(tv1);
            TextView tv2= new TextView(holder.timePicker.getContext());
            tv2.setText("                          "+"$"+String.valueOf(items.getTimePicker().get(i).getPrice()));
            tbr.addView(tv2);
            holder.timePicker.addView(tbr);
        }
        holder.imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagePrefConfig managePrefConfig = new ManagePrefConfig();
                managePrefConfig.removeItemInCart(context.getApplicationContext(), items.getFieldID(), items.getBookDate());
                notifyDataSetChanged();
                listData = managePrefConfig.readListCartFromPref(context);
            }
        });
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
        TextView txtTimePicker;
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
            txtTimePicker = itemView.findViewById(R.id.cart_list_txtTimePicker);
        }
    }




}
