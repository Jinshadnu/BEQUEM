package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bequem.R;
import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.home.pojo.ItemResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
 public NetworkInterface networkInterface;

    public ItemRepository() {
    }

    public LiveData<ItemResponse> getItems(String subcategory_id){
        MutableLiveData mutableLiveData=new MutableLiveData();
//        List<ItemResponse> itemsList=new ArrayList<>();
//        itemsList.add(new Items(100,"Shirt", R.drawable.casual_shirt,"950.00"));
//        itemsList.add(new Items(101,"Shirt", R.drawable.formal_shirt,"800.00"));
//        itemsList.add(new Items(102,"Shirt", R.drawable.casual_shirt,"900.00"));
//        itemsList.add(new Items(101,"Shirt", R.drawable.casual_shirt,"750.00"));

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<ItemResponse> responseCall=networkInterface.getItems(subcategory_id);
        responseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                ItemResponse itemResponse=response.body();
                if (itemResponse != null){
                    mutableLiveData.setValue(itemResponse);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
//

        return mutableLiveData;
    }
}
