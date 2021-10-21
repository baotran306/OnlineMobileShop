package com.example.phonestore.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.location.activityLocation;

public class ChangeLocationAndInfo extends AppCompatActivity {

    private TextView textCity,textDistrict, textWards ;
    private EditText textStreet, textName, textPhoneNum;
    private Button btn_OK;
    private Toolbar toolbar;
    private static int resultCode = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location_and_info);
        initUi();
        listener();
        initToolbar();
        setAddress();
        onClickButtonOKListener();
    }

    private void setAddress(){
        String address = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            address = bundle.getString("address");
            String temp[] = address.split(",");
            textCity.setText(temp[0]);
            textDistrict.setText(temp[1]);
            textWards.setText(temp[2]);
            address = "";
            for(int i=3;i<temp.length;i++){
                address = address+temp[i];
            }
            textStreet.setText(address);
            textName.setText(bundle.getString("name"));
            textPhoneNum.setText(bundle.getString("phoneNum"));
        }

    }

    private void initUi() {
        textCity = (TextView) findViewById(R.id.textCityCart_id);
        textDistrict = (TextView) findViewById(R.id.textDistrictsCart_id);
        textWards = (TextView) findViewById(R.id.textWardCart_id);
        textStreet = (EditText) findViewById(R.id.editText_StreetCart);
        textName = (EditText) findViewById(R.id.textRecipentName);
        textPhoneNum = (EditText) findViewById(R.id.textNumberPhoneCart);
        toolbar = (Toolbar) findViewById(R.id.toolbar_Location_Cart_id);
        btn_OK = (Button) findViewById(R.id.btnOk_locationCart_id);
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

    private void listener() {
        textCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitiLocation();
            }
        });
        textDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitiLocation();
            }
        });
        textWards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitiLocation();
            }
        });
    }

    private void onClickButtonOKListener() {
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senBackData();
                onBackPressed();
            }
        });
    }

    private void openActivitiLocation() {
        Intent intent = new Intent(this, activityLocation.class);
        startActivityForResult(intent, 8088);
        setResult(60);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
    private void senBackData(){
        String city, district, ward, street,name,phoneNum;
        city = (String) textCity.getText();
        district = (String) textDistrict.getText();
        ward = (String) textWards.getText();
        street = String.valueOf(textStreet.getText());
        name = textName.getText().toString();
        phoneNum = textPhoneNum.getText().toString();
        String address = city + ", " + district + ", " + ward + ", " + street;
        Intent intent = new Intent();
        intent.putExtra("address", address);
        intent.putExtra("phoneNum", phoneNum);
        intent.putExtra("name", name);
        setResult(resultCode, intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8088 && resultCode == 60) {
            if (data!=null) {
                textCity.setText(data.getStringExtra("City"));
                textDistrict.setText(data.getStringExtra("District"));
                textWards.setText(data.getStringExtra("Ward"));
            }
        }
    }
}