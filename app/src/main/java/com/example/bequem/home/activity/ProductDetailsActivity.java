package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityProductDetailsBinding;
import com.example.bequem.home.adapter.ImageSliderAdapter;
import com.example.bequem.home.adapter.ProductImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
 public ActivityProductDetailsBinding productDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDetailsBinding= DataBindingUtil.setContentView(this,R.layout.activity_product_details);


        productDetailsBinding.layoutBase.textTitle.setText("Product Details");

        productDetailsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        productDetailsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        productDetailsBinding.btnBuy.setOnClickListener(v -> {
            startActivity(new Intent(ProductDetailsActivity.this,ShippingAddressActivity.class));
        });

        setValuesToFields();



    }

    private void setValuesToFields() {
        //banner image
        List<String> bannerList = new ArrayList<>();
        bannerList.add("1");
        bannerList.add("2");
        bannerList.add("3");
        productDetailsBinding.rlBanner.setVisibility(View.VISIBLE);
        productDetailsBinding.vpImage.setAdapter(new ProductImageAdapter(this, bannerList));

        productDetailsBinding.cpImage.setViewPager(productDetailsBinding.vpImage);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        productDetailsBinding.cpImage.setRadius(5 * density);

        productDetailsBinding.vpImage.startAutoScroll();
        productDetailsBinding.vpImage.setInterval(5000);
        productDetailsBinding.vpImage.setCycle(true);
        productDetailsBinding.vpImage.setStopScrollWhenTouch(true);

        // Pager listener over indicator
        productDetailsBinding.cpImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

}