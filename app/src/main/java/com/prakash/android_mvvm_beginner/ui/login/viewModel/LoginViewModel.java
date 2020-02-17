package com.prakash.android_mvvm_beginner.ui.login.viewModel;

import android.app.Application;

import com.prakash.android_mvvm_beginner.ui.login.model.LoginGetOTPResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.repository.LoginSignUpRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel implements LifecycleObserver {

    public MutableLiveData<LoginGetOTPResponceModel> liveDataGetOTPLogin = new MutableLiveData<LoginGetOTPResponceModel>();
    public LoginViewModel(@NonNull Application application) {
        super(application);

    }

    public void setDataForLogin(Application application,String userId){
        getLoginOTP(application,userId);
    }

    private void getLoginOTP(Application application,String userId) {
        LoginSignUpRepository loginSignUpRepository = new LoginSignUpRepository();
        loginSignUpRepository.getLoginOTPData(application,userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(loginGetOTPObserver);
    }

    private Observer<LoginGetOTPResponceModel> loginGetOTPObserver = new Observer<LoginGetOTPResponceModel>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(LoginGetOTPResponceModel responceModel) {
            liveDataGetOTPLogin.postValue(responceModel);
        }

        @Override
        public void onError(Throwable e) {
            e.getMessage();
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    };

}
