package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityOrderBinding;
import com.example.bequem.home.adapter.OrderAdapter;
import com.example.bequem.home.viewmodel.OrderViewModel;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

public class OrderActivity extends AppCompatActivity {
public ActivityOrderBinding orderBinding;
    public OrderViewModel orderViewModel;
    public OrderAdapter orderAdapter;
    public String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        orderBinding= DataBindingUtil.setContentView(this,R.layout.activity_order);

        orderBinding.layoutBase.textTitle.setText("My Orders");

        orderBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        orderBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        orderBinding.recyclerMyorders.setLayoutManager(new LinearLayoutManager(this));

        orderBinding.recyclerMyorders.setHasFixedSize(true);
        orderViewModel= ViewModelProviders.of(this).get(OrderViewModel.class);

        getOrders();
    }

    private void getOrders() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            orderViewModel.getOrders(user_id).observe(this,orderResponse -> {
                if (orderResponse != null && orderResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    orderAdapter=new OrderAdapter(this,orderResponse.getOrders());
                    orderBinding.recyclerMyorders.setAdapter(orderAdapter);
                }
            });
        }

    }
}