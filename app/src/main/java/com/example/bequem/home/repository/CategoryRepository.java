package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.R;
import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.home.pojo.Categories;
import com.example.bequem.home.pojo.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    public NetworkInterface networkInterface;

    public CategoryRepository() {

    }

    public LiveData<CategoryResponse> getCategories(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CategoryResponse> responseCall=networkInterface.getCategories();
        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                CategoryResponse categoryResponse=response.body();
                if (categoryResponse != null){
                    mutableLiveData.setValue(categoryResponse);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
