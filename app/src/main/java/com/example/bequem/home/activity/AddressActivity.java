package com.example.bequem.home.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityAddressBinding;
import com.example.bequem.home.viewmodel.AddressViewModel;
import com.example.bequem.utils.BaseActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.GPSTracker;
import com.example.bequem.utils.NetworkUtilities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class AddressActivity extends BaseActivity {
    double latitude, longitude;
    public ActivityAddressBinding addressBinding;
    GPSTracker gps;
    private Geocoder geocoder;
    public String user_id;
    public String latit,longit;
    public String strAddress,address,landmark,pincode;
    public AddressViewModel addressViewModel;
    public int address_status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addressBinding= DataBindingUtil.setContentView(this,R.layout.activity_address);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        addressViewModel=new ViewModelProvider(this).get(AddressViewModel.class);


        addressBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        addressBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        addressBinding.buttonLocation.setOnClickListener(view -> {
            Intent OpenMap = new Intent(AddressActivity.this, MyLocationActivity.class);
            startActivityForResult(OpenMap, 1);
        });

        addressBinding.button.setOnClickListener(v -> {
            if (validatefields()){
                address();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("GetAddress");
                latit=data.getStringExtra("GetLatitude");
                longit=data.getStringExtra("GetLongitude");

                addressBinding.editTextLiveLocation.setText(strEditText);


            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gps = new GPSTracker(AddressActivity.this);

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

//    private void getTheAddress(double latitude, double longitude) {
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            //order_address = addresses.get(0).getAddressLine(0);
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
//          //  Log.d("neel", order_address);
//            //addressBinding.editTextLocation.setText(order_address);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


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

    public boolean validateLocation(){
        strAddress=requireNonNull(addressBinding.editTextLiveLocation.getText().toString().trim());
        if (isEmpty(strAddress)){
            showErrorSnackBar(this,"Please select your location");
            return false;
        }

        return true;

    }
    public boolean validatefields(){
        address=requireNonNull(addressBinding.editTextTextAddress.getText().toString().trim());
        pincode=requireNonNull(addressBinding.editTextTextPincode.getText().toString().trim());
        landmark=requireNonNull(addressBinding.editTextTextLandmark.getText().toString().trim());
        strAddress=requireNonNull(addressBinding.editTextLiveLocation.getText().toString().trim());

        if (isEmpty(address)){
            addressBinding.editTextTextAddress.setError("please enter your building address");
            return false;
        }

        if (isEmpty(pincode)){
            addressBinding.editTextTextPincode.setError("please enter your pincode");
            return false;
        }
        if (isEmpty(landmark)){
            addressBinding.editTextTextLandmark.setError("please enter your landmark");
            return false;
        }
        if (isEmpty(strAddress)){
            addressBinding.editTextLiveLocation.setError("please select your landmark");
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
                }
            });
        }
    }


}