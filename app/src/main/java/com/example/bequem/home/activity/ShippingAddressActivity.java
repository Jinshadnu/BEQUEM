package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityShippingAddressBinding;

public class ShippingAddressActivity extends AppCompatActivity {
    public ActivityShippingAddressBinding shippingAddressBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shippingAddressBinding= DataBindingUtil.setContentView(this,R.layout.activity_shipping_address);

        shippingAddressBinding.layoutBase.textTitle.setText("Shipping Address");

        shippingAddressBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        shippingAddressBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        shippingAddressBinding.btnBuy.setOnClickListener(v -> {
            startActivity(new Intent(ShippingAddressActivity.this, PriceDetailsActivity.class));
        });
    }
}