package com.zjc.keepwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zjc.keepwork.util.MyApplication;



public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //利用MyApplication获取已登录用户编号user_id
        final String obj= MyApplication.getObj();

        //开启一个独立的线程Handler，延时2秒钟（2000毫秒）
        Handler handler=new Handler();

        //使用PostDelayed方法，可以在两秒后调用Runnable对象
        //开启线程，运行run方法，根据userid的值跳转到不同的活动
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        if (TextUtils.isEmpty(obj)){
                            intent=new Intent(LaunchActivity.this,LoginActivity.class);
                            Toast.makeText(LaunchActivity.this,"请先登录",Toast.LENGTH_LONG).show();
                        }else{
                            intent=new Intent(LaunchActivity.this,MainActivity.class);
                            Toast.makeText(LaunchActivity.this,"已经登录",Toast.LENGTH_LONG).show();
                        }
                        //利用intent跳转到下一个活动
                        startActivity(intent);
                        //关闭当前活动WelcomeActivity
                        finish();
                    }
                },
                2000
        );
    }
}