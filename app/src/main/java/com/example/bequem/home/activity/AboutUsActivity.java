package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {
 public ActivityAboutUsBinding aboutUsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutUsBinding= DataBindingUtil.setContentView(this,R.layout.activity_about_us);

        aboutUsBinding.layoutBase.textTitle.setText("Contact us");


        aboutUsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        aboutUsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

    }
}