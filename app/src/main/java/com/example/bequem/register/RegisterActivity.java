package com.example.bequem.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityRegisterBinding;
import com.example.bequem.home.HomeActivity;
import com.example.bequem.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    public ActivityRegisterBinding registerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_register);

        registerBinding.buttonRegister.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
        });


        registerBinding.textViewLogin.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

    }
}