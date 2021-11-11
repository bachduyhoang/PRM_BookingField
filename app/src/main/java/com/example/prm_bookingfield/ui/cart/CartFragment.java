package com.example.prm_bookingfield.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_bookingfield.BookingActivity;
import com.example.prm_bookingfield.MainActivity;
import com.example.prm_bookingfield.R;
import com.example.prm_bookingfield.adapters.CustomRecyclerViewCart;
import com.example.prm_bookingfield.dtos.CartItemViewRequest;
import com.example.prm_bookingfield.dtos.CartTimePicker;
import com.example.prm_bookingfield.dtos.ItemInCart;
import com.example.prm_bookingfield.service.CheckOutService;
import com.example.prm_bookingfield.service.ManagePrefConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView listViewCart;
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

        listViewCart = view.findViewById(R.id.fragmentCart_recyclerViewCart);
        txtTotal = view.findViewById(R.id.fragmentCart_Total);
        btnBook = view.findViewById(R.id.fragmentCart_btnBook);

        //Render Cart
        ManagePrefConfig managePrefConfig = new ManagePrefConfig();
        List<ItemInCart> itemInCartList = managePrefConfig.readListCartFromPref(getActivity());
        txtTotal.setText("$"+String.valueOf(getTotalPrice(itemInCartList)));
        if(itemInCartList != null){
            CustomRecyclerViewCart customListViewCart =new CustomRecyclerViewCart(getActivity(),itemInCartList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
            listViewCart.setLayoutManager(linearLayoutManager);
            listViewCart.setAdapter(customListViewCart);
            btnBook.setClickable(true);

            btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemInCartList != null){
                        CheckOutService checkOutService = new CheckOutService(getContext());
                        List<CartItemViewRequest> cartItemViewRequestsList = transferToBook(itemInCartList);
                        checkOutService.checkOut(getContext(), cartItemViewRequestsList, new CheckOutService.CheckOutServiceListener() {
                            @Override
                            public void onError(String msg) {
                                Toast.makeText(getContext(),"Booking Fail at " + msg, Toast.LENGTH_LONG);
                            }

                            @Override
                            public void onResponse(String success) {
                                checkOutService.checkOutDetail(success, getContext(), cartItemViewRequestsList, new CheckOutService.CheckOutDetailServiceListener() {
                                    @Override
                                    public void onError(String msg) {

                                    }

                                    @Override
                                    public void onResponse(String success) {
                                        Toast.makeText(getContext(),"Booking Success", Toast.LENGTH_LONG);
                                        managePrefConfig.removeCart(getContext());
                                        Intent intent = new Intent(getContext(), MainActivity.class);
                                        intent.putExtra("action","add to cart");
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                }
            });
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

    public float getTotalPrice (List<ItemInCart> itemInCartList){
        if(itemInCartList != null){
            float total = 0;
            for (int i = 0; i < itemInCartList.size(); i++) {
                total +=itemInCartList.get(i).getTotalList();
            }
            return total;
        }
        return 0;
    }

    public List<CartItemViewRequest> transferToBook(List<ItemInCart> itemInCartList){
        List<CartItemViewRequest> cartItemViewRequests = new ArrayList<>();
        for (int i = 0; i < itemInCartList.size(); i++) {
            ItemInCart item = itemInCartList.get(i);
            for (int j = 0; j < item.getTimePicker().size(); j++) {

                CartItemViewRequest cartItemViewRequest = new CartItemViewRequest();

                cartItemViewRequest.setFieldName(item.getFiledName());
                cartItemViewRequest.setBookingDate(item.getBookDate() + " 00:00:00");
                cartItemViewRequest.setFieldForeignKey(Integer.parseInt(item.getFieldID()));
                cartItemViewRequest.setPrice(item.getTimePicker().get(j).getPrice());
                cartItemViewRequest.setTimeStart(item.getBookDate()+" " + item.getTimePicker().get(j).getTimePick()+":00:00");
                cartItemViewRequest.setTimeEnd(item.getBookDate()+" " + (Integer.parseInt(item.getTimePicker().get(j).getTimePick()) +1)+":00:00");
                cartItemViewRequest.setFieldScheduleId(1);

                cartItemViewRequests.add(cartItemViewRequest);
            }
        }
        return cartItemViewRequests;
    }
}