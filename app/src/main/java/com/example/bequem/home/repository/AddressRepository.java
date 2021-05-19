package com.example.bequem.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.home.pojo.AddressResponse;
import com.example.bequem.home.pojo.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressRepository {
    public NetworkInterface networkInterface;

    public AddressRepository() {
    }

    public LiveData<CommonResponse> addAddress(int address_status,String user_id,String address,String pincode,String latitude,String longitude,String landmark){
        MutableLiveData mutableLiveData=new MutableLiveData();
        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.addAddress(address_status, user_id, address, pincode, latitude, longitude, landmark);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse != null){
                    mutableLiveData.postValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
             mutableLiveData.postValue(null);
            }
        });
        return mutableLiveData;
    }

    public LiveData<AddressResponse> getAddress(String userId){
        MutableLiveData mutableLiveData=new MutableLiveData();
        networkInterface=RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);

        Call<AddressResponse> responseCall=networkInterface.getAddress(userId);
        responseCall.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                AddressResponse addressResponse=response.body();
                if (addressResponse != null){
                    mutableLiveData.setValue(addressResponse);
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
              mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
