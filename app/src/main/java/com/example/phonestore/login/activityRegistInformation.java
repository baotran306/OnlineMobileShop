package com.example.phonestore.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonestore.R;
import com.example.phonestore.location.activityGetLocation;
import com.google.android.material.textfield.TextInputLayout;

public class activityRegistInformation extends AppCompatActivity {
    private Button ButtonNextRegist;
    private TextView textSignUp;
    private EditText textFirstName;
    private EditText textLastName;
    private EditText textEmail;
    private EditText textBirtDay;
    private EditText textNumberPhone;
    private EditText textIdCard;
    private EditText textAddress;

    private RadioButton rbMale,rbFemale;

    private TextInputLayout textInputLayoutBirtDay;
    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutNumberPhone;
    private TextInputLayout textInputLayoutIdCard;
    private TextInputLayout textInputLayoutAddress;

    private String regexName = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]+";
    private String regexEmail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    private String regexDate = "\\d{1,2}[/]\\d{1,2}[/]\\d{4}";
    private String regexNumberPhone = "[0]{1}\\d{9}";
    private String regexNumber = "\\d{9}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerinformation);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        getIdObject();
        clickButtonNext();
        clickAddress();
        clickTextSign();
        catchFirstName();
        catchLastName();
        catchEmail();
        catchDateOfBirth();
        catchPhoneNumber();
        catchIdCard();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void openRegistAccount() {
        Intent intent = new Intent(this, activityRegistAccount.class);
        intent.putExtra("firstName",textFirstName.getText().toString());
        intent.putExtra("lastName",textLastName.getText().toString());
        intent.putExtra("email",textEmail.getText().toString());
        intent.putExtra("numberPhone",textNumberPhone.getText().toString());
        intent.putExtra("idCard",textIdCard.getText().toString());
        intent.putExtra("address",textAddress.getText().toString());
        intent.putExtra("dateOfBirth",textBirtDay.getText().toString());
        intent.putExtra("password","yes");
        if(rbMale.isChecked()){
            intent.putExtra("gender","Nam");
        }else {
            intent.putExtra("gender","Nữ");
        }

        startActivity(intent);
    }

    public void backLogin() {
        Intent intent = new Intent(this, activityLogin.class);
        startActivity(intent);
    }

    public void catchFirstName() {
        // Bắt lỗi first name
        textFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(regexName)) {
                    textInputLayoutFirstName.setError("Allow only character");
                } else {
                    textInputLayoutFirstName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void catchEmail() {
        // bắt lỗi Email
        textEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(regexEmail)) {
                    textInputLayoutEmail.setError("Allow only:abc12@abc.com");
                } else {
                    textInputLayoutEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void catchLastName() {
        // Bắt lỗi last name
        textLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(regexName)) {
                    textInputLayoutLastName.setError("Allow only character");
                } else {
                    textInputLayoutLastName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void catchDateOfBirth() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        // Set Calendar
        textBirtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(activityRegistInformation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                String date = dayOfMonth + "/" + month + "/" + year;
                                textBirtDay.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // bắt lỗi calendar
        textBirtDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(regexDate)) {
                    textInputLayoutBirtDay.setError("Allow only:dd/mm/yyyy");
                } else {
                    textInputLayoutBirtDay.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void catchPhoneNumber() {
        // bắt lỗi phone
        textNumberPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(regexNumberPhone)) {
                    textInputLayoutNumberPhone.setError("Allow only Number and prefix only'0'");
                } else {
                    textInputLayoutNumberPhone.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void catchIdCard() {
        // bắt lỗi id card
        textIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && !s.toString().matches(regexNumber)) {
                    textInputLayoutIdCard.setError("Allow only Number");
                } else {
                    textInputLayoutIdCard.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void clickTextSign() {
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });
    }

    public void getIdObject() {
        //get id idCard
        textIdCard = (EditText) findViewById(R.id.textIdCard_regist);
        //get id inputLayoutIdCard
        textInputLayoutIdCard = (TextInputLayout) findViewById(R.id.textInputLayoutIdCard_regist);
        //get id phoneNumber
        textNumberPhone = (EditText) findViewById(R.id.textPhoneNumber_regist);
        //get id inputLayoutPhoneNumber
        textInputLayoutNumberPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone_regist);
        // get id Date of birth
        textBirtDay = (EditText) findViewById(R.id.textDateOfBirth_regist);
        //get id layoutDateofbirth
        textInputLayoutBirtDay = (TextInputLayout) findViewById(R.id.textInputLayoutDayOfBirth_regist);
        //get id last name
        textLastName = (EditText) findViewById(R.id.lastName_regist);
        //get id inputLayoutLastName
        textInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName_regist);
        //get id email
        textEmail = (EditText) findViewById(R.id.textEmail_regist);
        //get id inputLayoutEmail
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail_regist);
        //get id firstName
        textFirstName = (EditText) findViewById(R.id.firstName_regist);
        // get id inputLayoutFirstName
        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName_regist);
        // get Id textSignUp
        textSignUp = (TextView) findViewById(R.id.textLoginRegist);
        // get Id ButtonNext
        ButtonNextRegist = (Button) findViewById(R.id.buttonNext_Regist);
        // get Id Address
        textAddress = (EditText) findViewById(R.id.textAddress_regist);
        //get id InputLayout Address
        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress_regist);
        rbMale =(RadioButton) findViewById(R.id.radioMale);
        rbFemale=(RadioButton) findViewById(R.id.radioFemale);
    }

    public Boolean checkNull() {
        boolean check = true;
        if (textFirstName.getText().toString().trim().equals("")) {
            textInputLayoutFirstName.setError("Not null");
            check = false;
        }

        if (textLastName.getText().toString().trim().equals("")) {
            textInputLayoutLastName.setError("Not null");
            check = false;
        }

        if (textEmail.getText().toString().trim().equals("")) {
            textInputLayoutEmail.setError("Not null");
            check = false;
        }

        if (textBirtDay.getText().toString().trim().equals("")) {
            textInputLayoutBirtDay.setError("Not null");
            check = false;
        }

        if (textNumberPhone.getText().toString().trim().equals("")) {
            textInputLayoutNumberPhone.setError("Not null");
            check = false;
        }

        if (textIdCard.getText().toString().equals("")) {
            textInputLayoutIdCard.setError("Not null");
            check = false;
        }

        if (textAddress.getText().toString().equals("")) {
            textInputLayoutAddress.setError("Not null");
            check = false;
        }
        return check;
    }

    private void clickAddress(){
        textAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityGetLocation();
            }
        });
    }

    private void openActivityGetLocation(){
        Intent intent = new Intent(this, activityGetLocation.class);
        startActivityForResult(intent,8089);
        setResult(61);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8089 && resultCode == 61) {
            if (data!=null) {
                textAddress.setText(data.getStringExtra("City")+", "
                        + data.getStringExtra("District")+", "
                        + data.getStringExtra("Ward") +", "
                        + data.getStringExtra("Street"));
            }
        }
    }

    public void clickButtonNext() {
        ButtonNextRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputLayoutFirstName.getError() == null
                        && textInputLayoutLastName.getError() == null
                        && textInputLayoutEmail.getError() == null
                        && textInputLayoutBirtDay.getError() == null
                        && textInputLayoutNumberPhone.getError() == null
                        && textInputLayoutNumberPhone.getError() == null
                        && textInputLayoutIdCard.getError() == null) {
                    if (checkNull()) {
                        openRegistAccount();
                    }
                }
            }
        });
    }
}
