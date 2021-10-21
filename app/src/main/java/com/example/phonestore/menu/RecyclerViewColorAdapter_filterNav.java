package com.example.phonestore.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Brands;
import com.example.phonestore.entity.Colors;
import com.example.phonestore.entity.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewColorAdapter_filterNav extends RecyclerView.Adapter<RecyclerViewColorAdapter_filterNav.MyViewHolder>{

    private Context mContext;
    private List<Colors> mData;

    public RecyclerViewColorAdapter_filterNav(Context mContext, List<Colors> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewColorAdapter_filterNav.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.brands_multiselect, parent, false);
        return new RecyclerViewColorAdapter_filterNav.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewColorAdapter_filterNav.MyViewHolder holder, int position) {
        holder.bindTextView(mData.get(position));
        if (mData.get(position).isSelectedColor==false){
            holder.multiSelectColors.setBackgroundResource(R.drawable.background_isnonselected);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Colors> getSelectColor() {
        List<Colors> selectedColors = new ArrayList<>();
        for (Colors color : mData) {
            if(color.isSelectedColor)
                selectedColors.add(color);
        }
        return selectedColors;
    }

    public void resetSelected(){
        for (int i=0;i<mData.size();i++){
            mData.get(i).isSelectedColor = false;
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView multiSelectColors;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            multiSelectColors = (TextView) itemView.findViewById(R.id.multiSelectBrands_id);
        }
        void bindTextView(final Colors  colors) {

            multiSelectColors.setText(colors.getColor());
            multiSelectColors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (colors.isSelectedColor) {
                        colors.isSelectedColor = false;
                    } else {
                        colors.isSelectedColor = true;
                    }
                    if (colors.isSelectedColor) {
                        multiSelectColors.setBackgroundResource(R.drawable.background_isselected);
                    } else {
                        multiSelectColors.setBackgroundResource(R.drawable.background_isnonselected);
                    }
                }
            });
        }
    }
}
