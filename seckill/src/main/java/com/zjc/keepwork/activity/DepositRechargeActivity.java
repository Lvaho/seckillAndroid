package com.zjc.keepwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.zjc.keepwork.R;
import com.zjc.keepwork.pojo.DepositVo;
import com.zjc.keepwork.pojo.PayResult;
import com.zjc.keepwork.service.IDepositService;
import com.zjc.keepwork.service.imp.DepositServiceImpl;

import java.math.BigDecimal;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DepositRechargeActivity extends AppCompatActivity {


    @BindView(R.id.CallAliPay_Button)
    Button callAliPay_Button;
    @BindView(R.id.recharge_edittext)
    EditText recharge_edittext;
    @BindView(R.id.deposit_recharge_back)
    ImageView deposit_recharge_back;
    Unbinder unbinder;
    IDepositService depositService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        setContentView(R.layout.activity_deposit_recharge);
        unbinder= ButterKnife.bind(this);
    }
    @OnClick({R.id.CallAliPay_Button,R.id.deposit_recharge_back})
    public void viewOnclick(View view){
        switch (view.getId()){
            case R.id.CallAliPay_Button:
                getTotalAndPay(new BigDecimal(recharge_edittext.getText().toString()));
                break;
            case R.id.deposit_recharge_back:
                finish();
                break;
        }
    }

    private void getTotalAndPay(BigDecimal chargenum) {
        depositService = new DepositServiceImpl(DepositRechargeActivity.this);
        depositService.createOrderAndPay(chargenum);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
             */
            String resultInfo = payResult.getResult();// ?????????????????????????????????
            String resultStatus = payResult.getResultStatus();
            // ??????resultStatus ???9000?????????????????????
            if (TextUtils.equals(resultStatus, "9000")) {
                // ??????????????????????????????????????????????????????????????????????????????
                Toast.makeText(DepositRechargeActivity.this, "????????????", Toast.LENGTH_SHORT).show();
            } else {
                // ???????????????????????????????????????????????????????????????????????????
                Toast.makeText(DepositRechargeActivity.this, "????????????????????????????????????", Toast.LENGTH_SHORT).show();
            }
        };
    };

    public void depositRechargeCallback(String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DepositRechargeActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}