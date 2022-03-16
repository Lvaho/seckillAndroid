package zjc.seckill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjc.seckill.R;
import zjc.seckill.service.imp.UserServiceImpl;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_identity)
    EditText registerIdentity;
    @BindView(R.id.register_mobile)
    EditText registerMobile;
    @BindView(R.id.register_nickname)
    EditText registerNickname;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_ll)
    LinearLayout registerLl;
    @BindView(R.id.register_button)
    Button registerButton;

    //服务代理接口
    private UserServiceImpl userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    //打印网络访问信息
    public void printResponseMessage(String responseMessage) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, responseMessage, Toast.LENGTH_LONG).show();
            }
        });

    }

    //网络访问成功之后回调
    public void registerCallback(){
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.register_button)
    public void onClick() {
        //构造服务代理类
        userService=new UserServiceImpl(RegisterActivity.this);
        //异步网络访问
        userService.doRegister(registerIdentity.getText().toString(),registerMobile.getText().toString(),registerNickname.getText().toString(),registerPassword.getText().toString());
    }
}