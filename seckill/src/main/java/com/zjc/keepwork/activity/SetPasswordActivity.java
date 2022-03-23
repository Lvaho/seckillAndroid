package com.zjc.keepwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.setpassword_ll)
    LinearLayout setpassword_ll;
    @BindView(R.id.setpassword_bt)
    Button setpassword_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.setpassword_ll,R.id.setpassword_bt})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.setpassword_ll:
                finish();
                break;
            case R.id.setpassword_bt:
                ToastUtils.toast("修改成功！");
                finish();
                break;
        }
    }
}