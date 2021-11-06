package com.example.phonestore.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.phonestore.information.fragmentDelivered;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Phone> mData;
    private List<Phone> mfilteredData;
    private static String url = "http://10.0.2.2:5000/get-image/";

    public RecyclerViewAdapter(Context mContext, List<Phone> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mfilteredData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_phone,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        holder.tv_phone_title.setText(mfilteredData.get(position).getPhoneName());
        Picasso.with(mContext).load(url+mfilteredData.get(position).getImage()).into(holder.img_phone_thumbnail);
        holder.phonePrice.setText(formatter.format(mfilteredData.get(position).getPrice())+" VNĐ");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPhone(position);
            }
        });
    }

    public void openActivityPhone(int position){
        Intent intent = new Intent(mContext,activityPhone.class);

        intent.putExtra("Phone",mfilteredData.get(position));
        intent.putExtra("show","disShow");

        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mfilteredData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_phone_title;
        ImageView img_phone_thumbnail;
        TextView phonePrice;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);
            tv_phone_title = (TextView) itemView.findViewById(R.id.phone_title_id);
            img_phone_thumbnail = (ImageView) itemView.findViewById(R.id.img_phone_id);
            phonePrice =(TextView) itemView.findViewById(R.id.phonePrice_id);
            cardView = (CardView) itemView.findViewById((R.id.cardview_id));
        }
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key = charSequence.toString();
                if(key.isEmpty()){
                    mfilteredData = mData;
                }else {
                    List<Phone> listFiltered = new ArrayList<>();
                    for (Phone row: mData){
                        if(row.getPhoneName().toLowerCase().contains(key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    }
                    mfilteredData = listFiltered;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values =mfilteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mfilteredData = (List<Phone>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void sortPrice(Boolean isSort){
        if(isSort){
            Collections.sort(mfilteredData, new Comparator<Phone>() {
                @Override
                public int compare(Phone phone, Phone t1) {
                    return Double.compare(phone.getPrice(),t1.getPrice());
                }
            });
            notifyDataSetChanged();
        }else{
            Collections.sort(mfilteredData, new Comparator<Phone>() {
                @Override
                public int compare(Phone phone, Phone t1) {
                    return Double.compare(t1.getPrice(),phone.getPrice());
                }
            });
            notifyDataSetChanged();
        }
    }

    public void sortByName(){
        Collections.sort(mfilteredData, new Comparator<Phone>() {
            @Override
            public int compare(Phone phone, Phone t1) {
                return phone.getPhoneName().compareTo(t1.getPhoneName());
            }
        });
        notifyDataSetChanged();
    }

    public List<Phone> filterByPrice(double min, double max) {

        List<Phone> temp = new ArrayList<>();

        for (Phone phone : mfilteredData){
            if(phone.getPrice()>=min&&phone.getPrice()<=max){
                temp.add(phone);
            }
        }
        return temp;
    }

    public List<Phone> filterByPrice_colors(double min, double max,String colors){
        List<Phone> temp = new ArrayList<>();
        for (Phone phone : mfilteredData){
            if(phone.getPrice()>=min&&phone.getPrice()<=max&&colors.equals(phone.getColor())){
                temp.add(phone);
            }
        }
        return temp;
    }

    public List<Phone> filterByPrice_Brands(double min, double max,String brands){
        List<Phone> temp = new ArrayList<>();
        for (Phone phone : mfilteredData){
            if(phone.getPrice()>=min&&phone.getPrice()<=max&&brands.equals(phone.getPhoneType())){
                temp.add(phone);
            }
        }
        return temp;
    }

    public List<Phone> filterByColor(String colors){
        List<Phone> temp = new ArrayList<>();
        for (Phone phone : mfilteredData){
            if(colors.equals(phone.getColor())){
                temp.add(phone);
            }
        }
        return temp;
    }

    public List<Phone> filterByBrands(String brands){
        List<Phone> temp = new ArrayList<>();
        for (Phone phone : mfilteredData){
            if(brands.equals(phone.getPhoneType())){
                temp.add(phone);
            }
        }
        return temp;
    }


    public void filterAll(List<Phone> filter){
        mfilteredData = filter;
    }

    public void showFilter(List<Phone> data){
        mfilteredData = data;
        notifyDataSetChanged();
    }

}
