package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.R;
import com.example.bequem.home.pojo.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    public CartRepository() {
    }

    public LiveData<List<Cart>> getCart(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Cart> cartList=new ArrayList<>();
        cartList.add(new Cart("Britex Watch","750", R.drawable.topwear));
        cartList.add(new Cart("Dc Shoe","950", R.drawable.sleepwear));
        cartList.add(new Cart("Britex Watch","750", R.drawable.bottomwear));



        mutableLiveData.setValue(cartList);

        return mutableLiveData;
    }
}
