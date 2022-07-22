package com.example.suryatradersemployee.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatradersemployee.databinding.SubOrderRowBinding;
import com.example.suryatradersemployee.model.SubOrders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SubOrderAdapter extends ListAdapter<SubOrders, SubOrderAdapter.SubOrderViewHolder> {

    private static final String TAG="SubOrderAdapter ";

    public SubOrderAdapter()
    {
        super(SubOrders.itemCallback);

    }

    @NonNull
    @Override
    public com.example.suryatradersemployee.adapters.SubOrderAdapter.SubOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        SubOrderRowBinding subOrderRowBinding=SubOrderRowBinding.inflate(layoutInflater, parent,false);

        return new SubOrderViewHolder(subOrderRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubOrderViewHolder holder, int position) {
        SubOrders subOrders =getItem(position);
        holder.subOrderRowBinding.setSubOrders(subOrders);

    }


    public class SubOrderViewHolder extends RecyclerView.ViewHolder {

        SubOrderRowBinding subOrderRowBinding;

        public SubOrderViewHolder(SubOrderRowBinding binding)
        {
            super(binding.getRoot());
            this.subOrderRowBinding=binding;
        }


    }
}
