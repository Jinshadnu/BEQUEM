package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.SubCategoryResponse;
import com.example.bequem.home.repository.SubCategoryRepository;

import java.util.List;

public class SubCategoryViewModel extends ViewModel {
    public SubCategoryRepository subCategoryRepository;
    public SubCategoryViewModel() {
        this.subCategoryRepository=new SubCategoryRepository();
    }

    public LiveData<SubCategoryResponse> getSubCategories(String category_id){
        return subCategoryRepository.getSubcategory(category_id);
    }



}
