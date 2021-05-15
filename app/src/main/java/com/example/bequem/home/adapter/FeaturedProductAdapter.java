package com.example.bequem.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutFeaturedBinding;
import com.example.bequem.home.pojo.FeaturedProducts;
import com.example.bequem.home.pojo.OfferResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.FeaturedViewHolder> {
    public Context context;
    public List<OfferResponse.Special_offer_items> featuredProductsList;

    public FeaturedProductAdapter(Context context, List<OfferResponse.Special_offer_items> featuredProductsList) {
        this.context = context;
        this.featuredProductsList = featuredProductsList;
    }


    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutFeaturedBinding subitemsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_featured,parent,false);
        return new FeaturedProductAdapter.FeaturedViewHolder(subitemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
     OfferResponse.Special_offer_items featuredProducts=featuredProductsList.get(position);
     holder.featuredBinding.setFeaturedproducts(featuredProducts);
    }

    @Override
    public int getItemCount() {
        return featuredProductsList.size();
    }

    public class FeaturedViewHolder extends RecyclerView.ViewHolder {
        public LayoutFeaturedBinding featuredBinding;
        public FeaturedViewHolder(@NonNull LayoutFeaturedBinding featuredBinding) {
            super(featuredBinding.getRoot());
            this.featuredBinding=featuredBinding;

        }
    }
}
