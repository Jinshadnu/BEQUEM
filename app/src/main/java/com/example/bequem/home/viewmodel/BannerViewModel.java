package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.BannerResponse;
import com.example.bequem.home.repository.BannerRepository;


public class BannerViewModel extends ViewModel {
    public BannerRepository bannerRepository;

    public BannerViewModel() {
        this.bannerRepository=new BannerRepository();
    }

    public LiveData<BannerResponse> getBanners(){
        return bannerRepository.getBanners();
    }
}
