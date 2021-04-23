package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.R;
import com.example.bequem.home.pojo.FeaturedProducts;

import java.util.ArrayList;
import java.util.List;

public class FeaturedProductRepository {

    public FeaturedProductRepository() {
    }

    public LiveData<List<FeaturedProducts>> getFearuredProducts(){
        MutableLiveData mutableLiveData=new
                MutableLiveData();
        List<FeaturedProducts> featuredProducts=new ArrayList<>();
        featuredProducts.add(new FeaturedProducts(R.drawable.bottomwear,"Track Pant","15% off","Rs.350/-"));
        featuredProducts.add(new FeaturedProducts(R.drawable.topwear,"T-shirts","10% off","Rs.300/-"));
        featuredProducts.add(new FeaturedProducts(R.drawable.sleepwear,"Sleepwear","10% off","Rs.250/-"));

        mutableLiveData.setValue(featuredProducts);
        return  mutableLiveData;
    }
}
