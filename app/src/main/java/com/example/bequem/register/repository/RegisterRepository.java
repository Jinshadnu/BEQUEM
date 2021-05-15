package com.example.bequem.register.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bequem.core.NetworkInterface;
import com.example.bequem.core.RetrofitClient;
import com.example.bequem.register.pojo.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    public NetworkInterface networkInterface;


    public RegisterRepository() {

    }

    public LiveData<RegisterResponse> register(String name,String mob,String email,String password,String confirm_password){
        MutableLiveData mutableLiveData=new MutableLiveData();
        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);

        Call<RegisterResponse> responseCall=networkInterface.userRegistration(name,mob,email,password,confirm_password);
        responseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse=response.body();
                if (registerResponse != null){
                    mutableLiveData.postValue(registerResponse);
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
             mutableLiveData.postValue(null);
            }
        });

        return mutableLiveData;
    }

}

