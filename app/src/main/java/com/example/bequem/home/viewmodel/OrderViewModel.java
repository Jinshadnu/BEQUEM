package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bequem.home.pojo.CommonResponse;
import com.example.bequem.home.pojo.OrderResponse;
import com.example.bequem.home.repository.OrderRepository;

import java.util.List;

public class OrderViewModel extends ViewModel {
    public OrderRepository orderRepository;


    public OrderViewModel() {
        this.orderRepository=new OrderRepository();
    }

    public LiveData<OrderResponse> getOrders(String userId){
        return orderRepository.getOrders(userId);
    }
    public LiveData<CommonResponse> placeOrder(String userId,String order_address){
        return orderRepository.placeOrder(userId,order_address);
    }
}

