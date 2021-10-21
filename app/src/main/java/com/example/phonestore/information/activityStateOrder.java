package com.example.phonestore.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phonestore.R;
import com.google.android.material.tabs.TabLayout;

public class activityStateOrder extends AppCompatActivity {

    private TabLayout tabLayoutMyOrder;
    private ViewPager viewPagerMyOrder;
    private fragmentAdapter adapter;
    Toolbar toolbar;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_order);
        initUI();
        setupPageView();
        getPosition();
        openFragment();
        initToolbar();
    }

    private void getPosition() {
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");
    }

    private void openFragment(){
        viewPagerMyOrder.setCurrentItem(position);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    private void initUI() {
        tabLayoutMyOrder = (TabLayout) findViewById(R.id.tabLayout_order_id);
        viewPagerMyOrder = (ViewPager) findViewById(R.id.viewPage_order_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_myorder_id);
    }

    private void setupPageView() {
        tabLayoutMyOrder.setupWithViewPager(viewPagerMyOrder);
        adapter = new fragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new fragmetn_waiforpay(), "history");
        adapter.addFragment(new fragmetnProcess(), "Process");
        adapter.addFragment(new fragmentTransport(), "Transport");
        adapter.addFragment(new fragmentDelivered(), "Delivered");
        adapter.addFragment(new fragmentCancelled(), "Cancelled");
        viewPagerMyOrder.setAdapter(adapter);
    }
}