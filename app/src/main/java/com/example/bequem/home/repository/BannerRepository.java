package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.home.pojo.BannerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerRepository {

    public BannerRepository() {

    }

    public NetworkInterface networkInterface;

    public LiveData<BannerResponse> getBanners(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<BannerResponse> responseCall=networkInterface.getSliderImages();
        responseCall.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                BannerResponse bannerResponse=response.body();
                if (bannerResponse !=null){
                    mutableLiveData.setValue(bannerResponse);
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }


}
