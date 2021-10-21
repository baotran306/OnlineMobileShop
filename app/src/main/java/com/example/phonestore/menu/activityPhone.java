package com.example.phonestore.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonestore.R;
import com.example.phonestore.cart.activityCart;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.entity.temp;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activityPhone extends AppCompatActivity {

    private TextView tvTitle, tvDescription, tvCategory;
    private Button btn_addOrder;
    private Phone temp;
    private ImageView img, itemBack;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private ImageView iconCart;
    private TextView textShowNum;
    private String show;
    private RelativeLayout relativeLayout;
    public final static String SHARED_PREFS = "sharedPrefs";
    private static String url = "http://10.0.2.2:5000/get-image/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        tvTitle = (TextView) findViewById(R.id.textViewTitle_id);
        tvDescription = (TextView) findViewById(R.id.textViewDecription);
        tvCategory = (TextView) findViewById(R.id.textViewCategory_id);
        img = (ImageView) findViewById(R.id.phoneThumbnail);
        itemBack = (ImageView) findViewById(R.id.icBack_id);
        btn_addOrder = (Button) findViewById(R.id.btn_order_id);
        iconCart = (ImageView) findViewById(R.id.store_icon);
        textShowNum = (TextView) findViewById(R.id.textShowNotifycation_id);
        relativeLayout = (RelativeLayout) findViewById(R.id.notifocation);

        Intent intent = getIntent();

        temp = (Phone) intent.getSerializableExtra("Phone");
        show = intent.getStringExtra("show");
        setShowNum();
        if (show.equals("show")) {
            btn_addOrder.setVisibility(View.INVISIBLE);
            iconCart.setVisibility(View.INVISIBLE);
        }
        tvTitle.setText(temp.getPhoneName());
        tvDescription.setText(temp.getDescription());
        Picasso.with(this).load(url + temp.getImage()).into(img);
        clickItemBack();
        clickButton();
        clickIconCart();

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
                    if (snapshot.exists()&&show.equals("disShow")) {
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

//
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void clickButton() {
        btn_addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void clickItemBack() {
        itemBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void saveData() {
        info info = DataLocalManager.getInfo();
        try {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Cart");
            temp.setQuantity(1);
            reference.child(info.getId()).child(String.valueOf(temp.getId())).setValue(temp, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    Toast.makeText(activityPhone.this, "add complete", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}