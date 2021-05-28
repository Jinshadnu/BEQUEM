package com.example.bequem.home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutMyorderBinding;
import com.example.bequem.home.activity.OrderedItemsActivity;
import com.example.bequem.home.pojo.OrderResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
public Context context;
public List<OrderResponse.Orders> orderList;
    public int postion=0;
    public String order_status,order_id;
    public cancelOrderListener cancelOrderListener;

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
        order_status=order.getOrder_status();

        if (order_status.equals("APPROVED")){
            holder.myorderBinding.textCancel.setVisibility(View.GONE);
            holder.myorderBinding.viewCancel.setVisibility(View.GONE);
        }
        if (order_status.equals("CANCELLED")){
            holder.myorderBinding.textCancel.setVisibility(View.GONE);
            holder.myorderBinding.viewCancel.setVisibility(View.GONE);
        }
        if (order_status.equals("DISPATCHED")){
            holder.myorderBinding.textCancel.setVisibility(View.GONE);
            holder.myorderBinding.viewCancel.setVisibility(View.GONE);
        }
        holder.myorderBinding.textCancel.setOnClickListener(view -> {
            order_id=orderList.get(position).getOrder_id();


            AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
            alertDialog.setTitle("Cancel Order");
            alertDialog.setMessage("Are you sure want to cancel this Order ? ");

            alertDialog.setPositiveButton("Yes",(dialogInterface, i) -> {
                cancelOrderListener.onOrderCancel(order_id);
            });

            alertDialog.setNegativeButton("No",(dialogInterface, i) -> {
                dialogInterface.cancel();
            });

            alertDialog.show();


        });

        holder.myorderBinding.textViewmore.setOnClickListener(v -> {
         Intent intent=new Intent(context.getApplicationContext(), OrderedItemsActivity.class);
         postion=holder.getAdapterPosition();
         OrderResponse.Orders orders = orderList.get(position);
         intent.putExtra("orderId",order.getOrder_id());
         context.startActivity(intent);
     });
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
    public interface cancelOrderListener{
        void onOrderCancel(String orderId);
    }
    public void setCancelListener(cancelOrderListener listener){
        this.cancelOrderListener=listener;
    }
}
