package com.example.phonestore.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phonestore.R;
import com.example.phonestore.entity.location.City;
import com.example.phonestore.entity.location.districts;
import com.example.phonestore.entity.location.wards;
import com.google.android.material.tabs.TabLayout;

public class activityLocation extends AppCompatActivity implements fragment_city.sendDataCity, fragmentDistricts.sendDataDistricts, fragmentWards.sendDataWards {

    private TabLayout tabLayoutLocation;
    private ViewPager viewPagerLocation;
    private Toolbar toolbar;
    private fragmentLocationAdapter adapter;
    private fragmentDistricts mfragmentDistricts;
    private fragmentWards mFragmentWards;
    private static int resultCode = 60;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);
        initUI();
        setupPageView();
        setEnable();
        tabLayoutLocation.getTabAt(1).view.setClickable(false);
        tabLayoutLocation.getTabAt(2).view.setClickable(false);
        initToolbar();
    }

    private void initUI() {
        tabLayoutLocation = (TabLayout) findViewById(R.id.tabLayout_location_id);
        viewPagerLocation = (ViewPager) findViewById(R.id.viewPage_location_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_city_id);
    }

    private void setEnable() {
        tabLayoutLocation.getTabAt(0).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayoutLocation.getTabAt(1).view.setClickable(false);
                tabLayoutLocation.getTabAt(2).view.setClickable(false);
            }
        });

        tabLayoutLocation.getTabAt(1).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayoutLocation.getTabAt(2).view.setClickable(false);
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    private void setupPageView() {
        mfragmentDistricts = new fragmentDistricts();
        mFragmentWards = new fragmentWards();
        tabLayoutLocation.setupWithViewPager(viewPagerLocation);
        adapter = new fragmentLocationAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new fragment_city(), "City");
        adapter.addFragment(mfragmentDistricts, "Districts");
        adapter.addFragment(mFragmentWards, "Wards");

        viewPagerLocation.setAdapter(adapter);
    }

    @Override
    public void sendDistricts(City city) {
        tabLayoutLocation.getTabAt(1).view.setClickable(true);
        tabLayoutLocation.getTabAt(0).setText(city.getName());
        viewPagerLocation.setCurrentItem(1);
        mfragmentDistricts.getDistrict(city);
    }

    @Override
    public void sendWarns(districts mDistricts) {
        tabLayoutLocation.getTabAt(2).view.setClickable(true);
        tabLayoutLocation.getTabAt(1).setText(mDistricts.getName());
        viewPagerLocation.setCurrentItem(2);
        mFragmentWards.getWards(mDistricts);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void sendData(){
        String city,district,ward;
        city = (String) tabLayoutLocation.getTabAt(0).getText();
        district = (String) tabLayoutLocation.getTabAt(1).getText();
        ward = (String) tabLayoutLocation.getTabAt(2).getText();
        Intent intent = new Intent();
        intent.putExtra("City", city);
        intent.putExtra("District", district);
        intent.putExtra("Ward", ward);
        setResult(resultCode, intent);
        finish();
    }

    @Override
    public void send(wards mWards) {
        tabLayoutLocation.getTabAt(2).setText(mWards.getName());
        sendData();
        onBackPressed();
    }
}