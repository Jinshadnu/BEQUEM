package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bequem.R;
import com.example.bequem.home.pojo.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    public LiveData<List<Items>> getItems(){
        MutableLiveData mutableLiveData=new MutableLiveData();
        List<Items> itemsList=new ArrayList<>();
        itemsList.add(new Items(100,"Shirt", R.drawable.casual_shirt,"950.00"));
        itemsList.add(new Items(101,"Shirt", R.drawable.formal_shirt,"800.00"));
        itemsList.add(new Items(102,"Shirt", R.drawable.casual_shirt,"900.00"));
        itemsList.add(new Items(101,"Shirt", R.drawable.casual_shirt,"750.00"));


        mutableLiveData.setValue(itemsList);

        return mutableLiveData;
    }
}
