package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Connection;
import android.view.View;
import android.widget.CheckBox;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityDefaultAddressBinding;
import com.example.bequem.home.viewmodel.AddressViewModel;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

public class DefaultAddressActivity extends AppCompatActivity {
 public ActivityDefaultAddressBinding defaultAddressBinding;
    public String user_id,name,address_id;
    public AddressViewModel addressViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultAddressBinding= DataBindingUtil.setContentView(this,R.layout.activity_default_address);

        defaultAddressBinding.layoutBase.textTitle.setText("Shipping Address");

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);
        name=sharedPreferences.getString(Constants.USER_NAME,null);

        defaultAddressBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        defaultAddressBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        addressViewModel=new ViewModelProvider(this).get(AddressViewModel.class);



        defaultAddressBinding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                getAddress();
                defaultAddressBinding.cardAddress.setVisibility(View.VISIBLE);
                defaultAddressBinding.btnBuy.setOnClickListener(v -> {
                    Intent intent=new Intent(DefaultAddressActivity.this,PriceDetailsActivity.class);
                    intent.putExtra("address_id",address_id);
                    startActivity(intent);
                });
            }
            else {
                Intent intent=new Intent(DefaultAddressActivity.this,ShippingAddressActivity.class);
                startActivity(intent);
            }


        });

        defaultAddressBinding.btnBuy.setOnClickListener(v -> {
            Intent intent=new Intent(DefaultAddressActivity.this,ShippingAddressActivity.class);
            startActivity(intent);
        });







    }
//    public void itemClicked(View v) {
//        //code to check if this checkbox is checked!
//
//        if(defaultAddressBinding.checkBox.isChecked()){
//            getAddress();
//            defaultAddressBinding.cardAddress.setVisibility(View.VISIBLE);
//        }
//    }


    public void getAddress(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            addressViewModel.getAddress(user_id).observe(this,addressResponse -> {
                if (addressResponse != null && addressResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    defaultAddressBinding.textName.setText(name);
                    address_id=addressResponse.getAddress().get(0).getAdd_id();
                    defaultAddressBinding.textAddress.setText(addressResponse.getAddress().get(0).getAddress());
                    defaultAddressBinding.textPincode.setText(addressResponse.getAddress().get(0).getPincode());
                    defaultAddressBinding.textLandmark.setText(addressResponse.getAddress().get(0).getLandmark());
                }
            });
        }
    }
}