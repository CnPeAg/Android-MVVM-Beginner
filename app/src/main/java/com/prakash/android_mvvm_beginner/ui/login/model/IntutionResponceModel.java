package com.prakash.android_mvvm_beginner.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IntutionResponceModel implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("learning")
    @Expose
    private LearningIntutionModel learningIntutionModel;
    @SerializedName("community")
    @Expose
    private String community;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("userid")
    @Expose
    private UserId userid;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LearningIntutionModel getLearningIntutionModel() {
        return learningIntutionModel;
    }

    public void setLearningIntutionModel(LearningIntutionModel learningIntutionModel) {
        this.learningIntutionModel = learningIntutionModel;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UserId getUserid() {
        return userid;
    }

    public void setUserid(UserId userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
