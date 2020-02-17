package com.prakash.android_mvvm_beginner.ui.login.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prakash.android_mvvm_beginner.R;
import com.prakash.android_mvvm_beginner.model.Session;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginGetOTPResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.viewModel.LoginViewModel;
import com.prakash.android_mvvm_beginner.utils.Methods;
import com.prakash.android_mvvm_beginner.utils.NetworkUtill;
import com.prakash.android_mvvm_beginner.utils.SessionManager;


public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener{
    private EditText edt_user,edt_password;
    private String mReferralcode;
    private Session session;
    private TextView tv_user,tv_password,tv_login, tv_register;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        session = SessionManager.getInstance(getApplication()).getSession();
        tv_user= findViewById(R.id.tv_user);
        edt_user = findViewById(R.id.edt_user);
        tv_password= findViewById(R.id.tv_password);
        edt_password = findViewById(R.id.edt_password);
        tv_login = findViewById(R.id.tv_login);
        tv_register = findViewById(R.id.tv_register);
        //circularProgressBar =  findViewById(R.id.circularProgressBar);
        edt_user.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tv_login.performClick();
                    return true;
                }
                return false;
            }
        });
        edt_user.setOnFocusChangeListener(this);
        edt_password.setOnFocusChangeListener(this);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (edt_user.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, R.string.user_id_empty, Toast.LENGTH_SHORT).show();
                } else {
                    if (Methods.isValidEmailAddress(edt_user.getText().toString().trim())) {
                        if (NetworkUtill.isNetworkAvailable(LoginActivity.this)) {
                            loginViewModel.setDataForLogin(getApplication(), edt_user.getText().toString().trim());
                        } else {
                            NetworkUtill.showNoInternetDialog(LoginActivity.this);
                        }
                    } else if (Methods.validCellPhone(edt_user.getText().toString().trim())) {
                        if (NetworkUtill.isNetworkAvailable(LoginActivity.this)) {
                            loginViewModel.setDataForLogin(getApplication(), edt_user.getText().toString().trim());
                        } else {
                            NetworkUtill.showNoInternetDialog(LoginActivity.this);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.valid_user_id, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("mReferralcode", mReferralcode);
                startActivity(intent);
            }
        });

        // Create the observer which updates the UI.
        final Observer<LoginGetOTPResponceModel> otpResponceModelObserver = new Observer<LoginGetOTPResponceModel>() {
            @Override
            public void onChanged(@Nullable final LoginGetOTPResponceModel responceModel) {
                // Update the UI, in this case
                if (responceModel != null) {
                    if (responceModel.getOtpDataModel().getUserID()!=null && !responceModel.getOtpDataModel().getUserID().isEmpty()) {
                        String userId = responceModel.getOtpDataModel().getUserID();
                        Intent intent = new Intent(LoginActivity.this, LoginWithOTP.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("UserId", userId);
                        intent.putExtra("phone", edt_user.getText().toString().trim());
                        startActivity(intent);
                    }
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        loginViewModel.liveDataGetOTPLogin.observe(this, otpResponceModelObserver);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edt_user:
            tv_user.setTextColor(getResources().getColor(R.color.edit_text_bottom_color));
            tv_password.setTextColor(getResources().getColor(R.color.prescription_tv_text_heading_color));
            break;
            case R.id.edt_password:
                tv_password.setTextColor(getResources().getColor(R.color.edit_text_bottom_color));
                tv_user.setTextColor(getResources().getColor(R.color.prescription_tv_text_heading_color));
                break;
            default:
                break;
        }

    }
}
