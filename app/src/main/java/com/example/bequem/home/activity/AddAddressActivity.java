package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityAddAddressBinding;
import com.example.bequem.home.viewmodel.AddressViewModel;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

public class AddAddressActivity extends AppCompatActivity {
  public ActivityAddAddressBinding addAddressBinding;
  public AddressViewModel addressViewModel;
  public String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addAddressBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_address);

        addressViewModel=new ViewModelProvider(this).get(AddressViewModel.class);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);


        addAddressBinding.buttonAdd.setOnClickListener(v -> {
            startActivity(new Intent(AddAddressActivity.this,AddressActivity.class));
        });

        getAddress();

    }
    public void getAddress(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            addressViewModel.getAddress(user_id).observe(this,addressResponse -> {
                if (addressResponse != null && addressResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    addAddressBinding.textAddress.setText(addressResponse.getAddress().get(0).getAddress());
                    addAddressBinding.textPincode.setText(addressResponse.getAddress().get(0).getPincode());
                    addAddressBinding.textLandmark.setText(addressResponse.getAddress().get(0).getLandmark());
                }
            });
        }
    }
}