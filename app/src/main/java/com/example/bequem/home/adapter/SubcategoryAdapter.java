package com.example.bequem.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bequem.R;
import com.example.bequem.databinding.LayoutSubcategoryBinding;
import com.example.bequem.home.pojo.SubCategory;

import java.util.List;

import static android.view.LayoutInflater.from;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubCategoryViewHolder> {
    public Context context;
    public List<SubCategory> subCategoryList;


    public SubcategoryAdapter(Context context, List<SubCategory> subCategoryList) {
        this.context = context;
        this.subCategoryList = subCategoryList;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubcategoryBinding subcategoryBinding= DataBindingUtil.inflate(from(context), R.layout.layout_subcategory,parent,false);
        return new SubCategoryViewHolder(subcategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
      SubCategory subCategory=subCategoryList.get(position);
      holder.subcategoryBinding.setSubcategory(subCategory);
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        public LayoutSubcategoryBinding subcategoryBinding;
        public SubCategoryViewHolder(@NonNull LayoutSubcategoryBinding subcategoryBinding) {
            super(subcategoryBinding.getRoot());
            this.subcategoryBinding=subcategoryBinding;
        }
    }
}
