package com.example.bequem.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bequem.R;
import com.example.bequem.databinding.LayoutSubcategoryBinding;
import com.example.bequem.home.pojo.SubCategoryResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubCategoryViewHolder> {
    public Context context;
    public List<SubCategoryResponse.Subcategory> subCategoryList;
    private int row_index = 0;
    private ItemClickListener clickListener;
    public String subcategory_id,defualt;



    public SubcategoryAdapter(Context context, List<SubCategoryResponse.Subcategory> subCategoryList) {
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
      SubCategoryResponse.Subcategory subCategory=subCategoryList.get(position);
      holder.subcategoryBinding.setSubcategory(subCategory);

        if(row_index == position){
            holder.subcategoryBinding.relativeLayout.setBackgroundColor(Color.parseColor("#253a95"));
            holder.subcategoryBinding.textViewSubcatname.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.subcategoryBinding.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.subcategoryBinding.textViewSubcatname.setTextColor(Color.parseColor("#253a95"));
        }



    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutSubcategoryBinding subcategoryBinding;
        public SubCategoryViewHolder(@NonNull LayoutSubcategoryBinding subcategoryBinding) {
            super(subcategoryBinding.getRoot());
            this.subcategoryBinding=subcategoryBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            row_index = getAdapterPosition();
            subcategory_id=subCategoryList.get(row_index).getSubcategory_id();
            if (clickListener != null) clickListener.onClick(v, getPosition(),subcategory_id);
            notifyDataSetChanged();
        }
    }
    public interface ItemClickListener {
        void onClick(View view, int position, String subcategoryid);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
