package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityOrderBinding;
import com.example.bequem.home.adapter.OrderAdapter;
import com.example.bequem.home.viewmodel.OrderViewModel;

public class OrderActivity extends AppCompatActivity {
public ActivityOrderBinding orderBinding;
    public OrderViewModel orderViewModel;
    public OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        orderBinding= DataBindingUtil.setContentView(this,R.layout.activity_order);

        orderBinding.layoutBase.textTitle.setText("My Orders");

        orderBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        orderBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        orderBinding.recyclerMyorders.setLayoutManager(new LinearLayoutManager(this));

        orderBinding.recyclerMyorders.setHasFixedSize(true);
        orderViewModel= ViewModelProviders.of(this).get(OrderViewModel.class);

        getOrders();
    }

    private void getOrders() {
        orderViewModel.getOrders().observe((LifecycleOwner)this, orders -> {
            orderAdapter=new OrderAdapter(this,orders);
            orderBinding.recyclerMyorders.setAdapter(orderAdapter);
        });
    }
}