package com.zjc.keepwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zjc.keepwork.R;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.IUserService;
import com.zjc.keepwork.service.imp.UserServiceImpl;
import com.zjc.keepwork.util.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_mobile)
    EditText loginMobile;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.register_button)
    Button registerButton;
    //代理接口
    private com.zjc.keepwork.service.IUserService IUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    //回调函数loginCallback
    public void loginCallback(RespBean respBean) {
        MyApplication.setObj(respBean.getObj());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        // 关闭当前 LoginActivity 活动
        finish();
    }

    public void printResponseMessage(String responseMessage) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, responseMessage, Toast.LENGTH_LONG).show();
            }
        });

    }

    @OnClick(R.id.login_button)
    public void onClickForLogin() {
        IUserService = new UserServiceImpl(LoginActivity.this);
        //调用UserService登录接口来实现业务
        IUserService.doLogin(loginMobile.getText().toString(), loginPassword.getText().toString());
        //Toast.makeText(LoginActivity.this,loginUser.getUserName(),Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.register_button)
    public void onClickForRegister() {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}