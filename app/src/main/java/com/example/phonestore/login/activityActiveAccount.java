package com.example.phonestore.login;

import static com.example.phonestore.api.customer.ApiActiveUser.active;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.entity.login.ResponseActiveEmail;
import com.example.phonestore.entity.login.SendEmail;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityActiveAccount extends AppCompatActivity {


    private TextView textActiveBack;
    private EditText editTextEmail,editTextUsername;
    private TextInputLayout inputLayoutEmail,inputLayoutUsername;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_account);
        initUI();
        getTitleTextView();
        onclickSendEmail();

    }

    private void initUI(){
        textActiveBack = (TextView) findViewById(R.id.textBackActive_count);
        editTextEmail = (EditText) findViewById(R.id.textActiveEmail_regist);
        editTextUsername = (EditText) findViewById(R.id.textActiveUsername_regist);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutActiveEmail);
        inputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutActiveUsername);
        btn_send = (Button) findViewById(R.id.btn_SendEmail);

    }

    private void getTitleTextView(){
        Intent intent = getIntent();
        if(intent!=null){
            textActiveBack.setText(intent.getStringExtra("forget"));
        }
    }

    private void openOTP(String username,int OTP){
        Intent intent = new Intent(this,ActivityOTP.class);
        intent.putExtra("username",username);
        intent.putExtra("OTP",OTP);
        intent.putExtra("password","no");
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        startActivity(intent);
    }
    private void catchError(String error){
        inputLayoutUsername.setError(error);
        inputLayoutEmail.setError(error);
    }

    private void getOTP(){
        SendEmail sendEmail = new SendEmail(editTextEmail.getText().toString(),editTextUsername.getText().toString());
        active.getOTP(sendEmail).enqueue(new Callback<ResponseActiveEmail>() {
            @Override
            public void onResponse(Call<ResponseActiveEmail> call, Response<ResponseActiveEmail> response) {
                ResponseActiveEmail activeEmail = response.body();
                if(!activeEmail.getResult()){
                    catchError(activeEmail.getInfo());
                }else {
                    try {
                        openOTP(editTextUsername.getText().toString(),activeEmail.getData().getVerifyNumber());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseActiveEmail> call, Throwable t) {

            }
        });
    }

    private void onclickSendEmail(){
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOTP();
            }
        });
    }
}