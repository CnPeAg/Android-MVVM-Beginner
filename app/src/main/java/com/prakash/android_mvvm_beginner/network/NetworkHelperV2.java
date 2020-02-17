package com.prakash.android_mvvm_beginner.network;

import android.app.Application;

import com.prakash.android_mvvm_beginner.utils.AppUrl;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkHelperV2 {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Application application) {

        if (retrofit == null) {
            synchronized (NetworkHelperBase.class){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(120, TimeUnit.SECONDS)
                        .writeTimeout(180, TimeUnit.SECONDS)
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .addInterceptor(new HeaderIntercepter(application))
                        .addInterceptor(logging)
                        .build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(AppUrl.BaseUrl)
                        .client(client)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                        .build();
            }
        }
        return retrofit;
    }
}
