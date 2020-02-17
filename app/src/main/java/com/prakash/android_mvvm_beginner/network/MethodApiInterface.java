package com.prakash.android_mvvm_beginner.network;

import com.prakash.android_mvvm_beginner.model.CommonRequestModel;
import com.prakash.android_mvvm_beginner.ui.login.model.IntutionResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginGetOTPResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginRequestModel;
import com.prakash.android_mvvm_beginner.ui.login.model.LoginSuccessModel;
import com.prakash.android_mvvm_beginner.ui.login.model.VersionCheckRequstModel;
import com.prakash.android_mvvm_beginner.ui.login.model.VersionCheckResponceModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface MethodApiInterface {

    @POST(UrlConstant.APP_VERSION)
    Observable<VersionCheckResponceModel> getVersionCheck(@Body VersionCheckRequstModel versionCheckRequstModel);

    @POST(UrlConstant.NOTIFY_TEXTCOUNT)
    Observable<IntutionResponceModel> getIntutionData(@Body CommonRequestModel commonRequestModel);

    @FormUrlEncoded
    @POST(UrlConstant.LOGIN_VIA_OTP)
    Observable<LoginGetOTPResponceModel> getLoginOTP(@Field("appId") String appID, @Field("mobile") String mobile);

    @POST(UrlConstant.LOGIN_WITH_OTP)
    Observable<LoginSuccessModel> getLoginWithOTP(@Body LoginRequestModel loginRequestModel);

}
