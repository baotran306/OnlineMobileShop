package com.example.phonestore.information;

import static com.example.phonestore.api.customer.apiUser.apiPostUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonestore.R;
import com.example.phonestore.entity.ChangePassword;
import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityChangePassword extends AppCompatActivity {
    private TextInputLayout textInputLayoutOldPassWord,textInputLayoutNewPassWord,textInputLayoutVerifyPass;
    private EditText editTextOldPass,editTextNewPass,editTextVerifyPass,editTextUsername;
    private Button btn_save;
    private String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    boolean isCheckPass = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initUi();
        catchPassword();
        catchVerifyPassword();
        clickButtonSave();
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initUi(){
        textInputLayoutOldPassWord = (TextInputLayout) findViewById(R.id.textInputLayoutOldPassword);
        textInputLayoutNewPassWord = (TextInputLayout) findViewById(R.id.textInputLayoutNewPassword);
        textInputLayoutVerifyPass = (TextInputLayout) findViewById(R.id.textInputLayoutVerifyPass_change);
        editTextOldPass = (EditText) findViewById(R.id.textOldPassword);
        editTextNewPass = (EditText) findViewById(R.id.textUserNewPass);
        editTextVerifyPass = (EditText) findViewById(R.id.textVerifyPassword_change);
        editTextUsername = (EditText) findViewById(R.id.textUserName_changepass);
        btn_save = (Button) findViewById(R.id.btn_saveChangePass);
        info info = DataLocalManager.getInfo();
        editTextUsername.setText(info.getUsername());
    }
    public void clickButtonSave(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck()){
                    postChangePass();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    private Boolean isCheck(){
        boolean check=true;
        if(textInputLayoutNewPassWord.getError()!=null){
            check = false;
        }
        if(editTextNewPass.getText().toString().equals("")){
            check = false;
            textInputLayoutNewPassWord.setError("New pass cannot blank");
        }
        if(isCheckPass == false){
            check = false;
        }
        if(editTextOldPass.getText().toString().equals("")){
            check = false;
            textInputLayoutOldPassWord.setError("Old pass cannot blank");
        }
        return check;
    }
    private void postChangePass(){
        ChangePassword changePassword = new ChangePassword(editTextUsername.getText().toString()
                ,editTextOldPass.getText().toString(),editTextNewPass.getText().toString());
        apiPostUser.changePassword(changePassword).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.body().getResult()==false){
                    textInputLayoutOldPassWord.setError("Old password is incorrect");
                }else {
                    textInputLayoutOldPassWord.setError(null);
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });
    }
    public void catchPassword(){
        editTextNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()&&!s.toString().matches(regexPassword)){
                    textInputLayoutNewPassWord.setError("User more than 8 characters and combinations of letters, numbers, and symbols.");
                }else {
                    textInputLayoutNewPassWord.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void catchVerifyPassword() {
        editTextVerifyPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(editTextNewPass.getText().toString())) {
                    textInputLayoutVerifyPass.setError("incorrect with password");
                    isCheckPass = false;
                } else {
                    textInputLayoutVerifyPass.setError(null);
                    isCheckPass = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}