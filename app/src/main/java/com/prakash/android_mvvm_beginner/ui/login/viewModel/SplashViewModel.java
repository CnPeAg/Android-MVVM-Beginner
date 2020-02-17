package com.prakash.android_mvvm_beginner.ui.login.viewModel;

import android.app.Application;
import android.util.Log;

import com.prakash.android_mvvm_beginner.ui.login.model.IntutionResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.model.VersionCheckResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.repository.LoginSignUpRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashViewModel extends AndroidViewModel implements LifecycleObserver {

    private String TAG = this.getClass().getSimpleName();
    public MutableLiveData<VersionCheckResponceModel> liveDataVersion = new MutableLiveData<VersionCheckResponceModel>();
    public MutableLiveData<IntutionResponceModel> liveDataIntution = new MutableLiveData<IntutionResponceModel>();

    private Observer<VersionCheckResponceModel> versionCheckResponceModelObservable = new Observer<VersionCheckResponceModel>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(VersionCheckResponceModel versionCheckResponceModel) {
            liveDataVersion.postValue(versionCheckResponceModel);
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, ""+e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    };
    private Observer<IntutionResponceModel> intutionResponceModelObserver = new Observer<IntutionResponceModel>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(IntutionResponceModel intutionResponceModel) {
            liveDataIntution.postValue(intutionResponceModel);
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, ""+e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    };

    public SplashViewModel(@NonNull Application application) {
        super(application);
        getVersionData(application);
        getInstitutionData(application);
    }

    private void getVersionData(Application application) {
        LoginSignUpRepository loginSignUpRepository = new LoginSignUpRepository();
        loginSignUpRepository.getVersionUpdate(application)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(versionCheckResponceModelObservable);
    }

    private void getInstitutionData(Application application) {
        LoginSignUpRepository loginSignUpRepository = new LoginSignUpRepository();
        loginSignUpRepository.getIntutionData(application)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(intutionResponceModelObserver);

    }
}
