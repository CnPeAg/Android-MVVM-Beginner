package com.prakash.android_mvvm_beginner.ui.login.model;

public class LoginRequestModel {

    private String appID;
    private String mobile;
    private String otp;
    private String userID;
    private String type;
    private String device_token;

    public LoginRequestModel(String appID, String mobile, String otp, String userID, String type, String device_token) {
        this.appID = appID;
        this.mobile = mobile;
        this.otp = otp;
        this.userID = userID;
        this.type = type;
        this.device_token = device_token;
    }
}
