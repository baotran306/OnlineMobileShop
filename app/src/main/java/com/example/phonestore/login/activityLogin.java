package com.example.phonestore.login;

import static com.example.phonestore.api.customer.apiUser.apiPostUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.User;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.entity.login.responseLogin;
import com.example.phonestore.home.activityHome;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityLogin extends AppCompatActivity {

    private Button buttonRegist;
    private Button btnLogin, btnForgetPass;
    private TextView textSignUp;
    private EditText userName;
    private EditText password;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;
    private static Boolean result_intense = false;
    public static responseLogin loginInfo;
//    ActivityResultLauncher<Intent> activityResultLauncher;
//    private final static int REQUEST_LONGIN = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
        initUI();
        clickButtonSignUp();
        clickTextSignUp();
        clickLogin();
        getDataInRegister();
        onClickButtonForgetPass();
        setError();
    }

    public void getDataInRegister() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userName.setText(bundle.getString("username"));
            password.setText(bundle.getString("password"));
        }
    }

    public void initUI() {
        buttonRegist = (Button) findViewById(R.id.btn_regist);
        textSignUp = (TextView) findViewById(R.id.textSignLogin);
        userName = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUserName_login);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnForgetPass = (Button) findViewById(R.id.btn_forgetpassword);
    }


    public void clickButtonSignUp() {
        buttonRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegisInformation();
            }
        });
    }

    public void postUser() {

        User user = new User(userName.getText().toString(), password.getText().toString());
        apiPostUser.postUser(user).enqueue(new Callback<responseLogin>() {
            @Override
            public void onResponse(Call<responseLogin> call, Response<responseLogin> response) {
                responseLogin info = response.body();
                System.out.println(info.toString());
                if (info.getResult() == true) {
                    openActivityPhoneMenu();
                    System.out.println(info.getInfo().toString());
                    overridePendingTransition(0, 0);
                    DataLocalManager.setInformationUser(info.getInfo());
                } else {
                    textInputLayoutPassword.setError(info.getMessage());
                    textInputLayoutUsername.setError(info.getMessage());
                }
            }

            @Override
            public void onFailure(Call<responseLogin> call, Throwable t) {
                System.out.println("error");
                t.printStackTrace();
            }
        });
    }

    private void clickLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUser();
            }
        });
    }

    public void clickTextSignUp() {
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegisInformation();
            }
        });
    }

    public void setError() {
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayoutPassword.setError(null);
                textInputLayoutUsername.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayoutPassword.setError(null);
                textInputLayoutUsername.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void openActivityRegisInformation() {
        Intent intent = new Intent(this, activityRegistInformation.class);
        startActivity(intent);
    }

    private void onClickButtonForgetPass() {
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityActiveAccount();
            }
        });
    }


    private void openActivityActiveAccount() {
        Intent intent = new Intent(this, activityActiveAccount.class);
        String forget = "Forget";
        intent.putExtra("forget", forget);
        startActivity(intent);
    }

    public void openActivityPhoneMenu() {
//        Intent intent = new Intent(this, activityMenuPhone.class);
//        startActivity(intent);
//            Intent intent = new Intent(this, activityInformation.class);
//            startActivity(intent);
        Intent intent = new Intent(this, activityHome.class);
        startActivity(intent);

//        Intent intent = new Intent(this, activityStateOrder.class);
//        startActivity(intent);

//        Intent intent = new Intent(this, activityCart.class);
//        startActivity(intent);
//        Intent intent = new Intent(this, activityGetLocation.class);
//        startActivity(intent);

    }
}