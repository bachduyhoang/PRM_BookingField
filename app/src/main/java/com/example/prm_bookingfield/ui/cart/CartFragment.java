package com.example.prm_bookingfield.ui.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prm_bookingfield.BookingActivity;
import com.example.prm_bookingfield.MainActivity;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.adapters.CustomGridViewBookingButton;
import com.example.prm_bookingfield.adapters.CustomListViewCart;
import com.example.prm_bookingfield.dtos.CartTimePicker;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.example.prm_bookingfield.service.ManagePrefConfig;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private ListView listViewCart;
    private TextView txtTotal;
    private Button btnBook;
    public CartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        listViewCart = view.findViewById(R.id.fragmentCart_listViewCart);
        txtTotal = view.findViewById(R.id.fragmentCart_Total);
        btnBook = view.findViewById(R.id.fragmentCart_btnBook);

        //Render Cart
        ManagePrefConfig managePrefConfig = new ManagePrefConfig();
        List<ItemInCart> itemInCartList = managePrefConfig.readListCartFromPref(getActivity());
        if(itemInCartList != null){
            CustomListViewCart customListViewCart =new CustomListViewCart(getActivity(),itemInCartList);
            listViewCart.setAdapter(customListViewCart);
            btnBook.setClickable(true);
        }else {
            btnBook.setClickable(false);
        }
        return view;
    }

    public List<ItemInCart> getList(){
        List<ItemInCart> list = new ArrayList<>();
        ItemInCart item = new ItemInCart();
        item.setFieldID("1");
        List<CartTimePicker> cartTimePickers = new ArrayList<>();
        cartTimePickers.add(new CartTimePicker("2",4));
        cartTimePickers.add(new CartTimePicker("3",4));
        cartTimePickers.add(new CartTimePicker("4",4));
        item.setTimePicker(cartTimePickers);
        item.setAddress("HN");
        item.setFiledName("A");
        item.setGroupFiledName("Hoang");
        item.setTotal(100);
        item.setTypeField(5);
        return list;
    }
}