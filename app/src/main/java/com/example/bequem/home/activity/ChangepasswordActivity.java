package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityChangepasswordBinding;
import com.example.bequem.home.viewmodel.ChangePasswordViewModel;
import com.example.bequem.utils.BaseActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class ChangepasswordActivity extends BaseActivity {
    public ChangePasswordViewModel passwordViewModel;
    public ProgressDialog progressDialog;
    public ActivityChangepasswordBinding changePasswordBinding;
    public String oldPassword,newPassword,confirmPassword;
    public String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // setContentView(R.layout.activity_change_password);

        changePasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_changepassword);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);

        id=sharedPreferences.getString(Constants.USER_ID,null);



        passwordViewModel= ViewModelProviders.of(this).get(ChangePasswordViewModel.class);

        changePasswordBinding.buttonLogin.setOnClickListener(v -> {
            if (validatefield()){
                changePassword();
            }
        });




    }

    public boolean validatefield(){
        oldPassword= requireNonNull(changePasswordBinding.editTextOldPassword.getText().toString().trim());
        newPassword= requireNonNull(changePasswordBinding.editTextNewPassword.getText().toString().trim());
        confirmPassword=requireNonNull(changePasswordBinding.editTextConfirmPassword.getText().toString().trim());

        if (isEmpty(oldPassword)){
            changePasswordBinding.editTextOldPassword.setError("please enter your password");
            return false;
        }

        if (isEmpty(newPassword)){
            changePasswordBinding.editTextNewPassword.setError("please enter your new password");
            return false;
        }

        if (isEmpty(confirmPassword)){
            changePasswordBinding.editTextConfirmPassword.setError("please confirm your password");
            return false;
        }


        if (newPassword.length() < 6){
            changePasswordBinding.editTextNewPassword.setError("Password must be atleast 6 characters");
            return false;
        }

        if(!newPassword.equals(confirmPassword)){
            changePasswordBinding.editTextConfirmPassword.setError("Password not matching");
            return false;
        }

        return true;
    }
    public void changePassword(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();

            passwordViewModel.changePassword(id,oldPassword,newPassword,confirmPassword).observe(this,commonResponse -> {
                progressDialog.dismiss();

                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    showSnackBar(this,commonResponse.getMessage());
                    changePasswordBinding.editTextOldPassword.setText(" ");
                    changePasswordBinding.editTextNewPassword.setText(" ");
                    changePasswordBinding.editTextConfirmPassword.setText(" ");
                    finish();
                }
                else {
                    showSnackBar(this,commonResponse.getMessage());
                }
            });
        }
        else {
            showErrorSnackBar(this,"No Internet Connection");
        }
    }

}