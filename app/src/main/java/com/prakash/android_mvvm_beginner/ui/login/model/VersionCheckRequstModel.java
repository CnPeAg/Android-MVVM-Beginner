package com.prakash.android_mvvm_beginner.ui.login.model;

public class VersionCheckRequstModel {

    private String appID;
    private String authToken;

    public VersionCheckRequstModel(String appID, String authToken) {
        this.appID = appID;
        this.authToken = authToken;
    }
}
