package com.prakash.android_mvvm_beginner.model;


import com.prakash.android_mvvm_beginner.ui.login.model.IntutionResponceModel;

public class SingletonIntutionData {

    private static SingletonIntutionData singletonIntutionData;
    private IntutionResponceModel intutionModel;

    private SingletonIntutionData() {}

    public static SingletonIntutionData getInstance() {
        if (singletonIntutionData == null) {
            singletonIntutionData = new SingletonIntutionData();
        }
        return(singletonIntutionData);
    }

    public IntutionResponceModel getIntutionModel() {
        return intutionModel;
    }

    public void setIntutionModel(IntutionResponceModel intutionModel) {
        this.intutionModel = intutionModel;
    }
}
