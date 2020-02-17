package com.prakash.android_mvvm_beginner.ui.login.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.prakash.android_mvvm_beginner.R;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginSuccessModel;
import com.prakash.android_mvvm_beginner.ui.login.viewModel.LoginWithOTPViewModel;

public class LoginWithOTP extends AppCompatActivity {
    private ImageView img_back;
    private EditText edt_user_id,otpEtOne,otpEtTwo,otpEtThree,otpEtFour;
    private TextView resend_otp;
    private Button btnLogin;
    private LoginWithOTPViewModel loginWithOTPViewModel;
    private String userId, phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);

        img_back = findViewById(R.id.img_back);
        btnLogin = findViewById(R.id.btn_continue);
        edt_user_id = findViewById(R.id.edt_user_id);
        otpEtOne = findViewById(R.id.otpEtOne);
        otpEtTwo = findViewById(R.id.otpEtTwo);
        otpEtThree = findViewById(R.id.otpEtThree);
        otpEtFour = findViewById(R.id.otpEtFour);
        resend_otp= findViewById(R.id.resend_otp);


        if (getIntent().hasExtra("UserId")) {
            userId = getIntent().getStringExtra("UserId");
        }
        if (getIntent().hasExtra("phone")) {
            phoneNo = getIntent().getStringExtra("phone");
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=otpEtOne.getText().toString().trim()+""+
                        otpEtTwo.getText().toString().trim()+""+
                        otpEtThree.getText().toString().trim()+""+
                        otpEtFour.getText().toString().trim();
                loginWithOTPViewModel.getLoginData(getApplication(), userId, edt_user_id.getText().toString().trim(), otp);
            }
        });


        loginWithOTPViewModel = ViewModelProviders.of(this).get(LoginWithOTPViewModel.class);


        // Create the observer which updates the UI.
        final Observer<LoginSuccessModel> loginSuccessModelObserver = new Observer<LoginSuccessModel>() {
            @Override
            public void onChanged(@Nullable final LoginSuccessModel loginSuccessModel) {
                // Update the UI, in this case
                if (loginSuccessModel != null) {

                }
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        loginWithOTPViewModel.liveDataLoginSuccess.observe(this, loginSuccessModelObserver);
    }
}
