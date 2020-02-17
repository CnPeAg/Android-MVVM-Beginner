package com.prakash.android_mvvm_beginner.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.prakash.android_mvvm_beginner.model.Session;
import com.prakash.android_mvvm_beginner.ui.login.activity.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    public static final String AUTH_TOKEN = "Auth_Token";
    public static final String KEY_USER_ID = "user_Id";
    private static final String PREFER_LANGUAGE = "Language";
    private static final String PREFER_NAME = "AndroidExamplePref";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    private static final String CREATED_ON = "createdOn";
    private static final String KEY_USER_TYPE = "user_type";
    private static final String KEY_REFERRAL_CODE = "referral_code";
    private static final String KEY_TITLE = "title";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_POFILE_PIC = "profile";
    private static final String KEY_LNAME = "lname";
    private static final String IS_CONSULTANT_DOCTOR = "isConsultantDoctor";
    private static final String KEY_MOBILE = "mobile";
    private static SessionManager instance;
    int PRIVATE_MODE = 0;
    private SharedPreferences pref, pref1;
    private SharedPreferences.Editor editor, editor1;
    private Context context;

    private SessionManager(Application context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        pref1 = context.getSharedPreferences(PREFER_LANGUAGE, PRIVATE_MODE);
        editor1 = pref1.edit();
        editor = pref.edit();
    }

    public static SessionManager getInstance(Application application) {
        if (instance == null) {
            synchronized (SessionManager.class) {
                return new SessionManager(application);
            }
        }
        return instance;
    }

    public void createUserLoginSession(String fname, String lname, String email, String auth_token, String mobile, String createdOn, String userId, String user_type, String referral_code, String title, String is_consultant_doctor) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(CREATED_ON, createdOn);
        editor.putString(IS_CONSULTANT_DOCTOR, is_consultant_doctor);
        editor.putString(AUTH_TOKEN, auth_token);
        editor.putString(KEY_FNAME, fname);
        editor.putString(KEY_LNAME, lname);
        editor.putString(KEY_TITLE, title);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_TYPE, user_type);
        editor.putString(KEY_REFERRAL_CODE, referral_code);
        editor.commit();
    }

    public Session getSession() {
        Session session = new Session();
        session.setAuthToken(pref.getString(AUTH_TOKEN, null));
        session.setFirstName(pref.getString(KEY_FNAME, null));
        session.setLastName(pref.getString(KEY_LNAME, null));
        session.setTitle(pref.getString(KEY_TITLE, null));
        session.setEmail(pref.getString(KEY_EMAIL, null));
        session.setMobile(pref.getString(KEY_MOBILE, null));
        session.setUserId(pref.getString(KEY_USER_ID, null));
        session.setUserType(pref.getString(KEY_USER_TYPE, null));
        session.setReferralCode(pref.getString(KEY_REFERRAL_CODE, null));
        return session;
    }



    public boolean checkLogedIn() {
        if (!isUserLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<String, String>();

        user.put(AUTH_TOKEN, pref.getString(AUTH_TOKEN, null));
        user.put(KEY_FNAME, pref.getString(KEY_FNAME, null));
        user.put(KEY_USER_TYPE, pref.getString(KEY_USER_TYPE, null));
        user.put(KEY_LNAME, pref.getString(KEY_LNAME, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        user.put(KEY_REFERRAL_CODE, pref.getString(KEY_REFERRAL_CODE, null));
        user.put(KEY_TITLE, pref.getString(KEY_TITLE, ""));
        return user;
    }


    public HashMap<String, String> getProfile() {

        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_POFILE_PIC, pref1.getString(KEY_POFILE_PIC, null));
        return user;
    }

    public void logoutUser() {

        editor.clear();
        editor.commit();
    }


    public void updateProfile(String language) {

        languageLogOut();
        editor1.putString(KEY_POFILE_PIC, language);
        editor1.commit();
    }


    public void languageLogOut() {
        editor1.clear();
        editor1.commit();

    }
}
