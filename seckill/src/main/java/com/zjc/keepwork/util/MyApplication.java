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
    private static String obj="";    //登录用户的编号


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }

    public static String getObj() {
        return obj;
    }

    public static void setObj(String obj) {
        MyApplication.obj = obj;
        editor.putString("obj",obj);
        editor.apply();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        pref= PreferenceManager.getDefaultSharedPreferences(context);
        editor=pref.edit();
        editor.apply();
        obj=pref.getString("obj","");
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

}
