package com.zjc.keepwork.service.imp;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.activity.OrderDetailActivity;
import com.zjc.keepwork.pojo.GoodsDetailVo;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.IOrderService;
import com.zjc.keepwork.util.MyApplication;
import com.zjc.keepwork.util.ResponseUtil;
import com.zjc.keepwork.util.UrlUtil;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderServiceImpl implements IOrderService {
    private OrderDetailActivity orderDetailActivity;
    public OrderServiceImpl(OrderDetailActivity orderDetailActivity){
        this.orderDetailActivity=orderDetailActivity;
    }
    @Override
    public void payOrder(String orderid) {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody =new FormBody.Builder()
                .add("orderId",orderid)
                .build();
        Request request=new Request.Builder()
                .url(UrlUtil.PAY_ORDER_URL)
                .addHeader("Cookie","userTicket="+ MyApplication.getCookie())
                .post(requestBody)
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
                ToastUtils.toast(respBean.getMessage());
            }
        });
    }

    @Override
    public void getOrderByOrderId(String orderid) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(UrlUtil.GET_ORDER_DETAIL_URL(orderid))
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
                ToastUtils.toast(respBean.getObj().toString());
                if (respBean.getCode()==200){
                    JSONObject jsonObject = JSONObject.parseObject(respBean.getObj().toString());
                    JSONObject goodsVo = (JSONObject)jsonObject.get("goodsVo");
                    JSONObject order = (JSONObject)jsonObject.get("order");
                    GoodsDetailVo goodsDetailVo =new GoodsDetailVo();
                    goodsDetailVo.setGoodsName(goodsVo.getString("goodsName"));
                    goodsDetailVo.setCreatetime(order.getDate("createDate"));
                    goodsDetailVo.setGoodsStatus(order.getString("status"));
                    goodsDetailVo.setGoodsPrice(goodsVo.getString("seckillPrice"));
                    orderDetailActivity.OrderCallBack(goodsDetailVo);
                }

            }
        });
    }
}
