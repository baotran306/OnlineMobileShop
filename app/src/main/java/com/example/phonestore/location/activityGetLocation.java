package com.example.phonestore.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phonestore.R;

public class activityGetLocation extends AppCompatActivity {

    private TextView textViewCity,textViewDistricts,textViewWards;
    private EditText editTextStreet;
    private Button btn_OK;
    private static int resultCode = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        initUI();
        listener();
        onClickButtonOKListener();
    }

    private void initUI(){
        textViewCity = (TextView) findViewById(R.id.textViewCity_id);
        textViewDistricts = (TextView) findViewById(R.id.textViewDistricts_id);
        textViewWards = (TextView) findViewById(R.id.textViewWard_id);
        editTextStreet = (EditText) findViewById(R.id.editText_Street);
        btn_OK = (Button) findViewById(R.id.btnOk_location_id);
    }

    private void listener(){
        textViewCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitiLocation();
            }
        });
        textViewDistricts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitiLocation();
            }
        });
        textViewWards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitiLocation();
            }
        });
    }

    private void onClickButtonOKListener(){
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 onBackPressed();
            }
        });
    }

    private void openActivitiLocation(){
        Intent intent = new Intent(this,activityLocation.class);
        startActivityForResult(intent,8088);
        setResult(60);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8088 && resultCode == 60) {
            if (data!=null) {
                textViewCity.setText(data.getStringExtra("City"));
                textViewDistricts.setText(data.getStringExtra("District"));
                textViewWards.setText(data.getStringExtra("Ward"));
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        String city,district,ward,street;
        city = (String) textViewCity.getText();
        district = (String) textViewDistricts.getText();
        ward = (String) textViewWards.getText();
        street = String.valueOf(editTextStreet.getText());
        Intent intent = new Intent();
        intent.putExtra("City", city);
        intent.putExtra("District", district);
        intent.putExtra("Ward", ward);
        intent.putExtra("Street", street);
        setResult(resultCode, intent);
        finish();
    }
}