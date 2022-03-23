package com.zjc.keepwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjc.keepwork.R;
import com.zjc.keepwork.pojo.DepositVo;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.IDepositService;
import com.zjc.keepwork.service.IUserService;
import com.zjc.keepwork.service.imp.DepositServiceImpl;
import com.zjc.keepwork.service.imp.UserServiceImpl;
import com.zjc.keepwork.util.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DepositActivity extends AppCompatActivity {


    @BindView(R.id.deposit_back)
    ImageView user_deposit_back;
    @BindView(R.id.deposit)
    TextView deposit;
    @BindView(R.id.recharge_button)
    Button recharge_button;
    IDepositService depositService;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        unbinder= ButterKnife.bind(this);
        initDeposit();
    }

    private void initDeposit() {
       depositService = new DepositServiceImpl(DepositActivity.this);
       depositService.findDepositByCookie();
    }

    @OnClick({R.id.deposit_back,R.id.recharge_button})
    public void viewOnclick(View view){
        switch (view.getId()){
            case R.id.deposit_back:
                finish();
                break;
            case R.id.recharge_button:
                Intent intent = new Intent(DepositActivity.this, DepositRechargeActivity.class);
                startActivity(intent);
        }
    }
    //回调函数loginCallback
    public void depositCallback(DepositVo depositVo) {
        deposit.setText("￥"+depositVo.getDeposit().toString());
    }

}