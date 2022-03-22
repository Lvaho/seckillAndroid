package com.zjc.keepwork.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MyApplication extends Application {

    private static SharedPreferences pref;  //共享偏好对象
    private static SharedPreferences.Editor editor;    //共享偏好编辑器对象


    private static Context context;   //上下文环境变量，用于多个活动之间的变量共享
    public static Context getContext() {
        return context;
    }
    public static void setContext(Context context) {
        MyApplication.context = context;
    }
    //cookie
    private static String cookie="";    //用户登录之后的cookie
    public static String getCookie() {
        return cookie;
    }
    public static void setCookie(String obj) {
        MyApplication.cookie = obj;
        editor.putString("cookie",obj);
        editor.apply();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        pref= PreferenceManager.getDefaultSharedPreferences(context);
        editor=pref.edit();
        editor.apply();
        cookie=pref.getString("cookie","");
    }

    //用于在非UI线程类okhttp中显示Toast信息
    public static void subThreadToast(String message){
        Looper.prepare();
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    public static void clearPref(){
        editor.remove("obj");
        editor.apply();
    }

    //定位相关
    private static String lat="0";
    private static String lot="0";
    private static String city="绍兴市";
    public static String getCity() {
        return city;
    }
    public static void setCity(String city) {
        MyApplication.city = city;
    }
    public static String getLat() {
        return lat;
    }
    public static void setLat(String lat) {
        MyApplication.lat = lat;
    }
    public static String getLot() {
        return lot;
    }
    public static void setLot(String lot) {
        MyApplication.lot = lot;
    }
    //个人界面相关
    private static String isPush="true";
    private static String isMes="true";
    public static String getIsPush() {
        return isPush;
    }
    public static void setIsPush(String isPush) {
        MyApplication.isPush = isPush;
    }
    public static String getIsMes() {
        return isMes;
    }
    public static void setIsMes(String isMes) {
        MyApplication.isMes = isMes;
    }
    //用户名
    private static String user_name="";
    public static String getUser_name() {
        return user_name;
    }
    //登录用户编号
    private static String user_id="";
    public static void setUser_name(String user_name) {
        MyApplication.user_name = user_name;
        editor.putString("user_name",user_name);
        editor.apply();
    }
    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        MyApplication.user_id = user_id;
        editor.putString("user_id",user_id);
        editor.apply();
    }
}
