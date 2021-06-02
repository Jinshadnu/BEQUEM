package com.example.bequem.home.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bequem.R;
import com.example.bequem.core.SessionManager;
import com.example.bequem.databinding.FragmentProfileBinding;
import com.example.bequem.home.activity.AddAddressActivity;
import com.example.bequem.home.activity.AddressActivity;
import com.example.bequem.home.activity.ChangepasswordActivity;
import com.example.bequem.home.activity.EditProfileActivity;
import com.example.bequem.home.activity.ForgotPasswordActivity;
import com.example.bequem.home.activity.MyOrderActivity;
import com.example.bequem.home.activity.OrderActivity;
import com.example.bequem.home.viewmodel.CartViewModel;
import com.example.bequem.home.viewmodel.ProfileViewModel;
import com.example.bequem.login.LoginActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.text.TextUtils.isEmpty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    public FragmentProfileBinding profileBinding;
    public ProfileViewModel profileViewModel;
    public String username,phone,email;
    public View view;
    public String user_id;
    public int position;
    public String userphone,useremail;
    public EditText editText_phone,editText_email;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        profileViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,user_id);
        userphone=sharedPreferences.getString(Constants.PHONE,null);
        useremail=sharedPreferences.getString(Constants.EMAIL,null);
        username=sharedPreferences.getString(Constants.USER_NAME,null);


        profileBinding.textView5.setText(userphone);
        profileBinding.textView6.setText(useremail);
        profileBinding.textProfilename.setText(username);


        profileBinding.textAddress.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddAddressActivity.class));
        });
        profileBinding.textHistory.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), OrderActivity.class));
        });

        profileBinding.textChangepassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangepasswordActivity.class));
        });

        profileBinding.textForgetpassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
        });

        profileBinding.textLogout.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(this.getString(R.string.logout));
            alertDialog.setTitle(getActivity().getString(R.string.logout_message));

            alertDialog.setPositiveButton(this.getString(R.string.yes),(dialog, which) -> {
                SessionManager.getSessionInstance(getActivity()).clearUserCredentials();
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.setFlags((FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK));
                dialog.cancel();
                startActivity(loginIntent);
            });
            alertDialog.setNegativeButton(getActivity().getString(R.string.no), (dialog, which) -> dialog.cancel());
            alertDialog.show();
        });

        profileBinding.textEditprofile.setOnClickListener(v1 -> {
            startActivity(new Intent(getActivity(), EditProfileActivity.class));
        });
        return profileBinding.getRoot();
    }









//    public void editProfile(String users_id,String userphoneNu,String usersemail){
//        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
//            profileViewModel.editProfile(users_id,userphoneNu,usersemail).observe(getActivity(),comonResponse -> {
//                if (comonResponse != null && comonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
//                    Toast.makeText(getActivity(),comonResponse.getMessage(),Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getActivity(),comonResponse.getMessage(),Toast.LENGTH_LONG).show();
//                }
//
//            });
//        }
//    }


}