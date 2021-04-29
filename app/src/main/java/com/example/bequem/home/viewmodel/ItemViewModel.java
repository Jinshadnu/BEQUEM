package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.Items;
import com.example.bequem.home.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends ViewModel {

    public ItemRepository itemRepository;

    public ItemViewModel() {
        this.itemRepository=new ItemRepository();
    }

    public LiveData<List<Items>> getItems(){
        return itemRepository.getItems();
    }
}
