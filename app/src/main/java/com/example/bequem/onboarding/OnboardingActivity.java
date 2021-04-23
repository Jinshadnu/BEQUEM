package com.example.bequem.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bequem.MainActivity;
import com.example.bequem.R;
import com.example.bequem.onboarding.adapter.SliderPagerAdapter;
import com.example.bequem.register.RegisterActivity;
import com.google.android.material.tabs.TabLayout;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView textView;
    private SliderPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.pagerIntroSlider);
//        TabLayout tabLayout = findViewById(R.id.tabs);
        textView = findViewById(R.id.textView);

        adapter = new SliderPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // set adapter
        viewPager.setAdapter(adapter);
        // set dot indicators
//        tabLayout.setupWithViewPager(viewPager);
        // make status bar transparent
        changeStatusBarColor();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (viewPager.getCurrentItem() < adapter.getCount()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
        /**
         * Add a listener that will be invoked whenever the page changes
         * or is incrementally scrolled
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override public void onPageSelected(int position) {
                if (position == adapter.getCount() - 1) {
                    textView.setText(R.string.get_started);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(OnboardingActivity.this, RegisterActivity.class));
                        }
                    });

                } else {
                    textView.setText(R.string.next);
                }
            }
            @Override public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


}