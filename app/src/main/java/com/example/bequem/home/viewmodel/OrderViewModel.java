package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bequem.home.pojo.Order;
import com.example.bequem.home.repository.OrderRepository;

import java.util.List;

public class OrderViewModel extends ViewModel {
    public OrderRepository orderRepository;


    public OrderViewModel() {
        this.orderRepository=new OrderRepository();
    }

    public LiveData<List<Order>> getOrders(){
        return orderRepository.getOrders();
    }
}

