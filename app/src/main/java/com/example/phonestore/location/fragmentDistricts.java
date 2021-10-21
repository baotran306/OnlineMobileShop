package com.example.phonestore.location;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.entity.location.City;
import com.example.phonestore.entity.location.districts;

import java.util.ArrayList;
import java.util.List;


public class fragmentDistricts extends Fragment {

    RecyclerView myRecyclerviewDistricts;
    activityLocation mActivityLocation;
    recycleAdapterDistrict adapter;
    sendDataDistricts dataDistricts;
    private TextView textSearch;
    private CharSequence search ="";
    List<districts> districts;

    public interface sendDataDistricts {
        void sendWarns(districts mDistricts);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataDistricts = (sendDataDistricts) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_districts, container, false);
        initUI(view);
        searchDistrictListener();
        return view;
    }
    private void initUI(View view) {
        myRecyclerviewDistricts = (RecyclerView) view.findViewById(R.id.recyclerviewDistricts_id);
        textSearch = (TextView) view.findViewById(R.id.searchDistrict_id);
    }

    private void setAdapterCity(List<districts> data) {

        mActivityLocation = (activityLocation) getActivity();
        myRecyclerviewDistricts.setLayoutManager(new LinearLayoutManager(mActivityLocation));
        adapter = new recycleAdapterDistrict(data, new recycleAdapterDistrict.ItemClickDistrictListener() {
            @Override
            public void onItemClick(districts data) {
                senDataToWards(data);
            }
        });
        myRecyclerviewDistricts.setAdapter(adapter);
    }

    public void getDistrict(City city){
        setAdapterCity(city.getDistricts());
    }

    private void senDataToWards(districts mDistricts) {
        dataDistricts.sendWarns(mDistricts);
    }

    private void searchDistrictListener(){
        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}