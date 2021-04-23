package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bequem.home.pojo.Cart;
import com.example.bequem.home.repository.CartRepository;

import java.util.List;

public class CartViewModel extends ViewModel {
    public CartRepository cartRepository;

    public CartViewModel() {
        this.cartRepository=new CartRepository();
    }

    public LiveData<List<Cart>> getCart(){
        return cartRepository.getCart();
    }
}
