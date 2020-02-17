package com.prakash.android_mvvm_beginner.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.prakash.android_mvvm_beginner.R;

public class NetworkUtill {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showNoInternetDialog(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
    }

}
