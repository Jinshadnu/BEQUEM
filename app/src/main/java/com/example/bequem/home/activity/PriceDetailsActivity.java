package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityPriceDetailsBinding;

public class PriceDetailsActivity extends AppCompatActivity {
    public ActivityPriceDetailsBinding priceDetailsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        priceDetailsBinding= DataBindingUtil.setContentView(this,R.layout.activity_price_details);

        priceDetailsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        priceDetailsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

//        priceDetailsBinding.btnBuy.setOnClickListener(v -> {
//            startActivity(new Intent(PriceDetailsActivity.this,SuccessActivity.class));
//        });
    }



}