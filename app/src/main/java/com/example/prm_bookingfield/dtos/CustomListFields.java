package com.example.prm_bookingfield.dtos;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prm_bookingfield.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomListFields extends BaseAdapter {
    private List<Field> listFieldData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListFields(Context aContext, List<Field> listFieldData) {
        this.context = aContext;
        this.listFieldData = listFieldData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listFieldData.size();
    }

    @Override
    public Object getItem(int position) {
        return listFieldData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_fields, null);
            holder = new ViewHolder();
            holder.fieldName = (TextView) convertView.findViewById(R.id.tvFieldName);

            holder.imageField =(ImageView) convertView.findViewById(R.id.ivField);

            holder.imageIconAmount = (ImageView) convertView.findViewById(R.id.ivIconAmount);
            holder.amount = (TextView) convertView.findViewById(R.id.tvAmount);
            holder.imageIconLocation = (ImageView) convertView.findViewById(R.id.ivIconLocation);
            holder.location = (TextView) convertView.findViewById(R.id.tvLocation);

            holder.timeStart = (TextView) convertView.findViewById(R.id.tvStartTime);
            holder.timeEnd = (TextView) convertView.findViewById(R.id.tvEndTime);
            holder.price = (TextView) convertView.findViewById(R.id.tvOriginalPrice);

            holder.slotAvailable = (TextView) convertView.findViewById(R.id.tvSlotsAvailable);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Field field = this.listFieldData.get(position);
        int imageAmount = this.getMipmapResIdByName("multiple_user");
        int imageLocation = this.getMipmapResIdByName("outline_location_on_24");

        if(field.getImagePath() != null){
            Uri uri = Uri.parse(field.getImagePath());
            Glide.with(holder.imageField.getContext())
                    .load(uri)
                    .into(holder.imageField);
        }

        holder.fieldName.setText(field.getFieldName());
        //lack date

        holder.imageIconAmount.setImageResource(imageAmount);
        holder.amount.setText("5");
        holder.imageIconLocation.setImageResource(imageLocation);
        holder.location.setText(field.getAddress());

//        holder.timeStart.setText(field.g);
//        holder.timeEnd.setText();
//
//        holder.timeStartNight.setText();
//        holder.timeEndNight.setText();
//        holder.discountNight.setText();

//        holder.imageStar.setImageResource(imageStar);
//        holder.numOfStar.setText("4.5");

        holder.slotAvailable.setText("Slots available: 3");

        return convertView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+" ==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        TextView fieldName;

        ImageView imageField;

        ImageView imageIconAmount;
        TextView amount;
        ImageView imageIconLocation;
        TextView location;

        TextView timeStart;
        TextView timeEnd;
        TextView price;

        TextView slotAvailable;
    }
}
