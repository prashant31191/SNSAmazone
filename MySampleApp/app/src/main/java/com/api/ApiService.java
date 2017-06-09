package com.api;

import com.api.response.ChangePasswordResponse;
import com.api.response.CityListResponse;
import com.api.response.CommonResponse;
import com.api.response.CountryListResponse;
import com.api.response.LanguageResponce;

import com.api.response.NotificationListResponse;
import com.api.response.RegisterResponse;
import com.api.response.StateListResponse;
import com.api.response.TestExamResponce;
import com.api.response.TestResponse;
import com.api.response.ViewProfileResponse;
import com.api.response.WebviewContentResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


/**
 * Created by Admin on 11/15/2016.
 */

public interface ApiService {

    //http://websitename/foldername/ws.webservice.php?op=language_list
    @GET("ws.webservice.php")
    Call<LanguageResponce> getLanguageList(
            @Query("op") String op
            //@Query("uid") String uid

    );




    @GET("ws.webservice.php")
    Call<RegisterResponse> setLoginUser(
            @Query("op") String op,
            @Query("email") String email,
            @Query("pwd") String pass,
            @Query("app_mode") String app_mode,
            @Query("platform") String platform,
            @Query("android_token") String android_token,
            @Query("device_id") String device_id
    );


    @POST("ws.webservice.php")
    Call<RegisterResponse> setRegisterNewUser(
            @Query("op") String op,
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,
            @Query("pwd") String pass,
            @Query("device_id") String device_id,
            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("android_token") String android_token,
            @Query("isEmail") String isEmail,
            @Query("fbid") String fbid,
            @Query("gid") String gid,
            @Query("twid") String tid
    );

    //avatar
    @Multipart
    @POST("ws.webservice.php")
    Call<RegisterResponse> setRegisterNewUser(
            @Query("op") String op,
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,
            @Query("pwd") String pass,
            @Query("device_id") String device_id,
            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("android_token") String android_token,
            @Query("isEmail") String isEmail,
            @Query("fbid") String fbid,
            @Query("gid") String gid,
            @Query("twid") String tid,
            @Part MultipartBody.Part filePhoto
    );
    //avatar


    @GET("ws.webservice.php")
    Call<RegisterResponse> setCheckSocialUser(
            @Query("op") String op,
            @Query("fbid") String fbid,
            @Query("gid") String gid,
            @Query("twid") String tid,
            @Query("email") String email,
            @Query("plaform") String platform,

            @Query("app_mode") String app_mode,
            @Query("device_id") String device_id,
            @Query("android_token") String android_token
    );


//    http://websitename/foldername/ws.webservice.php?op=Change_pwd&uid=93&curnt_pwd=111111&npwd=222222

    @GET("ws.webservice.php")
    Call<ChangePasswordResponse> setChangePassword(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("curnt_pwd") String curnt_pwd,
            @Query("npwd") String npwd,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("accessToken") String accessToken

    );

    // http://websitename.com/foldername/ws.webservice.php?op=Forgot_pwd&email=vishal@eliteinfoworld.com
    @GET("ws.webservice.php")
    Call<CommonResponse> getForgotPassword(
            @Query("op") String op,
            @Query("email") String email

    );

    @GET("ws.webservice.php")
    Call<TestExamResponce> getTestExam(
            @Query("op") String op,
            @Query("email") String email

    );



    @GET("ws.webservice.php")
    Call<CommonResponse> updateDeviceInfo(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("device_id") String android_token,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("accessToken") String accessToken

    );


    //http://websitename.com/foldername/ws.webservice.php?op=ViewProfile&uid=46&fid=46 or 47  (46 my profile   47 other user profile)
    @GET("ws.webservice.php")
    Call<ViewProfileResponse> getMyProfile(
            @Query("op") String op,
            @Query("uid") String uid,


            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("user_type") String user_type,
            @Query("accessToken") String accessToken
    );


    @POST("ws.webservice.php")
    Call<ViewProfileResponse> setUpdateUserProfile(
            @Query("op") String op,
            @Query("uid") String uid,

            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,

            @Query("age") String age,
            @Query("weight") String weight,
            @Query("height") String height,

            @Query("add") String add, // Address


            @Query("ctid") String cityid,
            @Query("sid") String stateid,
            @Query("ctrid") String countryid,

            @Query("zipcode") String zipcode,
            @Query("phone") String phone,
            @Query("about") String about,
            @Query("college") String college,
            @Query("company") String company,
            @Query("url") String url,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("device_id") String device_id,
            @Query("android_token") String android_token,
            @Query("accessToken") String accessToken
    );


    //avatar
    @Multipart
    @POST("ws.webservice.php")
    Call<ViewProfileResponse> setUpdateUserProfile(
            @Query("op") String op,
            @Query("uid") String uid,

            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,

            @Query("age") String age,
            @Query("weight") String weight,
            @Query("height") String height,

            @Query("add") String add, // Address


            @Query("ctid") String cityid,
            @Query("sid") String stateid,
            @Query("ctrid") String countryid,

            @Query("zipcode") String zipcode,
            @Query("phone") String phone,
            @Query("about") String about,
            @Query("college") String college,
            @Query("company") String company,
            @Query("url") String url,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("device_id") String device_id,
            @Query("android_token") String android_token,
            @Query("accessToken") String accessToken,

            @Part MultipartBody.Part filePhoto
    );
    //avatar








    // http://websitename.com/web-service.php?op=addRoute&uid=&json_array=
    // &st_pt_lat=&st_pt_lon=&ed_pt_lat=&ed_pt_lon=&time=&length=&locality=&st_pic=&ed_pic

    @Multipart
    @POST("ws.webservice.php")
    Call<CommonResponse> addRoute(
            @Query("op") String op,
            @Query("uid") String uid,

            @Query("st_pt_lat") String st_pt_lat,
            @Query("st_pt_lon") String st_pt_lon,
            @Query("ed_pt_lat") String ed_pt_lat,
            @Query("ed_pt_lon") String ed_pt_lon,
            @Query("time") String time,
            @Query("length") String length,
            @Query("locality") String locality,
            //@Field("json_array") String json_array,
            @Part("json_array") RequestBody json_array,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("device_id") String device_id,
            @Query("android_token") String android_token,
            @Query("accessToken") String accessToken,

            @Part MultipartBody.Part filePhotoStart,
            @Part MultipartBody.Part filePhotoEnd
    );








    //http://websitename.com/foldername/ws.webservice.php?op=GetCountry
    @GET("ws.webservice.php")
    Call<CountryListResponse> getCountryList(
            @Query("op") String op,
            @Query("uid") String uid
    );



    //http://websitename.com/foldername/ws.webservice.php?op=GetState&ctrid=101
    @GET("ws.webservice.php")
    Call<StateListResponse> getStateList(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("ctrid") String ctrid
    );

    //http://websitename.com/foldername/ws.webservice.php?op=GetCountry
    @GET("ws.webservice.php")
    Call<CityListResponse> getCityList(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("ctrid") String ctrid,
            @Query("sid") String sid
    );




    //http://websitename/foldername/ws.webservice.php?op=noti_list&uid=116&user_type=0
    @GET("ws.webservice.php")
    Call<NotificationListResponse> getNotificationList(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("page") String page,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("user_type") String user_type,
            @Query("accessToken") String accessToken
    );


    //http://websitename/foldername/ws.webservice.php?op=language_list
    @GET("ws.webservice.php")
    Call<WebviewContentResponse> getPageContent(
            @Query("op") String op,
            @Query("uid") String uid

    );



    // http://websitename/foldername/ws.webservice.php?op=noti_read&noti_id=1
    @GET("ws.webservice.php")
    Call<CommonResponse> setReadDeleteNotification(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("noti_id") String noti_id,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("accessToken") String accessToken

    );


    // http://websitename/foldername/ws.webservice.php?op=noti_read&noti_id=1
    @GET("ws.webservice.php")
    Call<CommonResponse> challangeStartRoute(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("routeid") String rid,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("accessToken") String accessToken

    );


    // http://websitename/foldername/ws.webservice.php?op=noti_read&noti_id=1
    //type -1 win  2 loss
    @GET("ws.webservice.php")
    Call<CommonResponse> challangeResult(
            @Query("op") String op,
            @Query("uid") String uid,
            @Query("rid") String rid,
            @Query("time") String time,
            @Query("type") String type,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("accessToken") String accessToken

    );

    //http://websitename/foldername/ws.webservice.php?op=Logout&uid=76
    @GET("ws.webservice.php")
    Call<CommonResponse> setUserLogout(
            @Query("op") String op,
            @Query("uid") String uid,

            @Query("platform") String platform,
            @Query("app_mode") String app_mode,
            @Query("user_type") String user_type,
            @Query("accessToken") String accessToken
    );




    @Headers({
            "aaaa-API-HEADER-KEY: sssssaaaaa"
    })
    @POST("ws-wwwwwwsssssss.php")
    Call<TestResponse> GetAllHomeScreenData(@Query("op") String common);


   // op = Helthtips_NewsEvent
}