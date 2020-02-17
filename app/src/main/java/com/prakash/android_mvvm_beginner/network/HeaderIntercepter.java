package com.prakash.android_mvvm_beginner.network;

import android.app.Application;
import android.content.Context;

import com.prakash.android_mvvm_beginner.model.Session;
import com.prakash.android_mvvm_beginner.utils.AppUrl;
import com.prakash.android_mvvm_beginner.utils.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderIntercepter implements Interceptor {
        private Session session;
        private Application application;
        private String authToken;

        public HeaderIntercepter(Application application) {
            this.application = application;
            this.session= SessionManager.getInstance(application).getSession();
            authToken=session.getAuthToken();
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("authorization",  AppUrl.BASIC + authToken)
                    .addHeader("Content-Type",AppUrl.CONTENT_TYPE)
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            Response response = chain.proceed(request);
            return response;
        }

}
