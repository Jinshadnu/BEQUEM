package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bequem.home.pojo.FeaturedProducts;
import com.example.bequem.home.repository.FeaturedProductRepository;

import java.util.List;

public class FeaturedProductViewModel extends ViewModel {
    public FeaturedProductRepository featuredProductRepository;
    public FeaturedProductViewModel() {
        this.featuredProductRepository = new FeaturedProductRepository();

    }
    public LiveData<List<FeaturedProducts>> getFeaturedProducts(){
        return featuredProductRepository.getFearuredProducts();
    }
}
