package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityForgotPasswordBinding;
import com.example.bequem.home.viewmodel.ForgotViewModel;
import com.example.bequem.utils.BaseActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class ForgotPasswordActivity extends BaseActivity {
    public ActivityForgotPasswordBinding forgetPasswordBinding;
    public ForgotViewModel forgotViewModel;
    public ProgressDialog progressDialog;
    public String email,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        forgetPasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);

        forgotViewModel= ViewModelProviders.of(this).get(ForgotViewModel.class);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        userId=sharedPreferences.getString(Constants.USER_ID,null);


        forgetPasswordBinding.buttonLogin.setOnClickListener(v -> {
            if (validatefield()){
                forgetPassword();
            }
        });
    }

    public boolean validatefield(){
        email=requireNonNull(forgetPasswordBinding.editTextEmail.getText().toString().trim());

        if (isEmpty(email)){
            forgetPasswordBinding.editTextEmail.setError("please enter your email");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() < 5){
            forgetPasswordBinding.editTextEmail.setError("Invalid email address");
            return false;
        }
        return true;
    }

    public void forgetPassword(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            forgotViewModel.forgotPassword(email,userId).observe(this,comonResponse -> {
                progressDialog.dismiss();

                if(comonResponse != null && comonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    showSnackBar(this,comonResponse.getMessage());
                    forgetPasswordBinding.editTextEmail.setText("");
                }
            });


        }
        else {
            showErrorSnackBar(this,"No Internet Connection");
        }
    }
}