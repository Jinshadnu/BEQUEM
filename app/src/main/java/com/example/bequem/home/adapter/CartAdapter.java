package com.example.bequem.home.adapter;


import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutCartBinding;
import com.example.bequem.home.pojo.CartResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
public Context context;
public List<CartResponse.Cart> cartList;
public String cart_id,user_id;
    public onDeleteListener deleteListener;

    public CartAdapter(Context context, List<CartResponse.Cart> cartList,String userId) {
        this.context = context;
        this.cartList = cartList;
        this.user_id=userId;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCartBinding cartBinding= DataBindingUtil.inflate(from(context), R.layout.layout_cart,parent,false);
        return  new CartViewHolder(cartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
     CartResponse.Cart cart=cartList.get(position);
     holder.cartBinding.setCartItems(cart);
        holder.cartBinding.elegantQuantity.setNumber(cartList.get(position).getQuantity());
        holder.cartBinding.cardViewCart.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_fall_down));
        holder.cartBinding.imageViewDelete.setOnClickListener(v -> {
        cart_id=cartList.get(position).getItem_id();
         deleteListener.onDelete(user_id,cart_id);
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public LayoutCartBinding cartBinding;
        public CartViewHolder(@NonNull LayoutCartBinding cartBinding) {
            super(cartBinding.getRoot());
            this.cartBinding=cartBinding;
        }
    }
    public interface onDeleteListener{
        void onDelete(String userId,String cartId);
    }
    public void setDeleteListener(onDeleteListener listener)
    {
        this.deleteListener=listener;
    }


}
