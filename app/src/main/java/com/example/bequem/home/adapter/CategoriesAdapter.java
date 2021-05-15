package com.example.bequem.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bequem.R;
import com.example.bequem.databinding.LayoutCategoriesBinding;
import com.example.bequem.home.activity.ItemActivity;
import com.example.bequem.home.pojo.Categories;
import com.example.bequem.home.pojo.CategoryResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    public Context context;
    public List<CategoryResponse.Categoriess> categoriesList;

    public CategoriesAdapter(Context context, List<CategoryResponse.Categoriess> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCategoriesBinding categoriesBinding= DataBindingUtil.inflate(from(context), R.layout.layout_categories,parent,false);
        return new CategoryViewHolder(categoriesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryResponse.Categoriess categories=categoriesList.get(position);
        holder.categoriesBinding.setCategories(categories);

        holder.categoriesBinding.cardViewCategories.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

        holder.categoriesBinding.cardViewServices.setOnClickListener(view -> {
            Intent intent=new Intent(context.getApplicationContext(), ItemActivity.class);
            String category_id=categoriesList.get(position).getCategory_id();
            intent.putExtra("category_id",category_id);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public LayoutCategoriesBinding categoriesBinding;
        public CategoryViewHolder(@NonNull LayoutCategoriesBinding categoriesBinding) {
            super(categoriesBinding.getRoot());
            this.categoriesBinding=categoriesBinding;
        }
    }
}
