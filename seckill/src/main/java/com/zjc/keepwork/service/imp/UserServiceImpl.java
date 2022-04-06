package com.zjc.keepwork.service.imp;

import android.util.Log;

import com.zjc.keepwork.activity.DepositActivity;
import com.zjc.keepwork.activity.LoginActivity;
import com.zjc.keepwork.activity.RegisterActivity;
import com.zjc.keepwork.activity.UserDetailActivity;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.pojo.DepositVo;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.pojo.User;
import com.zjc.keepwork.service.IUserService;
import com.zjc.keepwork.util.MyApplication;
import com.zjc.keepwork.util.MyHttpUtil;
import com.zjc.keepwork.util.ResponseUtil;
import com.zjc.keepwork.util.SM3Util;
import com.zjc.keepwork.util.UrlUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UserServiceImpl implements IUserService {

    private RegisterActivity registerActivity;
    private LoginActivity loginActivity;
    private DepositActivity depositActivity;
    private UserDetailActivity userDetailActivity;
    public UserServiceImpl(LoginActivity loginActivity){this.loginActivity=loginActivity;}
    public UserServiceImpl(RegisterActivity registerActivity){this.registerActivity=registerActivity; }
    public UserServiceImpl(DepositActivity depositActivity){this.depositActivity=depositActivity; }
    public UserServiceImpl(UserDetailActivity userDetailActivity){this.userDetailActivity=userDetailActivity;}
    @Override
    public void doLogin(String mobile, String password) {
        password = SM3Util.inputPassToFormPass(password);
        //登陆地址
        String loginUrl = UrlUtil.LOGIN_URL;
        //POST请求主体
        RequestBody body = new FormBody.Builder()
                .add("mobile",mobile)
                .add("password",password)
                .build();
        //发送POST请求
        MyHttpUtil.sendOkhttpPostRequest(loginUrl, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc","网络请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                Log.i("zjc",respBean.getCode().toString()+respBean.getMessage());
                if(respBean.getCode()==200){
                    loginActivity.loginCallback(respBean);
                }else {
                }
                loginActivity.printResponseMessage(respBean.getMessage());
            }
        });
    }

    @Override
    public void doRegister(String identity, String mobile, String nickname, String password) {
        password= SM3Util.inputPassToFormPass(password);
        String registerURL= UrlUtil.REGISTER_URL;
        RequestBody body = new FormBody.Builder()
                .add("mobile",mobile)
                .add("password",password)
                .add("nickname",nickname)
                .add("identity",identity)
                .build();
        MyHttpUtil.sendOkhttpPostRequest(registerURL, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc","网络请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                if (respBean.getCode()==200){
                    registerActivity.registerCallback();
                }
                registerActivity.printResponseMessage(respBean.getMessage());
            }
        });
    }

    @Override
    public void getUserDetail() {

            OkHttpClient okHttpClient=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(UrlUtil.GET_USER_DETAIL_URL)
                    .addHeader("Cookie","userTicket="+ MyApplication.getCookie())
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("zjc","请求失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                    if(respBean.getCode()==200){
                        User user = ResponseUtil.dealresponse(respBean.getObj().toString(),User.class);
                        userDetailActivity.userDetailcallback(user);
                    }
                }
            });

    }


}