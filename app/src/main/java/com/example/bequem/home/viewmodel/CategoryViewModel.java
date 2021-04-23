package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.Categories;
import com.example.bequem.home.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    public CategoryRepository categoryRepository;

    public CategoryViewModel() {
        this.categoryRepository=new CategoryRepository();
    }

    public LiveData<List<Categories>> getCategories(){
        return categoryRepository.getCategories();
    }
}
