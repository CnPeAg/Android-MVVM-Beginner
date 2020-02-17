package com.prakash.android_mvvm_beginner.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AndroidModel {
    @SerializedName("minversion")
    @Expose
    private Double minversion;
    @SerializedName("latestversion")
    @Expose
    private Double latestversion;
    @SerializedName("is_need_to_update")
    @Expose
    private Integer isNeedToUpdate;


    public Double getMinversion() {
        return minversion;
    }

    public void setMinversion(Double minversion) {
        this.minversion = minversion;
    }

    public Double getLatestversion() {
        return latestversion;
    }

    public void setLatestversion(Double latestversion) {
        this.latestversion = latestversion;
    }

    public Integer getIsNeedToUpdate() {
        return isNeedToUpdate;
    }

    public void setIsNeedToUpdate(Integer isNeedToUpdate) {
        this.isNeedToUpdate = isNeedToUpdate;
    }
}
