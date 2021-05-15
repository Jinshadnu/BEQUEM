package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.R;
import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.home.pojo.CommonResponse;
import com.example.bequem.home.pojo.FavouritesResponse;
import com.example.bequem.home.pojo.Wishlist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistRepository {
    public NetworkInterface networkInterface;


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

    public LiveData<FavouritesResponse> getFavourites(String userId){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface=RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<FavouritesResponse> responseCall=networkInterface.getFavourites(userId);
        responseCall.enqueue(new Callback<FavouritesResponse>() {
            @Override
            public void onResponse(Call<FavouritesResponse> call, Response<FavouritesResponse> response) {
                FavouritesResponse favouritesResponse=response.body();

                if (favouritesResponse != null){
                    mutableLiveData.setValue(favouritesResponse);
                }
            }

            @Override
            public void onFailure(Call<FavouritesResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public LiveData<CommonResponse> addToFavourites(String userId,String itemId,int fav_status){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.addToFavourites(userId, itemId, fav_status);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse !=null){
                    mutableLiveData.postValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
            }
        });

        return mutableLiveData;


    }
}
