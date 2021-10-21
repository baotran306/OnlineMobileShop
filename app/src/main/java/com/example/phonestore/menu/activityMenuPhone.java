package com.example.phonestore.menu;

import static com.example.phonestore.api.customer.apiGetPhone.getPhoneFromApi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.api.customer.apiGetPhone;
import com.example.phonestore.cart.activityCart;
import com.example.phonestore.entity.Brands;
import com.example.phonestore.entity.Colors;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.home.activityHome;
import com.example.phonestore.information.activityInformation;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityMenuPhone extends AppCompatActivity {
    List<Phone> listPhone;
    List<Brands> brandsList;
    List<Brands> listAddBrands;
    private Toolbar toolbarMenu;
    private EditText searchPhone;
    private EditText EditTextmin, EditTextmax;
    private Button buttonSort;
    private Button buttonFilter;
    private Button buttonAdd;
    private Button btn_reset;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView myrv;
    private RecyclerView myNavRv;
    private RecyclerView myNavColorRV;
    private RecyclerViewAdapter myAdapter;
    private RecyclerViewBrandAdapter_menuNav navAdapter;
    private RecyclerViewColorAdapter_filterNav navColorAdapter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private ImageView iconCart;
    private TextView textShowNum;

    private boolean isExpanded = true, isSortPriceCick = true, isFilter = true;
    CharSequence search = "";


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle saveIntanceState) {
        super.onCreate(saveIntanceState);
        setContentView(R.layout.activity_menu_phone);
        initUi();
        setShowNum();
        clickButtonSort();
        clickbuttonFilter();
        threadAPI();
        initAnimationToolsBar();
        clickButtonAdd();
        clickButtonReset();
        setHomeSelected();
        clickIconCart();
        searchPhoneListener();
    }

    private void initUi() {
        toolbarMenu = (Toolbar) findViewById(R.id.myToolBar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarMenu_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayoutMenu_id);
        buttonSort = (Button) findViewById(R.id.buttonSort_id);
        buttonFilter = (Button) findViewById(R.id.buttonFilter_id);
        myNavRv = (RecyclerView) findViewById(R.id.recyclerview_listbrands_nav);
        buttonAdd = (Button) findViewById(R.id.btn_filter);
        btn_reset = (Button) findViewById(R.id.btn_reset_id);
        navigationView = (NavigationView) findViewById(R.id.nav_menuActivity);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_menu);
        EditTextmin = (EditText) findViewById(R.id.minPrice);
        EditTextmax = (EditText) findViewById(R.id.maxPrice);
        drawerLayout.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_CLOSED);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationViewMainMenu);
        iconCart = (ImageView) findViewById(R.id.store_icon);
        textShowNum = (TextView) findViewById(R.id.textShowNotifycation_id);
        searchPhone = (EditText) findViewById(R.id.textEditSearch);
    }

    private void initToolbar() {
        searchPhone.setWidth(150);
        setSupportActionBar(toolbarMenu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void clickButtonSort() {
        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonSort();
                isFilter = false;
                setButtonFilter();
            }
        });
    }


    private void clickButtonReset() {
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetSelectBrands_Colors();
            }
        });
    }

    private void clickbuttonFilter() {
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonFilter();
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    private void setButtonSort() {
        Drawable iconSort;
        buttonSort.setTextColor(getResources().getColor(R.color.selectedNav));
        if (isSortPriceCick) {
            iconSort = getBaseContext().getResources().getDrawable(R.drawable.ic_increase);
            iconSort.setBounds(0, 0, 100, 100);
            buttonSort.setCompoundDrawablesRelative(iconSort, null, null, null);
            myAdapter.sortPrice(isSortPriceCick);
            isSortPriceCick = false;
        } else {
            iconSort = getBaseContext().getResources().getDrawable(R.drawable.ic_decrease);
            iconSort.setBounds(0, 0, 100, 100);
            buttonSort.setCompoundDrawablesRelative(iconSort, null, null, null);
            myAdapter.sortPrice(isSortPriceCick);
            isSortPriceCick = true;
        }

    }

    private void setButtonFilter() {
        if (isFilter) {
            buttonSort.setTextColor(getResources().getColor(R.color.black));
            buttonFilter.setTextColor(getResources().getColor(R.color.selectedNav));
            isFilter = false;
        } else {
            buttonFilter.setTextColor(getResources().getColor(R.color.black));
            isFilter = true;
        }
    }

    private void clickButtonAdd() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataForFilterByAny();
                drawerLayout.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
    }

    private void resetSelectBrands_Colors() {
        navAdapter.resetSelected();
        navColorAdapter.resetSelected();
        getPhoneFromApi.getPhone().enqueue(new Callback<ArrayList<Phone>>() {
            @Override
            public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
                setPhoneRecycler(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setPhoneRecycler(List<Phone> data) {
        myrv = (RecyclerView) findViewById(R.id.recyclerview_phone_id);
        myAdapter = new RecyclerViewAdapter(this, data);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);
    }

    private void filterByAll() {
        List<Phone> filter = new ArrayList<>();
        double min, max;
        try {
            min = Double.parseDouble(EditTextmin.getText().toString());
            max = Double.parseDouble(EditTextmax.getText().toString());
            if (navColorAdapter.getSelectColor().size() != 0 && navAdapter.getSelectBrands().size() != 0) {
                List<Phone> filterBrands = new ArrayList<>();
                for (Colors colors : navColorAdapter.getSelectColor()) {
                    for (Phone phone : myAdapter.filterByPrice_colors(min, max, colors.getColor())) {
                        filter.add(phone);
                    }
                }
                myAdapter.filterAll(filter);
                for (Brands brands : navAdapter.getSelectBrands()) {
                    for (Phone phone : myAdapter.filterByPrice_Brands(min, max, brands.getBrandsName())) {
                        filterBrands.add(phone);
                    }
                }
                filter = filterBrands;
                myAdapter.showFilter(filter);
                myAdapter.sortByName();
            } else if (navColorAdapter.getSelectColor().size() == 0 && navAdapter.getSelectBrands().size() == 0) {
                filter = myAdapter.filterByPrice(min, max);
                myAdapter.showFilter(filter);
            } else if (navColorAdapter.getSelectColor().size() != 0) {
                for (Colors colors : navColorAdapter.getSelectColor()) {
                    for (Phone phone : myAdapter.filterByPrice_colors(min, max, colors.getColor())) {
                        filter.add(phone);
                    }
                }
                myAdapter.showFilter(filter);
                myAdapter.sortByName();
            } else if (navAdapter.getSelectBrands().size() != 0) {
                for (Brands brands : navAdapter.getSelectBrands()) {
                    for (Phone phone : myAdapter.filterByPrice_Brands(min, max, brands.getBrandsName())) {
                        filter.add(phone);
                    }
                }
                myAdapter.showFilter(filter);
                myAdapter.sortByName();
            }
        } catch (Exception ex) {
            List<Phone> filterBrands = new ArrayList<>();
            if (navAdapter.getSelectBrands().size() != 0 && navColorAdapter.getSelectColor().size() != 0) {
                for (Colors colors : navColorAdapter.getSelectColor()) {
                    for (Phone phone : myAdapter.filterByColor(colors.getColor())) {
                        filter.add(phone);
                    }
                }
                myAdapter.filterAll(filter);
                for (Brands brands : navAdapter.getSelectBrands()) {
                    for (Phone phone : myAdapter.filterByBrands(brands.getBrandsName())) {
                        filterBrands.add(phone);
                    }
                }
                filter = filterBrands;
                myAdapter.showFilter(filter);
                myAdapter.sortByName();
            } else if (navAdapter.getSelectBrands().size() != 0) {
                for (Brands brands : navAdapter.getSelectBrands()) {
                    for (Phone phone : myAdapter.filterByBrands(brands.getBrandsName())) {
                        filter.add(phone);
                    }
                }
                myAdapter.showFilter(filter);
                myAdapter.sortByName();
            } else if (navColorAdapter.getSelectColor().size() != 0) {
                for (Colors colors : navColorAdapter.getSelectColor()) {
                    for (Phone phone : myAdapter.filterByColor(colors.getColor())) {
                        filter.add(phone);
                    }
                }
                myAdapter.showFilter(filter);
                myAdapter.sortByName();
            }
        }
    }

    private void setBrandNav(List<Brands> data) {
        myNavRv = (RecyclerView) findViewById(R.id.recyclerview_listbrands_nav);
        navAdapter = new RecyclerViewBrandAdapter_menuNav(this, data);
        myNavRv.setLayoutManager(new GridLayoutManager(this, 3));
        myNavRv.setAdapter(navAdapter);
    }

    private void threadAPI() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    getAPIPhoneList();
                    getAPIListBrands();
                    getApiColor();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();
    }

    private void getAPIListBrands() {
        Call<ArrayList<Brands>> call = getPhoneFromApi.getBrands();
        try {
            Response<ArrayList<Brands>> response = call.execute();
            setBrandNav(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dataForFilterByAny() {
        getPhoneFromApi.getPhone().enqueue(new Callback<ArrayList<Phone>>() {
            @Override
            public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
                setPhoneRecycler(response.body());
                filterByAll();
            }

            @Override
            public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getAPIPhoneList() {

        Call<ArrayList<Phone>> call = getPhoneFromApi.getPhone();
        try {
            Response<ArrayList<Phone>> response = call.execute();
            System.out.println(response.body().size());
            setPhoneRecycler(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getPhoneFromApi.getPhone().enqueue(new Callback<ArrayList<Phone>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
//                try {
//                    System.out.println(response.body().size());
//                    setPhoneRecycler(response.body());
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }

    private void getApiColor() {
        Call<ArrayList<Colors>> call = getPhoneFromApi.getColors();
        try {
            Response<ArrayList<Colors>> response = call.execute();
            setColorNav(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getPhoneFromApi.getColors().enqueue(new Callback<ArrayList<Colors>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Colors>> call, Response<ArrayList<Colors>> response) {
//                setColorNav(response.body());
//                for (Colors colors1:response.body())
//                System.out.println(colors1.toString());
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Colors>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }

    private void setColorNav(List<Colors> data) {
        myNavColorRV = (RecyclerView) findViewById(R.id.recyclerview_listColor_nav);
        navColorAdapter = new RecyclerViewColorAdapter_filterNav(this, data);
        myNavColorRV.setLayoutManager(new GridLayoutManager(this, 3));
        myNavColorRV.setAdapter(navColorAdapter);
    }

    private void setHomeSelected() {
        bottomNavigationView.setSelectedItemId(R.id.nav_category);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_category:
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), activityHome.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_information:
                        startActivity(new Intent(getApplicationContext(), activityInformation.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void searchPhoneListener() {
        searchPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                myAdapter.getFilter().filter(charSequence);
                search = charSequence;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initAnimationToolsBar() {
        //collapsingToolbarLayout.setTitle("USER");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.phone1);
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
                        if (Math.abs(verticalOffset) > 200) {
                            isExpanded = false;
                        } else {
                            isExpanded = true;
                        }
                        invalidateOptionsMenu();
                    }
                });
            }
        });
    }

    private void clickIconCart() {
        iconCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCartActivity();
            }
        });
    }

    private void setShowNum() {
        info info = DataLocalManager.getInfo();
        try {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Cart").child(info.getId());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        textShowNum.setText(String.valueOf(snapshot.getChildrenCount()));
                        textShowNum.setVisibility(View.VISIBLE);

                    } else {
                        textShowNum.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCartActivity() {
        Intent intent = new Intent(this, activityCart.class);
        startActivity(intent);
    }
}
