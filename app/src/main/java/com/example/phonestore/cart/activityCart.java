package com.example.phonestore.cart;

import static com.example.phonestore.api.customer.ApiPostOrder.postOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonestore.R;
import com.example.phonestore.entity.CustomerOrder;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.home.ChangeLocationAndInfo;
import com.example.phonestore.menu.activityPhone;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class activityCart extends AppCompatActivity {

    private CartAdapter adapter;
    private RecyclerView myRecyclerView;
    private TextView totalPrice;
    private Button Buy;
    private List<Phone> listPhone;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private Toolbar toolbar;
    private String name, phoneNum, address;
    private TextView textAddress;
    private Boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initUI();
        getData();
        initToolbar();
        setCartAdapter(listPhone);
        callBackRecyclerviewAdapter();
        totalPriceItem();
        setFirstInfo();
        clickTextAddress();
        setOnclickBuy();

    }

    private void setFirstInfo() {
        info info = DataLocalManager.getInfo();
        this.name = info.getFirst_name() + " " + info.getLast_name();
        this.phoneNum = info.getPhone_num();
        this.address = info.getAddress();
        setTextDesign(info.getFirst_name() + " " + info.getLast_name(), info.getPhone_num(), info.getAddress());
    }

    private void callBackRecyclerviewAdapter() {
        adapter.setListener(new CartAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                totalPriceItem();
                if (adapter.getSelectItem().size() == 0) {
                    isSelected = false;
                } else {
                    isSelected = true;
                }
            }
        });
    }

    private void initUI() {
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_Cart_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_Cart_id);
        Buy = (Button) findViewById(R.id.btn_Buy_id);
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

    private void yesNo() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Confirmation")
                .setMessage("Do you want to buy this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            info info = DataLocalManager.getInfo();
                            List<Integer> listIdPhone, listQuantity;
                            List<Double> listPrice;
                            listIdPhone = new ArrayList<>();
                            listQuantity = new ArrayList<>();
                            listPrice = new ArrayList<>();
                            reference = rootNode.getReference("Cart").child(info.getId());
                            for (Phone phone : adapter.getSelectItem()) {
                                listIdPhone.add(phone.getId());
                                listPrice.add(phone.getPrice());
                                listQuantity.add(phone.getQuantity());
                                reference.child(String.valueOf(phone.getId())).removeValue();
                            }
                            CustomerOrder order = new CustomerOrder(info.getId(), address, phoneNum, listIdPhone, listPrice, listQuantity);
                            postOrder.postOrder(order).enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    ResponseMessage message = response.body();
                                    Toast.makeText(activityCart.this, message.getInfo(), Toast.LENGTH_SHORT).show();
                                    System.out.println(message.toString());
                                    onBackPressed();
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                })
                .setNegativeButton("No", null)
                .create();
        alertDialog.show();
    }

    private void setOnclickBuy() {
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelected) {
                    yesNo();
                } else {
                    new AlertDialog.Builder(activityCart.this)
                            .setTitle("Notification")
                            .setMessage("You aren't choose item")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            }).show();
                }
            }
        });
    }

    private void setCartAdapter(List<Phone> data) {
        adapter = new CartAdapter(this, data);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        myRecyclerView.setAdapter(adapter);
        totalPrice = (TextView) findViewById(R.id.totalPriceCart_id);
        textAddress = (TextView) findViewById(R.id.textAddress_Cart);

    }

    private void setTextDesign(String name, String phoneNum, String address) {
        textAddress.setText(Html.fromHtml(
                "<p style=\"color:blue\">Address</p><p>" +
                        "<b>" + name + " | " + phoneNum + "</b>"
                        + "<br>"
                        + address + "</p>"
        ));
    }

    private void clickTextAddress() {
        textAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangeLocationAndInfo();
            }
        });
    }

    private void totalPriceItem() {

        double total = 0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        for (Phone price : adapter.getSelectItem()) {
            total = total + price.getPrice() * price.getQuantity();
        }
        totalPrice.setText(formatter.format(total) + " đ");
    }

    private void openChangeLocationAndInfo() {
        Intent intent = new Intent(activityCart.this, ChangeLocationAndInfo.class);
        intent.putExtra("name", this.name);
        intent.putExtra("phoneNum", this.phoneNum);
        intent.putExtra("address", this.address);
        startActivityForResult(intent, 8089);
        setResult(61);
    }

    private void getData() {
        listPhone = new ArrayList<>();
        info info = DataLocalManager.getInfo();
        try {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Cart").child(info.getId());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Phone phone = dataSnapshot.getValue(Phone.class);
                        System.out.println(phone.toString());
                        listPhone.add(phone);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8089 && resultCode == 61) {
            if (data != null) {
                this.name = data.getStringExtra("name");
                this.phoneNum = data.getStringExtra("phoneNum");
                this.address = data.getStringExtra("address");
                setTextDesign(data.getStringExtra("name"), data.getStringExtra("phoneNum"), data.getStringExtra("address"));
            }
        }
    }
}