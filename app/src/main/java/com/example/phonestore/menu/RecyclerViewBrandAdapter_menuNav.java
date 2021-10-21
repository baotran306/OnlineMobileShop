package com.example.phonestore.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Brands;


import java.util.ArrayList;
import java.util.List;

public class RecyclerViewBrandAdapter_menuNav extends RecyclerView.Adapter<RecyclerViewBrandAdapter_menuNav.MyViewHolder> {

    private Context mContext;
    private List<Brands> mData;

    public RecyclerViewBrandAdapter_menuNav(Context mContext, List<Brands> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewBrandAdapter_menuNav.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.brands_multiselect, parent, false);
        return new RecyclerViewBrandAdapter_menuNav.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBrandAdapter_menuNav.MyViewHolder holder, int position) {
        holder.bindTextView(mData.get(position));
        if (mData.get(position).isSelected==false){
            holder.multiSelectBrands.setBackgroundResource(R.drawable.background_isnonselected);
        }
    }

    public List<Brands> getSelectBrands() {
        List<Brands> selectedBrands = new ArrayList<>();
        for (Brands brands : mData) {
            if (brands.isSelected)
                selectedBrands.add(brands);
        }
        return selectedBrands;
    }

    public void resetSelected(){
        for (int i=0;i<mData.size();i++){
            mData.get(i).isSelected = false;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView multiSelectBrands;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            multiSelectBrands = (TextView) itemView.findViewById(R.id.multiSelectBrands_id);
        }

        void bindTextView(final Brands brands) {
            multiSelectBrands.setText(brands.getBrandsName());
            multiSelectBrands.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (brands.isSelected) {
                        brands.isSelected = false;
                    } else {
                        brands.isSelected = true;
                    }
                    if (brands.isSelected) {
                        multiSelectBrands.setBackgroundResource(R.drawable.background_isselected);
                    } else {
                        multiSelectBrands.setBackgroundResource(R.drawable.background_isnonselected);
                    }
                }
            });
        }
    }
}
