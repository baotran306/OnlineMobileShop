package com.example.phonestore.location;

import static com.example.phonestore.api.apiLocation.apiLocationService.apiLocation;

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

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class fragment_city extends Fragment {

    private ArrayList<City> cities;
    private RecyclerView myRecyclerviewCity;
    private recycleAdapterCity adapter;
    private sendDataCity sendDataCity;
    private TextView textSearch;
    activityLocation mActivityLocation;
    private CharSequence search = "";
    private recycleAdapterCity.ItemClickCityListener listener;


    public interface sendDataCity {
        void sendDistricts(City city);
    }

    public fragment_city() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendDataCity = (sendDataCity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        initUI(view);
        getDataAPI();
        searchCityListener();
        return view;
    }

    private void initUI(View view) {
        myRecyclerviewCity = (RecyclerView) view.findViewById(R.id.recyclerviewCity_id);
        textSearch = (TextView) view.findViewById(R.id.searchCity_id);
    }

    private void setAdapterCity(ArrayList<City> data) {

        mActivityLocation = (activityLocation) getActivity();
        myRecyclerviewCity.setLayoutManager(new LinearLayoutManager(mActivityLocation));
        adapter = new recycleAdapterCity(data, new recycleAdapterCity.ItemClickCityListener() {
            @Override
            public void onItemClick(City city) {
                senDataToDistricts(city);
            }
        });
        myRecyclerviewCity.setAdapter(adapter);
    }

    private void getDataAPI() {
        apiLocation.convertCity(3).enqueue(new Callback<ArrayList<City>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<City>> call, Response<ArrayList<City>> response) {
                cities = response.body();
                setAdapterCity(cities);
                for (City city : cities)
                    System.out.println(city.toString());
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<City>> call, Throwable t) {
                System.out.println("Call api is error");
                System.out.println(t.getMessage());
            }
        });
    }

    private void senDataToDistricts(City city) {
        sendDataCity.sendDistricts(city);
    }

    private void searchCityListener(){
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