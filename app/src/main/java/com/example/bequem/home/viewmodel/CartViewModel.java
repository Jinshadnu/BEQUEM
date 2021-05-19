package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bequem.home.pojo.CartResponse;
import com.example.bequem.home.pojo.CommonResponse;
import com.example.bequem.home.repository.CartRepository;

import java.util.List;

public class CartViewModel extends ViewModel {
    public CartRepository cartRepository;

    public CartViewModel() {
        this.cartRepository=new CartRepository();
    }

    public LiveData<CartResponse> getCart(String userId){
        return cartRepository.getCart(userId);
    }

    public LiveData<CommonResponse> addToCart(String size_name,String user_id,String quantity,String product_id){
        return cartRepository.addToCart(size_name,user_id,quantity,product_id);
    }
    public LiveData<CommonResponse> deletCart(String cart_id,String user_id){
        return cartRepository.deleteCart(cart_id,user_id);
    }
}
