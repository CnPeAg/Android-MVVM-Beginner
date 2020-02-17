package com.prakash.android_mvvm_beginner.ui.login.repository;

import android.app.Application;

import com.prakash.android_mvvm_beginner.model.CommonRequestModel;
import com.prakash.android_mvvm_beginner.model.Session;
import com.prakash.android_mvvm_beginner.network.MethodApiInterface;
import com.prakash.android_mvvm_beginner.network.NetworkHelperBase;
import com.prakash.android_mvvm_beginner.network.NetworkHelperV3;
import com.prakash.android_mvvm_beginner.ui.login.model.IntutionResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginGetOTPResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginRequestModel;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginSuccessModel;
import com.prakash.android_mvvm_beginner.ui.login.model.VersionCheckRequstModel;
import com.prakash.android_mvvm_beginner.ui.login.model.VersionCheckResponceModel;
import com.prakash.android_mvvm_beginner.utils.AppUrl;
import com.prakash.android_mvvm_beginner.utils.SessionManager;

import io.reactivex.Observable;


public class LoginSignUpRepository {

    private MethodApiInterface apiInterface;
    private String authToken;
    private String userId;
    public LoginSignUpRepository() {

    }

    public Observable<VersionCheckResponceModel> getVersionUpdate(Application application) {
        Observable<VersionCheckResponceModel> observableData=null;
        try {
            Session session = SessionManager.getInstance(application).getSession();
            authToken = session.getAuthToken();
            if (authToken == null || authToken.isEmpty()) {
                authToken = "";
            }
            apiInterface = NetworkHelperBase.getClient(application).create(MethodApiInterface.class);
            observableData = apiInterface.getVersionCheck(new VersionCheckRequstModel(AppUrl.APP_ID_VALUE_POST, authToken));
            return observableData;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return observableData;
        }
    }

    public Observable<IntutionResponceModel> getIntutionData(Application application) {
        Observable<IntutionResponceModel> observableInstutionData=null;
        try {
            Session session = SessionManager.getInstance(application).getSession();
            userId = session.getUserId();
            apiInterface = NetworkHelperV3.getClient(application).create(MethodApiInterface.class);
            observableInstutionData = apiInterface.getIntutionData(new CommonRequestModel(AppUrl.APP_ID_VALUE_POST, userId));
            return observableInstutionData;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return observableInstutionData;
        }
    }

    public Observable<LoginGetOTPResponceModel> getLoginOTPData(Application application, String userId) {
        Observable<LoginGetOTPResponceModel> otpResponceModelObservable=null;
        try {
            apiInterface = NetworkHelperBase.getClient(application).create(MethodApiInterface.class);
            otpResponceModelObservable = apiInterface.getLoginOTP(AppUrl.APP_ID_VALUE_POST, userId);
            return otpResponceModelObservable;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return otpResponceModelObservable;
        }
    }

    public Observable<LoginSuccessModel> getLoginData(Application application, String userId,String mobile,String otp) {
        Observable<LoginSuccessModel> loginSuccessModelObservable=null;
        try {
            //String deviceToken = FirebaseInstanceId.getInstance().getToken();
            apiInterface = NetworkHelperBase.getClient(application).create(MethodApiInterface.class);
            loginSuccessModelObservable = apiInterface.getLoginWithOTP(new LoginRequestModel(AppUrl.APP_ID_VALUE_POST, mobile,otp,userId,"android","deviceToken"));
            return loginSuccessModelObservable;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return loginSuccessModelObservable;
        }
    }


}
