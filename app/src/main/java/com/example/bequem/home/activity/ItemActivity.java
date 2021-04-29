package com.example.bequem.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;

import com.example.bequem.R;
import com.example.bequem.databinding.ActivityItemBinding;
import com.example.bequem.home.adapter.ItemsAdapter;
import com.example.bequem.home.adapter.SubcategoryAdapter;
import com.example.bequem.home.pojo.SubCategory;
import com.example.bequem.home.viewmodel.ItemViewModel;
import com.example.bequem.home.viewmodel.SubCategoryViewModel;
import com.example.bequem.utils.GridSpacingItemDecoration;

import java.util.List;

public class ItemActivity extends AppCompatActivity {
 public ItemViewModel itemViewModel;
 public ItemsAdapter itemsAdapter;
 public ActivityItemBinding itemBinding;
 public SubCategoryViewModel subCategoryViewModel;
 public SubcategoryAdapter subcategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemBinding= DataBindingUtil.setContentView(this,R.layout.activity_item);

        itemBinding.layoutBase.textTitle.setText("Items");

        itemBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        itemBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        itemViewModel= ViewModelProviders.of(this).get(ItemViewModel.class);
        subCategoryViewModel= ViewModelProviders.of(this).get(SubCategoryViewModel.class);

        itemBinding.recyclerSubcategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        itemBinding.recyclerSubcategory.setHasFixedSize(true);


        itemBinding.recyclerProducts.setLayoutManager(new GridLayoutManager(this,2));
        itemBinding.recyclerProducts.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));

        itemBinding.recyclerProducts.setHasFixedSize(true);



        getItems();
        getSubcategory();

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void getItems(){
        itemViewModel.getItems().observe((LifecycleOwner)this, items -> {
            itemsAdapter=new ItemsAdapter(this,items);
            itemBinding.recyclerProducts.setAdapter(itemsAdapter);
        });
    }

    public void getSubcategory(){
      subCategoryViewModel.getSubCategories().observe((LifecycleOwner)this,subCategory -> {
          subcategoryAdapter=new SubcategoryAdapter(this,  subCategory);
          itemBinding.recyclerSubcategory.setAdapter(subcategoryAdapter);
      });
    }

}