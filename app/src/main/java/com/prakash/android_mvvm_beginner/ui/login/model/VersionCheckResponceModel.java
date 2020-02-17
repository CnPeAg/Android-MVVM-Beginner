package com.prakash.android_mvvm_beginner.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionCheckResponceModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("ios")
    @Expose
    private Object ios;
    @SerializedName("android")
    @Expose
    private AndroidModel androidModel;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getIos() {
        return ios;
    }

    public void setIos(Object ios) {
        this.ios = ios;
    }

    public AndroidModel getAndroidModel() {
        return androidModel;
    }

    public void setAndroidModel(AndroidModel androidModel) {
        this.androidModel = androidModel;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
