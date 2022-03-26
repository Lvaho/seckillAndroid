package com.zjc.keepwork.service.imp;

import android.util.Log;

import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.activity.SeckillDetailActivity;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.ISeckillService;
import com.zjc.keepwork.util.MyApplication;
import com.zjc.keepwork.util.ResponseUtil;
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

public class SeckillServiceImpl implements ISeckillService {

    private SeckillDetailActivity seckillDetailActivity;
    public SeckillServiceImpl(SeckillDetailActivity seckillDetailActivity){
        this.seckillDetailActivity=seckillDetailActivity;
    }

    @Override
    public void getSeckillPath(String goodsid) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(UrlUtil.GET_SECKILL_PATH(goodsid))
                .addHeader("Cookie","userTicket="+ MyApplication.getCookie())
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("zjc","请求成功");
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(), RespBean.class);
                String path = (String) respBean.getObj();
                doSeckill(path,goodsid);
            }
        });
    }

    private void doSeckill(String path,String goodsid){
        OkHttpClient okHttpClient= new OkHttpClient();
        RequestBody requestBody =new FormBody.Builder()
                .add("goodsId",goodsid)
                .build();
        Request request=new Request.Builder()
                .url(UrlUtil.doSeckill(path))
                .addHeader("Cookie","userTicket="+ MyApplication.getCookie())
                .post(requestBody)
                .build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("zjc","请求成功");
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(), RespBean.class);
                ToastUtils.toast(respBean.getMessage()+respBean.getCode());
            }
        });

    }
}
