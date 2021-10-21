package com.example.phonestore.information;

import static com.example.phonestore.api.customer.apiUser.apiPostUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.phonestore.R;
import com.example.phonestore.entity.Person;
import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityProfile extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnSave;
    private EditText edtFirstName, edtLastName, edtIdentityCard, edtDayOfBirth, edtAddress, edtEmail, edtPhoneNumber;
    private RadioButton rbFemale, rbMale;
    private info changeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initUI();
        initToolbar();
        setValue();
        setOnClickSave();
    }

    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbarProfile_id);
        edtFirstName = (EditText) findViewById(R.id.firstName_information_id);
        edtLastName = (EditText) findViewById(R.id.lastName_information_id);
        edtIdentityCard = (EditText) findViewById(R.id.idCard_information_id);
        edtDayOfBirth = (EditText) findViewById(R.id.birthday_information_id);
        edtAddress = (EditText) findViewById(R.id.address_information_id);
        edtEmail = (EditText) findViewById(R.id.email_information_id);
        edtPhoneNumber = (EditText) findViewById(R.id.phoneNumber_information_id);
        rbFemale = (RadioButton) findViewById(R.id.radioFemale_profile);
        rbMale = (RadioButton) findViewById(R.id.radioMale_profile);
        btnSave = (Button) findViewById(R.id.btn_saveProfile);
    }

    private void setValue() {
        info info = DataLocalManager.getInfo();
        if (info != null) {
            changeInfo = info;
            edtFirstName.setText(info.getFirst_name());
            edtLastName.setText(info.getLast_name());
            edtAddress.setText(info.getAddress());
            edtEmail.setText(info.getEmail());
            edtIdentityCard.setText(info.getIdentity_card());
            edtDayOfBirth.setText(info.getDay_of_birth());
            edtPhoneNumber.setText(info.getPhone_num());
            if (info.getGender().toLowerCase().equals("male") || info.getGender().toLowerCase().equals("nam"))
                rbMale.setChecked(true);
            else
                rbFemale.setChecked(true);
        }
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

    private info getChangeInfo(){
        info info = new info();
        info.setFirst_name(edtFirstName.getText().toString());
        info.setLast_name(edtLastName.getText().toString());
        info.setAddress(edtAddress.getText().toString());
        info.setEmail(edtEmail.getText().toString());
        info.setDay_of_birth(edtDayOfBirth.getText().toString());
        info.setPhone_num(edtPhoneNumber.getText().toString());
        info.setIdentity_card(edtIdentityCard.getText().toString());
        info.setAddress(edtAddress.getText().toString());
        if(rbMale.isChecked())
            info.setGender("Nam");
        else
            info.setGender("Nữ");
        info.setId(changeInfo.getId());
        info.setIs_active(changeInfo.getIs_active());
        return info;
    }

    private void setOnClickSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (changeInfo != null) {
                    DataLocalManager.setInformationUser(getChangeInfo());
                    info info = DataLocalManager.getInfo();
                    Person person = new Person(info.getId(),info.getFirst_name(),info.getLast_name(),
                            info.getGender(),info.getIdentity_card(),info.getEmail(),info.getPhone_num()
                            ,info.getDay_of_birth(),info.getAddress());
                    apiPostUser.updateUser(person).enqueue(new Callback<ResponseMessage>() {
                        @Override
                        public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                            ResponseMessage message = response.body();
                            System.out.println(message.toString());
                        }

                        @Override
                        public void onFailure(Call<ResponseMessage> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}