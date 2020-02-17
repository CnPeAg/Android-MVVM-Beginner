package com.prakash.android_mvvm_beginner.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserId implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("is_consultant_doctor")
    @Expose
    private String isConsultantDoctor;
    @SerializedName("degree")
    @Expose
    private String degree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsConsultantDoctor() {
        return isConsultantDoctor;
    }

    public void setIsConsultantDoctor(String isConsultantDoctor) {
        this.isConsultantDoctor = isConsultantDoctor;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
