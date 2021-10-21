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
import com.example.phonestore.entity.location.districts;
import com.example.phonestore.entity.location.wards;

import java.util.ArrayList;
import java.util.List;

public class recycleAdapterWards extends RecyclerView.Adapter<recycleAdapterWards.MyViewHolder> {

    private List<wards> mData;
    private ItemClickWardsListener listener;
    private List<wards> mfilteredData;

    public interface ItemClickWardsListener {
        //Achieve the click method, passing the subscript.
        void onItemClick(wards mWards);
    }

    public recycleAdapterWards(List<wards> mData, ItemClickWardsListener listener) {
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

        final wards mWards = mfilteredData.get(position);
        if(mWards==null)
            return;

        holder.textLocation.setText(mWards.getName());
        holder.textLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(mWards);
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

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key = charSequence.toString();
                if (key.isEmpty()) {
                    mfilteredData = mData;
                } else {
                    List<wards> listFiltered = new ArrayList<>();
                    for (wards row : mData) {
                        if (row.getName().toLowerCase().contains(key.toLowerCase())) {
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
                mfilteredData = (List<wards>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
