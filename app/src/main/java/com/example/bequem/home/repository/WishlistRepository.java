package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.R;
import com.example.bequem.home.pojo.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishlistRepository {

    public WishlistRepository() {
    }

    public LiveData<List<Wishlist>> getWishlist(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Wishlist> wishlists=new ArrayList<>();
        wishlists.add(new Wishlist(R.drawable.topwear,"T-shirt","Rs.250"));
        wishlists.add(new Wishlist(R.drawable.bottomwear,"Track Pant","Rs.220"));

       mutableLiveData.setValue(wishlists);

       return mutableLiveData;


    }
}
