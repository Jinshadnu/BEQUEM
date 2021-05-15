package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityItemBinding;
import com.example.bequem.home.adapter.ItemsAdapter;
import com.example.bequem.home.adapter.SubcategoryAdapter;
import com.example.bequem.home.pojo.SubCategoryResponse;
import com.example.bequem.home.viewmodel.ItemViewModel;
import com.example.bequem.home.viewmodel.SubCategoryViewModel;
import com.example.bequem.home.viewmodel.WishlistViewModel;
import com.example.bequem.utils.AddCartCallBack;
import com.example.bequem.utils.BaseActivity;
import com.example.bequem.utils.Constants;
import com.example.bequem.utils.GridSpacingItemDecoration;
import com.example.bequem.utils.NetworkUtilities;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends BaseActivity implements AddCartCallBack, SubcategoryAdapter.ItemClickListener {
 public ItemViewModel itemViewModel;
 public ItemsAdapter itemsAdapter;
 public ActivityItemBinding itemBinding;
 public SubCategoryViewModel subCategoryViewModel;
 public WishlistViewModel wishlistViewModel;
 public SubcategoryAdapter subcategoryAdapter;
 public String category_id,subcategoryId;
 public String defaultvalue;
 String userId;
 public int fav_status;
 public List<SubCategoryResponse.Subcategory> subcategoryList;
    public int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemBinding= DataBindingUtil.setContentView(this,R.layout.activity_item);

        itemBinding.layoutBase.textTitle.setText("Items");

        itemBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        itemBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        userId=sharedPreferences.getString(Constants.USER_ID,null);


        category_id=getIntent().getStringExtra("category_id");


        itemViewModel= ViewModelProviders.of(this).get(ItemViewModel.class);
        subCategoryViewModel= ViewModelProviders.of(this).get(SubCategoryViewModel.class);
        wishlistViewModel= ViewModelProviders.of(this).get(WishlistViewModel.class);

        itemBinding.recyclerSubcategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        itemBinding.recyclerSubcategory.setHasFixedSize(true);


        itemBinding.recyclerProducts.setLayoutManager(new GridLayoutManager(this,2));
        itemBinding.recyclerProducts.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));

        itemBinding.recyclerProducts.setHasFixedSize(true);


        getSubcategory();


    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onClick(View view, int position, String subcategoryid) {
        subcategoryAdapter.setItemClickListener(this);
        this.position=position;
        this.subcategoryId=subcategoryid;
    }




    public void getSubcategory(){
      if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
          subCategoryViewModel.getSubCategories(category_id).observe(this,subCategoryResponse -> {
              if (subCategoryResponse !=null && subCategoryResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                  defaultvalue=subCategoryResponse.getSubcategory().get(0).getSubcategory_id();
                  subcategoryAdapter=new SubcategoryAdapter(this,  subCategoryResponse.getSubcategory());
                  itemBinding.recyclerSubcategory.setAdapter(subcategoryAdapter);
                  getItems(defaultvalue);
                  subcategoryAdapter.setItemClickListener((view, position1, subcategoryid) -> {
                   getItems(subcategoryid);
                  });
              }

          });
      }

    }



    public void getItems(String subcategoryid){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            itemViewModel.getItems(subcategoryid).observe(this,response -> {
                if(response != null && response.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    itemsAdapter=new ItemsAdapter(this,response.getItems(),subcategoryid);
                    itemBinding.recyclerProducts.setAdapter(itemsAdapter);

                }
            });
        }

    }

    @Override
    public void onAddProduct() {

    }

    @Override
    public void onRemoveProduct() {

    }

    @Override
    public void addToFavourite(String item_id) {
     if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
         fav_status=1;
         wishlistViewModel.addToFavourites(userId,item_id,fav_status).observe(this,commonResponse -> {
             if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                 showSnackBar(this,commonResponse.getMessage());
             }
         });
     }
    }
}