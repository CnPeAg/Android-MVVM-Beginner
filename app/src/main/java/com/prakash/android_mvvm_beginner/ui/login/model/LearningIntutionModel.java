package com.prakash.android_mvvm_beginner.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LearningIntutionModel implements Serializable {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("ayurveda_exam")
    @Expose
    private String ayurvedaExam;
    @SerializedName("courses")
    @Expose
    private String courses;
    @SerializedName("quiz")
    @Expose
    private String quiz;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getAyurvedaExam() {
        return ayurvedaExam;
    }

    public void setAyurvedaExam(String ayurvedaExam) {
        this.ayurvedaExam = ayurvedaExam;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
