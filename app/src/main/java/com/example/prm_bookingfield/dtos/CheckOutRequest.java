package com.example.prm_bookingfield.dtos;

import java.util.List;

public class CheckOutRequest {
    private List<CartItemViewRequest> listCart;

    public CheckOutRequest() {
    }

    public CheckOutRequest(List<CartItemViewRequest> listCart) {
        this.listCart = listCart;
    }

    public List<CartItemViewRequest> getListCart() {
        return listCart;
    }

    public void setListCart(List<CartItemViewRequest> listCart) {
        this.listCart = listCart;
    }
}
