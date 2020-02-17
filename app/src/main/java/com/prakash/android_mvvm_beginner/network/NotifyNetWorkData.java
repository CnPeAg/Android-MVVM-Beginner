package com.prakash.android_mvvm_beginner.network;

import retrofit2.Response;

public interface NotifyNetWorkData {
    void onSuccess(Response response);

    void onFailure(String error);
}
