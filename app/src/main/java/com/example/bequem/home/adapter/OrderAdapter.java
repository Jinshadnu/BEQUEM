package com.example.bequem.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutMyorderBinding;
import com.example.bequem.home.pojo.OrderResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
public Context context;
public List<OrderResponse.Orders> orderList;

    public OrderAdapter(Context context, List<OrderResponse.Orders> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutMyorderBinding myorderBinding= DataBindingUtil.inflate(from(context), R.layout.layout_myorder,parent,false);
        return  new OrderAdapter.OrderViewHolder(myorderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
     OrderResponse.Orders order=orderList.get(position);
     holder.myorderBinding.setOrders(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public LayoutMyorderBinding myorderBinding;
        public OrderViewHolder(@NonNull LayoutMyorderBinding myorderBinding) {
            super(myorderBinding.getRoot());
            this.myorderBinding=myorderBinding;

        }
    }
}
