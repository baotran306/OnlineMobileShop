package com.example.phonestore.login;

import static com.example.phonestore.api.customer.ApiActiveUser.active;
import static com.example.phonestore.api.customer.ApiRegisterAccount.apiPostRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonestore.R;
import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.login.Register;
import com.example.phonestore.entity.login.SendOTP;
import com.example.phonestore.entity.login.responseLogin;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOTP extends AppCompatActivity {

    private EditText editTextOTP;
    private TextInputLayout inputLayoutOTP;
    private Button buttonActive;
    private String username, password;
    private int OTP;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initUi();
        getData();
        onclickButtonActive();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            OTP = bundle.getInt("OTP");
            email = bundle.getString("email");
            username = bundle.getString("username");
            password = bundle.getString("password");
        }
    }

    private boolean checkOTP() {

        if (!String.valueOf(OTP).equals(editTextOTP.getText().toString())) {
            return false;
        }

        return true;
    }

    private void openActivityRegisterAccount() {
        Intent intent = new Intent(this, activityRegistAccount.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        startActivity(intent);
    }

    private void openLoginPage() {
        Intent intent = new Intent(this, activityLogin.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        startActivity(intent);
    }

    private void active(){
        SendOTP sendOTP = new SendOTP(email,Integer.parseInt(editTextOTP.getText().toString()),OTP);
        active.activeAccount(sendOTP).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });
    }

    private void onclickButtonActive() {
        buttonActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOTP()) {
                    if (password.equals("no"))
                        openActivityRegisterAccount();
                    else {
                        active();
                        openLoginPage();
                    }
                } else {
                    inputLayoutOTP.setError("is in correct");
                }
            }
        });
    }

    private void initUi() {
        editTextOTP = (EditText) findViewById(R.id.textActiveOTP_regist);
        inputLayoutOTP = (TextInputLayout) findViewById(R.id.textInputLayoutActiveOTP);
        buttonActive = (Button) findViewById(R.id.btn_Active);
    }

}