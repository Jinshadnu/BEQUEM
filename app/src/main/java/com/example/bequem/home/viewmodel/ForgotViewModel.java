package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.CommonResponse;
import com.example.bequem.home.repository.ForgetPasswordRepository;


public class ForgotViewModel extends ViewModel {
    public ForgetPasswordRepository forgetPasswordRepository;

    public ForgotViewModel() {
        this.forgetPasswordRepository=new ForgetPasswordRepository();
    }

    public LiveData<CommonResponse> forgotPassword(String email,String userId){
        return forgetPasswordRepository.forgetPassword(email,userId);
    }
}
