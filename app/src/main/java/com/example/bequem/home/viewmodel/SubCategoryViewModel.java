package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.SubCategory;
import com.example.bequem.home.repository.SubCategoryRepository;

import java.util.List;

public class SubCategoryViewModel extends ViewModel {
    public SubCategoryRepository subCategoryRepository;
    public SubCategoryViewModel() {
        this.subCategoryRepository=new SubCategoryRepository();
    }

    public LiveData<List<SubCategory>> getSubCategories(){
        return subCategoryRepository.getSubcategory();
    }



}
