package com.example.bequem.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.home.pojo.CommonResponse;
import com.example.bequem.home.repository.ChangePasswordRepository;


public class ChangePasswordViewModel extends ViewModel {
    public ChangePasswordRepository changePasswordRepository;

    public ChangePasswordViewModel() {
        this.changePasswordRepository=new ChangePasswordRepository();
    }

    public LiveData<CommonResponse> changePassword(String id, String oldPassword, String newPassword,String confirmpassword){
        return changePasswordRepository.changePassword(id,oldPassword,newPassword,confirmpassword);
    }
}
