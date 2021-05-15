package com.example.bequem.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bequem.login.pojo.LoginResponse;
import com.example.bequem.login.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    public LoginRepository loginRepository;

    public LoginViewModel() {
        this.loginRepository=new LoginRepository();
    }

    public LiveData<LoginResponse> login(String phone,String password){
        return loginRepository.login(phone, password);
    }

}
