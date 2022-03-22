package com.zjc.keepwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zjc.keepwork.R;
import com.zjc.keepwork.service.IUserService;
import com.zjc.keepwork.service.imp.UserServiceImpl;
import com.zjc.keepwork.util.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailActivity extends AppCompatActivity {
    @BindView(R.id.user_detail_back)
    LinearLayout user_detail_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.user_detail_back})
    public void viewOnclick(View view){
        switch (view.getId()){
            case R.id.user_detail_back:
                finish();
                break;
        }
    }


}