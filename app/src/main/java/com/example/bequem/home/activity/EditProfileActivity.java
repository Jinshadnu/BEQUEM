package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityEditProfileBinding;
import com.example.bequem.home.viewmodel.ProfileViewModel;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class EditProfileActivity extends AppCompatActivity {
 public ActivityEditProfileBinding editProfileBinding;
    public ProfileViewModel profileViewModel;
    public String userphone,useremail,user_id;
    public String phone,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editProfileBinding= DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);

        editProfileBinding.layoutBase.textTitle.setText("Edit Profile");

        editProfileBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        editProfileBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        userphone=sharedpreferences.getString(Constants.PHONE,null);
        useremail=sharedpreferences.getString(Constants.EMAIL,null);
        user_id=sharedpreferences.getString(Constants.USER_ID,null);



        profileViewModel=new ViewModelProvider(this).get(ProfileViewModel.class);



        editProfileBinding.buttonSubmit.setOnClickListener(v -> {
            if (validate()){
                editProfile();
            }
        });



    }

    public boolean validate(){
        phone=requireNonNull(editProfileBinding.editTextPhone.getText().toString().trim());
        email=requireNonNull(editProfileBinding.editTextEmail.getText().toString().trim());
        if (isEmpty(phone)) {
            editProfileBinding.editTextPhone.setError("Please enter phone number");
            return false;
        }

        if (isEmpty(email)) {
            editProfileBinding.editTextEmail.setError("Please enter email");

            return false;
        }
        if(phone.length() < 10){
            editProfileBinding.editTextPhone.setError("Invalid phone number");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() < 5){
            //editText_email.setError("Invalid email address");
            return false;
        }
        return true;
    }

    public void editProfile(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            profileViewModel.editProfile(user_id,phone,email).observe(this,commonResponse -> {
                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    Toast.makeText(getApplicationContext(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }
        else {
            Toast.makeText(getApplicationContext(),"Internet Connetion Not Available",Toast.LENGTH_LONG).show();
        }
    }
}