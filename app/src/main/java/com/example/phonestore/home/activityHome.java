package com.example.phonestore.home;

import static com.example.phonestore.api.customer.apiGetPhone.getPhoneFromApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.api.customer.apiGetPhone;
import com.example.phonestore.cart.activityCart;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.entity.temp;
import com.example.phonestore.information.activityInformation;
import com.example.phonestore.menu.activityMenuPhone;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityHome extends AppCompatActivity {

    private RecyclerView recyclerViewTopbuy;
    private RecyclerView recyclerViewNewPhone;
    private EditText searchView;
    private ImageView iconCart;

    private TextView textShowNum;
    private BottomNavigationView bottomNavigationView;

    private ScrollView scrollView;
    private RecyclerView recyclerViewSearch;

    private RecyclerView recyclerViewListPhone;
    private Toolbar toolbar;
    private RecyclerViewAdapter_home adapterHome;

    private RecyclerViewAdapter_home_search adapterHomeSearch;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    CharSequence search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        setShowNum();
        //setRecyclerHomeSearch(listPhone);
        getPhoneFromAPI();
        changeTextSearchPhone();
        setHomeSelected();
        clickIconCart();
        //setSupportActionBar(toolbar);
    }


    private void initUI() {
        recyclerViewTopbuy = (RecyclerView) findViewById(R.id.recyclerview_topphone_id);
        recyclerViewNewPhone = (RecyclerView) findViewById(R.id.recyclerview_newphone_id);
        recyclerViewListPhone = (RecyclerView) findViewById(R.id.recyclerview_listPhoneHome_id);
        scrollView = (ScrollView) findViewById(R.id.scroll_home_id);
        recyclerViewSearch = (RecyclerView) findViewById(R.id.recyclerview_search_id);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationViewMainMenu);
        searchView = (EditText) findViewById(R.id.textEditSearch);
        iconCart = (ImageView) findViewById(R.id.store_icon);
        textShowNum = (TextView) findViewById(R.id.textShowNotifycation_id);
        toolbar = (Toolbar) findViewById(R.id.myToolBar);
    }


    private void setHomeSelected() {
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_category:
                        startActivity(new Intent(getApplicationContext(), activityMenuPhone.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_information:
                        startActivity(new Intent(getApplicationContext(), activityInformation.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void clickIconCart(){
        iconCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCartActivity();
            }
        });
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

    private void openCartActivity(){
        Intent intent = new Intent(this, activityCart.class);
        startActivity(intent);
    }

    public void changeTextSearchPhone() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    scrollView.setVisibility(View.VISIBLE);
                    recyclerViewSearch.setVisibility(View.INVISIBLE);
                } else {
                    recyclerViewSearch.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.INVISIBLE);
                    adapterHomeSearch.getFilter().filter(charSequence);
                    search = charSequence;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void designHorizonLayoutTopBuy(List<Phone> listPhone) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activityHome.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTopbuy.setLayoutManager(layoutManager);
        recyclerViewTopbuy.setItemAnimator(new DefaultItemAnimator());
        adapterHome = new RecyclerViewAdapter_home(listPhone, activityHome.this);
        recyclerViewTopbuy.setAdapter(adapterHome);
    }

    public void designHorizonLayoutNewPhone(List<Phone> listPhone) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activityHome.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewNewPhone.setLayoutManager(layoutManager);
        recyclerViewNewPhone.setItemAnimator(new DefaultItemAnimator());
        adapterHome = new RecyclerViewAdapter_home(listPhone, activityHome.this);
        recyclerViewNewPhone.setAdapter(adapterHome);
    }

    public void designHorizonLayoutListPhone(List<Phone> listPhone) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activityHome.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewListPhone.setLayoutManager(layoutManager);
        recyclerViewListPhone.setItemAnimator(new DefaultItemAnimator());
        adapterHome = new RecyclerViewAdapter_home(listPhone, activityHome.this);
        recyclerViewListPhone.setAdapter(adapterHome);
    }

    public void setRecyclerHomeSearch(List<Phone> data) {
        adapterHomeSearch = new RecyclerViewAdapter_home_search(this, data);
        recyclerViewSearch.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewSearch.setAdapter(adapterHomeSearch);
    }

    private void getPhoneFromAPI(){
       getPhoneFromApi.getPhone().enqueue(new Callback<ArrayList<Phone>>() {
           @Override
           public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
               designHorizonLayoutNewPhone(response.body());
               designHorizonLayoutListPhone(response.body());
               designHorizonLayoutTopBuy(response.body());
               setRecyclerHomeSearch(response.body());

               for (Phone phone : response.body())
                   System.out.println(phone.toString());

           }

           @Override
           public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
                System.out.println("Error");
                t.printStackTrace();
           }
       });
    }
}