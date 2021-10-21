package com.example.phonestore.location;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.phonestore.entity.location.districts;
import com.example.phonestore.entity.location.wards;

import java.util.List;

public class fragmentWards extends Fragment {

    RecyclerView myRecyclerviewWards;
    activityLocation mActivityLocation;
    sendDataWards sendDataWards;
    private TextView textSearch;
    private CharSequence search ="";
    recycleAdapterWards adapter;

    public interface sendDataWards {
        void send(wards mWards);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendDataWards = (sendDataWards) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wards, container, false);
        initUI(view);
        searchWardsListener();
        return view;
    }

    private void setAdapterWards(List<wards> data) {

        mActivityLocation = (activityLocation) getActivity();
        myRecyclerviewWards.setLayoutManager(new LinearLayoutManager(mActivityLocation));
        adapter = new recycleAdapterWards(data, new recycleAdapterWards.ItemClickWardsListener() {
            @Override
            public void onItemClick(wards data) {
                senData(data);
            }
        });
        myRecyclerviewWards.setAdapter(adapter);
    }

    private void initUI(View view) {
        myRecyclerviewWards = view.findViewById(R.id.recyclerviewWards_id);
        textSearch = (TextView) view.findViewById(R.id.searchWard_id);
    }

    public void getWards(districts data){
        setAdapterWards(data.getWards());
    }

    private void senData(wards data) {
        sendDataWards.send(data);
    }
    private void searchWardsListener(){
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