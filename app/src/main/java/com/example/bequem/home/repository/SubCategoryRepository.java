package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.home.pojo.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryRepository {
    public NetworkInterface networkInterface;

    public SubCategoryRepository() {

    }

    public LiveData<SubCategoryResponse> getSubcategory(String category_id){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<SubCategoryResponse> responseCall=networkInterface.getSubCategories(category_id);
        responseCall.enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                SubCategoryResponse subCategoryResponse=response.body();
                if (subCategoryResponse !=null){
                    mutableLiveData.setValue(subCategoryResponse);
                }
            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
