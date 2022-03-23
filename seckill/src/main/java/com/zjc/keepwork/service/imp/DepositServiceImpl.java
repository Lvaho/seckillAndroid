package com.zjc.keepwork.service.imp;

import android.util.Log;
import android.widget.Toast;

import com.zjc.keepwork.activity.DepositActivity;
import com.zjc.keepwork.activity.DepositRechargeActivity;
import com.zjc.keepwork.pojo.DepositVo;
import com.zjc.keepwork.pojo.OrderInfo;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.IDepositService;
import com.zjc.keepwork.util.MyApplication;
import com.zjc.keepwork.util.ResponseUtil;
import com.zjc.keepwork.util.UrlUtil;

import java.io.IOException;
import java.math.BigDecimal;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DepositServiceImpl implements IDepositService {
    private DepositActivity depositActivity;
    public DepositServiceImpl(DepositActivity depositActivity){this.depositActivity=depositActivity; }
    private DepositRechargeActivity depositRechargeActivity;
    public DepositServiceImpl(DepositRechargeActivity depositRechargeActivity){
        this.depositRechargeActivity=depositRechargeActivity;
    }
    @Override
    public void findDepositByCookie() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(UrlUtil.GET_DEPOSIT_URL)
                .addHeader("Cookie"," userTicket="+ MyApplication.getCookie())
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc","请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("zjc","请求成功");
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                if (respBean.getCode()==200){
                    DepositVo depositVo = (DepositVo) ResponseUtil.dealresponse(respBean.getObj().toString(), DepositVo.class);
                    depositActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            depositActivity.depositCallback(depositVo);
                        }
                    });
                }else {
                    Log.i("zjc","出现问题");
                }
            }
        });

    }

    @Override
    public void createOrderAndPay(BigDecimal chargenum) {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("chargenum",chargenum.toString())
                .build();
        Request request=new Request.Builder()
                .url(UrlUtil.RECHARGE_DEPOSIT_URL)
                .addHeader("Cookie","userTicket="+MyApplication.getCookie())
                .post(requestBody)//余额添加
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc",UrlUtil.FAIL);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("zjc","请求成功");
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                if (respBean.getCode()==200){
                    OrderInfo orderInfo = (OrderInfo) ResponseUtil.dealresponse(respBean.getObj().toString(),OrderInfo.class);
                    String orderInfoBody = orderInfo.getBody();
                    depositRechargeActivity.depositRechargeCallback(orderInfoBody);
                }else {
                Log.i("zjc","出错了");
                }
            }
        });
    }
}
