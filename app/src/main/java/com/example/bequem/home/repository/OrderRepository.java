package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.bequem.home.pojo.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    public OrderRepository() {
    }

    public LiveData<List<Order>> getOrders(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Order> orderList=new ArrayList<>();
        orderList.add(new Order(12122123,"03-01-2021","Rs.1200/-","Pending"));
        orderList.add(new Order(12122123,"03-01-2021","Rs.1500/-","Delivered"));
        orderList.add(new Order(12122123,"03-01-2021","Rs.2000/-","Delivered"));
        orderList.add(new Order(12122123,"03-01-2021","Rs.4000/-","Delivered"));

        mutableLiveData.setValue(orderList);

        return mutableLiveData;

    }
}
