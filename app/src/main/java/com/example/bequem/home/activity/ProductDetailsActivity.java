package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityProductDetailsBinding;
import com.example.bequem.home.adapter.ImageSliderAdapter;
import com.example.bequem.home.adapter.ProductImageAdapter;
import com.example.bequem.home.viewmodel.CartViewModel;
import com.example.bequem.utils.BaseActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity {
 public ActivityProductDetailsBinding productDetailsBinding;
 public String item_name,item_price,item_desription,item_id,pic1,pic2,pic3,pic4,subcategoryId,size,user_id;
 public CartViewModel cartViewModel;
 public String quantity="1";
 public RadioButton radioButton;
 public String selected_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDetailsBinding= DataBindingUtil.setContentView(this,R.layout.activity_product_details);


        productDetailsBinding.layoutBase.textTitle.setText("Product Details");

        productDetailsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        productDetailsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);


        cartViewModel=new ViewModelProvider(this).get(CartViewModel.class);
        subcategoryId=getIntent().getStringExtra("subcategory_id");
        item_id=getIntent().getStringExtra("item_id");
        item_name=getIntent().getStringExtra("item_name");
        item_desription=getIntent().getStringExtra("item_description");
        item_price=getIntent().getStringExtra("item_price");
        pic1=getIntent().getStringExtra("pic1");
        pic2=getIntent().getStringExtra("pic2");
        pic3=getIntent().getStringExtra("pic3");
        pic4=getIntent().getStringExtra("pic4");
        productDetailsBinding.elegantButton.setOnValueChangeListener((view, oldValue, newValue) -> {
            quantity=productDetailsBinding.elegantButton.getNumber();
        });

        productDetailsBinding.txtHeading.setText(item_name);
        productDetailsBinding.txtDescription.setText(item_desription);
        productDetailsBinding.txtFinalPrice.setText(item_price);

        productDetailsBinding.btnBuy.setOnClickListener(v -> {
            startActivity(new Intent(ProductDetailsActivity.this,ShippingAddressActivity.class));
        });

        productDetailsBinding.btnAddToCart.setOnClickListener(v -> {
            addToCart();
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
        productDetailsBinding.vpImage.setAdapter(new ProductImageAdapter(this, bannerList,pic1,pic2,pic3,pic4));

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

    private void addToCart() {
        int selected_buttonid= productDetailsBinding.radioSize.getCheckedRadioButtonId();
        radioButton=findViewById(selected_buttonid);
        selected_size=radioButton.getText().toString();
        if (selected_size.equals("XS")) {
        size="xs_stock";
        }
        if (selected_size.equals("S")) {
            size="s_stock";
        }
        if (selected_size.equals("M")) {
            size="m_stock";
        }
        if (selected_size.equals("L")) {
            size="l_stock";
        }
        if (selected_size.equals("XL")) {
            size="xl_stock";
        }
        if (selected_size.equals("XXL")) {
            size="xxl_stock";
        }

        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
      cartViewModel.addToCart(size,user_id,quantity,item_id).observe(this,commonResponse -> {
          if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
              showSnackBar(this,commonResponse.getMessage());
          }
      });
     }
    }


}