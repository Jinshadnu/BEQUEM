package com.example.bequem.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bequem.R;
import com.example.bequem.databinding.LayoutAddressBinding;
import com.example.bequem.databinding.LayoutShipaddressBinding;
import com.example.bequem.home.activity.PriceDetailsActivity;
import com.example.bequem.home.pojo.AddressResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class ShipAddressAdapter extends RecyclerView.Adapter<ShipAddressAdapter.AddressViewHolder> {
    public Context context;
    public List<AddressResponse.Address> addressList;
    public String count,price,minimum,delivery_charge;

    public ShipAddressAdapter(Context context, List<AddressResponse.Address> addressList,String count,String price,String minimum,String delivery_charge) {
        this.context = context;
        this.addressList = addressList;
        this.count=count;
        this.price=price;
        this.minimum=minimum;
        this.delivery_charge=delivery_charge;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAddressBinding addressBinding= DataBindingUtil.inflate(from(context), R.layout.layout_address,parent,false);
        return new AddressViewHolder(addressBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressResponse.Address address=addressList.get(position);
        holder.addressBinding.setAddress(address);

        holder.addressBinding.buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), PriceDetailsActivity.class);
                String address_id=addressList.get(position).getAdd_id();
                intent.putExtra("address_id",address_id);
                intent.putExtra("count",count);
                intent.putExtra("price",price);
                intent.putExtra("minimum",minimum);
                intent.putExtra("delivery_charge",delivery_charge);
                context.startActivity(intent);
            }
        });


//        holder.categoriesBinding.cardViewServices.setOnClickListener(view -> {
//            Intent intent=new Intent(context.getApplicationContext(), ItemActivity.class);
//            String category_id=categoriesList.get(position).getCategory_id();
//            intent.putExtra("category_id",category_id);
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        public LayoutAddressBinding addressBinding;
        public AddressViewHolder(@NonNull LayoutAddressBinding layoutAddressBinding) {
            super(layoutAddressBinding.getRoot());
            this.addressBinding=layoutAddressBinding;
        }
    }
}
