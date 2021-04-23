package com.example.bequem.home.adapter;


import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bequem.R;
import com.example.bequem.databinding.LayoutCartBinding;
import com.example.bequem.home.pojo.Cart;

import java.util.List;

import static android.view.LayoutInflater.from;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
public Context context;
public List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCartBinding cartBinding= DataBindingUtil.inflate(from(context), R.layout.layout_cart,parent,false);
        return  new CartViewHolder(cartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
     Cart cart=cartList.get(position);
     holder.cartBinding.setCartItems(cart);
     holder.cartBinding.cardViewCart.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_fall_down));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public LayoutCartBinding cartBinding;
        public CartViewHolder(@NonNull LayoutCartBinding cartBinding) {
            super(cartBinding.getRoot());
            this.cartBinding=cartBinding;
        }
    }
}
