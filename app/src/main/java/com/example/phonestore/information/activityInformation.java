package com.example.phonestore.information;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.cart.activityCart;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.home.activityHome;
import com.example.phonestore.login.activityLogin;
import com.example.phonestore.menu.activityMenuPhone;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activityInformation extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private BottomNavigationView bottomNavigationView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView waitforpay,process,transport,iconCart;
    private TextView textViewProfile,logout,textShowName,textShowNum,textChangePassword;

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;


    private Boolean isExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initUi();
        //initToolbar();
        initAnimationToolsBar();
        clickTextViewProfile();
        setHomeSelected();
        clickWaitforpay();
        clickProcess();
        clickTransport();
        setOnclickLogout();
        showName();
        setShowNum();
        clickIconCart();
        clickChangePassword();
    }
    private void initUi(){
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout_id);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapsingToolbarLayout_id);
        toolbar = (Toolbar) findViewById(R.id.topdown_toolbar);
        textViewProfile = (TextView) findViewById(R.id.textViewProfile_infor_id);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationViewMainMenu);
        waitforpay = (ImageView) findViewById(R.id.waitforpay_id);
        process =(ImageView) findViewById(R.id.process_id);
        transport =(ImageView) findViewById(R.id.transport_id);
        logout = (TextView) findViewById(R.id.textViewlogout_id);
        iconCart = (ImageView) findViewById(R.id.store_icon);
        textShowNum = (TextView) findViewById(R.id.textShowNotifycation_id);
        textShowName = (TextView) findViewById(R.id.shop_fullname_id);
        textChangePassword = (TextView) findViewById(R.id.textViewChangePass_id);

        //recyclerView = (RecyclerView) findViewById(R.id.recyclerview_information_id);
    }
    private void clickTextViewProfile(){
        textViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });
    }

    private void clickChangePassword(){
        textChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangePassword();
            }
        });
    }
    private void openChangePassword(){
        Intent intent = new Intent(this,ActivityChangePassword.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
    private void clickIconCart(){
        iconCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCartActivity();
            }
        });
    }
    private void openCartActivity(){
        Intent intent = new Intent(this, activityCart.class);
        startActivity(intent);
    }

    private void setShowNum(){
        info info = DataLocalManager.getInfo();
        try {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Cart").child(info.getId());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        textShowNum.setText(String.valueOf(snapshot.getChildrenCount()));
                        textShowNum.setVisibility(View.VISIBLE);

                    }
                    else {
                        textShowNum.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showName(){
        info info = DataLocalManager.getInfo();
        textShowName.setText(info.getFirst_name()+" "+info.getLast_name());
    }

    private void openLogin(){
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    private void setOnclickLogout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    private void clickWaitforpay(){
        waitforpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityStateOrder(0);
            }
        });
    }

    private void clickProcess(){
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityStateOrder(1);
            }
        });
    }

    private void clickTransport(){
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityStateOrder(2);
            }
        });
    }

    private void openActivityStateOrder(int position){
        Intent intent = new Intent(this,activityStateOrder.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    private void openProfile(){
        Intent intent = new Intent(this,activityProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void initAnimationToolsBar(){
        collapsingToolbarLayout.setTitle("USER");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.phone1);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int mycolor = palette.getVibrantColor(getResources().getColor(R.color.selectedNav));
                collapsingToolbarLayout.setContentScrimColor(mycolor);
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black_trans));
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if(Math.abs(verticalOffset)>200){
                            isExpanded = false;
                        }else {
                            isExpanded = true;
                        }
                        invalidateOptionsMenu();
                    }
                });
            }
        });
    }

    private void setHomeSelected() {
        bottomNavigationView.setSelectedItemId(R.id.nav_information);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_category:
                        startActivity(new Intent(getApplicationContext(), activityMenuPhone.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), activityHome.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_information:
                        return true;
                }
                return false;
            }
        });
    }
}