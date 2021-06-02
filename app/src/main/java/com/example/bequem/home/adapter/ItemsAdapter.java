package com.example.bequem.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutSubitemsBinding;
import com.example.bequem.home.activity.ItemActivity;
import com.example.bequem.home.activity.ProductDetailsActivity;
import com.example.bequem.home.pojo.ItemResponse;
import com.example.bequem.utils.AddCartCallBack;

import java.util.List;

import static android.view.LayoutInflater.from;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    public Context context;
    public List<ItemResponse.Items> itemsList;
    public String subcategoryId;

    public ItemsAdapter(Context context, List<ItemResponse.Items> itemsList,String subcategoryId) {
        this.context = context;
        this.itemsList = itemsList;
        this.subcategoryId=subcategoryId;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubitemsBinding subitemsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_subitems,parent,false);
        return new ItemViewHolder(subitemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
      ItemResponse.Items items=itemsList.get(position);
      holder.subitemsBinding.setSubitems(items);
      holder.subitemsBinding.cardViewItem.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
      holder.subitemsBinding.cardViewItem.setOnClickListener(v -> {
          Intent intent=new Intent(context.getApplicationContext(), ProductDetailsActivity.class);
          intent.putExtra("subcategory_id",subcategoryId);
          intent.putExtra("item_name",itemsList.get(position).getItem_name());
          intent.putExtra("item_price",itemsList.get(position).getItem_price());
          intent.putExtra("item_decription",itemsList.get(position).getItem_description());
          intent.putExtra("item_id",itemsList.get(position).getItem_id());
          intent.putExtra("pic1",itemsList.get(position).getPic1());
          intent.putExtra("pic2",itemsList.get(position).getPic2());
          intent.putExtra("pic3",itemsList.get(position).getPic3());
          intent.putExtra("pic4",itemsList.get(position).getPic4());
          context.startActivity(intent);
      });
//      holder.subitemsBinding.favourite.setOnClickListener(v -> {
//       String item_id=itemsList.get(position).getItem_id();
//          if (context instanceof ItemActivity) {
//              ((AddCartCallBack) context).addToFavourite(item_id);
//              //((AddCartCallBack) context).onAddProduct();
//          }
//      });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public LayoutSubitemsBinding subitemsBinding;
        public ItemViewHolder(@NonNull LayoutSubitemsBinding subitemsBinding) {
            super(subitemsBinding.getRoot());
            this.subitemsBinding=subitemsBinding;
        }
    }
}
