package com.example.bequem.home.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bequem.R;
import com.example.bequem.databinding.FragmentFavoriteBinding;
import com.example.bequem.home.adapter.WishlistAdapter;
import com.example.bequem.home.pojo.Wishlist;
import com.example.bequem.home.viewmodel.WishlistViewModel;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
    public WishlistViewModel wishlistViewModel;
    public WishlistAdapter wishlistAdapter;
    public FragmentFavoriteBinding favouriteBinding;
    public String user_id;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        wishlistViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(WishlistViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favouriteBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_favorite, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        favouriteBinding.recyclerFavourites.setLayoutManager(new LinearLayoutManager(getActivity()));

        favouriteBinding.recyclerFavourites.setHasFixedSize(true);



        getFav();

        return  favouriteBinding.getRoot();


    }

    public void getFav(){
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            wishlistViewModel.getFav(user_id).observe(this.getActivity(),favouritesResponse -> {
                if (favouritesResponse != null && favouritesResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    wishlistAdapter=new WishlistAdapter(getActivity(),favouritesResponse.getFavourites());
                    favouriteBinding.recyclerFavourites.setAdapter(wishlistAdapter);
                }

            });
        }
    }


}