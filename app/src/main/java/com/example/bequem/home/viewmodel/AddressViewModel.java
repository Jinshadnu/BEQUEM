package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.AddressResponse;
import com.example.bequem.home.pojo.CommonResponse;
import com.example.bequem.home.repository.AddressRepository;

public class AddressViewModel extends ViewModel {
    public AddressRepository addressRepository;

    public AddressViewModel() {
        this.addressRepository=new AddressRepository();
    }

    public LiveData<CommonResponse> addAddress(int address_status,String user_id,String address,String pincode,String latitude,String longitude,String landmark,String location){
        return addressRepository.addAddress(address_status, user_id, address, pincode, latitude, longitude, landmark,location);
    }

    public LiveData<AddressResponse> getAddress(String userId){
        return addressRepository.getAddress(userId);
    }


}
