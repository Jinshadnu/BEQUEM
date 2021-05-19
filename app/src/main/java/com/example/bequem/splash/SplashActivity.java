package com.example.bequem.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivitySplashBinding;
import com.example.bequem.home.HomeActivity;
import com.example.bequem.onboarding.OnboardingActivity;
import com.example.bequem.utils.Constants;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    public ActivitySplashBinding splashBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splashBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);

        splashBinding.imageviewLogo.setAnimation(animation);


        new Handler().postDelayed(this::openScreens,SPLASH_TIME_OUT);


    }
    private void openScreens() {
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        boolean isLogdin = sharedpreferences.getBoolean(Constants.IsUserLogIn, false);

        if(isLogdin){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        else {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
        }

    }

}