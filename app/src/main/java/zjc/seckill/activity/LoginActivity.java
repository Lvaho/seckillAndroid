package zjc.seckill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjc.seckill.R;
import zjc.seckill.pojo.ResponseBody;
import zjc.seckill.service.LoginService;
import zjc.seckill.service.imp.LoginServiceImp;
import zjc.seckill.util.MyApplication;

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
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    //回调函数loginCallback
    public void loginCallback(ResponseBody responseBody) {
        MyApplication.setObj(responseBody.getResponseObj());
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
        //构造代理接口
        loginService = new LoginServiceImp(LoginActivity.this);
        //异步网络访问，登录验证
        loginService.doLogin(loginMobile.getText().toString(), loginPassword.getText().toString());
        //Toast.makeText(LoginActivity.this,loginUser.getUserName(),Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.register_button)
    public void onClickForRegister() {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}