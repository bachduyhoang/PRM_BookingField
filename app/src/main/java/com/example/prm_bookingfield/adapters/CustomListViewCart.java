package com.example.prm_bookingfield.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.dtos.ItemInCart;

import java.util.List;

public class CustomListViewCart extends BaseAdapter {
    private List<ItemInCart> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListViewCart( Context context,List<ItemInCart> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_cart_layout, null);
            holder = new ViewHolder();
            holder.groupField = convertView.findViewById(R.id.cart_list_txtGroupFieldName);
            holder.field = convertView.findViewById(R.id.cart_list_txtFieldName);
            holder.typeField = convertView.findViewById(R.id.cart_list_txtTypeField);
            holder.address = convertView.findViewById(R.id.cart_list_txtLocation);
            holder.bookingDate = convertView.findViewById(R.id.cart_list_txtBookingDate);
            holder.timePicker = convertView.findViewById(R.id.cart_list_tableBook);
            holder.total = convertView.findViewById(R.id.cart_list_txtTotal);
            holder.imageViewField = convertView.findViewById(R.id.cart_list_imageView);
            holder.imageViewCancel = convertView.findViewById(R.id.cart_list_btnCancel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemInCart items = this.listData.get(position);
        Glide.with(holder.imageViewField.getContext())
                .load(items.getImageUrl())
                .into(holder.imageViewField);
        holder.groupField.setText(items.getGroupFiledName());
        holder.field.setText(items.getFiledName());
        holder.typeField.setText(items.getTypeField());
        holder.address.setText(items.getAddress());
        holder.bookingDate.setText((CharSequence) items.getBookDate());
        holder.total.setText((int) items.getTotalList());
        holder.imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        for (int i = 0; i < items.getTimePicker().size(); i++) {
//            TableRow tbr = new TableRow(holder.timePicker.getContext());
//            TextView tv1 = new TextView(holder.timePicker.getContext());
//            tv1.setText(items.getTimePicker().get(i).getTimePick());
//            tbr.addView(tv1);
//            TextView tv2= new TextView(holder.timePicker.getContext());
//            tv2.setText((int) items.getTimePicker().get(i).getPrice());
//            tbr.addView(tv2);
//            holder.timePicker.addView(tbr);
//        }

        return convertView;
    }

    static class ViewHolder {
        TextView groupField;
        ImageView imageViewCancel;
        TextView field;
        TextView typeField;
        TextView address;
        TextView bookingDate;
        ImageView imageViewField;
        TableLayout timePicker;
        TextView total;
    }
}
