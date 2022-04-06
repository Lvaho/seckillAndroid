package com.zjc.keepwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjc.keepwork.R;
import com.zjc.keepwork.pojo.User;
import com.zjc.keepwork.service.IUserService;
import com.zjc.keepwork.service.imp.UserServiceImpl;
import com.zjc.keepwork.util.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailActivity extends AppCompatActivity {
    private IUserService userService;
    @BindView(R.id.user_detail_back)
    LinearLayout user_detail_back;
    @BindView(R.id.user_detail_name)
    TextView user_detail_name;
    @BindView(R.id.user_deatil_identity)
    TextView user_detail_identity;
    @BindView(R.id.user_deatil_phone)
    TextView user_detail_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);

        initUser();
    }

    private void initUser() {
        userService=new UserServiceImpl(this);
        userService.getUserDetail();
    }

    @OnClick({R.id.user_detail_back})
    public void viewOnclick(View view){
        switch (view.getId()){
            case R.id.user_detail_back:
                finish();
                break;
        }
    }


    public void userDetailcallback(User user) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyApplication.setUser_name(user.getNickname());
                user_detail_name.setText(user.getNickname());
                user_detail_identity.setText(user.getIdentity());
                user_detail_phone.setText(user.getId());
            }
        });

    }
}