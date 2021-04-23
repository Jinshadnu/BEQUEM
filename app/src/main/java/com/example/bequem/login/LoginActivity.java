package com.example.bequem.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityLoginBinding;
import com.example.bequem.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {
    public ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);

        loginBinding.buttonLogin.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        });

    }
}