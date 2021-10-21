package com.example.phonestore.location;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.location.City;

import java.util.ArrayList;
import java.util.List;

public class recycleAdapterCity extends RecyclerView.Adapter<recycleAdapterCity.MyViewHolder> {

    private List<City> mData;
    private ItemClickCityListener listener;
    private List<City> mfilteredData;

    public interface ItemClickCityListener {
        //Achieve the click method, passing the subscript.
        void onItemClick(City city);
    }

    public recycleAdapterCity(List<City> mData, ItemClickCityListener listener) {
        this.mData = mData;
        this.listener = listener;
        this.mfilteredData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.itemlocation, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final City city = mfilteredData.get(position);
        if(city==null)
            return;

        holder.textLocation.setText(mfilteredData.get(position).getName());
        holder.textLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mfilteredData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textLocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textLocation = (TextView) itemView.findViewById(R.id.location_id);

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
                    List<City> listFiltered = new ArrayList<>();
                    for (City row: mData){
                        if(row.getName().toLowerCase().contains(key.toLowerCase())){
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
                mfilteredData = (List<City>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
