package com.example.bequem.home.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bequem.R;
import com.example.bequem.databinding.FragmentProfileBinding;
import com.example.bequem.home.activity.AddressActivity;
import com.example.bequem.home.activity.ChangepasswordActivity;
import com.example.bequem.home.activity.ForgotPasswordActivity;
import com.example.bequem.home.activity.MyOrderActivity;
import com.example.bequem.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    public FragmentProfileBinding profileBinding;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        profileBinding.textAddress.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddressActivity.class));
        });
        profileBinding.textHistory.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), MyOrderActivity.class));
        });

        profileBinding.textChangepassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangepasswordActivity.class));
        });

        profileBinding.textForgetpassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
        });

        profileBinding.textLogout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        profileBinding.textEditprofile.setOnClickListener(v -> {
            withEditText(v);
        });
        return profileBinding.getRoot();
    }

    public void withEditText(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Profile");
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.layout_edit_profile, null);
        editText_phone = view.findViewById(R.id.editText_phone);
        editText_email = view.findViewById(R.id.editTextEmail);
        builder.setView(view);


        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Toast.makeText(cogetApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                // Toast.makeText(cogetApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}