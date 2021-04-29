package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bequem.home.pojo.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryRepository {
    public SubCategoryRepository() {

    }

    public LiveData<List<SubCategory>> getSubcategory(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<SubCategory> subCategoryList=new ArrayList<>();
        subCategoryList.add(new SubCategory("1","T-shirt"));
        subCategoryList.add(new SubCategory("2","Casual Shirt"));
        subCategoryList.add(new SubCategory("3","Formal Shirt"));
        subCategoryList.add(new SubCategory("4","Jackets"));

        mutableLiveData.setValue(subCategoryList);
        return mutableLiveData;
    }
}
