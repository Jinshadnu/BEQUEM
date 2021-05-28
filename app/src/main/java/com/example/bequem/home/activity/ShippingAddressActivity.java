package com.example.bequem.home.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityShippingAddressBinding;
import com.example.bequem.home.viewmodel.AddressViewModel;
import com.example.bequem.utils.BaseActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.GPSTracker;
import com.example.bequem.utils.NetworkUtilities;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class ShippingAddressActivity extends BaseActivity {
    public ActivityShippingAddressBinding shippingAddressBinding;
    GPSTracker gps;
    private Geocoder geocoder;
    public String user_id;
    public String latit,longit;
    public String strAddress,address,landmark,pincode,addressId;
    public AddressViewModel addressViewModel;
    public int address_status;
    double latitude, longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shippingAddressBinding= DataBindingUtil.setContentView(this,R.layout.activity_shipping_address);

        shippingAddressBinding.layoutBase.textTitle.setText("Shipping Address");

        shippingAddressBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        shippingAddressBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        addressViewModel=new ViewModelProvider(this).get(AddressViewModel.class);

        askUserToOpenGPS();

        shippingAddressBinding.buttonLocation.setOnClickListener(view -> {
            Intent OpenMap = new Intent(ShippingAddressActivity.this, MyLocationActivity.class);
            startActivityForResult(OpenMap, 1);
        });

        shippingAddressBinding.btnBuy.setOnClickListener(v -> {
            if (validatefields()){
                address();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        gps = new GPSTracker(ShippingAddressActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                    + latitude + "\nLong: " + longitude + "https://www.google.com/maps/search/?api=1&query= " + String.valueOf(latitude) + "," +String.valueOf(longitude)  , Toast.LENGTH_LONG).show();

            //getTheAddress(latitude,longitude);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            //   gps.showSettingsAlert();
            askUserToOpenGPS();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("GetAddress");
                latit=data.getStringExtra("GetLatitude");
                longit=data.getStringExtra("GetLongitude");

                shippingAddressBinding.editTextLiveLocation.setText(strEditText);


            }
        }
    }

    public void askUserToOpenGPS() {
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        mAlertDialog.setTitle("Location not available, Open GPS?")
                .setMessage("Activate GPS to use use location services?")
                .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }
    public boolean validatefields(){
        address=requireNonNull(shippingAddressBinding.edittextAddress1.getText().toString().trim());
        pincode=requireNonNull(shippingAddressBinding.edittextPin.getText().toString().trim());
        landmark=requireNonNull(shippingAddressBinding.edittextLandmark.getText().toString().trim());
        strAddress=requireNonNull(shippingAddressBinding.editTextLiveLocation.getText().toString().trim());

        if (isEmpty(address)){
            shippingAddressBinding.edittextAddress1.setError("please enter your building address");
            return false;
        }

        if (isEmpty(pincode)){
            shippingAddressBinding.edittextPin.setError("please enter your pincode");
            return false;
        }
        if (isEmpty(landmark)){
            shippingAddressBinding.edittextLandmark.setError("please enter your landmark");
            return false;
        }
        if (isEmpty(strAddress)){
            shippingAddressBinding.editTextLiveLocation.setError("please select your live location");
            return false;
        }
        return true;




    }

    public void address(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            address_status=0;
            addressViewModel.addAddress(address_status,user_id,address,pincode,latit,longit,landmark).observe(this,commonResponse -> {
                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    showSnackBar(this,commonResponse.getMessage());

                    Intent intent=new Intent(ShippingAddressActivity.this,PriceDetailsActivity.class);
                    startActivity(new Intent(ShippingAddressActivity.this, PriceDetailsActivity.class));
                }
            });
        }
    }

}