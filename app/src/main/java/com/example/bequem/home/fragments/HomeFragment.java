package com.example.bequem.home.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bequem.R;
import com.example.bequem.databinding.FragmentHomeBinding;
import com.example.bequem.home.adapter.CategoriesAdapter;
import com.example.bequem.home.adapter.FeaturedProductAdapter;
import com.example.bequem.home.adapter.ImageSliderAdapter;
import com.example.bequem.home.pojo.Categories;
import com.example.bequem.home.pojo.FeaturedProducts;
import com.example.bequem.home.viewmodel.BannerViewModel;
import com.example.bequem.home.viewmodel.CategoryViewModel;
import com.example.bequem.home.viewmodel.FeaturedProductViewModel;
import com.example.bequem.home.viewmodel.OfferViewModel;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.NetworkUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public FragmentHomeBinding homeBinding;
    public CategoryViewModel categoryViewModel;
    public CategoriesAdapter categoriesAdapter;
    public OfferViewModel offerViewModel;
    public FeaturedProductViewModel featuredProductViewModel;
    public BannerViewModel bannerViewModel;
    public FeaturedProductAdapter featuredProductAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        bannerViewModel=ViewModelProviders.of((FragmentActivity)this.getActivity()).get(BannerViewModel.class);
        categoryViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(CategoryViewModel.class);
        offerViewModel=ViewModelProviders.of((FragmentActivity)this.getActivity()).get(OfferViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);



        homeBinding.recyclerCategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.recyclerCategories.setHasFixedSize(true);

        homeBinding.recyclerSpecialoffers.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.recyclerSpecialoffers.setHasFixedSize(true);

        homeBinding.swipeLayout.setOnRefreshListener(() -> {
            if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
                setValuesToFields();
                getCategories();
                getfeaturedProduct();
                homeBinding.swipeLayout.setRefreshing(false);
            }else {
                Toast.makeText(getActivity(),"Internet Connection not available",Toast.LENGTH_LONG).show();
            }
        });


        setValuesToFields();

        getCategories();
        getfeaturedProduct();


        return homeBinding.getRoot();
    }


    private void setValuesToFields() {
        //banner image

        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            bannerViewModel.getBanners().observe(getActivity(),bannerResponse -> {
                // List<BannerResponse.Banners> bannerList = new ArrayList<>();

                homeBinding.rlBanner.setVisibility(View.VISIBLE);
                homeBinding.vpImage.setAdapter(new ImageSliderAdapter(getActivity(), bannerResponse.getBanners()));

                //homeBinding.cpImage.setViewPager(homeBinding.vpImage);

                final float density = getResources().getDisplayMetrics().density;

                //Set circle indicator radius
                //homeBinding.cpImage.setRadius(5 * density);

                homeBinding.vpImage.startAutoScroll();
                homeBinding.vpImage.setInterval(5000);
                homeBinding.vpImage.setCycle(true);
                homeBinding.vpImage.setStopScrollWhenTouch(true);

                // Pager listener over indicator
//               // homeBinding.cpImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//                    @Override
//                    public void onPageSelected(int position) {
//
//                    }
//
//                    @Override
//                    public void onPageScrolled(int pos, float arg1, int arg2) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int pos) {
//
//                    }
//                });
            });

        }
        else{
            Toast.makeText(getActivity(),"Internet Connection not available",Toast.LENGTH_LONG).show();
        }

    }


    public void getCategories(){
            if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
               categoryViewModel.getCategories().observe(getActivity(),categoryResponse -> {
                 if(categoryResponse !=null && categoryResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                 categoriesAdapter=new CategoriesAdapter(getActivity(),categoryResponse.getCategories());
                 homeBinding.recyclerCategories.setAdapter(categoriesAdapter);
                 }
               });
            }


    }
    public void getfeaturedProduct(){
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            offerViewModel.getOffers().observe(this.getActivity(),offerResponse -> {
                if (offerResponse !=null && offerResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    featuredProductAdapter=new FeaturedProductAdapter(getActivity(),offerResponse.getSpecial_offer_items());
                    homeBinding.recyclerSpecialoffers.setAdapter(featuredProductAdapter);
                }
            });
        }
    }

}