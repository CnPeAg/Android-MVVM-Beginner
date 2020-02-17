package com.prakash.android_mvvm_beginner.ui.login.viewModel;

import android.app.Application;

import com.prakash.android_mvvm_beginner.ui.login.model.LoginSuccessModel;
import com.prakash.android_mvvm_beginner.ui.login.repository.LoginSignUpRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginWithOTPViewModel extends AndroidViewModel implements LifecycleObserver {

    public MutableLiveData<LoginSuccessModel> liveDataLoginSuccess = new MutableLiveData<LoginSuccessModel>();

    public LoginWithOTPViewModel(@NonNull Application application) {
        super(application);
    }

    public void getLoginData(Application application,String userId,String mobile,String otp) {
        LoginSignUpRepository loginSignUpRepository = new LoginSignUpRepository();
        loginSignUpRepository.getLoginData(application,userId,mobile,otp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(loginObserver);
    }
    private Observer<LoginSuccessModel> loginObserver = new Observer<LoginSuccessModel>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(LoginSuccessModel responceModel) {
            liveDataLoginSuccess.postValue(responceModel);
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
