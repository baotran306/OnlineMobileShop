package com.example.phonestore.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.menu.activityPhone;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class RecyclerViewAdapter_home_search extends RecyclerView.Adapter<RecyclerViewAdapter_home_search.myViewHolder> {


    private Context mContext;
    private List<Phone> mData;
    private List<Phone> mfilteredData;
    public static String url = "http://10.0.2.2:5000/get-image/";

    public RecyclerViewAdapter_home_search(Context mContext, List<Phone> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mfilteredData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_home_search.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_phone,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_home_search.myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        holder.tv_phone_title.setText(mfilteredData.get(position).getPhoneName());
        Picasso.with(mContext).load(url+mfilteredData.get(position).getImage())
                .resize(150,150).into(holder.img_phone_thumbnail);
        holder.phonePrice.setText(formatter.format(mfilteredData.get(position).getPrice())+" VNĐ");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPhone(position);
            }
        });
    }

    public void openActivityPhone(int position){
        Intent intent = new Intent(mContext, activityPhone.class);
        Phone phone = new Phone();
        phone = mfilteredData.get(position);
        intent.putExtra("Phone", phone);
        intent.putExtra("show", "disShow");
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mfilteredData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv_phone_title;
        ImageView img_phone_thumbnail;
        TextView phonePrice;
        CardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_phone_title = (TextView) itemView.findViewById(R.id.phone_title_id);
            img_phone_thumbnail = (ImageView) itemView.findViewById(R.id.img_phone_id);
            phonePrice =(TextView) itemView.findViewById(R.id.phonePrice_id);
            cardView = (CardView) itemView.findViewById((R.id.cardview_id));
        }
    }
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key = charSequence.toString();
                if (key.isEmpty()) {
                    mfilteredData = mData;
                } else {
                    List<Phone> listFiltered = new ArrayList<>();
                    for (Phone row : mData) {
                        if (row.getPhoneName().toLowerCase().contains(key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    mfilteredData = listFiltered;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mfilteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mfilteredData = (List<Phone>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
