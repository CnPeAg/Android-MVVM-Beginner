package com.prakash.android_mvvm_beginner.ui.login.model;

public class OTPRequestModel {
    private String appID;
    private String mobile;

    public OTPRequestModel(String appID, String mobile) {
        this.appID = appID;
        this.mobile = mobile;
    }
}
