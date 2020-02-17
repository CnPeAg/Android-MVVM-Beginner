package com.prakash.android_mvvm_beginner.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class UserData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("auth_token")
    @Expose
    private String authToken;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("user_type")
    @Expose
    private Integer userType;
    @SerializedName("referral_code")
    @Expose
    private String referralCode;
    @SerializedName("referral_points")
    @Expose
    private Integer referralPoints;
    @SerializedName("title")
    @Expose
    private Integer title;
    @SerializedName("is_consultant_doctor")
    @Expose
    private Integer isConsultantDoctor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Integer getReferralPoints() {
        return referralPoints;
    }

    public void setReferralPoints(Integer referralPoints) {
        this.referralPoints = referralPoints;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getIsConsultantDoctor() {
        return isConsultantDoctor;
    }

    public void setIsConsultantDoctor(Integer isConsultantDoctor) {
        this.isConsultantDoctor = isConsultantDoctor;
    }
}
