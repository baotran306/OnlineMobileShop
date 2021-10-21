package com.example.phonestore.login;

import static com.example.phonestore.api.customer.ApiActiveUser.active;
import static com.example.phonestore.api.customer.ApiRegisterAccount.apiPostRegister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonestore.R;
import com.example.phonestore.api.customer.ApiRegisterAccount;
import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.login.Register;
import com.example.phonestore.entity.login.ResponseActiveEmail;
import com.example.phonestore.entity.login.User;
import com.example.phonestore.entity.login.responseLogin;
import com.example.phonestore.menu.activityPhone;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityRegistAccount extends AppCompatActivity {
    private TextView textSignUp;
    private Button buttonRegist;
    private EditText textUserName;
    private EditText textPassword;
    private EditText textVerifyPassword;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutVerifyPassword;
    private String firstName,lastName,dateOfBirth,address,identityCard,gender,phoneNum,email,username,password;

    private String regexUserName = "[a-zA-Z0-9]+";
    private String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    boolean isCheckPass = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registaccount);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        getIdObject();
        Intent intent = getIntent();
        getData(intent);
        clickSignUp();
        catchUsername();
        catchPassword();
        catchVerifyPassword();
        clickRegister();

    }
    private void getData(Intent intent){
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        phoneNum = intent.getStringExtra("numberPhone");
        identityCard = intent.getStringExtra("idCard");
        address = intent.getStringExtra("address");
        dateOfBirth = intent.getStringExtra("dateOfBirth");
        gender = intent.getStringExtra("gender");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        if(password.equals("no")){
            textUserName.setText(username);
            textUserName.setEnabled(false);
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    public void openOTP(){
        if (check()==false){
            return;
        }
        saveRegisterAccount();
    }

    private boolean check(){
        boolean isCheck = true;
        if(textUserName.getText().toString().trim().equals("")){
            textInputLayoutUsername.setError("is not Empty");
            isCheck = false;
        }
        if(textPassword.getText().toString().trim().equals("")) {
            textInputLayoutPassword.setError("is not Empty");
            isCheck = false;
        }
        if(textVerifyPassword.getText().toString().trim().equals("")){
            textInputLayoutVerifyPassword.setError("is not Empty");
            isCheck = false;
        }
        return isCheck;
    }


    public void openAndLogin(){
        if (check()==false){
            return;
        }
        if(isCheckPass == false){
            return;
        }
        saveAccount();
        Intent intent = new Intent(this,activityLogin.class);
        intent.putExtra("username",textUserName.getText().toString());
        intent.putExtra("password",textPassword.getText().toString());
        startActivity(intent);
    }


    private void saveAccount(){
        String username = textUserName.getText().toString();
        String password = textPassword.getText().toString();
        User user = new User(username,password);
        active.changePassword(user).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                ResponseMessage responseMessage = response.body();
                Toast.makeText(activityRegistAccount.this,responseMessage.getInfo(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void saveRegisterAccount(){
        Register register = new Register(firstName,lastName,email,dateOfBirth
                ,gender,phoneNum,address,textUserName.getText().toString(),textPassword.getText().toString(),identityCard);
        apiPostRegister.registerAccount(register).enqueue(new Callback<ResponseActiveEmail>() {
            @Override
            public void onResponse(Call<ResponseActiveEmail> call, Response<ResponseActiveEmail> response) {
                ResponseActiveEmail registerInfo = response.body();
                if(registerInfo.getResult()) {
                    Intent intent = new Intent(activityRegistAccount.this, ActivityOTP.class);
                    intent.putExtra("OTP", registerInfo.getData().getVerifyNumber());
                    intent.putExtra("email", email);
                    intent.putExtra("username",textUserName.getText().toString());
                    intent.putExtra("password", textPassword.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(activityRegistAccount.this,registerInfo.getInfo(),Toast.LENGTH_SHORT).show();
                }
                System.out.println(registerInfo.toString());
            }

            @Override
            public void onFailure(Call<ResponseActiveEmail> call, Throwable t) {
                System.out.println("error");
                t.printStackTrace();
            }
        });
    }


    public void backLogin(){
        Intent intent = new Intent(this,activityLogin.class);
        startActivity(intent);
    }

    public void clickRegister(){
        buttonRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.equals("no")) {
                    openAndLogin();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }else{
                    openOTP();
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }
        });
    }
    public void getIdObject(){
        //get id button register
        buttonRegist = (Button) findViewById(R.id.buttonNext_Regist);
        // get id textLogin
        textSignUp = (TextView) findViewById(R.id.textLoginNext);
        // get id text username
        textUserName = (EditText) findViewById(R.id.textUserName_regist);
        // get id text password
        textPassword = (EditText) findViewById(R.id.textPassword_regist);
        // get id text verify password
        textVerifyPassword = (EditText) findViewById(R.id.textVerifyPassword_regist);

        // get input layout username
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUserName_Regist);
        // get input layout password
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword_regist);
        // get inputlayout verify password
        textInputLayoutVerifyPassword = (TextInputLayout) findViewById(R.id.textInputLayoutVerifyPass_regist);
    }

    public void catchUsername(){
        textUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()&&!s.toString().matches(regexUserName)){
                    textInputLayoutUsername.setError("Allow only character and number");
                }else {
                    textInputLayoutUsername.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void catchPassword(){
        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()&&!s.toString().matches(regexPassword)){
                    textInputLayoutPassword.setError("User more than 8 characters and combinations of letters, numbers, and symbols.");
                }else {
                    textInputLayoutPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void catchVerifyPassword(){
        textVerifyPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()&&!s.toString().matches(textPassword.getText().toString())){
                    textInputLayoutVerifyPassword.setError("incorrect with password");
                    isCheckPass = false;
                }else {
                    textInputLayoutVerifyPassword.setError(null);
                    isCheckPass = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void clickSignUp(){
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin();
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
            }
        });
    }
}
