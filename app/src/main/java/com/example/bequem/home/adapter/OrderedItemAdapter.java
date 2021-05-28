package com.example.bequem.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutOrderitemsBinding;
import com.example.bequem.home.pojo.OrderedItemResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class OrderedItemAdapter extends RecyclerView.Adapter<OrderedItemAdapter.OrderViewHolder> {
    public Context context;
    public List<OrderedItemResponse.OrderedItems> orderItemsList;


    public OrderedItemAdapter(Context context, List<OrderedItemResponse.OrderedItems> itemResponses) {
        this.context = context;
        this.orderItemsList = itemResponses;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutOrderitemsBinding orderedItemsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_orderitems,parent,false);
        return new OrderViewHolder(orderedItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
     OrderedItemResponse.OrderedItems itemResponse=orderItemsList.get(position);
     holder.orderedItemsBinding.setOrdereditems(itemResponse);

    }

    @Override
    public int getItemCount() {
        return orderItemsList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public LayoutOrderitemsBinding orderedItemsBinding;
        public OrderViewHolder(@NonNull LayoutOrderitemsBinding orderedItemsBinding) {
            super(orderedItemsBinding.getRoot());
            this.orderedItemsBinding=orderedItemsBinding;
        }
    }
}
