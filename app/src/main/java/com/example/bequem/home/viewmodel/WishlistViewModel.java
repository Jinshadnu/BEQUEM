package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bequem.home.pojo.Wishlist;
import com.example.bequem.home.repository.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends ViewModel {
    public WishlistRepository wishlistRepository;

    public WishlistViewModel() {
        this.wishlistRepository=new WishlistRepository();
    }

    public LiveData<List<Wishlist>> getWishlist(){
        return wishlistRepository.getWishlist();
    }
}
