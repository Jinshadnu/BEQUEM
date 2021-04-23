package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.R;
import com.example.bequem.home.pojo.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public LiveData<List<Categories>> getCategories(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Categories> categories=new ArrayList<>();
        categories.add(new Categories("Top Wear", R.drawable.topwear));
        categories.add(new Categories("Bottom Wear", R.drawable.bottomwear));
        categories.add(new Categories("Inner Wear", R.drawable.innerwear));
        categories.add(new Categories("Sleep Wear", R.drawable.sleepwear));
        mutableLiveData.setValue(categories);
        return mutableLiveData;
    }
}
